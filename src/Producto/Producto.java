package Producto;
import Enumeradores.ColorP;
import Excepciones.InvalidInputException;

import java.util.Scanner;
import java.util.regex.Pattern;

import Almacenamiento.*;
public abstract class Producto {
    protected String nombre;
    protected String marca;
    protected double precio;
    protected String descripcion;
    protected int id;
    protected static int contId = 0;
    protected ColorP color;
    protected int stock;

    public abstract boolean cargarProducto(Inventario<? extends Producto> lista);

    protected String escanearNombre(){
        Scanner sc = new Scanner(System.in);
        String nombreString = null;
        boolean validInput;

        do {
            try {
                System.out.print("Nombre: ");
                nombreString = sc.nextLine();
                if (!isValidInput(nombreString)) {
                    throw new InvalidInputException("El modelo solo puede contener letras y números.");
                }
                validInput = true;
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
                validInput = false;
            }
        } while (!validInput);

        sc.close();
        return nombreString;
    }

    protected String escanearMarca(){
        Scanner sc = new Scanner(System.in);
        String marcaString = null;
        boolean validInput;
        do {
            try {
                System.out.print("Marca: ");
                this.marca = sc.nextLine();
                if (!isValidInput(this.marca)) {
                    throw new InvalidInputException("La marca solo puede contener letras y números.");
                }
                validInput = true;
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
                validInput = false;
            }
        } while (!validInput);
        sc.close();
        return marcaString;
    }

    public ColorP escanearColor(){
        Scanner sc = new Scanner(System.in);
        ColorP colorP = null;
        boolean validInput;
        do {
            try {
                System.out.print("Color : ");
                colorP = ColorP.valueOf(sc.nextLine().toUpperCase());
                validInput = true;
            } catch (IllegalArgumentException e) {
                System.out.println("El color debe ser uno de los valores especificados.");
                validInput = false;
            }
        } while (!validInput);
        sc.close();
        return colorP;
    }

    protected boolean isValidInput(String input) {
        return Pattern.matches("[a-zA-Z0-9]+", input);
    }
    
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
    

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static void setContId(int contId) {
        Producto.contId = contId;
    }

    public void setColor(ColorP color) {
        this.color = color;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Producto [nombre=" + nombre + ", marca=" + marca + ", precio=" + precio + ", descripcion=" + descripcion
                + ", id=" + id + ", color=" + color + ", stock=" + stock + "]";
    }

    
}
