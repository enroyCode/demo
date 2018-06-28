/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	demo
 * 文件名：	TestDaoImpl.java
 * 模块说明：
 * 修改历史：
 * 2018/1/2 - zhuchao - 创建。
 */
package com.enroy.demo.dao.test;

import com.enroy.demo.core.test.PTest;
import com.enroy.demo.dao.BaseDao;
import com.enroy.demo.dao.DemoTX;
import com.enroy.demo.test.service.Test;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

/**
 * @author zhuchao
 */
@Repository(value = TestDao.DEFAULT_CONTEXT_ID)
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TestDaoImpl extends BaseDao implements TestDao {
  @DemoTX
  public PTest save(Test entity) {
    PTest perz = get(entity.getCode());
    if (perz == null) {
      perz = new PTest();
      perz.setCode(entity.getCode());
      perz.setName(entity.getName());
      perz.setExecTime(entity.getExecTime());
      em.persist(perz);
    } else {
      perz.setCode(entity.getCode());
      perz.setName(entity.getName());
      perz.setExecTime(entity.getExecTime());
      em.merge(perz);
    }
    em.flush();
    return perz;
  }

  public PTest get(String id) {
    if (StringUtils.isBlank(id)) {
      return null;
    }
    String hql = "from " + PTest.class.getName() + " o where o.code =:id";
    Query query = em.createQuery(hql);

    query.setParameter("id", id);
    List<PTest> list = query.getResultList();
    return list.isEmpty() ? null : list.get(0);
  }

}
