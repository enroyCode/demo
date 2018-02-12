/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	demo
 * 文件名：	TokenConfig.java
 * 模块说明：
 * 修改历史：
 * 2018/2/11 - zhuchao - 创建。
 */
package com.enroy.demo.web.filter;

/**
 * @author zhuchao
 */
public class TokenConfig {
  /** cookie的名字 */
  private static final String DEFAULT_COOKIE_NAME = "_uid_";

  /** 过期时间，单位:s */
  private int expiresIn = 24 * 60 * 60;

  /** 刷新时间，单位：s */
  private int refreshIn = 10 * 60;

  /** 签名用的私钥 */
  private String secret;

  /** 存放的cookie名 */
  private String cookieName = DEFAULT_COOKIE_NAME;

  public int getExpiresIn() {
    return expiresIn;
  }

  public void setExpiresIn(int expiresIn) {
    this.expiresIn = expiresIn;
  }

  public int getRefreshIn() {
    return refreshIn;
  }

  public void setRefreshIn(int refreshIn) {
    this.refreshIn = refreshIn;
  }

  public String getSecret() {
    return secret;
  }

  public void setSecret(String secret) {
    this.secret = secret;
  }

  public String getCookieName() {
    return cookieName;
  }

  public void setCookieName(String cookieName) {
    this.cookieName = cookieName;
  }
}
