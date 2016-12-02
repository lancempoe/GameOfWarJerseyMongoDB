package com.lancep.dao;

import com.lancep.controller.errorhandling.WarException;
import com.lancep.war.assembler.WarAssembler;
import com.lancep.war.orm.War;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class WarDaoImplTest {

    @Tested WarDaoImpl subject;
    @Injectable MongoTemplate template;

    private static final War WAR = WarAssembler.createWar(1,1);
    private static final String ID = "123";
    private static final List<War> WARS = new ArrayList<>();

    @Test
    public void findAllReturnsListOfGames() throws Exception {
        new Expectations() {{
            template.findAll(War.class); result = WARS;
        }};
        subject.findAll();
    }

    @Test(expected = WarException.class)
    public void findAllHandlesDbErrors() {
        new Expectations() {{
            template.findAll(War.class); result = new Exception();
        }};
        subject.findAll();
    }

    @Test
    public void canSave() throws Exception {
        new Expectations() {{
            template.save(WAR); times = 1;
        }};
        subject.save(WAR);
    }

    @Test(expected = WarException.class)
    public void saveHandlesDbErrors() {
        new Expectations() {{
            template.save(WAR); result = new Exception();
        }};
        subject.save(WAR);
    }

    @Test
    public void findByIdReturnsListOfGames() throws Exception {
        new Expectations() {{
            template.findById(ID, War.class); result = WAR;
        }};
        assertThat(subject.findById(ID), is(WAR));
    }

    @Test(expected = WarException.class)
    public void findByIdHandlesDbErrors() {
        new Expectations() {{
            template.findById(ID, War.class); result = new Exception();
        }};
        subject.findById(ID);
    }

    @Test
    public void canDelete() throws Exception {
        new Expectations() {{
            template.remove(WAR); times = 1;
        }};
        subject.delete(WAR);
    }

    @Test(expected = WarException.class)
    public void deleteHandlesDbErrors() {
        new Expectations() {{
            template.remove(WAR); result = new Exception();
        }};
        subject.delete(WAR);
    }
}