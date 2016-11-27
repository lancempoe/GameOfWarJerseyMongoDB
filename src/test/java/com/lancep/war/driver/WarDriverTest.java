package com.lancep.war.driver;

import com.lancep.war.client.WarResults;
import org.junit.Test;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class WarDriverTest {

    private static final int NUMBER_OF_RANKS = 3;
    private static final int NUMBER_OF_SUITS = 3;
    private WarDriver subject = new WarDriver();

    @Test
    public void playWillHaveAMinimumMoveCount() throws Exception {
        WarResults results = subject.play(NUMBER_OF_SUITS, NUMBER_OF_RANKS);

        assertThat(results.getTotalMoveCount(), greaterThan(0));
    }
    
    @Test
    public void playWillResultIn1PlayerWithNoCardsLeft() {
        WarResults results = subject.play(NUMBER_OF_SUITS, NUMBER_OF_RANKS);

        int player1DeckSize = results.getPlayer1DeckSize();
        int player2DeckSize = results.getPlayer2DeckSize();
        boolean onePlayerHasNoCardsLeft = player1DeckSize == 0 || player2DeckSize == 0;

        assertThat(onePlayerHasNoCardsLeft, is(true));
    }

    @Test
    public void afterPlayAllCardsWillBeAccountedFor() {
        int startingNumberOfCards = NUMBER_OF_SUITS * NUMBER_OF_RANKS * 2;
        
        WarResults results = subject.play(NUMBER_OF_SUITS, NUMBER_OF_RANKS);

        int player1DeckSize = results.getPlayer1DeckSize();
        int player2DeckSize = results.getPlayer2DeckSize();
        int endingNumberOfCards = player1DeckSize + player2DeckSize;

        boolean gameWonOrEndsInTie =
                endingNumberOfCards == startingNumberOfCards ||
                endingNumberOfCards == 0;

        assertThat(gameWonOrEndsInTie, is(true));
    }
    

}