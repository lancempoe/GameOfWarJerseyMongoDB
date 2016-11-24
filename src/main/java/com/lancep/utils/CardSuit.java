package com.lancep.utils;

import java.util.Arrays;
import java.util.List;

public enum CardSuit {
    SPADES,
    HEARTS,
    DIAMONDS,
    CLUBS;

    public static List<CardSuit> valuesAsList(){
        return Arrays.asList(CardSuit.values());
    }
}