/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	demo
 * 文件名：	ActionResultReturnValueHandler.java
 * 模块说明：
 * 修改历史：
 * 2017/12/29 - zhuchao - 创建。
 */
package com.enroy.demo.web.support;

import com.alibaba.dubbo.common.json.JSON;
import com.enroy.demo.commons.biz.ActionResult;
import org.springframework.core.MethodParameter;
import org.springframework.util.Assert;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletResponse;

/**
 * @author zhuchao
 */
public class ActionResultReturnValueHandler implements HandlerMethodReturnValueHandler {
  public boolean supportsReturnType(MethodParameter returnType) {
    return returnType.getParameterType() != null && ActionResult.class.isAssignableFrom(returnType.getParameterType());
  }

  public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
    Assert.isInstanceOf(ActionResult.class, returnValue);
    mavContainer.setRequestHandled(true);
    HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
    ActionResult actionResult = (ActionResult) returnValue;
    if (!actionResult.isSuccess()) {
      response.setStatus(500);
    }

    response.setCharacterEncoding("UTF-8");
    response.setContentType("application/json");
    response.getWriter().write(JSON.json(actionResult));//此处改用fastJson进行转换
  }
}
