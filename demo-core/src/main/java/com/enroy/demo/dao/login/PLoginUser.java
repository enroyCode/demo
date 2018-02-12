/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	demo
 * 文件名：	PLoginUser.java
 * 模块说明：
 * 修改历史：
 * 2018/2/12 - zhuchao - 创建。
 */
package com.enroy.demo.dao.login;

import com.enroy.demo.jpa.biz.PEntity;
import com.enroy.demo.service.user.LoginUser;
import org.hibernate.annotations.Index;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author zhuchao
 */
@Entity
@Table(name = "demo_login_user")
@org.hibernate.annotations.Table(appliesTo = "demo_login_user", indexes = {
        @Index(name = "idx_demo_login_user_1", columnNames = {
                "user_id"})})
public class PLoginUser extends PEntity {
  private static final long serialVersionUID = -1843603151681797341L;
  private String userId;
  private String password;
  private String telephone;
  private String email;

  @Column(name = "user_id", nullable = false, length = LoginUser.LENGTH_USER_ID)
  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  @Column(name = "user_password", nullable = false, length = LoginUser.LENGTH_PASSWORD)
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Column(name = "user_telephone", nullable = false, length = LoginUser.LENGTH_TELEPHONE)
  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  @Column(name = "user_email", nullable = false, length = LoginUser.LENGTH_EMAIL)
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
