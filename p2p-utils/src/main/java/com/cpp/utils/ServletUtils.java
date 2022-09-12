package com.cpp.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Configuration
public class ServletUtils {

    public  String getToken(HttpServletRequest request){
        if (request == null){
            System.out.println("请求为空");
        }else {
            for(Cookie c :request.getCookies()){
                if (StringUtils.equals(c.getName(), "Authorization")){
                    return c.getValue();
                }
            }
        }
        return null;
    }
}
