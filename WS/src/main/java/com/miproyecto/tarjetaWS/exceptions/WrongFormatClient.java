package com.miproyecto.tarjetaWS.exceptions;

public class WrongFormatClient extends BadRequestException{
    private static final String DESCRIPTION = " Mal formato de datos clientes";

    public WrongFormatClient(String details){ super(DESCRIPTION + " - " + details);}
}
