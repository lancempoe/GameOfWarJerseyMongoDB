package com.lancep.war.domain;

public interface Deck {
    /* Create the deck of cards */
    void create(int numberOfSuits, int numberOfRanks);

    /* Shuffle the deck */
    void shuffle();

    /* deal a card from the deck */
    Card deal();
}
