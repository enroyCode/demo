/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2014，所有权利保留。
 * <p>
 * 项目名：	m3-commons-ext
 * 文件名：	HttpClient.java
 * 模块说明：
 * 修改历史：
 * 2014年12月25日 - suizhe - 创建。
 */
package com.enroy.demo.commons.utils.http;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.protocol.Protocol;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * https请求工具类，适用于小数据量http请求
 *
 * @author suizhe
 */
public class HttpsClient {
  public static final String HTTPS = "https";
  public static final int DEFAULT_PORT = 443;
  public static final String CHARSET = "utf-8";

  /**
   * http请求-get
   *
   * @param url
   *         url
   * @return 请求结果
   */
  public static String get(String url) throws RuntimeException {
    return get(url, null, null);
  }

  /**
   * http请求-get
   *
   * @param url
   *         url
   * @param params
   *         query参数
   * @param headers
   *         header
   * @return 请求结果
   */
  public static String get(String url, Map<String, String> params, Map<String, String> headers) throws
          RuntimeException {
    String result;
    Protocol https = new Protocol(HTTPS, new HTTPSSecureProtocolSocketFactory(), DEFAULT_PORT);
    Protocol.registerProtocol(HTTPS, https);
    GetMethod get = new GetMethod(url);
    org.apache.commons.httpclient.HttpClient client = new org.apache.commons.httpclient.HttpClient();
    try {
      if (headers != null) {
        for (String key : headers.keySet()) {
          get.addRequestHeader(key, headers.get(key));
        }
      }
      if (params != null) {
        for (String key : params.keySet()) {
          get.getParams().setParameter(key, params.get(key));
        }
      }
      client.executeMethod(get);
      result = get.getResponseBodyAsString();
    } catch (Exception e) {
      throw new RuntimeException(e);
    } finally {
      Protocol.unregisterProtocol(HTTPS);
    }

    return result;
  }

  /**
   * http请求-get
   *
   * @param url
   *         url
   * @param params
   *         query参数
   * @param headers
   *         header
   * @return 请求结果
   */
  public static String get(String url, Map<String, String> params, Map<String, String> headers, String username, String password) throws
          RuntimeException {
    if (headers == null) {
      headers = new HashMap<String, String>();
    }
    if (username != null && params != null) {
      String auth = username + ":" + password;
      byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
      headers.put("Authorization", "Basic " + new String(encodedAuth));
    }
    return get(url, params, headers);
  }

  /**
   * http请求-delete
   *
   * @param url
   *         url
   * @return 请求结果
   */
  public static String delete(String url) throws RuntimeException {
    return get(url, null, null);
  }

  /**
   * http请求-delete
   *
   * @param url
   *         url
   * @param params
   *         query参数
   * @param headers
   *         header
   * @return 请求结果
   */
  public static String delete(String url, Map<String, String> params, Map<String, String> headers) throws
          RuntimeException {
    String result;
    Protocol https = new Protocol(HTTPS, new HTTPSSecureProtocolSocketFactory(), DEFAULT_PORT);
    Protocol.registerProtocol(HTTPS, https);
    DeleteMethod delete = new DeleteMethod(url);
    org.apache.commons.httpclient.HttpClient client = new org.apache.commons.httpclient.HttpClient();
    try {
      if (headers != null) {
        for (String key : headers.keySet()) {
          delete.addRequestHeader(key, headers.get(key));
        }
      }
      if (params != null) {
        for (String key : params.keySet()) {
          delete.getParams().setParameter(key, params.get(key));
        }
      }
      client.executeMethod(delete);
      result = delete.getResponseBodyAsString();
    } catch (Exception e) {
      throw new RuntimeException(e);
    } finally {
      Protocol.unregisterProtocol(HTTPS);
    }

    return result;
  }

  /**
   * post请求
   *
   * @param url
   *         url
   * @return 请求结果
   */
  public static String post(String url) throws RuntimeException {
    return post(url, null, null, null);
  }

  /**
   * post请求
   *
   * @param url
   *         url
   * @param params
   *         query参数
   * @param headers
   *         请求头
   * @param body
   *         内容
   * @return 请求结果
   */
  public static String post(String url, Map<String, String> params, Map<String, String> headers, String body)
          throws RuntimeException {
    String result;
    Protocol https = new Protocol(HTTPS, new HTTPSSecureProtocolSocketFactory(), DEFAULT_PORT);
    Protocol.registerProtocol(HTTPS, https);
    PostMethod post = new PostMethod(url);
    org.apache.commons.httpclient.HttpClient client = new org.apache.commons.httpclient.HttpClient();
    try {
      if (headers != null) {
        for (String key : headers.keySet()) {
          post.addRequestHeader(key, headers.get(key));
        }
      }
      if (params != null) {
        for (String key : params.keySet()) {
          post.getParams().setParameter(key, params.get(key));
        }
      }
      client.executeMethod(post);
      RequestEntity requestEntity = new StringRequestEntity(body, "text/html", "utf-8");
      post.setRequestEntity(requestEntity);
      result = post.getResponseBodyAsString();
    } catch (Exception e) {
      throw new RuntimeException(e);
    } finally {
      Protocol.unregisterProtocol(HTTPS);
    }

    return result;
  }

  /**
   * http请求-get
   *
   * @param url
   *         url
   * @param params
   *         query参数
   * @param headers
   *         header
   * @return 请求结果
   */
  public static String post(String url, Map<String, String> params, Map<String, String> headers, String username, String password, String body) throws
          RuntimeException {
    if (headers == null) {
      headers = new HashMap<String, String>();
    }
    if (username != null && params != null) {
      String auth = username + ":" + password;
      byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
      headers.put("Authorization", "Basic " + new String(encodedAuth));
    }
    return post(url, params, headers, body);
  }
}
