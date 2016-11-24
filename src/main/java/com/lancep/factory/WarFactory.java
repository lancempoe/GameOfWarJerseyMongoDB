package com.lancep.factory;

import com.lancep.model.Card;
import com.lancep.model.War;
import com.lancep.utils.CardNumber;
import com.lancep.utils.CardSuit;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class WarFactory {

    public static War createWar() {
        War war = new War();

        List<CardNumber> numbers = CardNumber.valuesAsList();
        List<CardSuit> suits = CardSuit.valuesAsList();

        war.setPlayer1DrawStack(getShuffledDeck(numbers, suits));
        war.setPlayer2DrawStack(getShuffledDeck(numbers, suits));

        return war;
    }

    private static Deque<Card> getShuffledDeck(List<CardNumber> numbers, List<CardSuit> suits) {
        Deque<Card> drawStack = new ArrayDeque<>();
        Collections.shuffle(numbers);
        Collections.shuffle(suits);
        suits.stream().forEach(suit -> numbers.stream().forEach(number -> {
            Card card = new Card(suit, number);
            drawStack.addLast(card);
        }));
        return drawStack;
    }
}
