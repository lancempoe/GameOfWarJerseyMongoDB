package com.lancep.war.orm;

import java.util.List;

public interface WarDeck extends Deck  {

    void addCards(List<Card> cards);

    int getSize();
}
