/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	demo
 * 文件名：	User.java
 * 模块说明：
 * 修改历史：
 * 2018/2/12 - zhuchao - 创建。
 */
package com.enroy.demo.service.user;

import com.enroy.demo.commons.biz.Entity;

/**
 * @author zhuchao
 */
public class User extends Entity {
  private static final long serialVersionUID = -7909829022290737991L;
  public static final String PART_LOGIN_USER = "loginUser";
  /** 属性 {@link #getUserId()}}的最大长度 */
  public static final int LENGTH_ID = 16;
  /** 属性 {@link #getNickName()}的最大长度 */
  public static final int LENGTH_NICK_NAME = 32;
  /** 属性 {@link #getLoginName()} 的最大长度 */
  public static final int LENGTH_LOGIN_NAME = 32;
  /** 属性 {@link #getPassword()} 的最大长度 */
  public static final int LENGTH_PASSWORD = 16;
  /** 属性 {@link #getTelephone()} 的最大长度 */
  public static final int LENGTH_TELEPHONE = 16;
  /** 属性 {@link #getAddress()} 的最大长度 */
  public static final int LENGTH_ADDRESS = 256;
  /** 属性 {@link #getEmail()} 的最大长度 */
  public static final int LENGTH_EMAIL = 32;
  /** 属性 {@link #getImageUrl()}的最大长度 */
  public static final int LENGTH_IMAGE_URL = 256;
  /** 属性 {@link #getRemark()}的最大长度 */
  public static final int LENGTH_IMAGE_REMARK = 256;
  private String userId;
  private String nickName;
  private String loginName;
  private String telephone;
  private String address;
  private String email;
  private String imageUrl;
  private String remark;

  /** 用户id */
  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  /** 昵称 */
  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  /** 登录名 */
  public String getLoginName() {
    return loginName;
  }

  public void setLoginName(String loginName) {
    this.loginName = loginName;
  }

  /** 电话 */
  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  /** 地址 */
  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  /** 邮箱 */
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  /** 头像 */
  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  /** 签名 */
  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }
}
