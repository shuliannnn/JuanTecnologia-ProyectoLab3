package Producto;

import Enumeradores.ColorP;

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

    
}
