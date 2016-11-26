package com.lancep.service;

import com.lancep.config.MongoDBConfig;
import com.lancep.war.errorhandling.WarException;
import com.lancep.war.orm.War;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.Verifications;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class WarServiceTest {
    public static final int NUMBER_OF_SUITS = 1;
    public static final int NUMBER_OF_RANK = 1;

    @Tested WarService subject;
    @Injectable MongoDBConfig mongoDBConfig;
    @Injectable MongoTemplate mongoOperation;

    public static final War WAR = new War();
    public static final String ID = "123";
    public static final List<War> WARS = new ArrayList<>();

    @Before
    public void init() throws Exception {
        new Expectations() {{
            mongoDBConfig.mongoTemplate(); result = mongoOperation; minTimes = 0;
            mongoOperation.findById(ID, War.class); result = WAR; minTimes = 0;
            mongoOperation.findAll(War.class); result = WARS; minTimes = 0;
        }};
    }

    @Test
    public void deleteWarGameCallsDelete() throws Exception {
        subject.deleteWarGame(ID);
        new Verifications() {{
            mongoOperation.remove(withAny(War.class));
        }};
    }

    @Test(expected = WarException.class)
    public void deleteWarGameThrowsErrorWhenInvalidId() throws Exception {
        new Expectations() {{
            mongoOperation.findById(ID, War.class); result = null;
        }};
        subject.deleteWarGame(ID);
    }

    @Test(expected = WarException.class)
    public void deleteWarGameThrowsErrorWhenMongoError() throws Exception {
        new Expectations() {{
            mongoDBConfig.mongoTemplate(); result = new Exception();
        }};
        String id = "1";
        subject.deleteWarGame(id);
    }

    @Test
    public void getWarGameReturnsAGame() throws Exception {
        assertThat(subject.getWarGame(ID), is(WAR));
    }

    @Test(expected = WarException.class)
    public void getWarGameThrowsErrorWhenMongoError() throws Exception {
        new Expectations() {{
            mongoDBConfig.mongoTemplate(); result = new Exception();
        }};
        subject.getWarGame(ID);
    }

    @Test(expected = WarException.class)
    public void getWarGameThrowsErrorWhenInvalidId() throws Exception {
        new Expectations() {{
            mongoOperation.findById(ID, War.class); result = null;
        }};
        subject.getWarGame(ID);
    }

    @Test
    public void getWarGamesReturnsListOfGames() throws Exception {
        assertThat(subject.getWarGames(), is(WARS));
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
        subject.createWarGame(NUMBER_OF_SUITS, NUMBER_OF_RANK);

        new Verifications() {{
            mongoOperation.save(withAny(War.class));
        }};
    }

    @Test(expected = WarException.class)
    public void willSaveWarThrowsErrorWhenMongoError() throws Exception {
        new Expectations() {{
            mongoDBConfig.mongoTemplate(); result = new Exception();
        }};
        subject.createWarGame(NUMBER_OF_SUITS,NUMBER_OF_RANK);
    }

}