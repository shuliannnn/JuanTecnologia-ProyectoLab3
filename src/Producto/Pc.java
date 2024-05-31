package Producto;
import Enumeradores.*;

public class Pc extends Computadora{

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

    
    








}
