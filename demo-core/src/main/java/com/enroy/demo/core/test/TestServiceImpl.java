/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	demo
 * 文件名：	TestServiceImpl.java
 * 模块说明：
 * 修改历史：
 * 2018/1/2 - zhuchao - 创建。
 */
package com.enroy.demo.core.test;

import com.enroy.demo.dao.test.TestDao;
import com.enroy.demo.service.TestService;
import com.enroy.demo.test.service.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhuchao
 */
public class TestServiceImpl implements TestService {

  @Autowired
  private TestDao testDao;

  public String sayHey() {
    return "hey";
  }

  public Test get(String id) {
    Test test = testDao.get(id);
    return test;
  }

}
