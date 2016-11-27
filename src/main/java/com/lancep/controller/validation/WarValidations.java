package com.lancep.controller.validation;

import com.lancep.war.errorhandling.WarException;

import javax.ws.rs.core.Response;

public class WarValidations {

    public static final int MIN_NUMBER_OF_SUITS = 1;
    public static final int MAX_NUMBER_OF_SUITS = 4;
    public static final int MAX_NUMBER_OF_RANK = 13;
    public static final int MIN_NUMBER_OF_RANK = 1;

    public static void hasValidPlayParams(int numberOfSuits, int numberOfRanks) {
        if (numberOfSuits < MIN_NUMBER_OF_SUITS || numberOfSuits > MAX_NUMBER_OF_SUITS) {
            throw new WarException(
                Response.Status.BAD_REQUEST,
                String.format("Invalid numberOfSuits QueryParam. Must be between %d-%d",
                        MIN_NUMBER_OF_SUITS,
                        MAX_NUMBER_OF_SUITS));
        } else if (numberOfRanks < MIN_NUMBER_OF_RANK || numberOfRanks > MAX_NUMBER_OF_RANK){
            throw new WarException(
                Response.Status.BAD_REQUEST,
                String.format("Invalid numberOfRanks QueryParam. Must be between %d-%d",
                        MIN_NUMBER_OF_RANK,
                        MAX_NUMBER_OF_RANK));
    }

    }
}
