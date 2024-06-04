package Producto;

import java.util.Scanner;

import Enumeradores.*;
import Excepciones.InvalidEnumException;
import Excepciones.InvalidIntegerException;

public class Mouse extends Periferico{
    private int dpi;
    private Sensores sensor;
    public Mouse(String nombre, String marca, double precio, String descripcion, ColorP color, int stock,
            boolean inalambrico, boolean rgb, Conexiones conexion, int dpi, Sensores sensor) {
        super(nombre, marca, precio, descripcion, color, stock, inalambrico, rgb, conexion);
        this.dpi = dpi;
        this.sensor = sensor;
    }
    public int getDpi() {
        return dpi;
    }
    public void setDpi(int dpi) {
        this.dpi = dpi;
    }
    public Sensores getSensor() {
        return sensor;
    }
    public void setSensor(Sensores sensor) {
        this.sensor = sensor;
    }
    @Override
    public String toString() {
        return "Mouse [dpi=" + dpi + ", sensor=" + sensor + "]";
    }
    
    public int escanearDpi(){
        Scanner sc = new Scanner(System.in);
        int dpi = -1;
        boolean validInput;
        do {
            try {
                System.out.print("Dpi (máximo): ");
                if (!sc.hasNextInt()) {
                    sc.next(); // Clear invalid input
                    throw new InvalidIntegerException("El dpi debe ser un número entero.");
                }
                dpi = sc.nextInt();
                sc.nextLine(); // Consume newline
                if (dpi <= 0) {
                    throw new InvalidIntegerException("El dpi debe ser un número positivo.");
                }
                validInput = true;
            } catch (InvalidIntegerException e) {
                System.out.println(e.getMessage());
                validInput = false;
            }
        } while (!validInput);
        sc.close();
        return dpi;
    }
    
    public Sensores escanearSensor(){
        Scanner sc = new Scanner(System.in);
        Sensores sensor= null;
        boolean validInput;
        do {
            try {
                System.out.println("Sensores disponibles: ");
                for (Sensores s : Sensores.values()) {
                    System.out.print(s+", ");
                }
                
                System.out.print("Sensor: ");
                String input = sc.nextLine().trim().toUpperCase();
                
                try {
                    sensor = Sensores.valueOf(input);
                    validInput = true;
                } catch (IllegalArgumentException e) {
                    throw new InvalidEnumException("Sensor inválido. Por favor, ingrese un valor válido.");
                }
                
            } catch (InvalidEnumException e) {
                System.out.println(e.getMessage());
                validInput = false;
            }
        } while (!validInput);
        
        sc.close();
        return sensor;
    }
/// comparables en periferico

///equals en periferico


    @Override
    public void escanearDatosEspecificos() {
        ///en periferico
        conexion = escanearConexion();
        inalambrico = escanearInalambrico();
        ///en mouse
        dpi = escanearDpi();
        sensor = escanearSensor();
        ///en producto
        descripcion = escanearDescripcion();
        stock = escanearStock();
        precio = escanearPrecio();
        id = asignarId();
    }

    
}
