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

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GameServiceTest {

    @Tested GameService subject;
    @Injectable MongoDBConfig mongoDBConfig;
    @Injectable MongoTemplate mongoTemplate;

    @Test
    public void getWarGamesReturnsListOfGames() throws Exception {
        List<War> wars = new ArrayList<>();
        new Expectations() {{
            mongoDBConfig.mongoTemplate(); result = mongoTemplate;
            mongoTemplate.findAll(War.class); result = wars;
        }};
        assertThat(subject.getWarGames(), is(wars));
    }

    @Test(expected = WarException.class)
    public void getWarGamesThrowsErrorWhenMongoError() throws Exception {
        new Expectations() {{
            mongoDBConfig.mongoTemplate(); result = new Exception();
        }};
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

    @Test(expected = WarException.class)
    public void willSaveWarThrowsErrorWhenMongoError() throws Exception {
        new Expectations() {{
            mongoDBConfig.mongoTemplate(); result = new Exception();
        }};
        subject.createWarGame();
    }

}