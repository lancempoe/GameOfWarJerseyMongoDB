package com.lancep.controller;

import com.lancep.service.MarketService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("market")
public class MarketController {

    @Autowired
    MarketService marketService;

    @GET
    @Path("checkout")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getInvoiceTotal(@QueryParam("productList") String productList) {
        if (StringUtils.isEmpty(productList)) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        String invoiceTotal = marketService.getInvoiceTotal(productList);
        return Response.ok(invoiceTotal, MediaType.TEXT_PLAIN).build();
    }
}
