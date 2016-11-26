package com.lancep.war.factory;

import com.lancep.war.orm.War;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class WarFactoryTest {


    public static final int NUMBER_OF_SUITS = 7;
    public static final int NUMBER_OF_RANKS = 9;

    @Test
    public void willCreatePlayer1DequesWith52Cards() {
        assertThat(WarFactory.createWar(NUMBER_OF_SUITS, NUMBER_OF_RANKS).getPlayer1Deck().getSize(),
                is(NUMBER_OF_SUITS*NUMBER_OF_RANKS));
    }

    @Test
    public void willCreatePlayer2DequesWith52Cards() {
        assertThat(WarFactory.createWar(NUMBER_OF_SUITS, NUMBER_OF_RANKS).getPlayer2Deck().getSize(),
                is(NUMBER_OF_SUITS*NUMBER_OF_RANKS));
    }

    /**
     * This may fail proof but will fail at a rate of:
     * 1/160,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000
     */
    @Test
    public void willCreate2DifferentDequesOfCards() {
        War war = WarFactory.createWar(NUMBER_OF_SUITS, NUMBER_OF_RANKS);
        assertThat(war.getPlayer1Deck(), is(not(war.getPlayer2Deck())));
    }

}