/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	demo
 * 文件名：	BaseDao.java
 * 模块说明：
 * 修改历史：
 * 2018/1/19 - zhuchao - 创建。
 */
package com.enroy.demo.dao;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author zhuchao
 */
public class BaseDao implements ApplicationContextAware {
  @PersistenceContext(unitName = "demo")
  protected EntityManager em;

  protected ApplicationContext appCtx;

  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.appCtx = applicationContext;
  }
}
