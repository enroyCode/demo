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

import com.enroy.demo.dao.BaseDao;
import com.enroy.demo.service.login.LoginResult;
import com.enroy.demo.service.user.User;

import javax.persistence.Query;
import java.util.List;

/**
 * @author zhuchao
 */
public class LoginDao extends BaseDao {
  /** 登录验证 */
  public LoginResult login(User user) {
    if (user == null) {
      return LoginResult.fail("用户名或密码不能为空！");
    }
    String hql = "from " + PUser.class.getName() + " o where o.username =:username";
    Query query = em.createQuery(hql);
    query.setParameter("username", user.getUsername());
    List<PUser> resultList = query.getResultList();
    if (resultList.size() == 0) {
      return LoginResult.fail(user.getUsername(), "账户不存在！");
    } else {
      PUser po = resultList.get(0);
      if (po.getPassword().equals(user.getPassword())) {
        return LoginResult.success(user);
      } else {
        return LoginResult.fail(user.getUsername(), "密码错误！");
      }
    }
  }
}
