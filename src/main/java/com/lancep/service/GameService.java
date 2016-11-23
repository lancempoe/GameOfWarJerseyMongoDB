package com.lancep.service;


import com.lancep.config.MongoDBConfig;
import com.lancep.factory.WarFactory;
import com.lancep.model.War;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@Component
public class GameService {

    private static final Logger logger = Logger.getLogger( GameService.class.getName() );

    @Autowired
    private MongoDBConfig mongoConfig;

    public String createWarGame() {

        try {
            MongoOperations mongoOperation = mongoConfig.mongoTemplate();

            War war = WarFactory.createWar();

            mongoOperation.save(war);

            logger.info(String.format("New War Created: %s", war.getId()));
            return war.getId();

        } catch (Exception e) {
            throw new WebApplicationException(Response.Status.GATEWAY_TIMEOUT);
        }

    }
}
