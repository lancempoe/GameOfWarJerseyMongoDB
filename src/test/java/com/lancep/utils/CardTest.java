package com.lancep.utils;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by lancepoehler on 11/24/16.
 */
public class CardTest {

    @Test
    public void valuesAndValuesAsListAreTheSameSize() throws Exception {
        assertThat(Card.values().length, is(Card.valuesAsList().size()));
    }

}