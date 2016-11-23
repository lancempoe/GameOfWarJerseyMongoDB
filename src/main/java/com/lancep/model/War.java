package com.lancep.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "games")
public class War {

    @Id
    private String id;

    private List<String> player1Cards;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getPlayer1Cards() {
        return player1Cards;
    }

    public void setPlayer1Cards(List<String> player1Cards) {
        this.player1Cards = player1Cards;
    }
}
