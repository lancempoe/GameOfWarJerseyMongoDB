package com.lancep.dao;

import com.lancep.controller.errorhandling.WarException;
import com.lancep.war.orm.War;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import javax.ws.rs.core.Response;
import java.util.List;

@Repository
public class WarDaoImpl implements WarDao {

    @Autowired private MongoTemplate mongoTemplate;

    @Override
    public List<War> findAll() {
        try {
            return mongoTemplate.findAll(War.class);
        } catch (Exception e) {
            throw new WarException(Response.Status.BAD_GATEWAY);
        }
    }

    @Override
    public String save(War war) {
        try {
            mongoTemplate.save(war);
        } catch (Exception e) {
            throw new WarException(Response.Status.BAD_GATEWAY);
        }
        return war.getId();
    }

    @Override
    public War findById(String id) {
        try {
            return mongoTemplate.findById(id, War.class);
        } catch (Exception e) {
            throw new WarException(Response.Status.BAD_GATEWAY);
        }
    }

    @Override
    public void delete(War war) {
        try {
            mongoTemplate.remove(war);
        } catch (Exception e) {
            throw new WarException(Response.Status.BAD_GATEWAY);
        }
    }
}