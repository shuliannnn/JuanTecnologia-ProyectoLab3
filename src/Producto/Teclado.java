package Producto;

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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    
}
