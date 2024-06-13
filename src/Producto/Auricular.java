package Producto;

import org.json.JSONException;
import org.json.JSONObject;

import Enumeradores.*;
import Excepciones.InvalidEnumException;
import App.App;

public class Auricular extends Audio {
    private FormatoAuricular formato;
    private CanalesAudio canal;
    
    
    @Override
    public String toString() {
        return "Auricular [formato=" + formato + ", canal=" + canal + "]";
    }

    
    public FormatoAuricular escanearFormato(){
        FormatoAuricular formato= null;
        boolean validInput;
        do {
            try {
                System.out.println("Formatos disponibles: ");
                for (FormatoAuricular s : FormatoAuricular.values()) {
                    System.out.print(s+", ");
                }
                
                System.out.print("FormatoAuricular: ");
                String input = App.sc.nextLine().trim().toUpperCase();
                
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
        
         
        return formato;
    }
    
    public CanalesAudio escanearCanal(){
        CanalesAudio canal= null;
        boolean validInput;
        do {
            try {
                System.out.println("Canales disponibles: ");
                for (CanalesAudio s : CanalesAudio.values()) {
                    System.out.print(s+", ");
                }
                
                System.out.print("Canal: ");
                String input = App.sc.nextLine().trim().toUpperCase();
                
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
        
         
        return canal;
    }

    public void modificarProducto(){
        int opcion;

        
        do {
            System.out.println("Producto con modificaciones actuales: ");
            System.out.println(this.toString() + '\n');
            System.out.println("Ingrese campo a modificar");
            System.out.println("1. Nombre");
            System.out.println("2. Marca");
            System.out.println("3. Precio");
            System.out.println("4. Descripcion");
            System.out.println("5. Color");
            System.out.println("6. Stock");
            System.out.println("7. Resistencia");
            System.out.println("8. Conexion");
            System.out.println("9. Tiene Microfono");
            System.out.println("10. Es Inalambrico");
            System.out.println("11. Formato");
            System.out.println("12. Canal");
            System.out.println("0. Salir");
            opcion = App.sc.nextInt();
            App.sc.nextLine();///buffer
            
            switch (opcion) {
                case 1:
                    nombre = escanearNombre();
                    break;
                case 2:
                    marca = escanearMarca();   
                    break;
                case 3:
                        
                    break;
                case 4:
                        
                    break;
                case 5:
                        
                    break;
                case 6:
                        
                    break;
                case 7:
                        
                    break;
                case 8:
                        
                    break;
                case 9:
                        
                    break;
                case 10:
                        
                    break;
                case 11:
                        
                    break;
                case 12:
                        
                    break;
                case 13:
                        
                    break;
                case 0:
                    System.out.println("");
                    break;
            
                default:
                    System.out.println("Ingrese un caracter válido");
                    break;
            }


        } while (opcion != 0);

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
    }

    @Override
    public Auricular clone(){
        return new Auricular(this.nombre, this.marca, this.precio, this.descripcion, this.color, this.stock, this.resistencia, this.conexion, this.microfono, this.inalambrico, this.formato, this.canal);
    }

    
    /// Archivos -------------------------------------------------------------------------------------------------------------------------------------------------------
    
    @Override
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        try{
            json.put("tipo", "Auricular");
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


    /// Constructores getters y setters ------------------------------------------------------------------------------------------------------------------------------------------------

    public Auricular(String nombre, String marca, double precio, String descripcion, ColorP color, int stock,
            ResistenciasP resistencia, Conexiones conexion, boolean microfono, boolean inalambrico,
            FormatoAuricular formato, CanalesAudio canal) {
        super(nombre, marca, precio, descripcion, color, stock, resistencia, conexion, microfono, inalambrico);
        this.formato = formato;
        this.canal = canal;
    }
    
    
    public Auricular() {
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
}
