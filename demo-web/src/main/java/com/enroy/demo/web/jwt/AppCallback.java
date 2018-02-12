/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	demo
 * 文件名：	AppCallback.java
 * 模块说明：
 * 修改历史：
 * 2018/2/12 - zhuchao - 创建。
 */
package com.enroy.demo.web.jwt;

import com.enroy.demo.web.filter.TokenCallback;
import com.enroy.demo.web.filter.TokenData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhuchao
 */
public class AppCallback implements TokenCallback {
  public void onAccepted(HttpServletRequest req, HttpServletResponse resp, TokenData data) {

  }

  public void onRejected(HttpServletRequest req, HttpServletResponse resp) {

  }

  public boolean onValidate(HttpServletRequest req, HttpServletResponse resp, TokenData data) {
    return false;
  }
}
