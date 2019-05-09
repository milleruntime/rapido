package com.zetcode.web;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.mvc.Template;

@Path("/hello")
@Produces(MediaType.TEXT_HTML)
public class WebRoot {

  // The Java method will process HTTP GET requests
  @GET
  // The Java method will produce content identified by the MIME Media
  // type "text/plain"
  @Produces("text/plain")
  public String getClichedMessage() {
    // Return some cliched textual content
    return "Hello World";
  }

  /**
   * Returns the overview template
   *
   * @return Overview model
   */
  @GET
  @Template(name = "/home.ftl")
  public Map<String,Object> get() {

    Map<String,Object> model = getModel();
    model.put("title", "Accumulo Overview");
    model.put("template", "home.ftl");
    //model.put("js", "overview.js");

    return model;
  }

  private Map<String,Object> getModel() {

    Map<String,Object> model = new HashMap<>();
    //model.put("version", Constants.VERSION);
    //model.put("instance_name", monitor.getContext().getInstanceName());
   // model.put("instance_id", monitor.getContext().getInstanceID());
    //addExternalResources(model);
    return model;
  }

}
