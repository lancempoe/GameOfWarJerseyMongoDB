package com.lancep.war.factory;

import com.lancep.war.domain.WarDeck;
import com.lancep.war.domain.WarDeckImpl;
import com.lancep.war.orm.War;

public class WarFactory {

    public static War createWar(int numberOfSuits, int numberOfRanks) {
        War war = new War();

        WarDeck player1Deck = new WarDeckImpl(numberOfSuits, numberOfRanks);
        WarDeck player2Deck = new WarDeckImpl(numberOfSuits, numberOfRanks);

        war.setPlayer1Deck(player1Deck);
        war.setPlayer2Deck(player2Deck);

        return war;
    }
}
