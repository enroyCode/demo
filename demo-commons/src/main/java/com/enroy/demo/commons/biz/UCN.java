/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	demo
 * 文件名：	UCN.java
 * 模块说明：
 * 修改历史：
 * 2018/1/22 - zhuchao - 创建。
 */
package com.enroy.demo.commons.biz;

/**
 * @author zhuchao
 */
public class UCN extends Entity {

  private static final long serialVersionUID = -8917625067496050898L;
  private String code;
  private String name;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
