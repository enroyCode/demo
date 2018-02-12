/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	demo
 * 文件名：	TokenFilter.java
 * 模块说明：
 * 修改历史：
 * 2018/2/11 - zhuchao - 创建。
 */
package com.enroy.demo.web.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhuchao
 */
public class TokenFilter implements Filter {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  public void init(FilterConfig filterConfig) throws ServletException {

  }

  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;
    // 请求方法是OPTIONS，跳过
    if ("OPTIONS".equals(httpRequest.getMethod())) {
      logger.debug("url {} method is option, need not authenticate, ignored", httpRequest.getRequestURI());
      chain.doFilter(request, response);
      return;
    }
//    tokenAccepted();
    // 从cookie中获得token

    chain.doFilter(request, response);
  }

  public void destroy() {

  }

//  private void tokenAccepted(HttpServletRequest req, HttpServletResponse resp, TokenData tokenData,
//                             TokenCallback callback) {
//
//  }
}
