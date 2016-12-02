package com.lancep.war.assembler;

import com.lancep.war.orm.WarDeck;
import com.lancep.war.orm.WarDeckImpl;
import com.lancep.war.orm.War;

public class WarAssembler {

    public static War createWar(int numberOfSuits, int numberOfRanks) {
        War war = new War();

        WarDeck player1Deck = new WarDeckImpl(numberOfSuits, numberOfRanks);
        WarDeck player2Deck = new WarDeckImpl(numberOfSuits, numberOfRanks);

        war.setPlayer1Deck(player1Deck);
        war.setPlayer2Deck(player2Deck);

        return war;
    }
}
