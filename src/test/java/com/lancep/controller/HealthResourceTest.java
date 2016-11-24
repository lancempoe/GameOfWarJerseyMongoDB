package com.lancep.controller;

import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class HealthResourceTest {

    HealthResource subject = new HealthResource();

    @Test
    public void amIAwakeReturnsOk() throws Exception {
        assertThat(subject.amIAwake().getStatusInfo(), is(Response.Status.OK));
    }

}