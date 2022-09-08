package com.cpp.auth.jpa;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("UserEntityRepository")
public interface UserEntityRepository extends JpaRepository<UserEntity,Integer> {

    @Query("select u from UserEntity u where u.phone = :phone")
    UserEntity findUserByPhone(@Param("phone") String phone);
}
