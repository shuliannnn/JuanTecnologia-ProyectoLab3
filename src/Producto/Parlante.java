package Producto;
import org.json.JSONException;
import org.json.JSONObject;

import Enumeradores.*;
import Excepciones.InvalidEnumException;
import Excepciones.InvalidIntegerException;
import App.App;

public class Parlante extends Audio { 
    private Radios radio;
    private short potencia;

    public Radios escanearRadio(){
        Radios radio= null;
        boolean validInput;
        do {
            try {
                System.out.println("Radios disponibles: ");
                for (Radios s : Radios.values()) {
                    System.out.print(s+", ");
                }
                
                System.out.print("Radio: ");
                String input = App.sc.nextLine().trim().toUpperCase();
                
                try {
                    radio = Radios.valueOf(input);
                    validInput = true;
                } catch (IllegalArgumentException e) {
                    throw new InvalidEnumException("Radio inválida. Por favor, ingrese un valor válido.");
                }
            } catch (InvalidEnumException e) {
                System.out.println(e.getMessage());
                validInput = false;
            }
        } while (!validInput);
        
         
        return radio;
    }

    public short escanearPotencia(){
        short potencia = -1;
        boolean validInput;
        do {
            try {
                System.out.print("Potencia (W): ");
                if (!App.sc.hasNextShort()) {
                    App.sc.next(); // Clear invalid input
                    throw new InvalidIntegerException("La potencia debe ser un número entero.");
                }
                potencia = App.sc.nextShort();
                App.sc.nextLine(); // Consume newline
                if (potencia <= 0) {
                    throw new InvalidIntegerException("La potencia debe ser un número positivo.");
                }
                validInput = true;
            } catch (InvalidIntegerException e) {
                System.out.println(e.getMessage());
                validInput = false;
            }
        } while (!validInput);

         
        return potencia;
    }
   
   ///equals de audio

   ///datos comparables de audio

   @Override
    public void escanearDatosComparables() {
        System.out.println("Cargando Parlante: ");
        super.escanearDatosComparables();
    }

   @Override
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
            System.out.println("11. Radio");
            System.out.println("12. Potencia");
            System.out.println("0. Listo");
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
                    precio = escanearPrecio();
                    break;
                case 4:
                    descripcion = escanearDescripcion();
                    break;
                case 5:
                    color = escanearColor();
                    break;
                case 6:
                    stock = escanearStock();
                    break;
                case 7:
                    resistencia = escanearResistencia();
                    break;
                case 8:
                    conexion = escanearConexion();
                    break;
                case 9:
                    microfono = escanearMicrofono();
                    break;
                case 10:
                    inalambrico = escanearInalambrico();
                    break;
                case 11:
                    radio = escanearRadio();
                    break;
                case 12:
                    potencia = escanearPotencia();
                    break;
                case 0:
                    break;
            
                default:
                    System.out.println("Ingrese un caracter válido");
                    break;
            }


        } while (opcion != 0);
    
    }
   
    @Override
    public String toString() {
        return "Parlante: ID: " + getId() + "\n  | Marca: " + getMarca() + " | Nombre: " + getNombre() + " | Color: " + getColor() + " |" + "\n  | Stock: " + getStock() +
         " | Precio: " + getPrecio() + " |" + " \n      Resistencia: " + getResistencia() + "\n      Conexion: " + getConexion() + "\n      Microfono" +  (microfono ? "Si" : "No") +
        " \n      Es inalambrico: " + (inalambrico ? "Si" : "No") + " \n      Radio: " + getRadio() + " \n      Potencia: " + getPotencia() + "\n     Descripcion: " + getDescripcion() + '\n';
    }

    @Override
    public Parlante clone() {
        return new Parlante(nombre, marca, precio, descripcion, color, stock, resistencia, conexion, microfono, inalambrico, radio, potencia);
    }
    @Override
    public void escanearDatosEspecificos() {
        ///en audio
        resistencia = escanearResistencia();
        microfono = escanearMicrofono();
        /// auricular
        potencia = escanearPotencia();
        radio = escanearRadio();
        ///en producto
        descripcion = escanearDescripcion();
        stock = escanearStock();
        precio = escanearPrecio();
    }


    /// Archivos -------------------------------------------------------------------------------------------------------------------------------------------------------
    
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
    
/// Constructores getters y setters ------------------------------------------------------------------------------------------------------------------------------------------------
    
    public Parlante(String nombre, String marca, double precio, String descripcion, ColorP color, int stock,
            ResistenciasP resistencia, Conexiones conexion, boolean microfono, boolean inalambrico, Radios radio,
            short potencia) {
        super(nombre, marca, precio, descripcion, color, stock, resistencia, conexion, microfono, inalambrico);
        this.radio = radio;
        this.potencia = potencia;
    }
    
    public Parlante() {
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

}
