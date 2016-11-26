package com.lancep.war.domain;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class WarDeckImpl implements WarDeck {

    private  ArrayDeque<Card> drawStack;

    public WarDeckImpl(int numberOfSuits, int numberOfRanks) {
        create(numberOfSuits, numberOfRanks);
        shuffle();
    }

    @Override
    public void create(int numberOfSuits, int numberOfRanks) {
        int numberOfCards = numberOfRanks * numberOfRanks;
        drawStack = new ArrayDeque<>(numberOfCards);
        IntStream.range(0,numberOfSuits).forEach(suit -> IntStream.range(0,numberOfRanks).forEach(rank -> {
            com.lancep.war.domain.Card card = new com.lancep.war.domain.Card(suit, rank);
            drawStack.add(card);
        }));
    }

    @Override
    public void shuffle() {
        List<Card> stackAsList = Arrays.asList(drawStack.toArray(new Card[drawStack.size()]));
        drawStack.clear();

        Collections.shuffle(stackAsList);
        drawStack.addAll(stackAsList);
    }

    @Override
    public Card deal() {
        return null;
    }

    @Override
    public void addCards(List<Card> cards) {

    }

    @Override
    public int getSize() {
        return CollectionUtils.isNotEmpty(drawStack) ? drawStack.size() : 0;
    }
}
