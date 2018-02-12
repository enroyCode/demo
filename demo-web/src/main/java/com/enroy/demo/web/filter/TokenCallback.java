/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	demo
 * 文件名：	TokenCallback.java
 * 模块说明：
 * 修改历史：
 * 2018/2/12 - zhuchao - 创建。
 */
package com.enroy.demo.web.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhuchao
 */
public interface TokenCallback {
  /**
   * token验证通过。
   */
  void onAccepted(HttpServletRequest req, HttpServletResponse resp, TokenData data);

  /**
   * token被拒绝。
   */
  void onRejected(HttpServletRequest req, HttpServletResponse resp);

  /**
   * token刷新认证。
   */
  boolean onValidate(HttpServletRequest req, HttpServletResponse resp, TokenData data);
}
