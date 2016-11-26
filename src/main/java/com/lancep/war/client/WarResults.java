package com.lancep.war.client;

import com.lancep.war.domain.Card;

public class WarResults {

    private int totalMoves;
    private Card player1DrawnCard;
    private Card player2DrawnCard;
    private int player1DeckSize;
    private int player2DeckSize;

    public int getTotalMoves() {
        return totalMoves;
    }

    public void setTotalMoves(int totalMoves) {
        this.totalMoves = totalMoves;
    }

    public Card getPlayer1DrawnCard() {
        return player1DrawnCard;
    }

    public void setPlayer1DrawnCard(Card player1DrawnCard) {
        this.player1DrawnCard = player1DrawnCard;
    }

    public Card getPlayer2DrawnCard() {
        return player2DrawnCard;
    }

    public void setPlayer2DrawnCard(Card player2DrawnCard) {
        this.player2DrawnCard = player2DrawnCard;
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
