package com.lancep.factory;

import com.lancep.model.War;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class WarFactoryTest {


    @Test
    public void willCreatePlayer1DequesWith52Cards() {
        assertThat(WarFactory.createWar().getPlayer1DrawStack().size(), is(52));
    }

    @Test
    public void willCreatePlayer2DequesWith52Cards() {
        assertThat(WarFactory.createWar().getPlayer2DrawStack().size(), is(52));
    }

    /**
     * This may fail proof but will fail at a rate of:
     * 1/160,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000
     */
    @Test
    public void willCreate2DifferentDequesOfCards() {
        War war = WarFactory.createWar();
        assertThat(war.getPlayer1DrawStack(), is(not(war.getPlayer2DrawStack())));
    }

}