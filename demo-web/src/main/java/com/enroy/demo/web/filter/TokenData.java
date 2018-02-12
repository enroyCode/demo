/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	demo
 * 文件名：	TokenData.java
 * 模块说明：
 * 修改历史：
 * 2018/2/11 - zhuchao - 创建。
 */
package com.enroy.demo.web.filter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhuchao
 */
public class TokenData {
  protected static final String KEY_ISSUED_AT = "iat";
  protected static final String KEY_ISSUER = "iss";
  protected static final String KEY_SUBJECT = "sub";
  protected static final String KEY_AUDIENCE = "aud";
  protected static final String KEY_EXPIRE = "exp";

  private Map<String, Object> map = new HashMap();

  public TokenData() {
  }

  public TokenData(Map<String, Object> map) {
    this.map = map;
  }

  public Map getMap() {
    return map;
  }

  public TokenData put(String key, Object value) {
    this.map.put(key, value);
    return this;
  }

  public Object get(String key) {
    return this.map.get(key);
  }

  /**
   * 签发时间，即 RFC7519中的iat
   */
  public Integer getIssuedAt() {
    return (Integer) map.get(KEY_ISSUED_AT);
  }

  public Integer getExpire() {
    return (Integer) map.get(KEY_EXPIRE);
  }

  public String toString() {
    return map.toString();
  }
}
