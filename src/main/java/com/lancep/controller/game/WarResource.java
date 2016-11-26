package com.lancep.controller.game;

import com.lancep.war.orm.War;
import com.lancep.service.WarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Component
public class WarResource {

    @Autowired private WarService warService;

    private static final int NUMBER_OF_RANK = 13;
    private static final int NUMBER_OF_SUITS = 4;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWarGames() {
        List<War> wars = warService.getWarGames();
        return Response
                .ok(wars)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    @POST
    public Response createWarGame(@Context UriInfo uriInfo) {
        String id = warService.createWarGame(NUMBER_OF_SUITS, NUMBER_OF_RANK);
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
        War war = warService.getWarGame(id);
        return Response
                .ok(war)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }


    @Path("{id}")
    @DELETE
    public Response deleteWar(@PathParam("id") String id) {
        warService.deleteWarGame(id);
        return Response
                .ok()
                .entity(String.format("Removed game of war: %s", id))
                .build();
    }

}
