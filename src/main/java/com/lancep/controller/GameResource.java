package com.lancep.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Path;

@Component
@Path("game")
public class GameResource {

    @Autowired
    WarResource warResource;

    @Path("war")
    public WarResource getWarResource() {
        return warResource;
    }


}
