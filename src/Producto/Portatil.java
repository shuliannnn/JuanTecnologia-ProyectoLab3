package Producto;
import org.json.JSONException;
import org.json.JSONObject;

import Enumeradores.*;

public class Portatil extends Computadora{

    private double pulgadas;
    private boolean ethernet;
    private boolean microfono;
    
    public Portatil(String nombre, String marca, double precio, String descripcion, ColorP color, int stock,
            String procesador, int memoriaRam, int memoriaInterna, String pVideo, boolean bluetooth, String mother,
            double pulgadas, boolean ethernet, boolean microfono) {
        super(nombre, marca, precio, descripcion, color, stock, procesador, memoriaRam, memoriaInterna, pVideo,
                bluetooth, mother);
        this.pulgadas = pulgadas;
        this.ethernet = ethernet;
        this.microfono = microfono;
    }

    public double getPulgadas() {
        return pulgadas;
    }

    public void setPulgadas(double pulgadas) {
        this.pulgadas = pulgadas;
    }

    public boolean isEthernet() {
        return ethernet;
    }

    public void setEthernet(boolean ethernet) {
        this.ethernet = ethernet;
    }

    public boolean isMicrofono() {
        return microfono;
    }

    public void setMicrofono(boolean microfono) {
        this.microfono = microfono;
    }

    @Override
    public String toString() {
        return "Portatil [pulgadas=" + pulgadas + ", ethernet=" + ethernet + ", microfono=" + microfono + "]";
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
            json.put("tipo", "Portatil");
            json.put("nombre", nombre);
            json.put("marca", marca);
            json.put("precio", precio);
            json.put("descripcion", descripcion);
            json.put("id", id);
            json.put("color", color.toString());
            json.put("stock", stock);
            json.put("procesador", procesador);
            json.put("memoriaRam", memoriaRam);
            json.put("memoriaInterna", memoriaInterna);
            json.put("pVideo", pVideo);
            json.put("bluetooth", bluetooth);
            json.put("mother", mother);
            json.put("pulgadas", pulgadas);
            json.put("ethernet", ethernet);
            json.put("microfono", microfono);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static Portatil fromJSON(JSONObject json) {
        try {
            String nombre = json.getString("nombre");
            String marca = json.getString("marca");
            double precio = json.getDouble("precio");
            String descripcion = json.getString("descripcion");
            ColorP color = ColorP.valueOf(json.getString("color"));
            int stock = json.getInt("stock");
            String procesador = json.getString("procesador");
            int memoriaRam = json.getInt("memoriaRam");
            int memoriaInterna = json.getInt("memoriaInterna");
            String pVideo = json.getString("pVideo");
            boolean bluetooth = json.getBoolean("bluetooth");
            String mother = json.getString("mother");
            double pulgadas = json.getDouble("pulgadas");
            boolean ethernet = json.getBoolean("ethernet");
            boolean microfono = json.getBoolean("microfono");
            int id = json.getInt("id");

            Portatil aux = new Portatil(nombre, marca, precio, descripcion, color, stock, procesador, memoriaRam, memoriaInterna, pVideo, bluetooth, mother, pulgadas, ethernet, microfono);
            aux.setId(id);
            return aux;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
