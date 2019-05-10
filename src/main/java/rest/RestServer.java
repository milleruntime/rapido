package rest;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;


@Path("/users")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class RestServer extends HttpServlet {

  @GET
  public Response getUser() {
    return Response.status(200).entity("getUser is called").build();
  }


  /*@GET
  public ServletHolder getRestServlet() {
    final ResourceConfig rc = new ResourceConfig().packages("rapido.rest")
            .register(new LoggingFeature(java.util.logging.Logger.getLogger(this.getClass().getName())))
            .register(JacksonFeature.class);
    return new ServletHolder(new ServletContainer(rc));
  }*/
}