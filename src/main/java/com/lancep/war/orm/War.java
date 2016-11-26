package com.lancep.war.orm;

import com.lancep.config.Collection;
import com.lancep.war.domain.WarDeck;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = Collection.WAR)
public class War {

    @Id private String id;
    private WarDeck player1Deck;
    private WarDeck player2Deck;
    private int moves = 0;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public WarDeck getPlayer1Deck() {
        return player1Deck;
    }

    public void setPlayer1Deck(WarDeck player1Deck) {
        this.player1Deck = player1Deck;
    }

    public WarDeck getPlayer2Deck() {
        return player2Deck;
    }

    public void setPlayer2Deck(WarDeck player2Deck) {
        this.player2Deck = player2Deck;
    }

    public int getMoves() {
        return moves;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }
}
