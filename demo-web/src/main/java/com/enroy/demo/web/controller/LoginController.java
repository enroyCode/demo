/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	demo
 * 文件名：	LoginController.java
 * 模块说明：
 * 修改历史：
 * 2017/12/29 - zhuchao - 创建。
 */
package com.enroy.demo.web.controller;

import com.enroy.demo.commons.biz.ActionResult;
import com.enroy.demo.service.login.LoginResult;
import com.enroy.demo.service.login.LoginService;
import com.enroy.demo.service.user.LoginUser;
import com.enroy.demo.service.user.User;
import com.enroy.demo.web.BaseController;
import com.enroy.demo.web.filter.TokenData;
import com.enroy.demo.web.filter.TokenService;
import com.enroy.demo.web.support.ResponseActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author zhuchao
 */

@Controller
@RequestMapping("/")
public class LoginController extends BaseController {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  @Autowired
  TokenService tokenServer;
  @Autowired
  LoginService loginService;

  @RequestMapping(path = "login.zc", method = RequestMethod.POST)
  @ResponseActionResult
  public ActionResult login(@RequestBody LoginUser user, HttpServletRequest request, HttpServletResponse response) throws Exception {

    HttpSession session = request.getSession();
    String redirectUrl = request.getParameter("redirectUrl");
    // 验证码检查
    String captcha = (String) session.getAttribute("captcha");
    //验证码暂不校验
//    if (captcha == null || user.getCaptcha() == null
//            || captcha.toLowerCase().equals(user.getCaptcha().toLowerCase()) == false) {
////      redirectToLogin(request, response, user.getUsername(), "验证码不正确");
//      return ActionResult.fail("验证码不正确");
//    }
    // 密码检查
    LoginResult loginResult = loginService.login(user);
    if (!loginResult.isAccepted()) {
      return ActionResult.fail(loginResult.getMessage());
    }

    User u = loginService.get(loginResult.getUserId());

    // 发放token
    TokenData tokenData = new TokenData();
    tokenData.put("userId", u.getUserId());
    String token = tokenServer.issueToken(tokenData);
    logger.info("发放token: {}", token);
    tokenServer.createCookie(request, response, token);

    // TODO 跳转地址

    return ActionResult.ok();
  }

}
