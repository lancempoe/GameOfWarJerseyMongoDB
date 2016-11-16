package com.lancep.api;

import com.lancep.service.MarketService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Component
@Path("market")
public class MarketResource extends Resouce {

    @Autowired
    MarketService marketService;

    @Path("checkout")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getInvoiceTotal(@QueryParam("productList") String productList) {
        if (StringUtils.isEmpty(productList)) {
            throwBadRequest("productList parameter is mandatory");
        }
        return marketService.getInvoiceTotal(productList);
    }
}
