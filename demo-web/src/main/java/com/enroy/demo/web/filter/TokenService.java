/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	demo
 * 文件名：	TokenService.java
 * 模块说明：
 * 修改历史：
 * 2018/2/11 - zhuchao - 创建。
 */
package com.enroy.demo.web.filter;

import com.auth0.jwt.Algorithm;
import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.google.common.net.InternetDomainName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author zhuchao
 */
public class TokenService {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private TokenConfig config;
  /**
   * 颁发token。
   */
  public String issueToken(TokenData tokenData) {
    return this.issueJwt(tokenData);
  }

  /**
   * 验证token。
   */
  public TokenData verifyToken(String token) {
    JWTVerifier jwtVerifier = new JWTVerifier(config.getSecret());
    Map<String, Object> claims;
    try {
      claims = jwtVerifier.verify(token);
    } catch (Exception e) {
      logger.info("token不合法: {}", token);
      return null;
    }
    return new TokenData(claims);
  }

  /**
   * 刷新token，会调用认证服务进行认证检查，如果检查不通过，返回null，否则返回原token或刷新后的token。
   */
  public String refreshToken(HttpServletRequest req, HttpServletResponse resp, String token,
                             TokenData data, TokenCallback callback) {
    long now = System.currentTimeMillis() / 1000l;
    int issuedAt = data.getIssuedAt();
    if (now - issuedAt > config.getRefreshIn()) {
      logger.debug("验证用户身份: {}", data);
      if (callback != null) {
        boolean valid = callback.onValidate(req, resp, data);
        if (valid == false) {
          return null;
        }
      }
      return issueJwt(data);
    }
    return token;
  }

  /**
   * 创建cookie。
   */
  public Cookie createCookie(HttpServletRequest request, HttpServletResponse response, String token) {
    Cookie cookie = new Cookie(config.getCookieName(), token);
    cookie.setDomain(getCookieDomain(request));
    cookie.setPath("/");
    cookie.setHttpOnly(true);
    cookie.setMaxAge(-1);
    response.addCookie(cookie);
    return cookie;
  }

  /**
   * 删除cookie。
   */
  public void removeCookie(HttpServletRequest request, HttpServletResponse response) {
    if (request.getCookies() != null) {
      for (Cookie cookie : request.getCookies()) {
        if (config.getCookieName().equals(cookie.getName())) {
          cookie.setValue(null);
          cookie.setDomain(getCookieDomain(request));
          cookie.setPath("/");
          cookie.setMaxAge(0);
          response.addCookie(cookie);
          break;
        }
      }
    }
  }

  /**
   * 从cookie中提取token。
   */
  public String extractTokenFromCookie(HttpServletRequest request) {
    if (request.getCookies() != null) {
      for (Cookie cookie : request.getCookies()) {
        if (config.getCookieName().equals(cookie.getName())) {
          return cookie.getValue();
        }
      }
    }
    return null;
  }

  /**
   * 生成新的token。
   */
  private String issueJwt(TokenData data) {
    // JWT签名设置
    JWTSigner.Options options = new JWTSigner.Options().setAlgorithm(Algorithm.HS256);
    options.setExpirySeconds(config.getExpiresIn());
    options.setIssuedAt(true);

    // 签名
    JWTSigner signer = new JWTSigner(config.getSecret());
    String jwt = signer.sign(data.getMap(), options);

    return jwt;
  }

  private String getCookieDomain(HttpServletRequest request) {
    String result = request.getServerName();
    try {
      result = "." + InternetDomainName.from(request.getServerName()).topPrivateDomain().toString();
    } catch (Exception e) {
    }
    return result;
  }

  public TokenConfig getConfig() {
    return config;
  }

  public void setConfig(TokenConfig config) {
    this.config = config;
  }
}
