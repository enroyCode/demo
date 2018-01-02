/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	demo
 * 文件名：	PEntity.java
 * 模块说明：
 * 修改历史：
 * 2018/1/2 - zhuchao - 创建。
 */
package com.enroy.demo.jpa.biz;

import com.enroy.demo.commons.biz.IsEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author zhuchao
 */
public class PEntity implements Serializable, IsEntity {

  private static final long serialVersionUID = -6822580840000923555L;
  public static final int LENGTH_UUID = 38;

  private String uuid;

  @Id
  @GeneratedValue(
          generator = "system-uuid"
  )
  @GenericGenerator(
          name = "system-uuid",
          strategy = "uuid"
  )
  @Column(
          name = "uuid",
          length = LENGTH_UUID,
          nullable = false
  )
  public String getUuid() {
    return this.uuid;
  }

  public void setUuid(String uuid) throws UnsupportedOperationException {
    this.uuid = uuid;
  }

  @Override
  public String toString() {
    return this.uuid;
  }
}
