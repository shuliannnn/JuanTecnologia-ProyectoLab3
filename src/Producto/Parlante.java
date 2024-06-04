package Producto;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Scanner;

import Enumeradores.*;
import Excepciones.InvalidEnumException;
import Excepciones.InvalidIntegerException;
public class Parlante extends Audio { 
    private Radios radio;
    private short potencia;

/// constructores getters setters tostring
    public Parlante(String nombre, String marca, double precio, String descripcion, ColorP color, int stock,
            ResistenciasP resistencia, Conexiones conexion, boolean microfono, boolean inalambrico, Radios radio,
            short potencia) {
        super(nombre, marca, precio, descripcion, color, stock, resistencia, conexion, microfono, inalambrico);
        this.radio = radio;
        this.potencia = potencia;
    }
    
    public Radios getRadio() {
        return radio;
    }
    public void setRadio(Radios radio) {
        this.radio = radio;
    }
    public short getPotencia() {
        return potencia;
    }
    public void setPotencia(short potencia) {
        this.potencia = potencia;
    }

   
    @Override
    public String toString() {
        return "Parlante [radio=" + radio + ", potencia=" + potencia + "]";
    }


///--------------------------------------------------------------------------------------------------------------------------
    

    public Radios escanearRadio(){
        Scanner sc = new Scanner(System.in);
        Radios radio= null;
        boolean validInput;
        do {
            try {
                System.out.println("Radios disponibles: ");
                for (Radios s : Radios.values()) {
                    System.out.print(s+", ");
                }
                
                System.out.print("Radio: ");
                String input = sc.nextLine().trim().toUpperCase();
                
                try {
                    radio = Radios.valueOf(input);
                    validInput = true;
                } catch (IllegalArgumentException e) {
                    throw new InvalidEnumException("Radio inválida. Por favor, ingrese un valor válido.");
                }
            } catch (InvalidEnumException e) {
                System.out.println(e.getMessage());
                validInput = false;
            }
        } while (!validInput);
        
        sc.close();
        return radio;
    }

    public short escanearPotencia(){
        Scanner sc = new Scanner(System.in);
        short potencia = -1;
        boolean validInput;
        do {
            try {
                System.out.print("Potencia (W): ");
                if (!sc.hasNextShort()) {
                    sc.next(); // Clear invalid input
                    throw new InvalidIntegerException("La potencia debe ser un número entero.");
                }
                potencia = sc.nextShort();
                sc.nextLine(); // Consume newline
                if (potencia <= 0) {
                    throw new InvalidIntegerException("La potencia debe ser un número positivo.");
                }
                validInput = true;
            } catch (InvalidIntegerException e) {
                System.out.println(e.getMessage());
                validInput = false;
            }
        } while (!validInput);

        sc.close();
        return potencia;
    }
   
   ///equals de audio

   ///datos comparables de audio
   
    @Override
    public void escanearDatosEspecificos() {
        ///en audio
        resistencia = escanearResistencia();
        microfono = escanearMicrofono();
        /// auricular
        potencia = escanearPotencia();
        radio = escanearRadio();
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
            json.put("tipo", "Parlante");
            json.put("nombre", nombre);
            json.put("marca", marca);
            json.put("precio", precio);
            json.put("descripcion", descripcion);
            json.put("id", id);
            json.put("color", color.toString());
            json.put("stock", stock);
            json.put("resistencia", resistencia.toString());
            json.put("conexion", conexion.toString());
            json.put("microfono", microfono);
            json.put("inalambrico", inalambrico);
            json.put("radio", radio.toString());
            json.put("potencia", potencia);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }
    
    public static Parlante fromJSON(JSONObject json) {
        try {
            String nombre = json.getString("nombre");
            String marca = json.getString("marca");
            double precio = json.getDouble("precio");
            String descripcion = json.getString("descripcion");
            ColorP color = ColorP.valueOf(json.getString("color"));
            int stock = json.getInt("stock");
            ResistenciasP resistencia = ResistenciasP.valueOf(json.getString("resistencia"));
            Conexiones conexion = Conexiones.valueOf(json.getString("conexion"));
            boolean microfono = json.getBoolean("microfono");
            boolean inalambrico = json.getBoolean("inalambrico");
            Radios radio = Radios.valueOf(json.getString("radio"));
            short potencia = (short) json.getInt("potencia");
            int id = json.getInt("id");

            Parlante aux = new Parlante(nombre, marca, precio, descripcion, color, stock, resistencia, conexion, microfono, inalambrico, radio, potencia);
            aux.setId(id);
            return aux;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
