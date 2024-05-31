package Producto;

import Enumeradores.ColorP;

public class Cable extends Producto{
    private double largo;

    public Cable(String nombre, String marca, double precio, String descripcion, ColorP color, int stock,
            double largo) {
        super(nombre, marca, precio, descripcion, color, stock);
        this.largo = largo;
    }

    public double getLargo() {
        return largo;
    }

    public void setLargo(double largo) {
        this.largo = largo;
    }

    @Override
    public String toString() {
        return "Cable [largo=" + largo + "]";
    }

    
}
