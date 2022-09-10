package com.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class ServletUtils {

    public static String getToken(HttpServletRequest request){
        for(Cookie c :request.getCookies()){
            if (StringUtils.equals(c.getName(), "Authorization")){
                return c.getValue();
            }
        }
        return null;
    }
}
