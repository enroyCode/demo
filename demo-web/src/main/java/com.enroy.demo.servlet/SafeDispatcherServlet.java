/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	demo
 * 文件名：	SafeDispatcherServlet.java
 * 模块说明：
 * 修改历史：
 * 2017/12/29 - zhuchao - 创建。
 */
package com.enroy.demo.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContextException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

/**
 * @author zhuchao
 */
public class SafeDispatcherServlet extends HttpServlet {
  private static final long serialVersionUID = 4331489240950327780L;

  public static final String CAUGHT_THROWABLE_KEY = "exceptionCaughtByServlet";

  private static final Logger log = LoggerFactory.getLogger(SafeDispatcherServlet.class);

  private DispatcherServlet delegate = new DispatcherServlet();

  private boolean initSuccess = true;

  @Override
  public void init(final ServletConfig config) {
    try {
      this.delegate.init(config);
    } catch (final Throwable t) {
      this.initSuccess = false;

      final String message = "SafeDispatcherServlet: \n" + "初始化前端控制器过程发生错误.....";

      log.error(message, t);

      System.err.println(message);
      t.printStackTrace();

      ServletContext context = config.getServletContext();
      context.log(message, t);

      context.setAttribute(CAUGHT_THROWABLE_KEY, t);
    }
  }

  @Override
  public void service(final ServletRequest req, final ServletResponse resp)
          throws ServletException, IOException {
    if (this.initSuccess) {
      this.delegate.service(req, resp);
    } else {
      throw new ApplicationContextException("Unable to initialize application context.");
    }
  }
}
