/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	demo
 * 文件名：	PTest.java
 * 模块说明：
 * 修改历史：
 * 2018/1/2 - zhuchao - 创建。
 */
package com.enroy.demo.core;

import com.enroy.demo.jpa.biz.PEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author zhuchao
 */
@Entity
@Table(name = "demo_test", uniqueConstraints = {
        @UniqueConstraint(columnNames = "uuid")})
public class PTest extends PEntity {
  private static final int CODE_LENGTH = 32;
  private static final int NAME_LENGTH = 64;

  private static final long serialVersionUID = 1980116360026741057L;
  private String name;
  private String code;

  @Column(name = "name", nullable = false, length = NAME_LENGTH)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Column(name = "code", nullable = false, length = CODE_LENGTH)
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }
}
