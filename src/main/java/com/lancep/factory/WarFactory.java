package com.lancep.factory;

import com.lancep.model.War;

import java.util.Arrays;

public class WarFactory {

    public static War createWar() {
        War war = new War();

        //todo build here:
        war.setPlayer1Cards(Arrays.asList("As", "Ad", "As", "Ac"));

        return war;
    }
}
