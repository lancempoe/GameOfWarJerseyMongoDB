package com.lancep.errorhandling;


import javax.ws.rs.core.Response.Status;

public class WarException extends RuntimeException {

    private Status status;

    public WarException(Status status) {
        this.status  = status;
    }

    public Status getStatus() {
        return status;
    }
}
