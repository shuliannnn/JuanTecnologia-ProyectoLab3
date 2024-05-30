package Producto;
import Enumeradores.ColorP;

public abstract class Producto {
    protected String nombre;
    protected String marca;
    protected double precio;
    protected String descripcion;
    protected int id;
    protected static int contId = 0;
    protected ColorP color;
    protected int stock;
    
    public Producto(String nombre, String marca, double precio, String descripcion, ColorP color, int stock) {
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.descripcion = descripcion;
        this.color = color;
        this.stock = stock;
    }


    public String getNombre() {
        return nombre;
    }

    public String getMarca() {
        return marca;
    }

    public double getPrecio() {
        return precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getId() {
        return id;
    }

    public static int getContId() {
        return contId;
    }

    public ColorP getColor() {
        return color;
    }

    public int getStock() {
        return stock;
    }

    @Override
    public String toString() {
        return "Producto [nombre=" + nombre + ", marca=" + marca + ", precio=" + precio + ", descripcion=" + descripcion
                + ", id=" + id + ", color=" + color + ", stock=" + stock + "]";
    }

    
}
