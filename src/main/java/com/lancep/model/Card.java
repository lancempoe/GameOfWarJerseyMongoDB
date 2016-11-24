package com.lancep.model;

import com.lancep.utils.CardNumber;
import com.lancep.utils.CardSuit;

public class Card {

    private CardSuit cardSuit;
    private CardNumber cardNumber;

    public Card(CardSuit cardSuit, CardNumber cardNumber) {
        this.cardSuit = cardSuit;
        this.cardNumber = cardNumber;
    }

    public CardSuit getCardSuit() {
        return cardSuit;
    }

    public CardNumber getCardNumber() {
        return cardNumber;
    }
}
