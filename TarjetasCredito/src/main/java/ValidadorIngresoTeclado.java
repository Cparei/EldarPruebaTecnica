import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ValidadorIngresoTeclado {
    public static boolean validarDatoIngresado(String dato){
        return dato.matches("[a-zA-Z\s]+");
    }
    public static boolean validarNumeroIngresado(String dato){
        return dato.matches("[0-9\s]+");
    }

    public static boolean validarMarcaIngresada(String dato){

        return (dato.equals(Constantes.AMEX) ||  dato.equals(Constantes.NARA) || dato.equals(Constantes.VISA));
    }
    public static boolean validarFechaIngresada(String fecha, DateTimeFormatter formato){

        try {
            LocalDate.parse(fecha, formato);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
