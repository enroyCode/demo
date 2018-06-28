package com.enroy.demo.service;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.enroy.demo.test.service.Test;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/testservice")
@Consumes({
        MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
@Produces({
        ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
public interface TestService {

  @GET
  @Path("/get")
  Test get(@QueryParam("id") String id);

  @POST
  @Path("/save")
  Test save(@RequestBody Test entity);

  @POST
  String doPost(
          @QueryParam("openid") String openId,
          @QueryParam("signature") String signature,
          @QueryParam("msg_signature") String msgSignature,
          @QueryParam("timestamp") String timestamp,
          @QueryParam("encrypt_type") String encryptType,
          @QueryParam("nonce") String nonce,
          String body
               );
}
