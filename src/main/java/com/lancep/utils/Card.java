package com.lancep.utils;

import java.util.Arrays;
import java.util.List;

public enum Card {
    HEART_ACE,
    HEART_ONE,
    HEART_TWO,
    HEART_THREE,
    HEART_FOUR,
    HEART_FIVE,
    HEART_SIX,
    HEART_SEVEN,
    HEART_EIGHT,
    HEART_NINE,
    HEART_TEN,
    HEART_JACK,
    HEART_QUEEN,
    HEART_KING,
    DIAMOND_ACE,
    DIAMOND_ONE,
    DIAMOND_TWO,
    DIAMOND_THREE,
    DIAMOND_FOUR,
    DIAMOND_FIVE,
    DIAMOND_SIX,
    DIAMOND_SEVEN,
    DIAMOND_EIGHT,
    DIAMOND_NINE,
    DIAMOND_TEN,
    DIAMOND_JACK,
    DIAMOND_QUEEN,
    DIAMOND_KING,
    SPADE_ACE,
    SPADE_ONE,
    SPADE_TWO,
    SPADE_THREE,
    SPADE_FOUR,
    SPADE_FIVE,
    SPADE_SIX,
    SPADE_SEVEN,
    SPADE_EIGHT,
    SPADE_NINE,
    SPADE_TEN,
    SPADE_JACK,
    SPADE_QUEEN,
    SPADE_KING,
    CLUB_ACE,
    CLUB_ONE,
    CLUB_TWO,
    CLUB_THREE,
    CLUB_FOUR,
    CLUB_FIVE,
    CLUB_SIX,
    CLUB_SEVEN,
    CLUB_EIGHT,
    CLUB_NINE,
    CLUB_TEN,
    CLUB_JACK,
    CLUB_QUEEN,
    CLUB_KING;

    private static final List<Card> CARD_ENUM_AS_LIST = Arrays.asList(Card.values());
    public static List<Card> valuesAsList(){
        return CARD_ENUM_AS_LIST;
    }

}
