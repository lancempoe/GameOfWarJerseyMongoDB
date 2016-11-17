package com.lancep;

import com.lancep.api.CSVResource;
import com.lancep.api.MarketResource;
import org.glassfish.jersey.server.ResourceConfig;

public class RestApplication extends ResourceConfig {

    public RestApplication() {
        // register application resources
        register(MarketResource.class);
        register(CSVResource.class);
    }
}
