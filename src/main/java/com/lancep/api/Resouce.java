package com.lancep.api;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.net.HttpURLConnection;

public abstract class Resouce {

    protected void throwBadRequest(String errorMessage) {
        throw new WebApplicationException(
                Response.status(HttpURLConnection.HTTP_BAD_REQUEST)
                        .entity(errorMessage)
                        .build()
        );
    }
}
