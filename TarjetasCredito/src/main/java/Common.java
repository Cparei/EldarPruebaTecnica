import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Common {
    public static LocalDate getFecha(String textoInicial, String textoError){
        String fecha = "";
        Scanner ingresoDato = new Scanner(System.in);

        System.out.println(textoInicial);
        fecha = ingresoDato.next();

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");


        while (!ValidadorIngresoTeclado.validarFechaIngresada(fecha,formato)){
            System.out.println("\n"+textoError);
            fecha = ingresoDato.next();
        }

        return LocalDate.parse(fecha, formato);

    }

}
