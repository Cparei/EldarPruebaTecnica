import java.math.BigDecimal;

public class ValidadorOperacion {
    public boolean esValidaOperacion(BigDecimal montoOperacion, String datoTec){
        boolean valida = false;

        if (ValidadorIngresoTeclado.validarNumeroIngresado(datoTec)) return false;

        valida =  ((montoOperacion.compareTo(Constantes.MONTO_OPERACION) > 0) || (montoOperacion.compareTo(BigDecimal.valueOf(0)) < 0));

        return valida;
    }
}
