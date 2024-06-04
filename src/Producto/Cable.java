package Producto;

import java.util.Scanner;

import Enumeradores.ColorP;
import Excepciones.InvalidDoubleException;

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

    @Override
    public void escanearDatosComparables() {
        ///en producto
        marca = escanearMarca();
        nombre = escanearNombre();
        color = escanearColor();
        ///en cable
        largo = escanearLargo();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(largo);
        result = prime * result + (int) (temp ^ (temp >>> 32));
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
        Cable other = (Cable) obj;
        if (Double.doubleToLongBits(largo) != Double.doubleToLongBits(other.largo))
            return false;
        return true;
    }

    public void escanearDatosEspecificos() {
        ///en producto
        descripcion = escanearDescripcion();
        stock = escanearStock();
        precio = escanearPrecio();
        id = asignarId();
    }

    public double escanearLargo(){
        Scanner sc = new Scanner(System.in);
        double largo = -1;
        boolean validInput;
        do {
            try {
                System.out.print("Largo del cable(metros, Ej: 1.5): ");
                if (!sc.hasNextDouble()) {
                    sc.next(); // Clear invalid input
                    throw new InvalidDoubleException("El largo debe ser un número.");
                }
                largo = sc.nextDouble();
                sc.nextLine(); // Consume newline
                if (largo <= 0) {
                    throw new InvalidDoubleException("El largo debe ser un número positivo.");
                }
                validInput = true;
            } catch (InvalidDoubleException e) {
                System.out.println(e.getMessage());
                validInput = false;
            }
        } while (!validInput);

        sc.close();
        return largo;
    }
}
