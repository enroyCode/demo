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

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * http请求工具类，适用于小数据量http请求
 *
 * @author suizhe
 */
public class HttpClient {
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
    return doGetDelete("GET", url, params, headers);
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
    return doGetDelete("GET", url, params, headers);
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
    return doGetDelete("DELETE", url, params, headers);
  }

  public static String doGetDelete(String type, String url, Map<String, String> params, Map<String, String> headers)
          throws RuntimeException {
    if (url == null) {
      throw new RuntimeException("null url");
    }
    String getURL = url + generateParameterUrl(params);
    try {
      URL getUrl = new URL(getURL);
      HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
      try {
        connection.setRequestMethod(type);
        injectRequestHeaders(connection, headers);

        connection.connect();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), CHARSET));
        String lines;
        StringBuilder sb = new StringBuilder();
        while ((lines = reader.readLine()) != null) {
          sb.append(lines);
        }

        reader.close();
        return sb.toString();
      } finally {
        // 断开连接
        connection.disconnect();
      }
    } catch (Exception e) {
      throw new RuntimeException(getURL, e);
    }
  }

  /**
   * post请求
   *
   * @param url
   *         url
   * @return 请求结果
   */
  public static String post(String url) throws RuntimeException {
    return post(url, null, newHeaders(), new byte[]{});
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
   * @param content
   *         内容
   * @return 请求结果
   */
  public static String post(String url, Map<String, String> params, Map<String, String> headers, String content)
          throws RuntimeException {
    try {
      if (content == null) {
        return post(url, params, headers, new byte[]{});
      } else {
        return post(url, params, headers, content.getBytes(CHARSET));
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
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
  public static String post(String url, Map<String, String> params, Map<String, String> headers, String username, String password, String content) throws
          RuntimeException {
    if (headers == null) {
      headers = new HashMap<String, String>();
    }
    if (username != null && params != null) {
      String auth = username + ":" + password;
      byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
      headers.put("Authorization", "Basic " + new String(encodedAuth));
    }
    return post(url, params, headers, content);
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
   * @param content
   *         内容
   * @return 请求结果
   */
  public static String post(String url, Map<String, String> params, Map<String, String> headers, byte[] content)
          throws RuntimeException {
    return doPostPut("POST", url, params, headers, content);
  }

  /**
   * put请求
   *
   * @param url
   *         url
   * @return 请求结果
   */
  public static String put(String url) throws RuntimeException {
    return post(url, null, newHeaders(), new byte[]{});
  }

  /**
   * put请求
   *
   * @param url
   *         url
   * @param params
   *         query参数
   * @param headers
   *         请求头
   * @param content
   *         内容
   * @return 请求结果
   */
  public static String put(String url, Map<String, String> params, Map<String, String> headers, String content)
          throws RuntimeException {
    try {
      if (content == null) {
        return post(url, params, headers, new byte[]{});
      } else {
        return post(url, params, headers, content.getBytes(CHARSET));
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * put请求
   *
   * @param url
   *         url
   * @param params
   *         query参数
   * @param headers
   *         请求头
   * @param content
   *         内容
   * @return 请求结果
   */
  public static String put(String url, Map<String, String> params, Map<String, String> headers, byte[] content)
          throws RuntimeException {
    return doPostPut("PUT", url, params, headers, content);
  }

  public static String doPostPut(String type, String url, Map<String, String> params, Map<String, String> headers,
                                 byte[] content) {
    if (url == null) {
      throw new RuntimeException("null url");
    }
    String getURL = url + generateParameterUrl(params);
    try {
      URL getUrl = new URL(getURL);
      HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
      try {
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setUseCaches(false);
        connection.setInstanceFollowRedirects(true);
        connection.setRequestMethod(type);
        injectRequestHeaders(connection, headers);

        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        if (content != null && content.length > 0) {
          out.write(content);
        }
        out.flush();
        out.close();

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), CHARSET));
        String lines;
        StringBuilder sb = new StringBuilder();
        while ((lines = reader.readLine()) != null) {
          sb.append(lines);
        }

        reader.close();
        return sb.toString();
      } finally {
        // 断开连接
        connection.disconnect();
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static Map<String, String> newHeaders() {
    Map<String, String> map = new HashMap<String, String>();
    map.put("Content-Type", "application/json");
    map.put("accept", "application/json");
    return map;
  }

  private static String generateParameterUrl(Map<String, String> params) throws RuntimeException {
    if (params == null || params.isEmpty()) {
      return "";
    }

    try {
      StringBuilder sb = new StringBuilder();
      sb.append("?");
      for (Entry<String, String> entry : params.entrySet()) {
        sb.append(entry.getKey());
        sb.append("=");
        sb.append(URLEncoder.encode(entry.getValue(), CHARSET));
        sb.append("&");
      }
      return sb.toString();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private static void injectRequestHeaders(HttpURLConnection connection, Map<String, String> headers) {
    if (connection == null || headers == null || headers.isEmpty()) {
      return;
    }
    for (Entry<String, String> entry : headers.entrySet()) {
      connection.setRequestProperty(entry.getKey(), entry.getValue());
    }
  }

}
