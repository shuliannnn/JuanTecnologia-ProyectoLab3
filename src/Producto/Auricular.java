package Producto;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.Scanner;


import Enumeradores.*;
import Excepciones.InvalidEnumException;
public class Auricular extends Audio {
    private FormatoAuricular formato;
    private CanalesAudio canal;
    
    public Auricular(String nombre, String marca, double precio, String descripcion, ColorP color, int stock,
            ResistenciasP resistencia, Conexiones conexion, boolean microfono, boolean inalambrico,
            FormatoAuricular formato, CanalesAudio canal) {
        super(nombre, marca, precio, descripcion, color, stock, resistencia, conexion, microfono, inalambrico);
        this.formato = formato;
        this.canal = canal;
    }
    public FormatoAuricular getFormato() {
        return formato;
    }
    public void setFormato(FormatoAuricular formato) {
        this.formato = formato;
    }
    public CanalesAudio getCanal() {
        return canal;
    }
    public void setCanal(CanalesAudio canal) {
        this.canal = canal;
    }
    @Override
    public String toString() {
        return "Auricular [formato=" + formato + ", canal=" + canal + "]";
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        try{
            json.put("nombre", nombre);
            json.put("marca", marca);
            json.put("precio", precio);
            json.put("descripcion", descripcion);
            json.put("color", color.toString());
            json.put("stock", stock);
            json.put("resistencia", resistencia.toString());
            json.put("conexion", conexion.toString());
            json.put("microfono", microfono);
            json.put("inalambrico", inalambrico);
            json.put("formato", formato.toString());
            json.put("canal", canal.toString());
            json.put("id", id);
        }catch(JSONException e){
            e.printStackTrace();
        }
        return json;
        
    }
    
    public static Auricular fromJSON(JSONObject json) {
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
            FormatoAuricular formato = FormatoAuricular.valueOf(json.getString("formato"));
            CanalesAudio canal = CanalesAudio.valueOf(json.getString("canal"));
            int id = json.getInt("id");

            Auricular aux = new Auricular(nombre, marca, precio, descripcion, color, stock, resistencia, conexion, microfono, inalambrico, formato, canal);
            aux.setId(id);
            return aux;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public FormatoAuricular escanearFormato(){
        Scanner sc = new Scanner(System.in);
        FormatoAuricular formato= null;
        boolean validInput;
        do {
            try {
                System.out.println("Formatos disponibles: ");
                for (FormatoAuricular s : FormatoAuricular.values()) {
                    System.out.print(s+", ");
                }
                
                System.out.print("FormatoAuricular: ");
                String input = sc.nextLine().trim().toUpperCase();
                
                try {
                    formato = FormatoAuricular.valueOf(input);
                    validInput = true;
                } catch (IllegalArgumentException e) {
                    throw new InvalidEnumException("Formato inválido. Por favor, ingrese un valor válido.");
                }
            } catch (InvalidEnumException e) {
                System.out.println(e.getMessage());
                validInput = false;
            }
        } while (!validInput);
        
        sc.close();
        return formato;
    }
    
    public CanalesAudio escanearCanal(){
        Scanner sc = new Scanner(System.in);
        CanalesAudio canal= null;
        boolean validInput;
        do {
            try {
                System.out.println("Canales disponibles: ");
                for (CanalesAudio s : CanalesAudio.values()) {
                    System.out.print(s+", ");
                }
                
                System.out.print("Canal: ");
                String input = sc.nextLine().trim().toUpperCase();
                
                try {
                    canal = CanalesAudio.valueOf(input);
                    validInput = true;
                } catch (IllegalArgumentException e) {
                    throw new InvalidEnumException("Canal inválido. Por favor, ingrese un valor válido.");
                }
            } catch (InvalidEnumException e) {
                System.out.println(e.getMessage());
                validInput = false;
            }
        } while (!validInput);
        
        sc.close();
        return canal;
    }
    
    ///datos comparables en audio

    ///equals en audio

    @Override
    public void escanearDatosEspecificos() {
        ///en audio
        resistencia = escanearResistencia();
        microfono = escanearMicrofono();
        /// auricular
        formato = escanearFormato();
        canal = escanearCanal();
        ///en producto
        descripcion = escanearDescripcion();
        stock = escanearStock();
        precio = escanearPrecio();
        id = asignarId();
    }
    
}
