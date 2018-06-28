/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	demo
 * 文件名：	PerzTestConverter.java
 * 模块说明：
 * 修改历史：
 * 2018/1/19 - zhuchao - 创建。
 */
package com.enroy.demo.dao.test;

import com.enroy.demo.commons.utils.converter.Converter;
import com.enroy.demo.core.test.PTest;
import com.enroy.demo.test.service.Test;

/**
 * @author zhuchao
 */
public class PerzTestConverter implements Converter<PTest, Test> {
  private static PerzTestConverter instance = null;

  public static synchronized PerzTestConverter getInstance() {
    if (instance == null) {
      instance = new PerzTestConverter();
    }
    return instance;
  }

  public Test convert(PTest source) throws RuntimeException {
    if (source == null) {
      return null;
    }
    Test target = new Test();
    target.setCode(source.getCode());
    target.setName(source.getName());
    target.setUuid(source.getUuid());
    target.setExecTime(source.getExecTime());
    return target;
  }
}
