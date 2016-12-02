package com.lancep.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Path;

@Controller
@Path("game")
public class GameResource {

    private WarResource warResource;

    @Path("war")
    public WarResource getWarResource() {
        return warResource;
    }

    @Autowired
    public void setWarResource(WarResource warResource) {
        this.warResource = warResource;
    }
}
