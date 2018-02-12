/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	demo
 * 文件名：	LoginDao.java
 * 模块说明：
 * 修改历史：
 * 2018/2/12 - zhuchao - 创建。
 */
package com.enroy.demo.dao.login;

import com.enroy.demo.service.login.LoginResult;
import com.enroy.demo.service.user.LoginUser;

/**
 * @author zhuchao
 */
public interface LoginDao {
  String DEFAULT_CONTEXT_ID = "demo-core.loginDao";

  LoginResult login(LoginUser user);

  PUser get(String userId);
}
