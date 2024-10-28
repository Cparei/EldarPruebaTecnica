package com.miproyecto.tarjetaWS.service;

import com.miproyecto.tarjetaWS.constants.Constantes;
import com.miproyecto.tarjetaWS.model.entity.TasaConsumo;
import com.miproyecto.tarjetaWS.model.request.TasaConsultaRequest;
import com.miproyecto.tarjetaWS.model.response.TasaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Calendar;

@RequiredArgsConstructor
@Service
public class TarjetaService {

    public TasaResponse getTasaOperacion(TasaConsultaRequest tasaConsultaRequest) {

        return TasaResponse.toResponse(getTasaTarjeta(tasaConsultaRequest.getMarca(), tasaConsultaRequest.getMonto()));
    }

    Calendar calendar = Calendar.getInstance();
    public TasaConsumo getTasaTarjeta(String marca, BigDecimal monto){

        return calcularTasa(marca, monto);

    }

    public TasaConsumo calcularTasa(String marca, BigDecimal monto){
        double tasaAplicar = 0.0;
        TasaConsumo tasaConsumo = new TasaConsumo();
        if ((monto.compareTo(Constantes.MONTO_OPERACION) > 0) || (monto.compareTo(BigDecimal.valueOf(0)) < 0)){

            BigDecimal montoTasa = monto.multiply(BigDecimal.valueOf(tasaAplicar));
            tasaConsumo.setMarca(marca);
            tasaConsumo.setMonto(monto);
            tasaConsumo.setTasaCalculada(montoTasa);
            tasaConsumo.setMontoTotal( monto.add(montoTasa));
            tasaConsumo.setError("El monto ingresado no es válido, no puede ser mayor a 1000 ni menor a 0");

        } else {
            switch (marca){
                case Constantes.VISA:
                    tasaAplicar = tasaVisa();
                    break;

                case Constantes.AMEX:
                    tasaAplicar = tasaAmex();
                    break;

                case Constantes.NARA:
                    tasaAplicar = tasaNara();
                    break;
                default:
                    tasaConsumo.setMarca(marca);
                    tasaConsumo.setMonto(monto);
                    tasaConsumo.setTasaCalculada(BigDecimal.valueOf(0));
                    tasaConsumo.setMontoTotal( BigDecimal.valueOf(0));
                    tasaConsumo.setError("La tarjeta ingresada es inexistente");
                    return tasaConsumo;
            }

            tasaAplicar = aplicarTopes(tasaAplicar);
            BigDecimal montoTasa = monto.multiply(BigDecimal.valueOf(tasaAplicar));
            tasaConsumo.setMarca(marca);
            tasaConsumo.setMonto(monto);
            tasaConsumo.setTasaCalculada(montoTasa);
            tasaConsumo.setMontoTotal( monto.add(montoTasa));
            tasaConsumo.setError("200 - OK");
        }


        return tasaConsumo;
    }

    public double tasaVisa(){
        return (calendar.get(Calendar.YEAR) / 12) / 100;
    }
    public double tasaNara(){
        return (calendar.get(Calendar.DAY_OF_MONTH) * 0.5) / 100;
    }
    public double tasaAmex(){
        return (calendar.get(Calendar.MONTH) * 0.1) / 100;
    }

    public double aplicarTopes(double tasaCalculada){
        double tasaTopeMin = 0.003;
        double tasaTopeMax = 0.05;
        if (tasaCalculada < tasaTopeMin) return tasaTopeMin;
        return Math.min(tasaCalculada, tasaTopeMax);
    }
}
