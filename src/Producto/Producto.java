package Producto;

import java.util.InputMismatchException;

import org.json.JSONObject;

import Almacenamiento.Archivo;
import Enumeradores.ColorP;
import Excepciones.InvalidDoubleException;
import Excepciones.InvalidInputException;
import Excepciones.InvalidIntegerException;
import App.App;
import App.Menu;

public abstract class Producto {
    protected String nombre;
    protected String marca;
    protected double precio;
    protected String descripcion;
    protected int id;
    protected ColorP color;
    protected int stock;

    
    /// Metodos Abstractos --------------------------------------------------------------------------------------------------------------------------
    
    public abstract void escanearDatosComparables(String print);
    public abstract void escanearDatosEspecificos();
    public abstract void modificarProducto();
    public abstract Producto clone();
    public abstract JSONObject toJSON();

    
    /// Metodo de variacion de stock acepta positivos y negativos
    
    public void modificarStock() {
        int input = 0;
        boolean validInput;
        do {
            try {
                System.out.print("Ingrese la variacion de stock(+/-): ");
                if (!App.sc.hasNextInt()) {
                    App.sc.next(); // Clear invalid input
                    throw new InvalidIntegerException("El stock debe ser un número entero.");
                }
                input = App.sc.nextInt();
                App.sc.nextLine(); // Consume newline
                if (input < 0 && Math.abs(input) > stock)
                    throw new InvalidIntegerException("El stock no puede quedar negativo.");

                    validInput = true;
            } catch (InvalidIntegerException e) {
                System.out.println(e.getMessage());
                validInput = false;
            }
        } while (!validInput);
        stock += input;
    }

    
/// Equals -------------------------------------------

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
    
    /// Scanners ----------------------------------------------------------------------------------------------------------------------------------------------------
    
    public static String escanearNombre() {
        String nombre = null;
        boolean validInput;
        do {
            try {
                System.out.print("Nombre: ");
                nombre = App.sc.nextLine();
                if (!isValidInput(nombre)) {
                    throw new InvalidInputException(
                            "El nombre contiene caracteres no permitidos. Por favor, utilice solo letras, números y signos de puntuación básicos.");
                }
                if (nombre.length() > 40) {
                    throw new InvalidInputException("El nombre no puede superar los 40 caracteres.");
                }
                validInput = true;
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
                validInput = false;
            }
        } while (!validInput);
        return nombre;
    }
    
    public static String escanearMarca() {
        String marca = null;
        boolean validInput;
        do {
            try {
                System.out.print("Marca: ");
                marca = App.sc.nextLine();
                if (!isValidInput(marca)) {
                    throw new InvalidInputException(
                            "La marca contiene caracteres no permitidos. Por favor, utilice solo letras, números y signos de puntuación básicos.");
                        }
                        if (marca.length() > 20) {
                            throw new InvalidInputException("La marca no puede superar los 20 caracteres.");
                        }
                        validInput = true;
                    } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
                validInput = false;
            }
        } while (!validInput);
        return marca;
    }
    
    public static ColorP escanearColor() {
        ColorP colorP = null;
        boolean validInput;
        do {
            try {
                System.out.print("Color ( ");
                for (ColorP s : ColorP.values()) {
                    System.out.print(s + ", ");
                }
                System.out.print("): ");
                colorP = ColorP.valueOf(App.sc.nextLine().toUpperCase());
                validInput = true;
            } catch (IllegalArgumentException e) {
                System.out.println("El color debe ser uno de los valores especificados.");
                validInput = false;
            }
        } while (!validInput);
        return colorP;
    }
    
    protected static String escanearDescripcion() {
        String descripcion = null;
        boolean validInput;
        do {
            try {
                System.out.print("Descripcion: ");
                descripcion = App.sc.nextLine();
                if (!isValidInput(descripcion)) {
                    throw new InvalidInputException(
                            "La descripcion contiene caracteres no permitidos. Por favor, utilice solo letras, números y signos de puntuación básicos.");
                }
                if (descripcion.length() > 100) {
                    throw new InvalidInputException("La descripcion no puede superar los 100 caracteres.");
                }
                validInput = true;
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
                validInput = false;
            }
        } while (!validInput);
        return descripcion;
    }
    
    public static int escanearId() {
        int id;
        System.out.println("Ingrese un id: ");
        try {
            id = App.sc.nextInt();
            if (id < 1) {
                throw new InvalidIntegerException();
            }
        } catch (InputMismatchException | InvalidIntegerException ex) {
            App.sc.nextLine();
            System.out.println("Id no valido");
            Menu.systemPause();
            return -1;
        }
        return id;
    }

    public static int escanearStock() {
        int stock = -1;
        boolean validInput;
        do {
            try {
                System.out.print("Stock: ");
                if (!App.sc.hasNextInt()) {
                    App.sc.next(); // Clear invalid input
                    throw new InvalidIntegerException("El stock debe ser un número entero.");
                }
                stock = App.sc.nextInt();
                App.sc.nextLine(); // Consume newline
                if (stock < 0) {
                    throw new InvalidIntegerException("El stock no puede ser menor a 0.");
                }
                validInput = true;
            } catch (InvalidIntegerException e) {
                System.out.println(e.getMessage());
                validInput = false;
            }
        } while (!validInput);
        return stock;
    }
    
    public static double escanearPrecio() {
        double precio = -1;
        boolean validInput;
        do {
            try {
                System.out.print("Precio: ");
                if (!App.sc.hasNextDouble()) {
                    App.sc.next(); // Clear invalid input
                    throw new InvalidDoubleException("El precio debe ser un número.");
                }
                precio = App.sc.nextDouble();
                App.sc.nextLine(); // Consume newline
                if (precio <= 0) {
                    throw new InvalidDoubleException("El precio debe ser un número positivo.");
                }
                validInput = true;
            } catch (InvalidDoubleException e) {
                System.out.println(e.getMessage());
                validInput = false;
            }
        } while (!validInput);
        return precio;
    }
    
    /// Eleccion de categoria
    public static Producto elegirCategoria() {
        int opcion, opcionC, opcionA, opcionP;
        Menu.clearScreen();
        do {
            System.out.println("Categorias");
            System.out.println("1. Celulares");
            System.out.println("2. Computadoras");
            System.out.println("3. Audio");
            System.out.println("4. Periféricos");
            System.out.println("5. Cables");
            System.out.println("0. Atras");
            System.out.print("-->");
            try {
                opcion = App.sc.nextInt();
            } catch (InputMismatchException ex) {
                opcion = -1;
            }
            App.sc.nextLine();
            Menu.clearScreen();
            switch (opcion) {
                case 1:
                    opcion = 0;
                    return new Celular();
                case 2:
                    do {
                        System.out.println("Computadoras");
                        System.out.println("    1. Pc");
                        System.out.println("    2. Portatil");
                        System.out.println("    0. Atras");
                        System.out.print("-->");
                        try {
                            opcionC = App.sc.nextInt();
                        } catch (InputMismatchException ex) {
                            opcionC = -1;
                        }
                        App.sc.nextLine();
                        App.sc.nextLine();
                        Menu.clearScreen();
                        switch (opcionC) {
                            case 1:
                                opcionC = 0;
                                opcion = 0;
                                return new Pc();
                            case 2:
                                opcionC = 0;
                                opcion = 0;
                                return new Portatil();
                            case 0:
                                break;
                            default:
                                System.out.println("Por favor ingrese un caracter valido");
                                break;
                        }

                    } while (opcionC != 0);
                    break;
                case 3:
                    do {
                        System.out.println("Audio");
                        System.out.println("    1. Auriculares");
                        System.out.println("    2. Parlantes");
                        System.out.println("    0. Atras");
                        System.out.print("-->");
                        try {
                            opcionA = App.sc.nextInt();
                        } catch (InputMismatchException ex) {
                            opcionA = -1;
                        }
                        App.sc.nextLine();
                        Menu.clearScreen();
                        switch (opcionA) {
                            case 1:
                                opcionA = 0;
                                opcion = 0;
                                return new Auricular();
                            case 2:
                                opcionA = 0;
                                opcion = 0;
                                return new Parlante();
                            case 0:
                                break;
                            default:
                                System.out.println("Por favor ingrese un caracter valido");
                                break;
                        }

                    } while (opcionA != 0);
                    break;
                case 4:
                    do {
                        System.out.println("Perifericos");
                        System.out.println("    1. Mouse");
                        System.out.println("    2. Teclado");
                        System.out.println("    0. Atras");
                        System.out.print("-->");
                        try {
                            opcionP = App.sc.nextInt();
                        } catch (InputMismatchException ex) {
                            opcionP = -1;
                        }
                        App.sc.nextLine();
                        Menu.clearScreen();
                        switch (opcionP) {
                            case 1:
                                opcionP = 0;
                                opcion = 0;
                                return new Mouse();
                            case 2:
                                opcionP = 0;
                                opcion = 0;
                                return new Teclado();
                            case 0:
                                break;
                            default:
                                System.out.println("Por favor ingrese un caracter valido");
                                break;
                        }

                    } while (opcionP != 0);
                    break;
                case 5:
                    opcion = 0;
                    return new Cable();
                case 0:
                    break;
                default:
                    System.out.println("Por favor ingrese una entrada válida");
                    break;
            }
        } while (opcion != 0);

        return null;
    }

    protected static boolean isValidInput(String cadena) {
        // Permite letras, números, puntos, comas, guiones medios y espacios
        return cadena.matches("^[a-zA-Z0-9.,\\-\\s]*$");
    }

    /// Constructores getters y setters  ------------------------------------------------------------------------------------------------------------------------------------------------
    
    public Producto() {
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
    
    public void setColor(ColorP color) {
        this.color = color;
    }
    
    public void setStock(int stock) {
        this.stock = stock;
    }
    
    public int asignarId() {
        int nuevoId = Archivo.leerContadorId();
        Archivo.subirContadorId();
        return nuevoId;
    }
    
}
