package com.lancep.errorhandling;

import com.lancep.service.GameService;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.logging.Logger;

@Provider
public class WarExceptionMapper implements ExceptionMapper<WarException> {

    private static final Logger logger = Logger.getLogger( GameService.class.getName() );

    public Response toResponse(WarException ex) {
        logger.warning(ex.getMessage());
        return Response.status(ex.getStatus())
                .entity(ex.getMessage())
                .type(MediaType.APPLICATION_JSON).
                        build();
    }

}
