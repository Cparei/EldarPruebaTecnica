package com.miproyecto.tarjetaWS.model.response;

import com.miproyecto.tarjetaWS.model.entity.TasaConsumo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class TasaResponse {

    private String marca;

    private BigDecimal monto;

    private BigDecimal tasaCalculada;

    private BigDecimal montoTotal;

    private String error;


    public static TasaResponse toResponse(TasaConsumo tasaConsumoEntity){
        if (tasaConsumoEntity == null) return null;

        return TasaResponse.builder()
                .marca(tasaConsumoEntity.getMarca())
                .monto(tasaConsumoEntity.getMonto())
                .tasaCalculada(tasaConsumoEntity.getTasaCalculada())
                .montoTotal(tasaConsumoEntity.getMontoTotal())
                .error(tasaConsumoEntity.getError())
                .build();
    }

    public static List<TasaResponse> toResponseList(List<TasaConsumo> tasaConsumoEntity){

        List<TasaResponse> tasaConsumoResponseList  = new ArrayList<>();

        tasaConsumoEntity.forEach((tasaConsumo) -> {
            tasaConsumoResponseList.add(TasaResponse.toResponse(tasaConsumo));
        });

        return tasaConsumoResponseList;
    }

}
