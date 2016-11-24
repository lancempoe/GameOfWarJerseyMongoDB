package com.lancep.utils;

import java.util.Arrays;
import java.util.List;

public enum CardNumber {
    ACE,
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    TEN,
    JACK,
    QUEEN,
    KING;

    private static final List<CardNumber> cardNumbersAsList = Arrays.asList(CardNumber.values());
    public static List<CardNumber> valuesAsList(){
        return cardNumbersAsList;
    }

}
