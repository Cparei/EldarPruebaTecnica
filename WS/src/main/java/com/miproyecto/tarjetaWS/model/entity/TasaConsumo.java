package com.miproyecto.tarjetaWS.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
public class TasaConsumo {

    @NotNull
    private String marca;

    @NotNull
    private BigDecimal monto;

    @NotNull
    private BigDecimal tasaCalculada;

    @NotNull
    private BigDecimal montoTotal;

    @NotNull
    private String error;


}