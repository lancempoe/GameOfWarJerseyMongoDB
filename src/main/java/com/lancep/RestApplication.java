package com.lancep;

import com.lancep.api.MarketResource;
import org.glassfish.jersey.server.ResourceConfig;

public class RestApplication extends ResourceConfig {
    /**
     * Register JAX-RS application components.
     */
    public RestApplication() {
        // register application resources
        register(MarketResource.class);
    }
}
