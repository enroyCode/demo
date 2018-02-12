/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	demo
 * 文件名：	PUser.java
 * 模块说明：
 * 修改历史：
 * 2018/2/12 - zhuchao - 创建。
 */
package com.enroy.demo.dao.login;

import com.enroy.demo.jpa.biz.PEntity;
import com.enroy.demo.service.user.User;
import org.hibernate.annotations.Index;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author zhuchao
 */
@Entity
@Table(name = "demo_user")
@org.hibernate.annotations.Table(appliesTo = "demo_user", indexes = {
        @Index(name = "idx_demo_user_1", columnNames = {
                "user_id"})})
public class PUser extends PEntity {
  private static final long serialVersionUID = -8837434444675911289L;
  private String userId;
  private String nickName;
  private String loginName;
  private String telephone;
  private String address;
  private String email;
  private String imageUrl;
  private String remark;

  @Column(name = "user_id", nullable = false, length = User.LENGTH_ID)
  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  @Column(name = "user_nick_name", nullable = false, length = User.LENGTH_NICK_NAME)
  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  @Column(name = "user_login_name", nullable = false, length = User.LENGTH_LOGIN_NAME)
  public String getLoginName() {
    return loginName;
  }

  public void setLoginName(String loginName) {
    this.loginName = loginName;
  }

  @Column(name = "user_telephone", nullable = true, length = User.LENGTH_TELEPHONE)
  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  @Column(name = "user_address", nullable = true, length = User.LENGTH_ADDRESS)
  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  @Column(name = "user_email", nullable = true, length = User.LENGTH_EMAIL)
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Column(name = "user_image_url", nullable = true, length = User.LENGTH_IMAGE_URL)
  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  @Column(name = "user_remark", nullable = true, length = User.LENGTH_IMAGE_REMARK)
  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

}
