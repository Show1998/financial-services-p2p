package com.cpp.loginandregister.pojo;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

    private static final long serialVersionUID = 8988317454216030117L;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column u_user.id
     *
     * @mbggenerated Mon Jun 27 00:09:40 CST 2022
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column u_user.phone
     *
     * @mbggenerated Mon Jun 27 00:09:40 CST 2022
     */
    private String phone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column u_user.login_password
     *
     * @mbggenerated Mon Jun 27 00:09:40 CST 2022
     */
    private String loginPassword;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column u_user.name
     *
     * @mbggenerated Mon Jun 27 00:09:40 CST 2022
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column u_user.id_card
     *
     * @mbggenerated Mon Jun 27 00:09:40 CST 2022
     */
    private String idCard;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column u_user.add_time
     *
     * @mbggenerated Mon Jun 27 00:09:40 CST 2022
     */
    private Date addTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column u_user.last_login_time
     *
     * @mbggenerated Mon Jun 27 00:09:40 CST 2022
     */
    private Date lastLoginTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column u_user.header_image
     *
     * @mbggenerated Mon Jun 27 00:09:40 CST 2022
     */
    private String headerImage;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column u_user.id
     *
     * @return the value of u_user.id
     *
     * @mbggenerated Mon Jun 27 00:09:40 CST 2022
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column u_user.id
     *
     * @param id the value for u_user.id
     *
     * @mbggenerated Mon Jun 27 00:09:40 CST 2022
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column u_user.phone
     *
     * @return the value of u_user.phone
     *
     * @mbggenerated Mon Jun 27 00:09:40 CST 2022
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column u_user.phone
     *
     * @param phone the value for u_user.phone
     *
     * @mbggenerated Mon Jun 27 00:09:40 CST 2022
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column u_user.login_password
     *
     * @return the value of u_user.login_password
     *
     * @mbggenerated Mon Jun 27 00:09:40 CST 2022
     */
    public String getLoginPassword() {
        return loginPassword;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column u_user.login_password
     *
     * @param loginPassword the value for u_user.login_password
     *
     * @mbggenerated Mon Jun 27 00:09:40 CST 2022
     */
    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword == null ? null : loginPassword.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column u_user.name
     *
     * @return the value of u_user.name
     *
     * @mbggenerated Mon Jun 27 00:09:40 CST 2022
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column u_user.name
     *
     * @param name the value for u_user.name
     *
     * @mbggenerated Mon Jun 27 00:09:40 CST 2022
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column u_user.id_card
     *
     * @return the value of u_user.id_card
     *
     * @mbggenerated Mon Jun 27 00:09:40 CST 2022
     */
    public String getIdCard() {
        return idCard;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column u_user.id_card
     *
     * @param idCard the value for u_user.id_card
     *
     * @mbggenerated Mon Jun 27 00:09:40 CST 2022
     */
    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column u_user.add_time
     *
     * @return the value of u_user.add_time
     *
     * @mbggenerated Mon Jun 27 00:09:40 CST 2022
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column u_user.add_time
     *
     * @param addTime the value for u_user.add_time
     *
     * @mbggenerated Mon Jun 27 00:09:40 CST 2022
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column u_user.last_login_time
     *
     * @return the value of u_user.last_login_time
     *
     * @mbggenerated Mon Jun 27 00:09:40 CST 2022
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column u_user.last_login_time
     *
     * @param lastLoginTime the value for u_user.last_login_time
     *
     * @mbggenerated Mon Jun 27 00:09:40 CST 2022
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column u_user.header_image
     *
     * @return the value of u_user.header_image
     *
     * @mbggenerated Mon Jun 27 00:09:40 CST 2022
     */
    public String getHeaderImage() {
        return headerImage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column u_user.header_image
     *
     * @param headerImage the value for u_user.header_image
     *
     * @mbggenerated Mon Jun 27 00:09:40 CST 2022
     */
    public void setHeaderImage(String headerImage) {
        this.headerImage = headerImage == null ? null : headerImage.trim();
    }
}
