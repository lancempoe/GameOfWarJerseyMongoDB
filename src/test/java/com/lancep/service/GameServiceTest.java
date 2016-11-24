package com.lancep.service;

import com.lancep.config.MongoDBConfig;
import com.lancep.errorhandling.WarException;
import com.lancep.model.War;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.Verifications;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoTemplate;

public class GameServiceTest {

    @Tested GameService subject;
    @Injectable MongoDBConfig mongoDBConfig;
    @Injectable MongoTemplate mongoTemplate;

    @Test(expected = WarException.class)
    public void getWarGamesIsNotYetBuilt() throws Exception {
        subject.getWarGames();
    }

    @Test
    public void willSaveWarToDb() throws Exception {
        new Expectations() {{
            mongoDBConfig.mongoTemplate(); result = mongoTemplate;
        }};

        subject.createWarGame();

        new Verifications() {{
            mongoTemplate.save(withAny(War.class));
        }};
    }

}