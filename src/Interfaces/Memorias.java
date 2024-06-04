package Interfaces;

import Excepciones.*;
import java.util.Scanner;
public interface Memorias {

    default int escanearMemoriaRAM(){
        Scanner sc = new Scanner(System.in);
        int memoria = -1;
        boolean validInput;
        do {
            try {
                System.out.print("RAM (GB): ");
                if (!sc.hasNextInt()) {
                    sc.next(); // Clear invalid input
                    throw new InvalidIntegerException("La memoria RAM debe ser un número entero.");
                }
                memoria = sc.nextInt();
                sc.nextLine(); // Consume newline
                if (memoria <= 0) {
                    throw new InvalidIntegerException("La memoria RAM debe ser un número positivo.");
                }
                validInput = true;
            } catch (InvalidIntegerException e) {
                System.out.println(e.getMessage());
                validInput = false;
            }
        } while (!validInput);

        sc.close();
        return memoria;
    }

    default int escanearMemoriaInterna(){
        Scanner sc = new Scanner(System.in);
        int memoria = -1;
        boolean validInput;

        do {
            try {
                System.out.print("Almacenamiento (GB): ");
                if (!sc.hasNextInt()) {
                    sc.next(); // Clear invalid input
                    throw new InvalidIntegerException("El almacenamiento debe ser un número entero.");
                }
                memoria = sc.nextInt();
                sc.nextLine(); // Consume newline
                if (memoria <= 0) {
                    throw new InvalidIntegerException("El almacenamiento debe ser un número positivo.");
                }
                validInput = true;
            } catch (InvalidIntegerException e) {
                System.out.println(e.getMessage());
                validInput = false;
            }
        } while (!validInput);
        sc.close();
        return memoria;
    }

}
