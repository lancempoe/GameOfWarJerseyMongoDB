package com.lancep.controller.game;

import mockit.Injectable;
import mockit.Tested;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class GameResourceTest {

    @Tested GameResource subject;
    @Injectable WarResource warResource;

    @Test
    public void getWarResource() throws Exception {
        assertThat(subject.getWarResource(), is(warResource));
    }

}