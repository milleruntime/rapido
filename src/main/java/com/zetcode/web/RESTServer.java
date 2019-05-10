package com.zetcode.web;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.mvc.Template;

@Path("/test")
@Produces(MediaType.TEXT_HTML)
public class RESTServer {

  /**
   * Returns the overview template
   *
   * @return Overview model
   */
  @GET
  @Template(name = "/test.ftl")
  public Map<String,Object> get() {

    Map<String,Object> model = new HashMap<>();
    //model.put("title", "Welcome Page");
    model.put("msg", "Home is where the heart is");

    return model;
  }

}