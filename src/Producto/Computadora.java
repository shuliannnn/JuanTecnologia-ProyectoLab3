package Producto;

import Enumeradores.*;
import Excepciones.InvalidCharacterException;
import Excepciones.InvalidInputException;
import App.App;

public abstract class Computadora extends Producto{

    protected String procesador;
    protected int memoriaRam; 
    protected int memoriaInterna;
    protected String pVideo;
    protected boolean bluetooth;
    protected String mother;
    
    
    public Computadora(String nombre, String marca, double precio, String descripcion, ColorP color, int stock,
            String procesador, int memoriaRam, int memoriaInterna, String pVideo, boolean bluetooth, String mother) {
        super(nombre, marca, precio, descripcion, color, stock);
        this.procesador = procesador;
        this.memoriaRam = memoriaRam;
        this.memoriaInterna = memoriaInterna;
        this.pVideo = pVideo;
        this.bluetooth = bluetooth;
        this.mother = mother;
    }

    public String getProcesador() {
        return procesador;
    }

    public void setProcesador(String procesador) {
        this.procesador = procesador;
    }

    public int getMemoriaRam() {
        return memoriaRam;
    }

    public void setMemoriaRam(int memoriaRam) {
        this.memoriaRam = memoriaRam;
    }

    public int getMemoriaInterna() {
        return memoriaInterna;
    }

    public void setMemoriaInterna(int memoriaInterna) {
        this.memoriaInterna = memoriaInterna;
    }

    public String getpVideo() {
        return pVideo;
    }

    public void setpVideo(String pVideo) {
        this.pVideo = pVideo;
    }

    public boolean isBluetooth() {
        return bluetooth;
    }

    public void setBluetooth(boolean bluetooth) {
        this.bluetooth = bluetooth;
    }

    public String getMother() {
        return mother;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    @Override
    public String toString() {
        return "Computadora [procesador=" + procesador + ", memoriaRam=" + memoriaRam + ", memoriaInterna="
                + memoriaInterna + ", pVideo=" + pVideo + ", bluetooth=" + bluetooth + ", mother=" + mother + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + memoriaInterna;
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
        Computadora other = (Computadora) obj;
        if (memoriaInterna != other.memoriaInterna)
            return false;
        return true;
    }

    public String escanearProcesador(){
        String procesador = null;
        boolean validInput;
        do {
            try {
                System.out.print("Descripcion: ");
                procesador = App.sc.nextLine();
                if (!isValidInput(procesador)) {
                    throw new InvalidInputException("El procesador solo puede contener letras y números.");
                }
                if(procesador.length() > 50){
                    throw new InvalidInputException("El procesador no puede superar los 20 caracteres.");
                }
                validInput = true;
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
                validInput = false;
            }
        } while (!validInput);
         
        return procesador;
    }

    public String escanearPlacaVideo(){
        String placaVideo = null;
        boolean validInput;
        do {
            try {
                System.out.print("Placa de video: ");
                placaVideo = App.sc.nextLine();
                if (!isValidInput(placaVideo)) {
                    throw new InvalidInputException("La placa de video solo puede contener letras y números.");
                }
                if(placaVideo.length() > 50){
                    throw new InvalidInputException("La placa de video no puede superar los 50 caracteres.");
                }
                validInput = true;
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
                validInput = false;
            }
        } while (!validInput);
         
        return placaVideo;
    }

    protected String escanearMother(){
        String mother = null;
        boolean validInput;
        do {
            try {
                System.out.print("Placa Madre: ");
                mother = App.sc.nextLine();
                if (!isValidInput(mother)) {
                    throw new InvalidInputException("La plata madre solo puede contener letras y números.");
                }
                if(mother.length() > 50){
                    throw new InvalidInputException("La placa madre no puede superar los 20 caracteres.");
                }
                validInput = true;
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
                validInput = false;
            }
        } while (!validInput);
         
        return mother;
    }

    public boolean escanearBluetooth(){
        char valor;
        boolean validInput = false;
        do {
            try {
                System.out.print("Tiene Bluetooth (t/f): ");
                String input = App.sc.next();
                
                if (input.length() != 1) {
                    throw new InvalidCharacterException("Debe ingresar solo un carácter ('t' o 'f').");
                }
                
                valor = input.charAt(0);
                
                if (valor == 't' || valor == 'f') {
                     
                    return valor == 't';
                } else {
                    throw new InvalidCharacterException("Carácter inválido. Debe ingresar 't' o 'f'.");
                }
                
            } catch (InvalidCharacterException e) {
                System.out.println(e.getMessage());
            }
        } while (!validInput);
         
        return false;
    }

    public String escanearMother(){
        String mother = null;
        boolean validInput = false;
        do {
            try {
                System.out.print("Motherboard: ");
                mother = App.sc.nextLine();
                if (!isValidInput(mother)) {
                    throw new InvalidInputException("La motherboard solo puede contener letras y números.");
                }
                if(mother.length() > 50){
                    throw new InvalidInputException("La motherboard no puede superar los 20 caracteres.");
                }
                validInput = true;
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        } while (!validInput);
         
        return mother;
    }

}

