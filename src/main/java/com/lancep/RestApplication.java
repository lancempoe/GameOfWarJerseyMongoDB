package com.lancep;

import com.lancep.controller.HealthController;
import com.lancep.controller.MarketController;
import org.glassfish.jersey.server.ResourceConfig;

public class RestApplication extends ResourceConfig {

    public RestApplication() {
        // register application resources
        register(MarketController.class);
        register(HealthController.class);
    }
}
