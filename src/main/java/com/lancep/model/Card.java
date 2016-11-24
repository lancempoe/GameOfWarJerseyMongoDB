package com.lancep.model;

import com.lancep.utils.Number;
import com.lancep.utils.Suits;

public class Card {

    private Suits suit;
    private Number number;

    public Card(Suits suit, Number number) {
        this.suit = suit;
        this.number = number;
    }

    public Suits getSuit() {
        return suit;
    }

    public Number getNumber() {
        return number;
    }
}
