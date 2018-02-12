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
                "username"})})
public class PUser extends PEntity {
  private static final long serialVersionUID = -8837434444675911289L;
  private String username;
  private String password;

  @Column(name = "username", nullable = false, length = User.LENGTH_USERNAME)
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @Column(name = "password", nullable = false, length = User.LENGTH_PASSWORD)
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
