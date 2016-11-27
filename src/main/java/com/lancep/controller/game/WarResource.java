package com.lancep.controller.game;

import com.lancep.service.WarService;
import com.lancep.war.client.WarResults;
import com.lancep.war.orm.War;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

import static com.lancep.controller.validation.WarValidations.hasValidPlayParams;

@Component
public class WarResource {

    @Autowired private WarService warService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWarGames() {
        List<War> wars = warService.getAll();
        return Response
                .ok(wars)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    @POST
    public Response createWarGame(@Context UriInfo uriInfo,
                                  @QueryParam("numberOfSuits") int numberOfSuits,
                                  @QueryParam("numberOfRanks") int numberOfRanks) {
        hasValidPlayParams(numberOfSuits, numberOfRanks);
        String id = warService.create(numberOfSuits, numberOfRanks);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(id);
        return Response
                .created(builder.build())
                .build();
    }

    @Path("play")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response quickGame(
            @QueryParam("numberOfSuits") int numberOfSuits,
            @QueryParam("numberOfRanks") int numberOfRanks) {
        hasValidPlayParams(numberOfSuits, numberOfRanks);
        WarResults warResults = warService.play(numberOfSuits, numberOfRanks);
        return Response
                .ok(warResults)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWar(@PathParam("id") String id) {
        War war = warService.get(id);
        return Response
                .ok(war)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteWar(@PathParam("id") String id) {
        warService.delete(id);
        return Response
                .ok()
                .entity(String.format("Removed game of war: %s", id))
                .build();
    }

    @Path("{id}/draw")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response draw(@PathParam("id") String id) {
        WarResults warResults = warService.draw(id);
        return Response
                .ok(warResults)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
