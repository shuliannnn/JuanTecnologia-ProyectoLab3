package Interfaces;

import Excepciones.*;
import App.App;
public interface Memorias {

    default int escanearMemoriaRAM(){
        int memoria = -1;
        boolean validInput;
        do {
            try {
                System.out.print("RAM (GB): ");
                if (!App.sc.hasNextInt()) {
                    App.sc.nextLine(); // Clear invalid input
                    throw new InvalidIntegerException("La memoria RAM debe ser un número entero.");
                }
                memoria = App.sc.nextInt();
                App.sc.nextLine(); // Consume newline
                if (memoria <= 0) {
                    throw new InvalidIntegerException("La memoria RAM debe ser un número positivo.");
                }
                validInput = true;
            } catch (InvalidIntegerException e) {
                System.out.println(e.getMessage());
                validInput = false;
            }
        } while (!validInput);

        return memoria;
    }

    default int escanearMemoriaInterna(){
        int memoria = -1;
        boolean validInput;

        do {
            try {
                System.out.print("Almacenamiento (GB): ");
                if (!App.sc.hasNextInt()) {
                    App.sc.nextLine(); // Clear invalid input
                    throw new InvalidIntegerException("El almacenamiento debe ser un número entero.");
                }
                memoria = App.sc.nextInt();
                App.sc.nextLine(); // Consume newline
                if (memoria <= 0) {
                    throw new InvalidIntegerException("El almacenamiento debe ser un número positivo.");
                }
                validInput = true;
            } catch (InvalidIntegerException e) {
                System.out.println(e.getMessage());
                validInput = false;
            }
        } while (!validInput);
        
        return memoria;
    }

}
