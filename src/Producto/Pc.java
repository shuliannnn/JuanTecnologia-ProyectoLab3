package Producto;
import java.util.Scanner;

import Enumeradores.*;
import Excepciones.InvalidCharacterException;
import Interfaces.Memorias;

public class Pc extends Computadora implements Memorias{

    private boolean perifericos;
    private boolean wifi;
    
    public Pc(String nombre, String marca, double precio, String descripcion, ColorP color, int stock,
            String procesador, int memoriaRam, int memoriaInterna, String pVideo, boolean bluetooth, String mother,
            boolean perifericos, boolean wifi) {
        super(nombre, marca, precio, descripcion, color, stock, procesador, memoriaRam, memoriaInterna, pVideo,
                bluetooth, mother);
        this.perifericos = perifericos;
        this.wifi = wifi;
    }

    public boolean isPerifericos() {
        return perifericos;
    }

    public void setPerifericos(boolean perifericos) {
        this.perifericos = perifericos;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    @Override
    public String toString() {
        return "Pc [perifericos=" + perifericos + ", wifi=" + wifi + "]";
    }


    @Override
    public void escanearDatosComparables() {
        ///en producto
        marca = escanearMarca();
        nombre = escanearNombre();
        color = escanearColor();
        ///en computadora
        memoriaInterna = escanearMemoriaInterna();
        /// en pc 
        perifericos = escanearPerifericos();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (perifericos ? 1231 : 1237);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pc other = (Pc) obj;
        if (perifericos != other.perifericos)
            return false;
        return true;
    }

    @Override
    public void escanearDatosEspecificos() {
        ///en computadora 
        procesador = escanearProcesador();
        memoriaRam = escanearMemoriaRAM();
        pVideo = escanearPlacaVideo();
        bluetooth = escanearBluetooth();
        /// en pc 
        wifi = escanearWifi();
        ///producto
        descripcion = escanearDescripcion();
        stock = escanearStock();
        precio = escanearPrecio();
        id = asignarId();
    }

    public boolean escanearPerifericos(){
        Scanner sc = new Scanner(System.in);
        char valor;
        boolean validInput = false;
        do {
            try {
                System.out.print("Incluye perifericos (t/f): ");
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

    public boolean escanearWifi(){
        Scanner sc = new Scanner(System.in);
        char valor;
        boolean validInput = false;
        do {
            try {
                System.out.print("Tiene Placa Wifi (t/f): ");
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
    








}
