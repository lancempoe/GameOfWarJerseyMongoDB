package com.lancep.war.driver;

import com.lancep.war.client.WarResults;
import com.lancep.war.domain.Card;
import com.lancep.war.domain.WarDeck;
import com.lancep.war.domain.WarDeckImpl;
import com.lancep.war.orm.War;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
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
    
    @Test
    public void drawWillAddCardsToWinnersDeck_Player1() {
        War war = new War();
        WarDeck player1WarDeck = getWarDeck(1);
        WarDeck player2WarDeck = getWarDeck(2);
        war.setPlayer1Deck(player1WarDeck);
        war.setPlayer2Deck(player2WarDeck);

        WarResults results = subject.draw(war);

        int totalCardsInGame = player1WarDeck.getSize() + player2WarDeck.getSize();
        assertThat(results.getPlayer1DeckSize(), is(totalCardsInGame));
    }

    @Test
    public void drawWillAddCardsToWinnersDeck_Player2() {
        War war = new War();
        WarDeck player1WarDeck = getWarDeck(2);
        WarDeck player2WarDeck = getWarDeck(1);
        war.setPlayer1Deck(player1WarDeck);
        war.setPlayer2Deck(player2WarDeck);

        WarResults results = subject.draw(war);

        int totalCardsInGame = player1WarDeck.getSize() + player2WarDeck.getSize();
        assertThat(results.getPlayer2DeckSize(), is(totalCardsInGame));
    }

    @Test
    public void drawWillRemoveCardsFromLosersDeck() {
        War war = new War();
        WarDeck player1WarDeck = getWarDeck(1);
        WarDeck player2WarDeck = getWarDeck(2);
        war.setPlayer1Deck(player1WarDeck);
        war.setPlayer2Deck(player2WarDeck);

        WarResults results = subject.draw(war);

        assertThat(results.getPlayer2DeckSize(), is(0));
    }

    @Test
    public void drawWillIncrementTheMoveCount() {
        War war = new War();
        WarDeck player1WarDeck = getWarDeck(1);
        WarDeck player2WarDeck = getWarDeck(2);
        war.setPlayer1Deck(player1WarDeck);
        war.setPlayer2Deck(player2WarDeck);
        int totalMoveCount = 3;
        war.setTotalMoveCount(totalMoveCount);


        WarResults results = subject.draw(war);

        assertThat(results.getTotalMoveCount(), is(totalMoveCount+1));
    }

    @Test
    public void drawHandlesInvalidWar() {
        assertThat(subject.draw(null), is(nullValue()));
    }

    @Test
    public void drawHandlesAlreadyCompletedGames() {
        int priorMoveCount = 1;
        War war = new War();
        WarDeck player1WarDeck = getWarDeck(1, 2);
        WarDeck player2WarDeck = getWarDeck();
        war.setPlayer1Deck(player1WarDeck);
        war.setPlayer2Deck(player2WarDeck);
        war.setTotalMoveCount(priorMoveCount);

        WarResults results = subject.draw(war);

        assertThat(results.getTotalMoveCount(), is(priorMoveCount));
    }
    
    @Test
    public void drawHandlesTie() {
        int priorMoveCount = 1;
        War war = new War();
        int[] cardRanks = {1, 2, 3, 4, 5};
        WarDeck player1WarDeck = getWarDeck(cardRanks);
        WarDeck player2WarDeck = getWarDeck(cardRanks);
        war.setPlayer1Deck(player1WarDeck);
        war.setPlayer2Deck(player2WarDeck);
        war.setTotalMoveCount(priorMoveCount);

        WarResults results = subject.draw(war);

        assertThat(results.getPlayer1DeckSize(), is(0));
        assertThat(results.getPlayer2DeckSize(), is(0));
    }

    @Test
    public void drawTotalMoveCountExcludesWars() {
        int priorMoveCount = 1;
        War war = new War();
        int[] cardRanks = {1, 2, 3, 4, 5};
        WarDeck player1WarDeck = getWarDeck(cardRanks);
        WarDeck player2WarDeck = getWarDeck(cardRanks);
        war.setPlayer1Deck(player1WarDeck);
        war.setPlayer2Deck(player2WarDeck);
        war.setTotalMoveCount(priorMoveCount);

        WarResults results = subject.draw(war);

        assertThat(results.getTotalMoveCount(), is(2));
    }

    private WarDeck getWarDeck(int ... ranks) {
        WarDeck warDeck = new WarDeckImpl(0,0);
        List<Card> cards = new ArrayList<>();
        for(int rank: ranks) {
            cards.add(new Card(rank,rank));
        }
        warDeck.addCards(cards);
        return warDeck;
    }


}