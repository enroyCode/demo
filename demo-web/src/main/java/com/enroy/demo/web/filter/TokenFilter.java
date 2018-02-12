/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	demo
 * 文件名：	TokenFilter.java
 * 模块说明：
 * 修改历史：
 * 2018/2/11 - zhuchao - 创建。
 */
package com.enroy.demo.web.filter;

import com.enroy.demo.web.login.LoginConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author zhuchao
 */
public class TokenFilter implements Filter {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  /** 忽略的URL配置 */
  public static final String EXCLUDES = "excludes";
  /** 如果url中包含此参数，表明是注销请求 */
  public static final String LOGOUT_REQUEST = "_logout";
  /** URL模式 */
  private List<Pattern> excludeChecker;

  public void init(FilterConfig filterConfig) throws ServletException {
    logger.info("加载忽略的URL配置...");
    String excludeStr = filterConfig.getInitParameter(EXCLUDES);
    if (excludeStr != null) {
      String[] excludes = excludeStr.split(",");
      if (excludes != null) {
        for (String exclude : excludes) {
          if (excludeChecker == null)
            excludeChecker = new ArrayList();
          String regex = "^" + exclude.replaceAll("\\*", ".*").replaceAll("\\?", ".") + "$";
          excludeChecker.add(Pattern.compile(regex));
        }
      }
    }
  }

  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;
    // 请求方法是OPTIONS，跳过
    if ("OPTIONS".equals(httpRequest.getMethod())) {
      logger.debug("url {} method is option, need not authenticate, ignored", httpRequest.getRequestURI());
      chain.doFilter(request, response);
      return;
    }
    // 如果指定的地址不进行授权控制，跳过
    if (isRequestUrlExcluded(httpRequest)) {
      logger.debug("url {} need not authenticate, ignored", httpRequest.getRequestURI());
      chain.doFilter(request, response);
      return;
    }

    ApplicationContext appCtx = WebApplicationContextUtils.getWebApplicationContext(httpRequest
                                                                                            .getServletContext());
    TokenService tokenService = appCtx.getBean(TokenService.class);
    TokenCallback callback = appCtx.getBean(TokenCallback.class);
    LoginConfig loginConfig = appCtx.getBean(LoginConfig.class);

    // 如果是注销请求

    // 从cookie中获得token
    String token = tokenService.extractTokenFromCookie(httpRequest);
    if (token == null) {
      logger.error("cookie中没有token");
      tokenRejected(httpRequest, httpResponse, loginConfig, callback);
      return;
    }

    chain.doFilter(request, response);
  }

  private boolean isRequestUrlExcluded(final HttpServletRequest request) {
    if (excludeChecker == null)
      return false;

    final StringBuffer urlBuffer = request.getRequestURL();
    if (request.getQueryString() != null) {
      urlBuffer.append("?").append(request.getQueryString());
    }

    final String requestUri = urlBuffer.toString();
    for (Pattern p : excludeChecker) {
      if (p.matcher(requestUri).find())
        return true;
    }
    return false;
  }

  private void tokenRejected(HttpServletRequest req, HttpServletResponse resp,
                             LoginConfig loginConfig, TokenCallback callback) throws IOException {
    if (callback != null)
      callback.onRejected(req, resp);
    // AJAX请求直接返回错误信息
    if (isAjaxRequest(req)) {
      resp.sendError(401, "账号过期，请重新登录");
    } else { // 重定向到登录页
      redirectToLogin(req, resp, loginConfig);
    }
  }

  private void redirectToLogin(HttpServletRequest req, HttpServletResponse resp,
                               LoginConfig loginConfig) throws IOException {
    String urlToRedirectTo = getQueryString(req);
//    URL url = new URL(urlToRedirectTo);
//    if (url.getQuery().contains(LOGOUT_REQUEST)) {
//      url.getQuery().remove(LOGOUT_REQUEST);
//      urlToRedirectTo = url.toString();
//    }
    resp.sendRedirect(resp.encodeRedirectURL(loginConfig.getLoginUrl() + "?redirectUrl="
                                                     + URLEncoder.encode(urlToRedirectTo, "UTF-8")));
  }

  private String getQueryString(HttpServletRequest request) throws IOException {
    StringBuffer sb = new StringBuffer(request.getRequestURL());
    String query = request.getQueryString();
    if (query != null && query.length() > 0) {
      sb.append("?").append(query);
    }
    return sb.toString();
  }

  public void destroy() {

  }

  //  private void tokenAccepted(HttpServletRequest req, HttpServletResponse resp, TokenData tokenData,
//                             TokenCallback callback) {
//
//  }
  private boolean isAjaxRequest(HttpServletRequest req) {
    return "XMLHttpRequest".equals(req.getHeader("x-requested-with"));
  }
}
