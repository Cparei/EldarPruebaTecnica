package com.miproyecto.tarjetaWS.exceptions;

public class BadRequestException extends RuntimeException{
    private static final String DESCRIPTION = "Bad Request Exception (400)";

    public BadRequestException(String details){
        super(DESCRIPTION + " - " + details);

    }

}
