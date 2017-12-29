/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	demo
 * 文件名：	ActionResult.java
 * 模块说明：
 * 修改历史：
 * 2017/12/29 - zhuchao - 创建。
 */
package com.enroy.demo.biz;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author zhuchao
 */
@XmlRootElement
public class ActionResult {
  private boolean success = true;
  private String code;
  private String message;

  public static ActionResult ok() {
    return OK;
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public static final ActionResult OK = new ActionResult();
}
