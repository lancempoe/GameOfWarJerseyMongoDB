package com.lancep.war.client;

import com.lancep.war.orm.Card;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class WarResultsTest {

    WarResults subject = new WarResults();

    @Test
    public void canGetTotalMoveCount() {
        int totalMoveCount = 4;
        subject.setTotalMoveCount(totalMoveCount);
        assertThat(subject.getTotalMoveCount(), is(totalMoveCount));
    }

    @Test
    public void canGetPriorPlayer1Draw() {
        Card card = new Card(1,1);
        subject.addPlayer1DrawnCard(card);
        assertThat(subject.getPlayer1DrawnCards().getLast(), is(card));
    }

    @Test
    public void canGetPriorPlayer2Draw() {
        Card card = new Card(1,1);
        subject.addPlayer2DrawnCard(card);
        assertThat(subject.getPlayer2DrawnCards().getLast(), is(card));
    }

    @Test
    public void canGetPlayer1DeckSize() {
        int deckSize = 4;
        subject.setPlayer1DeckSize(deckSize);
        assertThat(subject.getPlayer1DeckSize(), is(deckSize));
    }

    @Test
    public void canGetPlayer2DeckSize() {
        int deckSize = 4;
        subject.setPlayer2DeckSize(deckSize);
        assertThat(subject.getPlayer2DeckSize(), is(deckSize));
    }

    @Test
    public void canGetId() {
        String id = "2345d";
        subject.setId(id);
        assertThat(subject.getId(), is(id));
    }

}