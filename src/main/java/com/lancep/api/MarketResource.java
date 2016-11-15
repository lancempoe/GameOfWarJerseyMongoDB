package com.lancep.api;

import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/checkout")
public class MarketResource extends Resouce {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getBill(@QueryParam("productList") String productList) {
        if (StringUtils.isEmpty(productList)) {
            throwBadRequest("name parameter is mandatory");
        }
        return "Hello world!" + productList;
    }
}
