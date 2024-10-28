package com.miproyecto.tarjetaWS.controllers;

import com.miproyecto.tarjetaWS.exceptions.NotFoundException;
import com.miproyecto.tarjetaWS.model.request.TasaConsultaRequest;
import com.miproyecto.tarjetaWS.service.TarjetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tarjeta")
@CrossOrigin("*")

public class TarjetaController {

    private final TarjetaService tarjetaService;

    @GetMapping("/getTasaOperacion")
    public ResponseEntity getTasaOperacion(@Valid @RequestBody TasaConsultaRequest tasaConsultaRequest) throws NotFoundException{

        return ResponseEntity.status(HttpStatus.OK).body(tarjetaService.getTasaOperacion(tasaConsultaRequest));
    }



}
