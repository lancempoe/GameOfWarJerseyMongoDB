package com.lancep.errorhandling;

import org.junit.Test;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class WarExceptionMapperTest {

    WarExceptionMapper subject = new WarExceptionMapper();

    @Test
    public void willMapWarExceptionStatusToResponse() throws Exception {
        WarException exception = new WarException(Status.BAD_GATEWAY);
        assertThat(subject.toResponse(exception).getStatusInfo(), is(Status.BAD_GATEWAY));
    }

    @Test
    public void willMapWarExceptionMessageToResponse() throws Exception {
        String message = "Taran";
        WarException exception = new WarException(Status.BAD_GATEWAY, message);
        assertThat(subject.toResponse(exception).getEntity(), is(message));
    }

    @Test
    public void willReturnJsonMediaType() throws Exception {
        WarException exception = new WarException(Status.BAD_GATEWAY, null);
        assertThat(subject.toResponse(exception).getMediaType(), is(MediaType.APPLICATION_JSON_TYPE));
    }

}