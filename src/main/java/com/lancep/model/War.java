package com.lancep.model;

import com.lancep.utils.Card;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Deque;

@Document(collection = "games")
public class War {

    @Id private String id;
    private Deque<Card> player1DrawStack;
    private Deque<Card> player2DrawStack;

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

    public Deque<Card> getPlayer2DrawStack() {
        return player2DrawStack;
    }

    public void setPlayer2DrawStack(Deque<Card> player2DrawStack) {
        this.player2DrawStack = player2DrawStack;
    }
}
