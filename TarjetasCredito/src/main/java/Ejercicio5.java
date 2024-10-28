import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ejercicio5 {
    public static void ejercicio5(){
        String palabra="";

        List<String> palabras = new ArrayList<>();

        int i = 0;
        Scanner ingresoDato = new Scanner(System.in);
        System.out.println("Ingrese texto o '.' para finalizar la carga");
        while (!palabra.equals(".") && i < 10){
            palabra = ingresoDato.next();
            if (!palabra.equals(".")){
                if (ValidadorIngresoTeclado.validarDatoIngresado(palabra)){
                    i++;
                    palabra = palabra.toLowerCase();
                    palabras.add(palabra);
                    System.out.println("Puede ingresar " + (10 - i) + " palabra/s mas\n");
                } else {
                    System.out.println("La palabra ingresada no es valida. Puede ingresar " + (10 - i) + " palabra/s mas\n");
                }
            }
        }

        palabras.forEach(palabraCargada->{
            System.out.print(palabraCargada + " ");
        });

    }


}
