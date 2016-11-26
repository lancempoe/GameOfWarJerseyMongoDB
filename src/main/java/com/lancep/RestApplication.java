package com.lancep;

import com.lancep.controller.HealthResource;
import com.lancep.controller.game.GameResource;
import com.lancep.war.errorhandling.WarExceptionMapper;
import org.glassfish.jersey.server.ResourceConfig;

public class RestApplication extends ResourceConfig {

    public RestApplication() {
        // register application resources
        register(HealthResource.class);
        register(GameResource.class);

        // register application provider
        register(WarExceptionMapper.class);
    }
}
