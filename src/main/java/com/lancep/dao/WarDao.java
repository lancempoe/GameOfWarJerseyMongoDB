package com.lancep.dao;

import com.lancep.war.orm.War;

import java.util.List;

public interface WarDao {

    List<War> findAll();

    String save(War war);

    War findById(String id);

    void delete(War war);

}
