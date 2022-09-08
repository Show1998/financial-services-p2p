package com.cpp.loginandregister.service.impl;



import com.cpp.loginandregister.mapper.UserMapper;
import com.cpp.loginandregister.pojo.User;
import com.cpp.loginandregister.service.UserService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Resource
    RedisTemplate<Object,Object> redisTemplate;

//    @Override
//    public int queryAmountOfUser() {
//        Integer amountOfUser = (Integer) redisTemplate.opsForValue().get(Constants.AMOUNT_OF_USER);
//        if (!ObjectUtils.allNotNull(amountOfUser)){
//            synchronized (this){
//                amountOfUser = (Integer) redisTemplate.opsForValue().get(Constants.AMOUNT_OF_USER);
//                if (!ObjectUtils.allNotNull(amountOfUser)){
//                   amountOfUser =  userMapper.selectAmountOfUser();
//                   redisTemplate.opsForValue().set(Constants.AMOUNT_OF_USER, amountOfUser,60, TimeUnit.SECONDS);
//                }
//            }
//        }
//        return amountOfUser;
//    }

    @Override
    public User queryPhoneIsRegister(String phone) {
        User i = userMapper.checkPhoneNumber(phone);
        return i;
    }

//    @Override
//    public User insertUser(String phone,String loginPassword) {
//        User u = new User();
//        u.setPhone(phone);
//        u.setLoginPassword(loginPassword);
//        u.setLastLoginTime(new Date());
//        u.setAddTime(new Date());
//        int flag = userMapper.insertSelective(u);
//        if (flag == 0){
//            throw new RuntimeException("注册失败!");
//        }
//        Account account = new Account();
//        account.setUid(u.getId());
//        account.setAvailableMoney(880.0);
//        int flag2 = accountMapper.insertSelective(account);
//        if (flag2 == 0){
//            throw new RuntimeException("新增账户失败!");
//        }
//        return u;
//    }
//
//    @Override
//    public User queryAndCheckUser(String id, String loginPassword) {
//        return userMapper.selectAndCheckUser(id, loginPassword);
//    }
}
