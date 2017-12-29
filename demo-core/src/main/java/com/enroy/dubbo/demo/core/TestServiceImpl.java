package com.enroy.dubbo.demo.core;

import com.enroy.dubbo.demo.service.TestService;

public class TestServiceImpl implements TestService {
  public String sayHey() {
    return "demo-core say hello!";
  }
}
