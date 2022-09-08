package com.cpp.jwt.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * JWT 属性配置
 */
@Data
//@ConfigurationProperties(prefix = "jwtp")
@Component
public class JwtProperties {

    /**
     * 是否开启JWT，即注入相关的类对象
     */
    private Boolean enabled = true;
    /**
     * JWT 密钥
     */
    private String secret = "jsdfjs";
    /**
     * accessToken 有效时间
     */
    private Long expiration = Long.valueOf(3600000);
    /**
     * 前端向后端传递JWT时使用HTTP的header名称，前后端要统一
     */
    private String header = "Authorization";
    /**
     * 用户登录-用户名参数名称
     */
    private String userParamName = "userId";
    /**
     * 用户登录-密码参数名称
     */
    private String pwdParamName = "password";
    /**
     * 是否使用默认的JWTAuthController
     */
    private Boolean useDefaultController = true;

    private String skipValidUrl = "/page/login";

}
