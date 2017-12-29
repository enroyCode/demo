/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	demo
 * 文件名：	baseController.java
 * 模块说明：
 * 修改历史：
 * 2017/12/29 - zhuchao - 创建。
 */
package com.enroy.demo.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 基类控制器
 *
 * @author zhuchao
 */
public class BaseController implements ApplicationContextAware {
  protected final Logger logger = LoggerFactory.getLogger(this.getClass());
  private ApplicationContext appCtx;

  public BaseController() {
  }

  @RequestMapping(
          path = {"test"},
          method = {RequestMethod.GET}
  )
  public Object test() {
    return this.getClass().getName() + " test ok";
  }

  protected ApplicationContext getAppCtx() {
    return this.appCtx;
  }

  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.appCtx = applicationContext;
  }
}
