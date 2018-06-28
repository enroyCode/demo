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

import com.enroy.demo.dao.test.PerzTestConverter;
import com.enroy.demo.dao.test.TestDao;
import com.enroy.demo.service.TestService;
import com.enroy.demo.test.service.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhuchao
 */
public class TestServiceImpl implements TestService {

  @Autowired
  private TestDao testDao;

  public Test get(String id) {
    return PerzTestConverter.getInstance().convert(testDao.get(id));
  }

  public Test save(Test entity) {
//    entity.setExecTime(new Date());
    System.currentTimeMillis();
    String current = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS").format(new Date());
    Timestamp time = Timestamp.valueOf(current);
    entity.setExecTime(time);
    PTest perz = testDao.save(entity);
    Test target = new Test();
    target.setCode(perz.getCode());
    target.setName(perz.getName());
    target.setExecTime(perz.getExecTime());
    return target;
  }

  public String doPost(String openId, String signature, String msgSignature, String timestamp, String encryptType, String nonce, String body) {
    return null;
  }

}
