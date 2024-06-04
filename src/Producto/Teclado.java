package Producto;

import java.util.Scanner;

import Enumeradores.ColorP;
import Enumeradores.Conexiones;
import Excepciones.InvalidCharacterException;
import Excepciones.InvalidIntegerException;

public class Teclado extends Periferico{

    private short porcentaje;
    private boolean mecanico;
    private boolean cableRemovible;
    public Teclado(String nombre, String marca, double precio, String descripcion, ColorP color, int stock,
            boolean inalambrico, boolean rgb, Conexiones conexion, short porcentaje, boolean mecanico,
            boolean cableRemovible) {
        super(nombre, marca, precio, descripcion, color, stock, inalambrico, rgb, conexion);
        this.porcentaje = porcentaje;
        this.mecanico = mecanico;
        this.cableRemovible = cableRemovible;
    }
    public short getPorcentaje() {
        return porcentaje;
    }
    public void setPorcentaje(short porcentaje) {
        this.porcentaje = porcentaje;
    }
    public boolean isMecanico() {
        return mecanico;
    }
    public void setMecanico(boolean mecanico) {
        this.mecanico = mecanico;
    }
    public boolean isCableRemovible() {
        return cableRemovible;
    }
    public void setCableRemovible(boolean cableRemovible) {
        this.cableRemovible = cableRemovible;
    }
    @Override
    public String toString() {
        return "Teclado [porcentaje=" + porcentaje + ", mecanico=" + mecanico + ", cableRemovible=" + cableRemovible
                + "]";
    }
    
    public short escanearPorcentaje(){
        Scanner sc = new Scanner(System.in);
        short porcentaje = -1;
        boolean validInput;
        do {
            try {
                System.out.print("Porcentaje (%): ");
                if (!sc.hasNextShort()) {
                    sc.next(); // Clear invalid input
                    throw new InvalidIntegerException("El porcentaje debe ser un número entero.");
                }
                porcentaje = sc.nextShort();
                sc.nextLine(); // Consume newline
                if (porcentaje <= 0) {
                    throw new InvalidIntegerException("El porcentaje debe ser un número positivo.");
                }
                validInput = true;
            } catch (InvalidIntegerException e) {
                System.out.println(e.getMessage());
                validInput = false;
            }
        } while (!validInput);

        sc.close();
        return porcentaje;
    }

    public boolean escanearMecanico(){
        Scanner sc = new Scanner(System.in);
        char valor;
        boolean validInput = false;
        do {
            try {
                System.out.print("Es mecanico (t/f): ");
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

    public boolean escanearCableRemovible(){
        Scanner sc = new Scanner(System.in);
        char valor;
        boolean validInput = false;
        do {
            try {
                System.out.print("Posee cable removible (t/f): ");
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
    
    
    /// comparables en periferico

    ///equals en periferico

    @Override
    public void escanearDatosEspecificos() {
        ///en periferico
        conexion = escanearConexion();
        inalambrico = escanearInalambrico();
        ///en teclado
        porcentaje = escanearPorcentaje();
        mecanico = escanearMecanico();
        cableRemovible = escanearCableRemovible();
        ///en producto
        descripcion = escanearDescripcion();
        stock = escanearStock();
        precio = escanearPrecio();
        id = asignarId();
    }

    
}
