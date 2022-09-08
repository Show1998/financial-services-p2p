package com.cpp.auth.controller;

import com.cpp.auth.jpa.UserEntity;
import com.cpp.auth.jpa.UserEntityRepository;
import com.cpp.jwt.common.ResponseCodeEnum;
import com.cpp.jwt.common.ResponseResult;
import com.cpp.jwt.config.JwtProperties;
import com.cpp.jwt.utils.JwtTokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
public class JwtAuthController {

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtProperties jwtProperties;



    @PostMapping ("/auth/checkAndLogin")
    public String loginAndGenerateToke(HttpServletResponse response, String loginPassword, @RequestParam("id") String phone){
        //根据id去查找用户
        UserEntity user = userEntityRepository.findUserByPhone(phone);

        if (user != null){
            //用户是否实名认证
            String username = user.getName()==null?"投资者": user.getName();
            //判断前端的密码和数据库中的密码是否一致
            boolean isAuthenticated = StringUtils.equals(loginPassword, user.getLoginPassword());
            //如果一致，生成token，并设置cookie
            if(isAuthenticated){
                Map<String, Object> tokenmap = jwtTokenUtil.generateTokenAndRefreshToken(phone,username);
                Cookie cookie = new Cookie(jwtProperties.getHeader(),(String)tokenmap.get("access_token"));
                cookie.setMaxAge(2*60*60);
                cookie.setPath("/");
                response.addCookie(cookie);
                return "1";
            }
        }
        return "0";
    }

    /**
     * 用已过期的token刷新令牌
     * @param token
     * @return
     */
    @RequestMapping("/auth/refreshToken")
    public Mono<ResponseResult> refreshToken(String token){

        String refreshToken = jwtTokenUtil.selectRefreshToken(token);
        if (refreshToken == null){
            return buildErrorResponse(ResponseCodeEnum.REFRESH_TOKEN_INVALID);
        }
        if(jwtTokenUtil.isTokenExpired(refreshToken)){
            return buildErrorResponse(ResponseCodeEnum.TOKEN_INVALID);
        }
        //判断此账号是否已经登陆过
        if(jwtTokenUtil.isTokenNotExistCache(token)){
//            System.out.println("账号在别的地方登陆或已经登出！");
            return buildErrorResponse(ResponseCodeEnum.ALREADY_LOGIN_OR_LOGOUT);
        }
        String userId = jwtTokenUtil.getUserIdFromToken(token);
        String username = jwtTokenUtil.getUserNameFromToken(token);
        if (StringUtils.isEmpty(userId)){
            return buildErrorResponse(ResponseCodeEnum.TOKEN_CHECK_INFO_FAILED);
        }
        //用刷新令牌刷新token
        Map<String, Object> newTokenMap = jwtTokenUtil.refreshTokenAndGenerateToken(userId, username);

        //TODO 重新设置cookie

        return buildSuccessResponse(newTokenMap);
    }


    @PostMapping("/auth/logout")
    public  Mono<ResponseResult> logout(@RequestParam("username") String username){


        //TODO 删除redis中的token
        boolean logoutResult = jwtTokenUtil.removeToken(username);
        if (logoutResult) {
            buildSuccessResponse(ResponseCodeEnum.SUCCESS);
        } else {
            buildErrorResponse(ResponseCodeEnum.LOGOUT_ERROR);
        }

        return buildSuccessResponse(ResponseCodeEnum.SUCCESS);
    }

    private Mono<ResponseResult> buildErrorResponse(ResponseCodeEnum responseCodeEnum){
        return Mono.create(callback -> callback.success(
                ResponseResult.error(responseCodeEnum.getCode(), responseCodeEnum.getMessage())
        ));
    }

    private Mono<ResponseResult> buildSuccessResponse(Object data){
        return Mono.create(callback -> callback.success(ResponseResult.success(data)
        ));
    }
}