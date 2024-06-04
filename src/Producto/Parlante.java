package Producto;
import java.util.Scanner;

import Enumeradores.*;
import Excepciones.InvalidEnumException;
import Excepciones.InvalidIntegerException;
public class Parlante extends Audio { 
    private Radios radio;
    private short potencia;

/// constructores getters setters tostring
    public Parlante(String nombre, String marca, double precio, String descripcion, ColorP color, int stock,
            ResistenciasP resistencia, Conexiones conexion, boolean microfono, boolean inalambrico, Radios radio,
            short potencia) {
        super(nombre, marca, precio, descripcion, color, stock, resistencia, conexion, microfono, inalambrico);
        this.radio = radio;
        this.potencia = potencia;
    }
    
    public Radios getRadio() {
        return radio;
    }
    public void setRadio(Radios radio) {
        this.radio = radio;
    }
    public short getPotencia() {
        return potencia;
    }
    public void setPotencia(short potencia) {
        this.potencia = potencia;
    }

   
    @Override
    public String toString() {
        return "Parlante [radio=" + radio + ", potencia=" + potencia + "]";
    }


///--------------------------------------------------------------------------------------------------------------------------
    

    public Radios escanearRadio(){
        Scanner sc = new Scanner(System.in);
        Radios radio= null;
        boolean validInput;
        do {
            try {
                System.out.println("Radios disponibles: ");
                for (Radios s : Radios.values()) {
                    System.out.print(s+", ");
                }
                
                System.out.print("Radio: ");
                String input = sc.nextLine().trim().toUpperCase();
                
                try {
                    radio = Radios.valueOf(input);
                    validInput = true;
                } catch (IllegalArgumentException e) {
                    throw new InvalidEnumException("Radio inválida. Por favor, ingrese un valor válido.");
                }
            } catch (InvalidEnumException e) {
                System.out.println(e.getMessage());
                validInput = false;
            }
        } while (!validInput);
        
        sc.close();
        return radio;
    }

    public short escanearPotencia(){
        Scanner sc = new Scanner(System.in);
        short potencia = -1;
        boolean validInput;
        do {
            try {
                System.out.print("Potencia (W): ");
                if (!sc.hasNextShort()) {
                    sc.next(); // Clear invalid input
                    throw new InvalidIntegerException("La potencia debe ser un número entero.");
                }
                potencia = sc.nextShort();
                sc.nextLine(); // Consume newline
                if (potencia <= 0) {
                    throw new InvalidIntegerException("La potencia debe ser un número positivo.");
                }
                validInput = true;
            } catch (InvalidIntegerException e) {
                System.out.println(e.getMessage());
                validInput = false;
            }
        } while (!validInput);

        sc.close();
        return potencia;
    }
   
   ///equals de audio

   ///datos comparables de audio
   
    @Override
    public void escanearDatosEspecificos() {
        ///en audio
        resistencia = escanearResistencia();
        microfono = escanearMicrofono();
        /// auricular
        potencia = escanearPotencia();
        radio = escanearRadio();
        ///en producto
        descripcion = escanearDescripcion();
        stock = escanearStock();
        precio = escanearPrecio();
        id = asignarId();
    }
}
