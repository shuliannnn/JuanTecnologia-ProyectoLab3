package Producto;

import java.util.InputMismatchException;

import org.json.JSONException;
import org.json.JSONObject;

import Enumeradores.*;
import Excepciones.InvalidEnumException;
import Excepciones.InvalidIntegerException;
import App.App;
import App.Menu;

public class Mouse extends Periferico{
    private int dpi;
    private Sensores sensor;
    
    
    /// Implementaciones Metodos Abstractos ----------------------------------------------------------------------------------------------------------------------------

    @Override
    public void escanearDatosComparables(String print) {
        System.out.println(print + " mouse: ");
        super.escanearDatosComparables();
    }

    @Override
    public void escanearDatosEspecificos() {
        ///en periferico
        conexion = escanearConexion();
        inalambrico = escanearInalambrico();
        ///en mouse
        dpi = escanearDpi();
        sensor = escanearSensor();
        ///en producto
        descripcion = escanearDescripcion();
        stock = escanearStock();
        precio = escanearPrecio();
    }

    @Override
    public void modificarProducto(){
        int opcion;

        do {
            Menu.clearScreen();
            System.out.println("Producto con modificaciones actuales: ");
            System.out.println(this.toString() + '\n');
            System.out.println("Ingrese campo a modificar");
            System.out.println("1. Nombre");
            System.out.println("2. Marca");
            System.out.println("3. Precio");
            System.out.println("4. Descripcion");
            System.out.println("5. Color");
            System.out.println("6. Stock");
            System.out.println("7. Es Inalambrico");
            System.out.println("8. RGB");
            System.out.println("9. Conexion");
            System.out.println("10. Dpi");
            System.out.println("11. Sensor");
            System.out.println("0. Listo");
            System.out.print("-->");
            try {
                opcion = App.sc.nextInt();
            } catch (InputMismatchException ex) {
                opcion = -1;
            }
            App.sc.nextLine();
            
            switch (opcion) {
                case 1:
                    nombre = escanearNombre();
                    break;
                case 2:
                    marca = escanearMarca();   
                    break;
                case 3:
                    precio = escanearPrecio();
                    break;
                case 4:
                    descripcion = escanearDescripcion();
                    break;
                case 5:
                    color = escanearColor();
                    break;
                case 6:
                    stock = escanearStock();
                    break;
                case 7:
                    inalambrico = escanearInalambrico();
                    break;
                case 8:
                    rgb = escanearRgb();
                    break;
                case 9:
                    conexion = escanearConexion();
                    break;
                case 10:
                    dpi = escanearDpi();
                    break;
                case 11:
                    sensor = escanearSensor();
                    break;
                case 0:
                    break;
            
                default:
                    System.out.println("Ingrese un caracter válido");
                    break;
            }


        } while (opcion != 0);

    }

    @Override
    public Mouse clone() {
        return new Mouse(nombre, marca, precio, descripcion, color, stock, inalambrico, rgb, conexion, dpi, sensor);
    }

    @Override
    public String toString() {
        return "Mouse: ID: " + (getId()==0?"No asignado":getId()) + "\n  | Marca: " + getMarca() + " | Nombre: " + getNombre() + " | Color: " + getColor() + " |" + "\n  | Stock: " + getStock() + " | Precio: " + getPrecio() + " |" +
          " \n      Es inalambrico: " + (inalambrico ? "Si" : "No") + " \n      Es RGB: " + (rgb ? "Si" : "No") + "\n      Conexion: " + getConexion() + " \n      DPI: " + getDpi() +
          " \n      Sensor: " + getConexion() + "\n      Descripcion: " + getDescripcion() + '\n';
    }
    
    /// Scanners --------------------------------------------------------------------------------------------------------------------------------

    public static int escanearDpi(){
        int dpi = -1;
        boolean validInput;
        do {
            try {
                System.out.print("Dpi (máximo): ");
                if (!App.sc.hasNextInt()) {
                    App.sc.nextLine(); // Clear invalid input
                    throw new InvalidIntegerException("El dpi debe ser un número entero.");
                }
                dpi = App.sc.nextInt();
                App.sc.nextLine(); // Consume newline
                if (dpi <= 0) {
                    throw new InvalidIntegerException("El dpi debe ser un número positivo.");
                }
                validInput = true;
            } catch (InvalidIntegerException e) {
                System.out.println(e.getMessage());
                validInput = false;
            }
        } while (!validInput);
         
        return dpi;
    }
    
    public static Sensores escanearSensor(){
        Sensores sensor= null;
        boolean validInput;
        do {
            try {
                System.out.println("Sensores disponibles: ");
                for (Sensores s : Sensores.values()) {
                    System.out.print(s+", ");
                }
                
                System.out.print("Sensor: ");
                String input = App.sc.nextLine().trim().toUpperCase();
                
                try {
                    sensor = Sensores.valueOf(input);
                    validInput = true;
                } catch (IllegalArgumentException e) {
                    throw new InvalidEnumException("Sensor inválido. Por favor, ingrese un valor válido.");
                }
                
            } catch (InvalidEnumException e) {
                System.out.println(e.getMessage());
                validInput = false;
            }
        } while (!validInput);
        
         
        return sensor;
    }

    
    
    /// Archivos -------------------------------------------------------------------------------------------------------------------------------------------------------

    @Override
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        try {
            json.put("tipo", "Mouse");
            json.put("nombre", nombre);
            json.put("marca", marca);
            json.put("precio", precio);
            json.put("descripcion", descripcion);
            json.put("id", id);
            json.put("color", color.toString());
            json.put("stock", stock);
            json.put("inalambrico", inalambrico);
            json.put("rgb", rgb);
            json.put("conexion", conexion.toString());
            json.put("dpi", dpi);
            json.put("sensor", sensor.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static Mouse fromJSON(JSONObject json) {
        try {
            String nombre = json.getString("nombre");
            String marca = json.getString("marca");
            double precio = json.getDouble("precio");
            String descripcion = json.getString("descripcion");
            ColorP color = ColorP.valueOf(json.getString("color"));
            int stock = json.getInt("stock");
            boolean inalambrico = json.getBoolean("inalambrico");
            boolean rgb = json.getBoolean("rgb");
            Conexiones conexion = Conexiones.valueOf(json.getString("conexion"));
            int dpi = json.getInt("dpi");
            Sensores sensor = Sensores.valueOf(json.getString("sensor"));
            int id = json.getInt("id");

            Mouse aux = new Mouse(nombre, marca, precio, descripcion, color, stock, inalambrico, rgb, conexion, dpi, sensor);
            aux.setId(id);
            return aux;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /// Constructores getters y setters ------------------------------------------------------------------------------------------------------------------------------------------------

    public Mouse(String nombre, String marca, double precio, String descripcion, ColorP color, int stock,
            boolean inalambrico, boolean rgb, Conexiones conexion, int dpi, Sensores sensor) {
        super(nombre, marca, precio, descripcion, color, stock, inalambrico, rgb, conexion);
        this.dpi = dpi;
        this.sensor = sensor;
    }
    public Mouse() {
    }


    public int getDpi() {
        return dpi;
    }
    public void setDpi(int dpi) {
        this.dpi = dpi;
    }
    public Sensores getSensor() {
        return sensor;
    }
    public void setSensor(Sensores sensor) {
        this.sensor = sensor;
    }
}
