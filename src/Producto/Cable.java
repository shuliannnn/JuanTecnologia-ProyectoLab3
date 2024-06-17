package Producto;

import java.util.InputMismatchException;

import org.json.JSONException;
import org.json.JSONObject;

import Enumeradores.ColorP;
import Excepciones.InvalidDoubleException;
import App.App;
import App.Menu;

public class Cable extends Producto{
    private double largo;

    @Override
    public String toString() {
        return "Cable: ID: " +(getId()==0?"No asignado":getId())+ "\n  | Marca: " + getMarca() + " | Nombre: " + getNombre() + " | Color: " + getColor() + " |" + "\n  | Stock: " + getStock() + " | Precio: " + getPrecio() + " |" +
          "\n      Largo: " + getLargo() + "\n      Descripcion: " + getDescripcion() + '\n';
    }
    
    @Override
    public void escanearDatosComparables() {
        ///en producto
        System.out.println("Cargando cable: ");
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

    @Override
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

    @Override
    public void escanearDatosEspecificos() {
        ///en producto
        descripcion = escanearDescripcion();
        stock = escanearStock();
        precio = escanearPrecio();
    }

    public static double escanearLargo(){
        double largo = -1;
        boolean validInput;
        do {
            try {
                System.out.print("Largo del cable(metros, Ej: 1,5): ");
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

    @Override
    public void modificarProducto(){
        int opcion;

        do {
            Menu.clearScreen();
            System.out.println("Producto con modificaciones actuales: ");
            System.out.println(this.toString() + '\n');
            System.out.println("Ingrese campo a modificar");
            System.out.println("1. Nombre");
            System.out.println("2. Marca");
            System.out.println("3. Precio");
            System.out.println("4. Descripcion");
            System.out.println("5. Color");
            System.out.println("6. Stock");
            System.out.println("7. Largo");
            System.out.println("0. Listo");
            System.out.print("-->");
            try {
                opcion = App.sc.nextInt();
                App.sc.nextLine();
            } catch (InputMismatchException ex) {
                App.sc.nextLine();
                opcion = -1;
            }
            
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
                    largo = escanearLargo();
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
    public Cable clone() {
        return new Cable(nombre, marca, precio, descripcion, color, stock, largo);
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

    public Cable() {
    }

    public double getLargo() {
        return largo;
    }

    public void setLargo(double largo) {
        this.largo = largo;
    }
}
