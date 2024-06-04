package Interfaces;
import java.util.Scanner;

import Enumeradores.Conexiones;
import Excepciones.*;
public interface Conectividad {

    default boolean escanearInalambrico(){
        Scanner sc = new Scanner(System.in);
        char valor;
        boolean validInput = false;
        do {
            try {
                System.out.print("Es inalambrico (t/f): ");
                String input = sc.next();
                
                if (input.length() != 1) {
                    throw new InvalidCharacterException("Debe ingresar solo un carácter ('t' o 'f').");
                }
                
                valor = input.charAt(0);
                
                if (valor == 't' || valor == 'f') {
                    sc.close();
                    return valor == 't';
                } else {
                    throw new InvalidCharacterException("Carácter inválido. Debe ingresar 't' o 'f'.");
                }
                
            } catch (InvalidCharacterException e) {
                System.out.println(e.getMessage());
            }
        } while (!validInput);
        sc.close();
        return false;
    }

    default Conexiones escanearConexion(){
        Scanner sc = new Scanner(System.in);
        Conexiones conexion= null;
        boolean validInput;
        do {
            try {
                System.out.println("Conexiones disponibles: ");
                for (Conexiones s : Conexiones.values()) {
                    System.out.print(s+", ");
                }
                
                System.out.print("Conexion: ");
                String input = sc.nextLine().trim().toUpperCase();
                
                try {
                    conexion = Conexiones.valueOf(input);
                    validInput = true;
                } catch (IllegalArgumentException e) {
                    throw new InvalidEnumException("Conexion inválida. Por favor, ingrese un valor válido.");
                }
            } catch (InvalidEnumException e) {
                System.out.println(e.getMessage());
                validInput = false;
            }
        } while (!validInput);
        
        sc.close();
        return conexion;
    }
}
