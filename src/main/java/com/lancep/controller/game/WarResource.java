package com.lancep.controller.game;

import com.lancep.model.War;
import com.lancep.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.*;
import java.util.List;

@Component
public class WarResource {

    @Autowired
    private GameService gameService;

    @Context
    private ResourceContext resourceContext;

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

    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWar(@PathParam("id") String id) {
        War war = gameService.getWarGame(id);
        return Response
                .ok(war)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }


    @Path("{id}")
    @DELETE
    public Response deleteWar(@PathParam("id") String id) {
        gameService.deleteWarGame(id);
        return Response
                .ok()
                .entity(String.format("Removed game of war: %s", id))
                .build();
    }

}
