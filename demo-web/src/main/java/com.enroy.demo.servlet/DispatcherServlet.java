/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	demo
 * 文件名：	DispatcherServlet.java
 * 模块说明：
 * 修改历史：
 * 2017/12/29 - zhuchao - 创建。
 */
package com.enroy.demo.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * @author zhuchao
 */
public class DispatcherServlet extends org.springframework.web.servlet.DispatcherServlet {
  private static final long serialVersionUID = -5219821332389965809L;

  @Override
  protected void doService(HttpServletRequest request, HttpServletResponse response)
          throws Exception {

    logger.debug("请求：" + request.getRequestURI());
    try {
      super.doService(request, response);
    } catch (Throwable t) {
      logger.error("do service error", t);
      String msg = t.getMessage();
      try {
        if (t instanceof WebApplicationException) {
          WebApplicationException e1 = (WebApplicationException) t;
          if (e1.getResponse().getStatus() == Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()) {
            Response webApplicationResponse = e1.getResponse();
            if (webApplicationResponse == null) {
              throw e1;
            }
            RuntimeException standInException = new RuntimeException(e1.getMessage());
            standInException.initCause(e1);
            throw standInException;
          }
        } else if (t.getClass().getName().equals("com.alibaba.dubbo.rpc.RpcException")) {
          msg = "服务异常";
        } else if (msg != null && msg.contains("com.alibaba.dubbo.rpc.RpcException")) {
          msg = "服务异常";
        }
      } catch (Exception e2) {
      }

      // CORS
      response.setHeader("Access-Control-Allow-Origin", "*");
      response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE");
      response.setHeader("Access-Control-Max-Age", "3600");
      response.setHeader("Access-Control-Allow-Credentials", "false");
      response.setHeader("Access-Control-Allow-Headers", "x-requested-with");

      // ERROR WRAPPER -> 500
      response.setStatus(Status.INTERNAL_SERVER_ERROR.getStatusCode());
      response.setCharacterEncoding("UTF-8");
      response.setContentType("application/json");

      if (msg != null) {
        msg = msg.replace("\"", "'").replace("\n", "").replace("\r", "");
      }
      String str = "{ \"message\":\"" + msg + "\"}";

      response.getWriter().print(str);
      response.getWriter().flush();
      response.getWriter().close();
    }
  }
}
