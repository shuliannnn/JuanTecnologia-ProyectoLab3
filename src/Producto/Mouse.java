package Producto;

import org.json.JSONException;
import org.json.JSONObject;

import Enumeradores.*;
import Excepciones.InvalidEnumException;
import Excepciones.InvalidIntegerException;
import App.App;

public class Mouse extends Periferico{
    private int dpi;
    private Sensores sensor;
    public Mouse(String nombre, String marca, double precio, String descripcion, ColorP color, int stock,
            boolean inalambrico, boolean rgb, Conexiones conexion, int dpi, Sensores sensor) {
        super(nombre, marca, precio, descripcion, color, stock, inalambrico, rgb, conexion);
        this.dpi = dpi;
        this.sensor = sensor;
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
    @Override
    public String toString() {
        return "Mouse [dpi=" + dpi + ", sensor=" + sensor + "]";
    }
    
    public int escanearDpi(){
        int dpi = -1;
        boolean validInput;
        do {
            try {
                System.out.print("Dpi (máximo): ");
                if (!App.sc.hasNextInt()) {
                    App.sc.next(); // Clear invalid input
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
    
    public Sensores escanearSensor(){
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
/// comparables en periferico

///equals en periferico


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
        id = asignarId();
    }
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
}
