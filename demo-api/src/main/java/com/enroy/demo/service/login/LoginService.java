/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	demo
 * 文件名：	LoginServic3e3.java
 * 模块说明：
 * 修改历史：
 * 2018/2/12 - zhuchao - 创建。
 */

package com.enroy.demo.service.login;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.enroy.demo.service.user.LoginUser;
import com.enroy.demo.service.user.User;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * @author zhuchao
 */
@Path("/loginservice")
@Consumes({
        MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
@Produces({
        ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
public interface LoginService {
  @POST
  @Path("/login")
  LoginResult login(LoginUser user);

  @GET
  @Path("/get")
  User get(@QueryParam("id") String userId);
}
