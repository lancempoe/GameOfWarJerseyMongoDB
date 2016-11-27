package com.lancep.war.client;

import com.lancep.war.orm.Card;

import java.util.ArrayDeque;

public class WarResults {

    private String id;
    private int totalMoveCount;
    private ArrayDeque<Card> player1DrawnCards = new ArrayDeque<>();
    private ArrayDeque<Card> player2DrawnCards = new ArrayDeque<>();
    private int player1DeckSize;
    private int player2DeckSize;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTotalMoveCount() {
        return totalMoveCount;
    }

    public void setTotalMoveCount(int totalMoveCount) {
        this.totalMoveCount = totalMoveCount;
    }

    public ArrayDeque<Card> getPlayer1DrawnCards() {
        return player1DrawnCards;
    }

    public ArrayDeque<Card> getPlayer2DrawnCards() {
        return player2DrawnCards;
    }

    public void addPlayer1DrawnCard(Card card) {
        addDrawnCard(card, player1DrawnCards);
    }

    public void addPlayer2DrawnCard(Card card) {
        addDrawnCard(card, player2DrawnCards);
    }

    private void addDrawnCard(Card card, ArrayDeque<Card> drawnCards) {
        if (card != null) {
            drawnCards.addLast(card);
        }
    }

    public int getPlayer1DeckSize() {
        return player1DeckSize;
    }

    public void setPlayer1DeckSize(int player1DeckSize) {
        this.player1DeckSize = player1DeckSize;
    }

    public int getPlayer2DeckSize() {
        return player2DeckSize;
    }

    public void setPlayer2DeckSize(int player2DeckSize) {
        this.player2DeckSize = player2DeckSize;
    }
}
