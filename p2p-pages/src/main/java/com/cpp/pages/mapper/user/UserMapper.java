package com.cpp.pages.mapper.user;

import com.cpp.pages.pojo.User;

public interface UserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table u_user
     *
     * @mbggenerated Mon Jun 27 00:09:40 CST 2022
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table u_user
     *
     * @mbggenerated Mon Jun 27 00:09:40 CST 2022
     */
    int insert(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table u_user
     *
     * @mbggenerated Mon Jun 27 00:09:40 CST 2022
     */
    int insertSelective(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table u_user
     *
     * @mbggenerated Mon Jun 27 00:09:40 CST 2022
     */
    User selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table u_user
     *
     * @mbggenerated Mon Jun 27 00:09:40 CST 2022
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table u_user
     *
     * @mbggenerated Mon Jun 27 00:09:40 CST 2022
     */
    int updateByPrimaryKey(User record);

    //查询总的用户数量
    int selectAmountOfUser();

    //查询此手机号是否被注册
    User checkPhoneNumber(String phone);

    //验证账号密码
    User selectAndCheckUser(String id,String loginPassword);
}
