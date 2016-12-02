package com.lancep.service;

import com.lancep.controller.errorhandling.WarException;
import com.lancep.dao.WarDao;
import com.lancep.war.client.WarResults;
import com.lancep.war.driver.WarDriver;
import com.lancep.war.assembler.WarAssembler;
import com.lancep.war.orm.War;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Logger;

@Service
public class WarServiceImpl implements WarService {
    private static final Logger logger = Logger.getLogger( WarService.class.getName() );
    private WarDao warDao;

    @Override
    public List<War> getAll() {
        return warDao.findAll();
    }

    @Override
    public String create(int numberOfSuits, int numberOfRank) {
        War war = WarAssembler.createWar(numberOfSuits, numberOfRank);
        String id = warDao.save(war);

        logger.info(String.format("New War Created: %s", id));
        return id;
    }

    @Override
    public War get(String id) {
        War war = warDao.findById(id);
        if (war == null) {
            throw new WarException(Response.Status.BAD_REQUEST);
        }
        return war;
    }

    @Override
    public void delete(String id) {
        War war = warDao.findById(id);
        if (war == null) {
            throw new WarException(Response.Status.BAD_REQUEST);
        }
        warDao.delete(war);
    }

    @Override
    public WarResults draw(String id) {
        War war = warDao.findById(id);

        WarDriver driver = new WarDriver();
        WarResults results = driver.draw(war);

        warDao.save(war);

        return results;
    }

    @Override
    public WarResults play(int numberOfSuits, int numberOfRanks) {
        WarDriver driver = new WarDriver();
        WarResults results;
        try {
            results = driver.play(numberOfSuits, numberOfRanks);
        } catch (WarException e) {
            throw e;
        } catch (Exception e) {
            throw new WarException(Response.Status.GATEWAY_TIMEOUT);
        }
        return results;
    }

    @Autowired
    public void setWarDao(WarDao warDao) {
        this.warDao = warDao;
    }
}
