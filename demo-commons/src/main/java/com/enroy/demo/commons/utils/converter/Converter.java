/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	demo
 * 文件名：	Converter.java
 * 模块说明：
 * 修改历史：
 * 2018/1/19 - zhuchao - 创建。
 */
package com.enroy.demo.commons.utils.converter;

/**
 * @author zhuchao
 */
public interface Converter<S, T> {
  T convert(S source) throws RuntimeException;
}
