package com.lancep.controller.game;

import com.lancep.model.War;
import com.lancep.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
import java.util.List;

@Component
public class WarResource {

    @Autowired
    GameService gameService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWarGames() {
        List<War> wars = gameService.getWarGames();
        return Response
                .ok(wars)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    @POST
    public Response createWarGame(@Context UriInfo uriInfo) {
        String id = gameService.createWarGame();
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(id);
        return Response
                .created(builder.build())
                .build();
    }

}
