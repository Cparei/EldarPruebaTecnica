import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public class OperacionTarjeta {
    LocalDate fechaOperacion;

    BigDecimal montoOperacion;

    public LocalDate getFechaOperacion() {
        return fechaOperacion;
    }

    public void setFechaOperacion(LocalDate fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }

    public BigDecimal getMontoOperacion() {
        return montoOperacion;
    }

    public void setMontoOperacion(BigDecimal montoOperacion) {
        this.montoOperacion = montoOperacion;
    }

    public boolean esValida(BigDecimal montoOperacion){
        ValidadorOperacion validadorOperacion = new ValidadorOperacion();

        return validadorOperacion.esValidaOperacion(montoOperacion, montoOperacion.toString());
    }

    public static BigDecimal montoOperacion(){
        ValidadorOperacion validadorOperacion = new ValidadorOperacion();
        String montoIng;
        BigDecimal monto = BigDecimal.valueOf(0);
        Scanner ingresoDato = new Scanner(System.in);

        System.out.println("Ingrese el monto de la operación \n");
        montoIng = ingresoDato.next();

        while (!validadorOperacion.esValidaOperacion(monto, montoIng)) {
            System.out.println("\nDebe ingresar un monto numérico válido, mayor a 0 y no mayor a 1000 \n");
            montoIng = ingresoDato.next();
            monto = BigDecimal.valueOf(Double.valueOf(montoIng));

        }
        System.out.println("\n");

        return monto;
    }
}
