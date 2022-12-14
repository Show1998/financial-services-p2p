package com.cpp.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.cpp.jwt.common.ResponseCodeEnum;
import com.cpp.jwt.common.ResponseResult;
import com.cpp.jwt.config.JwtProperties;
import com.cpp.jwt.constants.TokenConstants;
import com.cpp.jwt.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static com.cpp.common.Constants.*;

@Configuration
@Slf4j
@Order(-101)
public class JwtCheckFilter implements GlobalFilter {

    private static final String PAGE_PATTERN= "/p2p/page/.*";
    private static final String LOGIN_PATTERN= "/p2p/auth/.*";
    private static final String INDEX= "/p2p/index";
    private static final String CSS_PATTERN="/p2p/css/.*";
    private static final String JS_PATTERN="/p2p/js/.*";
    private static final String IMAGES_PATTERN="/p2p/images/.*";
    private static final String IMG_PATTERN="/p2p/img/.*";



    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtProperties jwtProperties;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        ServerHttpResponse serverHttpResponse = exchange.getResponse();
        ServerHttpRequest.Builder mutate = serverHttpRequest.mutate();
        String requestUrl = serverHttpRequest.getURI().getPath();

        //????????????url??????-----------------/p2p/auth/checkAndLogin-----------------------
//        System.out.println("-----------------"+requestUrl+"-----------------------");

        //????????????????????????????????????????????????????????????????????????????????????
        //????????????????????????
        if (Pattern.matches(LOGIN_PATTERN, requestUrl) || Pattern.matches(PAGE_PATTERN, requestUrl)){
            return chain.filter(exchange);
        }
        if (Pattern.matches(CSS_PATTERN, requestUrl) || Pattern.matches(JS_PATTERN, requestUrl) || Pattern.matches(IMAGES_PATTERN, requestUrl) || Pattern.matches(IMG_PATTERN, requestUrl)){
            return chain.filter(exchange);
        }

        //??????????????????token
        String token = this.getToken(serverHttpRequest);

        if(StringUtils.isEmpty(token)){
            //????????????index????????????token??????????????????
            if (INDEX.equals(requestUrl)){
                return chain.filter(exchange);
            }
            return unauthorizedResponse(exchange, serverHttpResponse, ResponseCodeEnum.TOKEN_MISSION);
        }

        //??????token????????????
        if (jwtTokenUtil.isTokenExpired(token)){
            //token??????????????????refreshToken????????????
            //TODO ??????????????????????????????

            return null;


        }else {
            //????????????
            String userId = jwtTokenUtil.getUserIdFromToken(token);
            String username = jwtTokenUtil.getUserNameFromToken(token);
            if (StringUtils.isEmpty(userId)){
                return unauthorizedResponse(exchange, serverHttpResponse, ResponseCodeEnum.TOKEN_CHECK_INFO_FAILED);
            }
            //????????????????????????????????????
            if(jwtTokenUtil.isTokenNotExistCache(token)){

                // ??????cookie
                MultiValueMap<String, HttpCookie> cookies = serverHttpRequest.getCookies();
                for (Map.Entry<String, List<HttpCookie>> cookie : cookies.entrySet()) {
                    for (HttpCookie cookieToBeDeleted : cookie.getValue()) {
//                        log.debug("Deleting cookie: {} having value: {}", cookieToBeDeleted.getName(), cookieToBeDeleted.getValue());
//
                        if(StringUtils.equals(cookieToBeDeleted.getName(), jwtProperties.getHeader())){
                            serverHttpResponse.addCookie(ResponseCookie.from(cookieToBeDeleted.getName(), cookieToBeDeleted.getValue()) .maxAge(0).path("/").build());
                        }
                    }
                }

//            System.out.println("?????????????????????????????????????????????");
                return unauthorizedResponse(exchange, serverHttpResponse, ResponseCodeEnum.ALREADY_LOGIN_OR_LOGOUT);
            }

            //???????????????????????????
            this.addHeader(mutate, USER_ID,userId);
            this.addHeader(mutate, USER_NAME,username);
            //??????????????????????????????
            this.removeHeader(mutate, FROM_SOURCE);
            return chain.filter(exchange);
        }
    }


    private void addHeader(ServerHttpRequest.Builder mutate, String name, Object value) {
        if (value == null) {
            return;
        }
        String valueStr = value.toString();
        String valueEncode = urlEncode(valueStr);
        mutate.header(name, valueEncode);
    }

    private void removeHeader(ServerHttpRequest.Builder mutate, String name) {
        mutate.headers(httpHeaders -> httpHeaders.remove(name)).build();
    }

    /**
     * ????????????
     *
     * @param str ??????
     * @return ??????????????????
     */
    public static String urlEncode(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            return StringUtils.EMPTY;
        }
    }

    /**
     * ????????????token
     */
    private String getToken(ServerHttpRequest request) {
        if (request.getCookies().get(jwtProperties.getHeader()) == null){
            return null;
        }
        String token = request.getCookies().get(jwtProperties.getHeader()).get(0).getValue();
        // ??????????????????????????????????????????????????????
        if (StringUtils.isNotEmpty(token) && token.startsWith(TokenConstants.PREFIX))
        {
            token = token.replaceFirst(TokenConstants.PREFIX, StringUtils.EMPTY);
        }
        return token;
    }

    /**
     * ??? JWT ???????????????????????????????????????
     */
    private Mono<Void> unauthorizedResponse(ServerWebExchange exchange, org.springframework.http.server.reactive.ServerHttpResponse serverHttpResponse, ResponseCodeEnum responseCodeEnum) {
        log.error("[??????????????????]????????????:{}", exchange.getRequest().getPath());
        serverHttpResponse.setStatusCode(HttpStatus.UNAUTHORIZED);
        serverHttpResponse.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        ResponseResult responseResult = ResponseResult.error(responseCodeEnum.getCode(), responseCodeEnum.getMessage());
        DataBuffer dataBuffer = serverHttpResponse.bufferFactory()
                .wrap(JSON.toJSONStringWithDateFormat(responseResult, JSON.DEFFAULT_DATE_FORMAT)
                        .getBytes(StandardCharsets.UTF_8));
        return serverHttpResponse.writeWith(Flux.just(dataBuffer));
    }


}
