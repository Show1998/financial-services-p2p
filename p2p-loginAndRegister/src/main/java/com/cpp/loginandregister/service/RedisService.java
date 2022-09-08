package com.cpp.loginandregister.service;

public interface RedisService {
    void put(String key,String value);

    String get(String key);
}
