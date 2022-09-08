package com.cpp.auth;

import com.cpp.auth.jpa.UserEntity;
import com.cpp.auth.jpa.UserEntityRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
public class Test {

    @Autowired
    UserEntityRepository userEntityRepository;

    @org.junit.Test
    public void testquery(){
        UserEntity userByPhone = userEntityRepository.findUserByPhone("139999999239");
        if (userByPhone==null){
            System.out.println("我是空");
        }else{
            System.out.println(userByPhone);
        }
//        Optional<UserEntity> userop = userEntityRepository.findById(12);
//        UserEntity user = userop.get();
//        System.out.println(user.getPhone());
        System.out.println("我执行了！！");
    }
}
