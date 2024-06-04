package Producto;

import org.json.JSONException;
import org.json.JSONObject;

import Enumeradores.ColorP;
import Enumeradores.Conexiones;

public class Teclado extends Periferico{

    private short porcentaje;
    private boolean mecanico;
    private boolean cableRemovible;
    public Teclado(String nombre, String marca, double precio, String descripcion, ColorP color, int stock,
            boolean inalambrico, boolean rgb, Conexiones conexion, short porcentaje, boolean mecanico,
            boolean cableRemovible) {
        super(nombre, marca, precio, descripcion, color, stock, inalambrico, rgb, conexion);
        this.porcentaje = porcentaje;
        this.mecanico = mecanico;
        this.cableRemovible = cableRemovible;
    }
    public short getPorcentaje() {
        return porcentaje;
    }
    public void setPorcentaje(short porcentaje) {
        this.porcentaje = porcentaje;
    }
    public boolean isMecanico() {
        return mecanico;
    }
    public void setMecanico(boolean mecanico) {
        this.mecanico = mecanico;
    }
    public boolean isCableRemovible() {
        return cableRemovible;
    }
    public void setCableRemovible(boolean cableRemovible) {
        this.cableRemovible = cableRemovible;
    }
    @Override
    public String toString() {
        return "Teclado [porcentaje=" + porcentaje + ", mecanico=" + mecanico + ", cableRemovible=" + cableRemovible
                + "]";
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
            json.put("tipo", "Teclado");
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
            json.put("porcentaje", porcentaje);
            json.put("mecanico", mecanico);
            json.put("cableRemovible", cableRemovible);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static Teclado fromJSON(JSONObject json) {
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
            short porcentaje = (short) json.getInt("porcentaje");
            boolean mecanico = json.getBoolean("mecanico");
            boolean cableRemovible = json.getBoolean("cableRemovible");
            int id = json.getInt("id");

            Teclado aux = new Teclado(nombre, marca, precio, descripcion, color, stock, inalambrico, rgb, conexion, porcentaje, mecanico, cableRemovible);
            aux.setId(id);
            return aux;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
