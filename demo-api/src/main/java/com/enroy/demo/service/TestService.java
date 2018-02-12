package com.enroy.demo.service;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.enroy.demo.test.service.Test;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
}
