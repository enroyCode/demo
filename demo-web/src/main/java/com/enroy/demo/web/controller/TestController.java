/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	demo
 * 文件名：	TestController.java
 * 模块说明：
 * 修改历史：
 * 2017/12/29 - zhuchao - 创建。
 */
package com.enroy.demo.web.controller;

import com.enroy.demo.service.TestService;
import com.enroy.demo.test.service.Test;
import com.enroy.demo.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhuchao
 */
@Controller
@RequestMapping("/test/*")
public class TestController extends BaseController {
  @Autowired
  private TestService testService;

  @RequestMapping(value = "/load", method = RequestMethod.GET)
  @ResponseBody
  public Test load(@RequestParam("id") String id) {
    return testService.get(id);
  }

}
