package Producto;
import org.json.JSONException;
import org.json.JSONObject;

import Enumeradores.*;
public class Parlante extends Audio { 
    private Radios radio;
    private short potencia;

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
    public void escanearDatosComparables() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'escanearDatosComparables'");
    }

    @Override
    public void escanearDatosEspecificos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'escanearDatosEspecificos'");
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
