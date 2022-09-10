package com.cpp.usercenter.controller;

import com.cpp.common.Constants;
import com.cpp.jwt.utils.JwtTokenUtil;
import com.cpp.usercenter.pojo.User;
import com.utils.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserCenterController {


    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @RequestMapping("/user/myCenter")
    public String toCenter(HttpServletRequest request){

        //从token中获取用户的信息,并且存放在session中。
        String token = ServletUtils.getToken(request);
        if (token != null){
            String phone = jwtTokenUtil.getUserIdFromToken(token);
            String username = jwtTokenUtil.getUserNameFromToken(token);
            if(phone != null){
                User user = new User();
                user.setPhone(phone);
                user.setName(username);
                request.getSession().setAttribute(Constants.LOGIN_USER_INFO, user);
            }
        }else {
            request.getSession().removeAttribute(Constants.LOGIN_USER_INFO);
        }

        return "myCenter";
    }
}
