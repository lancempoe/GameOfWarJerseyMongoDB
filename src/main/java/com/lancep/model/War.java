package com.lancep.model;

import com.lancep.config.Collection;
import com.lancep.utils.Card;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayDeque;

@Document(collection = Collection.WAR)
public class War {

    @Id private String id;
    private ArrayDeque<Card> player1DrawStack;
    private ArrayDeque<Card> player2DrawStack;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayDeque<Card> getPlayer1DrawStack() {
        return player1DrawStack;
    }

    public void setPlayer1DrawStack(ArrayDeque<Card> player1DrawStack) {
        this.player1DrawStack = player1DrawStack;
    }

    public ArrayDeque<Card> getPlayer2DrawStack() {
        return player2DrawStack;
    }

    public void setPlayer2DrawStack(ArrayDeque<Card> player2DrawStack) {
        this.player2DrawStack = player2DrawStack;
    }
}
