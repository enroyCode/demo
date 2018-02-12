/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	demo
 * 文件名：	LoginServiceImpl.java
 * 模块说明：
 * 修改历史：
 * 2018/2/12 - zhuchao - 创建。
 */
package com.enroy.demo.core.test.login;

import com.enroy.demo.dao.login.LoginDao;
import com.enroy.demo.service.login.LoginResult;
import com.enroy.demo.service.login.LoginService;
import com.enroy.demo.service.user.LoginUser;
import com.enroy.demo.service.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

/**
 * @author zhuchao
 */
public class LoginServiceImpl implements LoginService {
  @Autowired
  private LoginDao loginDao;

  public LoginResult login(LoginUser user) {
    Assert.notNull(user, "用户名或密码不能为空！");
    Assert.notNull(user.getUsername(), "用户名不能为空！");
    Assert.notNull(user.getPassword(), "密码不能为空！");
    //TODO 验证码验证

    return loginDao.login(user);
  }

  public User get(String userId) {
    Assert.notNull(userId, "用户名不能为空！");
    return UserConverter.get().convert(loginDao.get(userId));
  }
}
