package com.lancep.controller.validation;

import com.lancep.war.errorhandling.WarException;
import org.junit.Test;

public class WarValidationsTest {

    public static final int VALID_NUMBER_OF_RANKS = 1;
    public static final int VALID_NUMBER_OF_SUITS = 1;

    @Test
    public void numberOfSuitsIsValidAbove0() throws Exception {
        WarValidations.hasValidPlayParams(1, VALID_NUMBER_OF_RANKS);

    }

    @Test(expected = WarException.class)
    public void numberOfSuitsIsInvalidLessThan1() throws Exception {
        WarValidations.hasValidPlayParams(0, VALID_NUMBER_OF_RANKS);
    }

    @Test
    public void numberOfSuitsIsValidLessThan5() throws Exception {
        WarValidations.hasValidPlayParams(4, VALID_NUMBER_OF_RANKS);
    }

    @Test(expected = WarException.class)
    public void numberOfSuitsIsInvalidGreaterThan4() throws Exception {
        WarValidations.hasValidPlayParams(5, VALID_NUMBER_OF_RANKS);
    }

    @Test
    public void numberOfRanksIsValidAbove0() throws Exception {
        WarValidations.hasValidPlayParams(VALID_NUMBER_OF_SUITS, 1);

    }

    @Test(expected = WarException.class)
    public void numberOfRanksIsInvalidLessThan1() throws Exception {
        WarValidations.hasValidPlayParams(VALID_NUMBER_OF_SUITS, -1);
    }

    @Test
    public void numberOfRanksIsValidLessThan14() throws Exception {
        WarValidations.hasValidPlayParams(VALID_NUMBER_OF_SUITS, 13);
    }

    @Test(expected = WarException.class)
    public void numberOfRanksIsInvalidGreaterThan13() throws Exception {
        WarValidations.hasValidPlayParams(VALID_NUMBER_OF_SUITS, 14);
    }

}