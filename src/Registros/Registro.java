package Registros;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.JSONException;
import org.json.JSONObject;

public class Registro {

    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy | HH:mm:ss");
    private LocalDateTime fecha;
    private String cambio;
    
    /// Mostrar -------------------------------------------------------------------------------------------------------------------------------------------------------

    @Override
     public String toString() {
        StringBuilder sb = new StringBuilder();
        
        // Header
        sb.append("-----------------------------------------------------\n");
        sb.append(String.format("%-20s | %s\n", "Fecha", fecha.format(formatter)));
        sb.append(String.format("%-20s | %s\n", "--------------------", "------------------------------"));

        // Cambio section
        String[] lineasCambio = cambio.split("\n");
        sb.append(lineasCambio[0]);
        sb.append("\n-----------------------------------------------------\n");
        for (int i = 1; i < lineasCambio.length; i++) {
            sb.append(String.format("%s\n", lineasCambio[i]));
        }
        sb.append("-----------------------------------------------------\n");

        return sb.toString();
    }

   
   /// Archivos -------------------------------------------------------------------------------------------------------------------------------------------------------
   
    public JSONObject toJSON(){
            JSONObject json = new JSONObject();
        try{
            json.put("fecha", fecha.toString());
            json.put("cambio",cambio);
        }catch(JSONException e){
            e.printStackTrace();
        }
            return json;
    
    }
    public static Registro fromJSON(JSONObject json){
        Registro registro = new Registro();
        try{
            registro.fecha = LocalDateTime.parse(json.getString("fecha"));
            registro.cambio = json.getString("cambio");
        }
        catch(JSONException e){
            e.printStackTrace();
            return null;
        }
        return registro;
    }

    /// Constructores getters y setters ------------------------------------------------------------------------------------------------------------------------------------------------
    
    
    public Registro(LocalDateTime fecha, String cambio) {
        this.fecha = fecha;
        this.cambio = cambio;
    }
    public Registro(){
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getCambio() {
        return cambio;
    }

    public void setCambio(String cambio) {
        this.cambio = cambio;
    }
}
