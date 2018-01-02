/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	demo
 * 文件名：	Entity.java
 * 模块说明：
 * 修改历史：
 * 2018/1/2 - zhuchao - 创建。
 */
package com.enroy.demo.commons.biz;

import java.io.Serializable;

/**
 * @author zhuchao
 */
public class Entity implements Serializable, IsEntity {

  private static final long serialVersionUID = 5110576367928498341L;
  private String uuid;

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) throws UnsupportedOperationException {
    this.uuid = uuid;
  }

  @Override
  public String toString() {
    return this.uuid;
  }
}
