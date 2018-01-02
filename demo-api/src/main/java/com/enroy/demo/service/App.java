/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	demo
 * 文件名：	App.java
 * 模块说明：
 * 修改历史：
 * 2017/12/29 - zhuchao - 创建。
 */
package com.enroy.demo.service;

import java.io.File;
import java.io.FileInputStream;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

/**
 * @author zhuchao
 */
public class App {
  private String version;

  public String getVersion() {
    return INSTANCE.version;
  }

  public App get() {
    return INSTANCE;
  }

  private static final App INSTANCE = new App();

  static {
    try {
      // <app>/WEB-INF/classes -> <app>/META-INF
      File baseDir = new File(App.class.getResource("/").toURI());
      File manifestFile = new File(baseDir.getParentFile().getParentFile(), "/META-INF/MANIFEST.MF");
      if (manifestFile.exists()) {
        Manifest manifest = new Manifest(new FileInputStream(manifestFile));
        INSTANCE.version = manifest.getMainAttributes().getValue(
                Attributes.Name.IMPLEMENTATION_VERSION);
      }
    } catch (Exception e) {
    }
  }
}
