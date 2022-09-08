package com.cpp.pages.service;


import com.cpp.pages.pojo.User;

public interface UserService {
    int queryAmountOfUser();

    User queryPhoneIsRegister(String phone);

    User insertUser(String phone,String loginPassword);

    User queryAndCheckUser(String id,String loginPassword);
}
