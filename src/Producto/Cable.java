package Producto;

import org.json.JSONException;
import org.json.JSONObject;

import Enumeradores.ColorP;
import Excepciones.InvalidDoubleException;
import App.App;

public class Cable extends Producto{
    private double largo;

    @Override
    public String toString() {
        return "Cable [largo=" + largo + "]";
    }

    @Override
    public void escanearDatosComparables() {
        ///en producto
        marca = escanearMarca();
        nombre = escanearNombre();
        color = escanearColor();
        ///en cable
        largo = escanearLargo();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(largo);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cable other = (Cable) obj;
        if (Double.doubleToLongBits(largo) != Double.doubleToLongBits(other.largo))
            return false;
        return true;
    }

    public void escanearDatosEspecificos() {
        ///en producto
        descripcion = escanearDescripcion();
        stock = escanearStock();
        precio = escanearPrecio();
        id = asignarId();
    }

    public double escanearLargo(){
        double largo = -1;
        boolean validInput;
        do {
            try {
                System.out.print("Largo del cable(metros, Ej: 1.5): ");
                if (!App.sc.hasNextDouble()) {
                    App.sc.next(); // Clear invalid input
                    throw new InvalidDoubleException("El largo debe ser un número.");
                }
                largo = App.sc.nextDouble();
                App.sc.nextLine(); // Consume newline
                if (largo <= 0) {
                    throw new InvalidDoubleException("El largo debe ser un número positivo.");
                }
                validInput = true;
            } catch (InvalidDoubleException e) {
                System.out.println(e.getMessage());
                validInput = false;
            }
        } while (!validInput);

         
        return largo;
    }

    /// Archivos -------------------------------------------------------------------------------------------------------------------------------------------------------
    
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

    public static Cable fromJSON(JSONObject json) {
        try {
            String nombre = json.getString("nombre");
            String marca = json.getString("marca");
            double precio = json.getDouble("precio");
            String descripcion = json.getString("descripcion");
            ColorP color = ColorP.valueOf(json.getString("color"));
            int stock = json.getInt("stock");
            double largo = json.getDouble("largo");
            int id = json.getInt("id");
            
            Cable aux = new Cable(nombre, marca, precio, descripcion, color, stock, largo);
            aux.setId(id);
            return aux;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }


    /// Constructores getters y setters ------------------------------------------------------------------------------------------------------------------------------------------------

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
}
