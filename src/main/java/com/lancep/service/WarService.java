package com.lancep.service;


import com.lancep.war.client.WarResults;
import com.lancep.war.orm.War;

import java.util.List;

public interface WarService {

    List<War> getAll();

    String create(int numberOfSuits, int numberOfRank);

    War get(String id);

    void delete(String id);

    WarResults draw(String id);

    WarResults play(int numberOfSuits, int numberOfRanks);
}
