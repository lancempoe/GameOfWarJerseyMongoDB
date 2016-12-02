package com.lancep.controller.errorhandling;


import javax.ws.rs.core.Response.Status;

public class WarException extends RuntimeException {

    private Status status;

    public WarException(Status status) {
        this(status, null);
    }

    public WarException(Status status, String message) {
        super(message);
        this.status  = status;
    }

    public Status getStatus() {
        return status;
    }
}
