package Producto;

import Enumeradores.ColorP;

public class Portatil extends Computadora{

    private double pulgadas;
    private boolean ethernet;
    private boolean microfono;
    
    public Portatil(String nombre, String marca, double precio, String descripcion, ColorP color, int stock,
            String procesador, int memoriaRam, int memoriaInterna, String pVideo, boolean bluetooth, String mother,
            double pulgadas, boolean ethernet, boolean microfono) {
        super(nombre, marca, precio, descripcion, color, stock, procesador, memoriaRam, memoriaInterna, pVideo,
                bluetooth, mother);
        this.pulgadas = pulgadas;
        this.ethernet = ethernet;
        this.microfono = microfono;
    }

    public double getPulgadas() {
        return pulgadas;
    }

    public void setPulgadas(double pulgadas) {
        this.pulgadas = pulgadas;
    }

    public boolean isEthernet() {
        return ethernet;
    }

    public void setEthernet(boolean ethernet) {
        this.ethernet = ethernet;
    }

    public boolean isMicrofono() {
        return microfono;
    }

    public void setMicrofono(boolean microfono) {
        this.microfono = microfono;
    }

    @Override
    public String toString() {
        return "Portatil [pulgadas=" + pulgadas + ", ethernet=" + ethernet + ", microfono=" + microfono + "]";
    }

    
}
