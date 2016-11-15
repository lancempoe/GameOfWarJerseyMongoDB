package com.lancep.api;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.HttpURLConnection;

@Path("/checkout")
public class MarketResource {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getBill(@QueryParam("productList") String productList) {
        if (productList == null) {
            throw new WebApplicationException(
                Response.status(HttpURLConnection.HTTP_BAD_REQUEST)
                        .entity("name parameter is mandatory")
                        .build()
            );
        }
        return "Hello world!" + productList;
    }
}
