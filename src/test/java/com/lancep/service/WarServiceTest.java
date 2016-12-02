package com.lancep.service;

import com.lancep.controller.errorhandling.WarException;
import com.lancep.dao.WarDao;
import com.lancep.war.client.WarResults;
import com.lancep.war.factory.WarFactory;
import com.lancep.war.orm.War;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static javax.ws.rs.core.Response.Status.BAD_GATEWAY;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

public class WarServiceTest {

    @Tested private WarServiceImpl subject = new WarServiceImpl();
    @Injectable WarDao warDao;

    private static final int NUMBER_OF_SUITS = 1;
    private static final int NUMBER_OF_RANK = 1;
    private static final War WAR = WarFactory.createWar(1,1);
    private static final String ID = "123";
    private static final List<War> WARS = new ArrayList<>();

    @Test
    public void getWarGamesReturnsListOfGames() throws Exception {
        new Expectations() {{
            warDao.findAll(); result = WARS;
        }};
        assertThat(subject.getAll(), is(WARS));
    }

    @Test(expected = WarException.class)
    public void getWarGamesThrowsErrorWhenMongoError() throws Exception {
        new Expectations() {{
            warDao.findAll(); result = new WarException(BAD_GATEWAY);
        }};
        subject.getAll();
    }

    @Test
    public void willSaveWarToDb() throws Exception {
        new Expectations() {{
            warDao.save((War)any); result = ID;
        }};
        assertThat(subject.create(NUMBER_OF_SUITS, NUMBER_OF_RANK), is(ID));
    }

    @Test(expected = WarException.class)
    public void willSaveWarThrowsErrorWhenMongoError() throws Exception {
        new Expectations() {{
            warDao.save((War)any); result =new WarException(BAD_GATEWAY);
        }};
        subject.create(NUMBER_OF_SUITS,NUMBER_OF_RANK);
    }

    @Test
    public void getWarGameReturnsAGame() throws Exception {
        new Expectations() {{
            warDao.findById(ID); result = WAR;
        }};
        assertThat(subject.get(ID), is(WAR));
    }

    @Test(expected = WarException.class)
    public void getWarGameThrowsErrorWhenInvalidId() throws Exception {
        new Expectations() {{
            warDao.findById(ID); result = new WarException(BAD_GATEWAY);
        }};
        subject.get(ID);
    }

    @Test
    public void deleteWarGameCallsDelete() throws Exception {
        new Expectations() {{
            warDao.findById(ID); result = WAR;
            warDao.delete(WAR);
        }};

        subject.delete(ID);
    }

    @Test(expected = WarException.class)
    public void deleteWarGameThrowsErrorWhenInvalidId() throws Exception {
        new Expectations() {{
            warDao.findById(ID); result = new WarException(BAD_GATEWAY);
        }};
        subject.delete(ID);
    }

    @Test
    public void drawWillReturnWith1MoreMoveCount() {
        new Expectations() {{
            warDao.findById(ID); result = WAR;
            warDao.save(WAR);
        }};

        int initialCount = WAR.getTotalMoveCount();
        WarResults results = subject.draw(ID);

        assertThat(results.getTotalMoveCount(), is(initialCount + 1));
    }

    @Test(expected = WarException.class)
    public void drawHandlesInvalidId() {
        new Expectations() {{
            warDao.findById(ID); result = new WarException(BAD_GATEWAY);
            warDao.save(WAR); times = 0;
        }};
        subject.draw(ID);
    }

    @Test(expected = WarException.class)
    public void drawHandlesWhenSaveFails() throws Exception {
        new Expectations() {{
            warDao.findById(ID); result = WAR;
            warDao.save(WAR); result = new WarException(BAD_GATEWAY);
        }};
        subject.draw(ID);
    }

    @Test
    public void playWillReturnResults() {
        WarResults results = subject.play(1,1);
        assertThat(results.getTotalMoveCount(), is(greaterThan(0)));
    }

}