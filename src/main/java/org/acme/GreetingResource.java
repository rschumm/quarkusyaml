package org.acme;

import java.util.List;

import com.fasterxml.jackson.jakarta.rs.yaml.YAMLMediaTypes;
import org.jboss.logging.Logger;
import java.util.stream.Collectors;

import org.jboss.logging.Logger;

import com.fasterxml.jackson.jakarta.rs.yaml.YAMLMediaTypes;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/hello")
public class GreetingResource {

    @Inject
    Logger log; 


     //andere mime-type: public static final String MEDIA_TEXT_X_YAML = "application/x-yaml"
    //mit enc: public static final String MEDIA_TEXT_X_YAML = "application/yaml;charset=utf-8"
    //public static final String MEDIA_TEXT_X_YAML = "application/yaml;charset=utf-8";

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello RESTEasy";
    }


    @POST
    @Consumes(YAMLMediaTypes.APPLICATION_JACKSON_YAML)
    @Produces(MediaType.TEXT_PLAIN)
    //@Transactional
    public Response upload(List<Tag> tage)  {
        System.out.println("TAGS " + tage);
         if (tage == null){
               String message = String.format("%semtpy File? %n", "");
               return Response.status(Response.Status.BAD_REQUEST).entity(message).build();
         }


         String response = tage.stream().map(t -> t.getName()).collect(Collectors.joining(",")); 

         log.info(response);
         return Response.ok(response).build();
    }
}
