import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.time.LocalDate.now;

public class Aplicativo {
    public static void main(String[] args) {
        //-----------------------------------------------------------------//
        //  Ejercicio 1 - Datos precargados
        //-----------------------------------------------------------------//

        //pruebaDatosPrecargados();

        //-----------------------------------------------------------------//
        //  Ejercicio 1 - Carga Manual
        //-----------------------------------------------------------------//

        List<TarjetaCredito> tarjetasIngresadas;

        tarjetasIngresadas = cargarDatosTarjeta();


        mostrarTarjetas(tarjetasIngresadas);


        //cargarMovimientosTarjetas();

        //-----------------------------------------------------------------//
        //  EJERCICIO 5
        //-----------------------------------------------------------------//

        //Ejercicio5.ejercicio5();

        //-----------------------------------------------------------------//
        //
        //-----------------------------------------------------------------//


    }
    public static void comparaTarjeta(TarjetaCredito tarjetaTemp, List<TarjetaCredito> tarjetas){
        CompararTarjetas compararTarjetas = new CompararTarjetas();
        if (tarjetas.size() != 0){
            tarjetas.forEach(tarjeta -> {
                if (compararTarjetas.tarjetaRepetida(tarjetaTemp, tarjeta)) System.out.println("La tarjeta cargada ESTÁ REPETIDA " + tarjeta.toStringBasico() + " \n");
            });
        }


    }

    public static void mostrarTarjetas(List<TarjetaCredito> tarjetasIngresadas){
        tarjetasIngresadas.forEach(tarjetaCredito -> {
            System.out.println("DATOS DE LAS TARJETAS INGRESADAS \n" + tarjetaCredito.toString() );
        });
    }
    public static List<TarjetaCredito> cargarDatosTarjeta(){
        List<TarjetaCredito> tarjetas = new ArrayList<>();
        Scanner ingresoDato = new Scanner(System.in);
        String datoIngresado="";
        TarjetaCredito tarjetaCargada =  new TarjetaCredito();
        System.out.println("INGRESE LOS DATOS DE LA TARJETA \n");
        while (!datoIngresado.equals(".")){
            if (!datoIngresado.equals(".")){
                tarjetaCargada.setTitular(getPersona());
                tarjetaCargada.setNumero(getNumero());
                tarjetaCargada.setFechaVencimiento(Common.getFecha("Ingrese la fecha de vencimiento de la tarjeta. Formtato: dd/mm/aaaa", "Debe ingresar una fecha válida"));
                tarjetaCargada.setMarca(getMarca());
                tarjetaCargada.setOperacionesTarjeta(TarjetaCredito.cargarMovimientos(tarjetaCargada));
                comparaTarjeta(tarjetaCargada,tarjetas);
                tarjetas.add(tarjetaCargada);
            }
            System.out.println("Continúa cargando tarjetas? ingrese '.' para finalizar\n");
            datoIngresado = ingresoDato.next();
        }
        return tarjetas;
    }

    public static String getNumero(){
        String nroTarjeta = "";
        Scanner ingresoDato = new Scanner(System.in);
        System.out.println("Ingrese el número de la tarjeta ");
        nroTarjeta = ingresoDato.next();
        while (!ValidadorIngresoTeclado.validarNumeroIngresado(nroTarjeta)){
            System.out.println("\nDebe ingresar un número de tarjeta válido. Los caracteres permitidos son 0-9 y espacios");
            nroTarjeta = ingresoDato.next();
        }
        System.out.println("\n");
        return nroTarjeta;
    }
    public static String getMarca(){
        String marcaTarjeta = "";
        Scanner ingresoDato = new Scanner(System.in);

        System.out.println("\nIngrese la marca de la tarjeta. Los valores permitidos son: " + Constantes.VISA + " / " + Constantes.NARA + " / " + Constantes.AMEX );
        marcaTarjeta = ingresoDato.next();

        while (!ValidadorIngresoTeclado.validarMarcaIngresada(marcaTarjeta)){
            System.out.println("\nDebe ingresar una marca de tarjeta valida. Los valores permitidos son: " + Constantes.VISA + " / " + Constantes.NARA + " / " + Constantes.AMEX );
            marcaTarjeta = ingresoDato.next();
        }
        return marcaTarjeta;
    }
    public static Persona getPersona(){
        Persona persona = new Persona();
        String nombre = "";
        String apellido = "";

        Scanner ingresoDato = new Scanner(System.in);

        System.out.println("Ingrese el nombre del titular ");
        nombre = ingresoDato.next();

        while (!ValidadorIngresoTeclado.validarDatoIngresado(nombre)){
            System.out.println("\nDebe ingresar un nombre válido. Los caracteres permitidos son a-z A-Z y espacios");
            nombre = ingresoDato.next();
        }
        System.out.println("\n");

        System.out.println("Ingrese el apellido del titular");
        apellido = ingresoDato.next();

        while (!ValidadorIngresoTeclado.validarDatoIngresado(apellido)){
            System.out.println("\nDebe ingresar un apellido válido. Los caracteres permitidos son a-z A-Z y espacios");
            apellido = ingresoDato.next();
        }

        System.out.println("\n");
        persona.setNombre(nombre);
        persona.setApellido(apellido);
        return persona;

    }
    public static void setTarjeta(String nombre, String apellido, String numero, String marca, LocalDate fechaVencimiento, List<OperacionTarjeta> operacionTarjetaList){

        if (fechaVencimiento.isBefore(now())){
            TarjetaCredito tarjetaCredito = new TarjetaCredito();
            ValidadorOperacion validadorOperacion = new ValidadorOperacion();
            OperacionTarjeta operacionTarjeta = new OperacionTarjeta();
            List<OperacionTarjeta> operacionTarjetaL = new ArrayList<>();

            Persona titular = new Persona();

            titular.setNombre(nombre);
            titular.setApellido(apellido);
            tarjetaCredito.setMarca(marca);
            tarjetaCredito.setNumero(numero);
            tarjetaCredito.setTitular(titular);
            tarjetaCredito.setFechaVencimiento(fechaVencimiento);
            operacionTarjetaList.forEach(operacionTarjetaLista -> {
                //verifico si el movimiento es valido, sino lo informo

                if (validadorOperacion.esValidaOperacion(operacionTarjetaLista.getMontoOperacion(), operacionTarjetaLista.getMontoOperacion().toString())) {
                    operacionTarjeta.setFechaOperacion(now());
                    operacionTarjeta.setMontoOperacion(operacionTarjetaLista.getMontoOperacion());
                    operacionTarjetaL.add(operacionTarjetaLista);
                }else{
                    System.out.println("Operación no válida por monto = " + operacionTarjetaLista.getMontoOperacion() + "\n");
                }
            });
            tarjetaCredito.setOperacionesTarjeta(operacionTarjetaL);
            System.out.println("DATOS DE TARJETA" + marca + " \n" + tarjetaCredito.toString() );
        } else {
            System.out.println("ATENCIÓN! La tarjeta ingresada no es válida " + nombre);
        }
    }

    public static void pruebaDatosPrecargados(){
        OperacionTarjeta operacionTarjeta = new OperacionTarjeta();
        List<OperacionTarjeta> operacionTarjetaList = new ArrayList<>();


        operacionTarjeta.setFechaOperacion(now());
        operacionTarjeta.setMontoOperacion(BigDecimal.valueOf(1000));
        operacionTarjetaList.add(operacionTarjeta);

        operacionTarjeta = new OperacionTarjeta();
        operacionTarjeta.setFechaOperacion(now());
        operacionTarjeta.setMontoOperacion(BigDecimal.valueOf(500));
        operacionTarjetaList.add(operacionTarjeta);

        operacionTarjeta = new OperacionTarjeta();
        operacionTarjeta.setFechaOperacion(now());
        operacionTarjeta.setMontoOperacion(BigDecimal.valueOf(1500));
        operacionTarjetaList.add(operacionTarjeta);

        operacionTarjeta = new OperacionTarjeta();
        operacionTarjeta.setFechaOperacion(now());
        operacionTarjeta.setMontoOperacion(BigDecimal.valueOf(300));
        operacionTarjetaList.add(operacionTarjeta);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse("15/10/2025", formatter);
        setTarjeta("Peter", "Parker", "45457812", Constantes.VISA, localDate, operacionTarjetaList);
    }

}
