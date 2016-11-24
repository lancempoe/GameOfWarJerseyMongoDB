package com.lancep.service;


import com.lancep.config.MongoDBConfig;
import com.lancep.errorhandling.WarException;
import com.lancep.factory.WarFactory;
import com.lancep.model.War;
import com.lancep.utils.Card;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Component
public class GameService {

    private static final Logger logger = Logger.getLogger( GameService.class.getName() );

    @Autowired
    private MongoDBConfig mongoConfig;

    public List<War> getWarGames() {
        try {
            MongoOperations mongoOperation = mongoConfig.mongoTemplate();
            return mongoOperation.findAll(War.class);
        } catch (Exception e) {
            throw new WarException(Response.Status.GATEWAY_TIMEOUT);
        }
    }

    public String createWarGame() {

        try {
            MongoOperations mongoOperation = mongoConfig.mongoTemplate();

            War war = WarFactory.createWar();
            mongoOperation.save(war);

            logger.info(String.format("New War Created: %s", war.getId()));
            return war.getId();

        } catch (Exception e) {
            throw new WarException(Response.Status.GATEWAY_TIMEOUT);
        }
    }
}
