package Producto;

import org.json.JSONException;
import org.json.JSONObject;

import Enumeradores.ColorP;

public class Cable extends Producto{
    private double largo;

    public Cable(String nombre, String marca, double precio, String descripcion, ColorP color, int stock,
            double largo) {
        super(nombre, marca, precio, descripcion, color, stock);
        this.largo = largo;
    }

    public double getLargo() {
        return largo;
    }

    public void setLargo(double largo) {
        this.largo = largo;
    }

    @Override
    public String toString() {
        return "Cable [largo=" + largo + "]";
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
        try{
            json.put("tipo", "Cable");
            json.put("nombre", nombre);
            json.put("marca", marca);
            json.put("precio", precio);
            json.put("descripcion", descripcion);
            json.put("id", id);
            json.put("color", color.toString());
            json.put("stock", stock);
            json.put("largo", largo);
        }catch(JSONException e){
            e.printStackTrace();
        }
        return json;
    }

    
}
