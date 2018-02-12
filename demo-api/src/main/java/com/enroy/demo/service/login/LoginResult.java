/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	demo
 * 文件名：	LoginResult.java
 * 模块说明：
 * 修改历史：
 * 2018/2/12 - zhuchao - 创建。
 */
package com.enroy.demo.service.login;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author zhuchao
 */
@XmlRootElement
public class LoginResult {
  private String userId;
  private boolean accepted = false;
  private String message;

  public static LoginResult success(String userId) {
    LoginResult result = new LoginResult();
    result.setUserId(userId);
    result.setAccepted(true);
    return result;
  }

  public static LoginResult fail(String message) {
    LoginResult result = new LoginResult();
    result.setAccepted(false);
    result.setMessage(message);
    return result;
  }

  public static LoginResult fail(String userId, String message) {
    LoginResult result = new LoginResult();
    result.setUserId(userId);
    result.setAccepted(false);
    result.setMessage(message);
    return result;
  }

  @XmlElement(name = "user_id")
  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  /** 是否验证通过，如果验证通过，将返回用户ID，{see {@link #getUserId()} */
  public boolean isAccepted() {
    return accepted;
  }

  public void setAccepted(boolean accepted) {
    this.accepted = accepted;
  }

  /** 提示信息 */
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
