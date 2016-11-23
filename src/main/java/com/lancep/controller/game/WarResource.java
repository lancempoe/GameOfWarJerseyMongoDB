package com.lancep.controller.game;

import com.lancep.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.POST;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

@Component
public class WarResource {

    @Autowired
    GameService gameService;

    @POST
    public Response createWarGame(@Context UriInfo uriInfo) {
        String id = gameService.createWarGame();
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(id);
        return  Response.created(builder.build()).build();
    }

}
