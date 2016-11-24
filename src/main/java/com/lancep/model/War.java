package com.lancep.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Deque;
import java.util.List;

@Document(collection = "games")
public class War {

    @Id private String id;
    private Deque<Card> player1DrawStack;
    private List<Card> player1DiscardPile;
    private Deque<Card> player2DrawStack;
    private List<Card> player2DiscardPile;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Deque<Card> getPlayer1DrawStack() {
        return player1DrawStack;
    }

    public void setPlayer1DrawStack(Deque<Card> player1DrawStack) {
        this.player1DrawStack = player1DrawStack;
    }

    public List<Card> getPlayer1DiscardPile() {
        return player1DiscardPile;
    }

    public void setPlayer1DiscardPile(List<Card> player1DiscardPile) {
        this.player1DiscardPile = player1DiscardPile;
    }

    public Deque<Card> getPlayer2DrawStack() {
        return player2DrawStack;
    }

    public void setPlayer2DrawStack(Deque<Card> player2DrawStack) {
        this.player2DrawStack = player2DrawStack;
    }

    public List<Card> getPlayer2DiscardPile() {
        return player2DiscardPile;
    }

    public void setPlayer2DiscardPile(List<Card> player2DiscardPile) {
        this.player2DiscardPile = player2DiscardPile;
    }
}
