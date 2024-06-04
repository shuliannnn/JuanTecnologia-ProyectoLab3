package Producto;
import Enumeradores.ColorP;
import Excepciones.InvalidDoubleException;
import Excepciones.InvalidInputException;
import Excepciones.InvalidIntegerException;

import java.util.Scanner;

public abstract class Producto {
    protected String nombre;
    protected String marca;
    protected double precio;
    protected String descripcion;
    protected int id;
    protected static int contId = 0;
    protected ColorP color;
    protected int stock;

    public int asignarId(){
        int nuevoId  = contId;
        contId++;
        return nuevoId;
    }
    
    public abstract void escanearDatosComparables();
    public abstract void escanearDatosEspecificos();
    
    public String escanearNombre(){
        Scanner sc = new Scanner(System.in);
        String nombre = null;
        boolean validInput;
        do {
            try {
                System.out.print("Nombre: ");
                nombre = sc.nextLine();
                if (!isValidInput(nombre)) {
                    throw new InvalidInputException("El modelo solo puede contener letras y números.");
                }
                if(nombre.length() > 40){
                    throw new InvalidInputException("El nombre no puede superar los 40 caracteres.");
                }
                validInput = true;
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
                validInput = false;
            }
        } while (!validInput);

        sc.close();
        return nombre;
    }

    public String escanearMarca(){
        Scanner sc = new Scanner(System.in);
        String marca = null;
        boolean validInput;
        do {
            try {
                System.out.print("Marca: ");
                marca = sc.nextLine();
                if (!isValidInput(marca)) {
                    throw new InvalidInputException("La marca solo puede contener letras y números.");
                }
                if(marca.length() > 20){
                    throw new InvalidInputException("La marca no puede superar los 20 caracteres.");
                }
                validInput = true;
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
                validInput = false;
            }
        } while (!validInput);
        sc.close();
        return marca;
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

    protected String escanearDescripcion(){
        Scanner sc = new Scanner(System.in);
        String descripcion = null;
        boolean validInput;
        do {
            try {
                System.out.print("Descripcion: ");
                descripcion = sc.nextLine();
                if (!isValidInput(descripcion)) {
                    throw new InvalidInputException("La descripcion solo puede contener letras y números.");
                }
                if(descripcion.length() > 100){
                    throw new InvalidInputException("La marca no puede superar los 20 caracteres.");
                }
                validInput = true;
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
                validInput = false;
            }
        } while (!validInput);
        sc.close();
        return descripcion;
    }

    public int escanearStock(){
        Scanner sc = new Scanner(System.in);
        int stock = -1;
        boolean validInput;
        do {
            try {
                System.out.print("Bateria (mAh): ");
                if (!sc.hasNextInt()) {
                    sc.next(); // Clear invalid input
                    throw new InvalidIntegerException("El stock debe ser un número entero.");
                }
                stock = sc.nextInt();
                sc.nextLine(); // Consume newline
                if (stock < 0) {
                    throw new InvalidIntegerException("El stock no puede ser menor a 0.");
                }
                validInput = true;
            } catch (InvalidIntegerException e) {
                System.out.println(e.getMessage());
                validInput = false;
            }
        } while (!validInput);
        sc.close();
        return stock;
    }

    public double escanearPrecio(){
        Scanner sc = new Scanner(System.in);
        double precio = -1;
        boolean validInput;
        do {
            try {
                System.out.print("Precio: ");
                if (!sc.hasNextDouble()) {
                    sc.next(); // Clear invalid input
                    throw new InvalidDoubleException("El precio debe ser un número.");
                }
                precio = sc.nextDouble();
                sc.nextLine(); // Consume newline
                if (precio <= 0) {
                    throw new InvalidDoubleException("El precio debe ser un número positivo.");
                }
                validInput = true;
            } catch (InvalidDoubleException e) {
                System.out.println(e.getMessage());
                validInput = false;
            }
        } while (!validInput);

        sc.close();
        return precio;
    }
    
    protected boolean isValidInput(String cadena) {
        // Permite letras, números, puntos, comas y guiones medios
        return cadena.matches("^[a-zA-Z0-9.,-]*$");
    }
    
    public Producto(String nombre, String marca, double precio, String descripcion, ColorP color, int stock) {
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.descripcion = descripcion;
        this.color = color;
        this.stock = stock;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + ((marca == null) ? 0 : marca.hashCode());
        result = prime * result + ((color == null) ? 0 : color.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Producto other = (Producto) obj;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equalsIgnoreCase(other.nombre))
            return false;
        if (marca == null) {
            if (other.marca != null)
                return false;
        } else if (!marca.equalsIgnoreCase(other.marca))
            return false;
        if (color != other.color)
            return false;
        return true;
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
