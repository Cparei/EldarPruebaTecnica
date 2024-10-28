package com.miproyecto.tarjetaWS.model.request;

import com.miproyecto.tarjetaWS.model.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TasaConsultaRequest {


    @NotNull
    private String marca;

    @NotNull
    private BigDecimal monto;

    public static TasaConsumo toResonse(TasaConsultaRequest tasaConsultaRequest){
        return TasaConsumo.builder()

                .marca(tasaConsultaRequest.getMarca())
                .monto(tasaConsultaRequest.getMonto())
                .build();
    }

}
