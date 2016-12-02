package com.lancep.service;

import com.lancep.config.MongoDBConfig;
import com.lancep.war.client.WarResults;
import com.lancep.controller.errorhandling.WarException;
import com.lancep.war.factory.WarFactory;
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

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class WarServiceTest {
    public static final int NUMBER_OF_SUITS = 1;
    public static final int NUMBER_OF_RANK = 1;

    @Tested WarServiceImpl subject;
    @Injectable MongoDBConfig mongoDBConfig;
    @Injectable MongoTemplate mongoOperation;

    public static final War WAR = WarFactory.createWar(1,1);
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
        subject.delete(ID);
        new Verifications() {{
            mongoOperation.remove(withAny(War.class));
        }};
    }

    @Test(expected = WarException.class)
    public void deleteWarGameThrowsErrorWhenInvalidId() throws Exception {
        new Expectations() {{
            mongoOperation.findById(ID, War.class); result = null;
        }};
        subject.delete(ID);
    }

    @Test(expected = WarException.class)
    public void deleteWarGameThrowsErrorWhenMongoError() throws Exception {
        new Expectations() {{
            mongoDBConfig.mongoTemplate(); result = new Exception();
        }};
        String id = "1";
        subject.delete(id);
    }

    @Test
    public void getWarGameReturnsAGame() throws Exception {
        assertThat(subject.get(ID), is(WAR));
    }
    
    @Test
    public void drawWillReturnWith1MoreMoveCount() {
        int initialCount = WAR.getTotalMoveCount();
        WarResults results = subject.draw(ID);
        assertThat(results.getTotalMoveCount(), is(initialCount + 1));
    }

    @Test(expected = WarException.class)
    public void drawHandlesInvalidId() {
        new Expectations() {{
            mongoOperation.findById(ID, War.class); result = null;
        }};
        subject.draw(ID);
    }

    @Test(expected = WarException.class)
    public void drawThrowsErrorWhenMongoError() throws Exception {
        new Expectations() {{
            mongoDBConfig.mongoTemplate(); result = new Exception();
        }};
        subject.draw(ID);
    }

    @Test
    public void playWillReturnResults() {
        WarResults results = subject.play(1,1);
        assertThat(results.getTotalMoveCount(), is(greaterThan(0)));
    }

    @Test(expected = WarException.class)
    public void getWarGameThrowsErrorWhenMongoError() throws Exception {
        new Expectations() {{
            mongoDBConfig.mongoTemplate(); result = new Exception();
        }};
        subject.get(ID);
    }

    @Test(expected = WarException.class)
    public void getWarGameThrowsErrorWhenInvalidId() throws Exception {
        new Expectations() {{
            mongoOperation.findById(ID, War.class); result = null;
        }};
        subject.get(ID);
    }

    @Test
    public void getWarGamesReturnsListOfGames() throws Exception {
        assertThat(subject.getAll(), is(WARS));
    }

    @Test(expected = WarException.class)
    public void getWarGamesThrowsErrorWhenMongoError() throws Exception {
        new Expectations() {{
            mongoDBConfig.mongoTemplate(); result = new Exception();
        }};
        subject.getAll();
    }

    @Test
    public void willSaveWarToDb() throws Exception {
        subject.create(NUMBER_OF_SUITS, NUMBER_OF_RANK);

        new Verifications() {{
            mongoOperation.save(withAny(War.class));
        }};
    }

    @Test(expected = WarException.class)
    public void willSaveWarThrowsErrorWhenMongoError() throws Exception {
        new Expectations() {{
            mongoDBConfig.mongoTemplate(); result = new Exception();
        }};
        subject.create(NUMBER_OF_SUITS,NUMBER_OF_RANK);
    }

}