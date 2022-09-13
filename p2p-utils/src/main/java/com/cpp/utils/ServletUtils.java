package com.cpp.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Configuration
public class ServletUtils {

    public  String getToken(HttpServletRequest request){
        if (request.getCookies() == null){
            System.out.println("cookie为空");
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
