package com.enroy.demo.web.filter;

/**
 * @author zhangyanbo
 * 
 */
public class LoginConfig {

  private String loginUrl;

  private boolean redirect = true;

  public String getLoginUrl() {
    return loginUrl;
  }

  public void setLoginUrl(String loginUrl) {
    this.loginUrl = loginUrl;
  }

  /** 是否直接重定向 */
  public boolean isRedirect() {
    return redirect;
  }

  public void setRedirect(boolean redirect) {
    this.redirect = redirect;
  }

}
