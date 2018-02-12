/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	demo
 * 文件名：	TokenContext.java
 * 模块说明：
 * 修改历史：
 * 2018/2/12 - zhuchao - 创建。
 */
package com.enroy.demo.web.filter;

/**
 * @author zhuchao
 */
public class TokenContext {
  private static final ThreadLocal<TokenData> CONTEXT = new ThreadLocal<TokenData>();

  public static void set(TokenData data) {
    CONTEXT.set(data);
  }

  public static TokenData get() {
    return CONTEXT.get();
  }

  public static void remove() {
    CONTEXT.remove();
  }
}
