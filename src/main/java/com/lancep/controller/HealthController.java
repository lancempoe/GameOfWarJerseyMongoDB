package com.lancep.controller;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("health")
public class HealthController {

    @GET
    public Response amIAwake() {
        return Response.ok("API is up and running!", MediaType.TEXT_PLAIN).build();
    }

}
