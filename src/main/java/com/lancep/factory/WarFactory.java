package com.lancep.factory;

import com.lancep.model.War;
import com.lancep.utils.Card;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class WarFactory {

    public static War createWar() {
        War war = new War();

        List<Card> cards = Card.valuesAsList();

        war.setPlayer1DrawStack(getShuffledDeck(cards));
        war.setPlayer2DrawStack(getShuffledDeck(cards));

        return war;
    }

    private static Deque<Card> getShuffledDeck(List<Card> cards) {
        Deque<Card> drawStack = new ArrayDeque<>(cards.size());

        Collections.shuffle(cards);
        cards.stream().forEach(card -> drawStack.addLast(card));

        return drawStack;
    }
}
