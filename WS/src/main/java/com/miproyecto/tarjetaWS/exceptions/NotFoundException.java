package com.miproyecto.tarjetaWS.exceptions;

public class NotFoundException extends RuntimeException{
    private static final String DESCRIPTION = "Resource not found ";

    public NotFoundException(String details){
        super(DESCRIPTION + " - " + details);

    }
}
