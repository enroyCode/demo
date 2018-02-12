/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	demo
 * 文件名：	UserConverter.java
 * 模块说明：
 * 修改历史：
 * 2018/2/12 - zhuchao - 创建。
 */
package com.enroy.demo.core.test.login;

import com.enroy.demo.commons.utils.converter.Converter;
import com.enroy.demo.dao.login.PUser;
import com.enroy.demo.service.user.User;

/**
 * @author zhuchao
 */
public class UserConverter implements Converter<PUser, User> {
  private static UserConverter instance = null;

  public static UserConverter get() {
    if (instance == null) {
      instance = new UserConverter();
    }
    return instance;
  }

  public User convert(PUser source) throws RuntimeException {
    if (source == null) {
      return null;
    }
    User target = new User();
    target.setUuid(source.getUuid());
    target.setUserId(source.getUserId());
    target.setAddress(source.getAddress());
    target.setEmail(source.getEmail());
    target.setLoginName(source.getLoginName());
    target.setRemark(source.getRemark());
    target.setNickName(source.getNickName());
    target.setImageUrl(source.getImageUrl());
    target.setTelephone(source.getTelephone());
    return target;
  }
}
