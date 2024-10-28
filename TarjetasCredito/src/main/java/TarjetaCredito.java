
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.time.LocalDate.now;

public class TarjetaCredito {

    String marca;

    String numero;

    String cardHolder;

    Persona titular;

    LocalDate fechaVencimiento;

    List<OperacionTarjeta> operacionesTarjeta;

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Persona getTitular() {
        return titular;
    }

    public void setTitular(Persona titular) {
        this.titular = titular;
        setCardHolder(titular.getApellido() + " " + titular.getNombre());
    }

    public String getCardHolder() {

        return this.titular.getApellido() + " " + this.titular.getNombre();
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public boolean esValida(LocalDate fechaActual){

        return this.fechaVencimiento.isAfter(fechaActual);

    }


    public List<OperacionTarjeta> getOperacionesTarjeta() {
        return operacionesTarjeta;
    }

    public void setOperacionesTarjeta(List<OperacionTarjeta> operacionesTarjeta) {

        operacionesTarjeta.forEach(operacionTarjeta -> {
            if (operacionTarjeta.esValida(operacionTarjeta.getMontoOperacion())) this.operacionesTarjeta = operacionesTarjeta;
        });

    }

    public static List<OperacionTarjeta> cargarMovimientos(TarjetaCredito tarjeta){
        List<OperacionTarjeta> operacionTarjetaL = new ArrayList<>();
        if (tarjeta.esValida(now())){
            OperacionTarjeta operacionTarjeta = new OperacionTarjeta();
            System.out.println("Ingrese los datos de los movimientos de la tarjeta");
            Scanner ingresoDato = new Scanner(System.in);
            String datoIngresado="";

            while (!datoIngresado.equals(".")){
                if (!datoIngresado.equals(".")){
                    operacionTarjeta.setFechaOperacion(Common.getFecha("Ingrese la fecha del movimiento. Formtato: dd/mm/aaaa", "Debe ingresar una fecha válida"));
                    operacionTarjeta.setMontoOperacion(OperacionTarjeta.montoOperacion());
                    operacionTarjetaL.add(operacionTarjeta);
                }
                System.out.println("Continúa cargando movimientos a la tarjeta? ingrese '.' para finalizar\n");
                datoIngresado = ingresoDato.next();
            }
        } else {
            System.out.println("\nATENCIÓN! La tarjeta a la cual se le quiere cargar movimientos no es válida " + tarjeta.toStringBasico());
        }
        return operacionTarjetaL;
    }

    @Override
    public String toString() {

        List<OperacionTarjeta> operacionTarjetaL = getOperacionesTarjeta();

        TasasTarjetas tasasTarjetas = new TasasTarjetas();

        double tasaAplicar = 0.0;

        switch (marca){
            case Constantes.VISA:
                tasaAplicar = tasasTarjetas.tasaVisa();
                break;

            case Constantes.AMEX:
                tasaAplicar = tasasTarjetas.tasaAmex();
                break;

            case Constantes.NARA:
                tasaAplicar = tasasTarjetas.tasaNara();
                break;
            default:
                break;
        }
        tasaAplicar = tasasTarjetas.aplicarTopes(tasaAplicar);
        StringBuilder datosOperaciones = new StringBuilder();
        datosOperaciones.append("\n\n");
        double finalTasaAplicar = tasaAplicar;
        operacionTarjetaL.forEach(operTarjeta -> {
            datosOperaciones.append("Monto $")
                    .append(operTarjeta.getMontoOperacion().toString())
                    .append("  -- Fecha: ")
                    .append(operTarjeta.getFechaOperacion())
                    .append("-- Recargo: ")
                    .append((operTarjeta.getMontoOperacion().multiply(BigDecimal.valueOf(finalTasaAplicar))))
                    .append("\n");
        });
        return  "marca: " + marca + "\n" +
                "numero: " + numero + "\n" +
                "cardHolder: " + getCardHolder() + "\n" +
                "fechaVencimiento: " + fechaVencimiento +"\n\n" +
                "tasa a aplicar: " + tasaAplicar + "\n" +
                "Datos de las operaciones " + datosOperaciones
                ;
    }
    public String toStringBasico() {

        return  "marca: " + marca + "\n" +
                "numero: " + numero + "\n" +
                "cardHolder: " + getCardHolder() + "\n" +
                "fechaVencimiento: " + fechaVencimiento +"\n\n";
    }

}
