package com.lancep.war.errorhandling;

import org.junit.Test;

import javax.ws.rs.core.Response.Status;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class WarExceptionTest {

    WarException subject;

    @Test
    public void constructorSetsTheStatus() throws Exception {
        subject = new WarException(Status.FORBIDDEN);
        assertThat(subject.getStatus(), is(Status.FORBIDDEN));
    }

}