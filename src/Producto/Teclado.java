package Producto;

import org.json.JSONException;
import org.json.JSONObject;

import Enumeradores.*;
import Excepciones.*;
import App.App;

public class Teclado extends Periferico{

    private short porcentaje;
    private boolean mecanico;
    private boolean cableRemovible;
    
    /// comparables en periferico

    ///equals en periferico

    @Override
    public void escanearDatosEspecificos() {
        ///en periferico
        conexion = escanearConexion();
        inalambrico = escanearInalambrico();
        ///en teclado
        porcentaje = escanearPorcentaje();
        mecanico = escanearMecanico();
        cableRemovible = escanearCableRemovible();
        ///en producto
        descripcion = escanearDescripcion();
        stock = escanearStock();
        precio = escanearPrecio();
    }
    
    public short escanearPorcentaje(){
        short porcentaje = -1;
        boolean validInput;
        do {
            try {
                System.out.print("Porcentaje (%): ");
                if (!App.sc.hasNextShort()) {
                    App.sc.next(); // Clear invalid input
                    throw new InvalidIntegerException("El porcentaje debe ser un número entero.");
                }
                porcentaje = App.sc.nextShort();
                App.sc.nextLine(); // Consume newline
                if (porcentaje <= 0) {
                    throw new InvalidIntegerException("El porcentaje debe ser un número positivo.");
                }
                validInput = true;
            } catch (InvalidIntegerException e) {
                System.out.println(e.getMessage());
                validInput = false;
            }
        } while (!validInput);

         
        return porcentaje;
    }

    public boolean escanearMecanico(){
        char valor;
        boolean validInput = false;
        do {
            try {
                System.out.print("Es mecanico (t/f): ");
                String input = App.sc.nextLine();
                
                if (input.length() != 1) {
                    throw new InvalidCharacterException("Debe ingresar solo un carácter ('t' o 'f').");
                }
                
                valor = input.charAt(0);
                
                if (valor == 't' || valor == 'f') {
                     
                    return valor == 't';
                } else {
                    throw new InvalidCharacterException("Carácter inválido. Debe ingresar 't' o 'f'.");
                }
                
            } catch (InvalidCharacterException e) {
                System.out.println(e.getMessage());
            }
        } while (!validInput);
         
        return false;
    }

    public boolean escanearCableRemovible(){
        char valor;
        boolean validInput = false;
        do {
            try {
                System.out.print("Posee cable removible (t/f): ");
                String input = App.sc.nextLine();
                
                if (input.length() != 1) {
                    throw new InvalidCharacterException("Debe ingresar solo un carácter ('t' o 'f').");
                }
                
                valor = input.charAt(0);
                
                if (valor == 't' || valor == 'f') {
                     
                    return valor == 't';
                } else {
                    throw new InvalidCharacterException("Carácter inválido. Debe ingresar 't' o 'f'.");
                }
                
            } catch (InvalidCharacterException e) {
                System.out.println(e.getMessage());
            }
        } while (!validInput);
         
        return false;
    }
    
    @Override
    public void modificarProducto(){
        throw new RuntimeException("Metodo no codeado");
    }
    
    @Override
    public String toString() {
        return "Teclado [porcentaje=" + porcentaje + ", mecanico=" + mecanico + ", cableRemovible=" + cableRemovible
                + "]";
    }
    
   /// Archivos -------------------------------------------------------------------------------------------------------------------------------------------------------

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
    
    
/// Constructores getters y setters ------------------------------------------------------------------------------------------------------------------------------------------------ 
   
    public Teclado(String nombre, String marca, double precio, String descripcion, ColorP color, int stock,
            boolean inalambrico, boolean rgb, Conexiones conexion, short porcentaje, boolean mecanico,
            boolean cableRemovible) {
        super(nombre, marca, precio, descripcion, color, stock, inalambrico, rgb, conexion);
        this.porcentaje = porcentaje;
        this.mecanico = mecanico;
        this.cableRemovible = cableRemovible;
    }
    
    public Teclado() {
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
	public Producto clone() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'clone'");
	}
}
