package com.lancep.war.orm;

import com.lancep.war.domain.WarDeck;
import com.lancep.war.domain.WarDeckImpl;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class WarTest {

    War subject = new War();

    @Test
    public void getId() throws Exception {
        String id = "123rthgfd";
        subject.setId(id);
        assertThat(subject.getId(), is(id));
    }

    @Test
    public void getPlayer1DrawStack() throws Exception {
        WarDeck deck = new WarDeckImpl(0,0);
        subject.setPlayer1Deck(deck);
        assertThat(subject.getPlayer1Deck(), is(deck));
    }

    @Test
    public void getPlayer2DrawStack() throws Exception {
        WarDeck deck = new WarDeckImpl(0,0);
        subject.setPlayer2Deck(deck);
        assertThat(subject.getPlayer2Deck(), is(deck));
    }

}