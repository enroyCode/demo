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

/**
 * @author zhuchao
 */
public class User {
  /** 属性{@link #getUsername()}的最大长度 */
  public static final int LENGTH_USERNAME = 16;
  /** 属性{@link #getPassword()}的最大长度 */
  public static final int LENGTH_PASSWORD = 16;

  private String username;
  private String password;
  private String captcha;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getCaptcha() {
    return captcha;
  }

  public void setCaptcha(String captcha) {
    this.captcha = captcha;
  }
}
