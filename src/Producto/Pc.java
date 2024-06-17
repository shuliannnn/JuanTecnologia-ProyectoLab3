package Producto;
import org.json.JSONException;
import org.json.JSONObject;

import Enumeradores.*;
import Excepciones.InvalidCharacterException;
import Interfaces.Memorias;
import App.App;
import App.Menu;

public class Pc extends Computadora implements Memorias{

    private boolean perifericos;
    private boolean wifi;

    @Override
    public void escanearDatosComparables() {
        System.out.println("Cargando Pc");
        ///en producto
        marca = escanearMarca();
        nombre = escanearNombre();
        color = escanearColor();
        ///en computadora
        memoriaInterna = escanearMemoriaInterna();
        /// en pc 
        perifericos = escanearPerifericos();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (perifericos ? 1231 : 1237);
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
        Pc other = (Pc) obj;
        if (perifericos != other.perifericos)
            return false;
        return true;
    }

    @Override
    public void escanearDatosEspecificos() {
        ///en computadora 
        procesador = escanearProcesador();
        memoriaRam = escanearMemoriaRAM();
        pVideo = escanearPlacaVideo();
        bluetooth = escanearBluetooth();
        mother = escanearMother();
        /// en pc 
        wifi = escanearWifi();
        ///producto
        descripcion = escanearDescripcion();
        stock = escanearStock();
        precio = escanearPrecio();
    }

    public boolean escanearPerifericos(){
        char valor;
        boolean validInput = false;
        do {
            try {
                System.out.print("Incluye perifericos (t/f): ");
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

    public boolean escanearWifi(){
        char valor;
        boolean validInput = false;
        do {
            try {
                System.out.print("Tiene Placa Wifi (t/f): ");
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
            System.out.println("7. Proceador");
            System.out.println("8. Memoria Ram");
            System.out.println("9. Memoria Interna");
            System.out.println("10. Placa de Video");
            System.out.println("11. Bluetooth");
            System.out.println("12. Mother");
            System.out.println("13. Tiene Perifericos");
            System.out.println("14. TIene WiFi");
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
                    procesador = escanearProcesador();
                    break;
                case 8:
                    memoriaRam = escanearMemoriaRAM();
                    break;
                case 9:
                    memoriaInterna = escanearMemoriaInterna();
                    break;
                case 10:
                    pVideo = escanearPlacaVideo();
                    break;
                case 11:
                    bluetooth = escanearBluetooth();
                    break;
                case 12:
                    mother = escanearMother();
                    break;
                case 13:
                    perifericos = escanearPerifericos();
                    break;
                case 14:
                    wifi = escanearWifi();
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
        return "PC: ID: " + (getId()==0?"No asignado":getId()) + "\n  | Marca: " + getMarca() + " | Nombre: " + getNombre() + " | Color: " + getColor() + " |" + "\n  | Stock: " + getStock() + " | Precio: " + getPrecio() + " |" +
          "\n      Procesador: " + getProcesador() + " \n      RAM: " + getMemoriaRam() + "\n      Almacenamiento: " + getMemoriaInterna() +
            "\n      Placa de Video: " + getpVideo() + "\n      Bluetooth: " + (bluetooth ? "Si" : "No") + "\n      Mother: " + getMother() +
            "\n      Tiene Perifericos: " + (perifericos ? "Si" : "No") + "\n      Wifi: " + (wifi ? "Si" : "No") + "\n      Descripcion: " + getDescripcion() + '\n';
    }
    
	@Override
	public Pc clone() {
		return new Pc(nombre, marca, precio, descripcion, color, stock, procesador, memoriaRam, memoriaInterna, pVideo, bluetooth, mother, perifericos, wifi);
	}
    
    /// Archivos -------------------------------------------------------------------------------------------------------------------------------------------------------
    
    @Override
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        try {
            json.put("tipo", "PC");
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
            json.put("perifericos", perifericos);
            json.put("wifi", wifi);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }
    
    public static Pc fromJSON(JSONObject json) {
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
            boolean perifericos = json.getBoolean("perifericos");
            boolean wifi = json.getBoolean("wifi");
            int id = json.getInt("id");

            Pc aux = new Pc(nombre, marca, precio, descripcion, color, stock, procesador, memoriaRam, memoriaInterna, pVideo, bluetooth, mother, perifericos, wifi);
            aux.setId(id);
            return aux;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /// Constructores getters y setters ------------------------------------------------------------------------------------------------------------------------------------------------
    
    public Pc(String nombre, String marca, double precio, String descripcion, ColorP color, int stock,
            String procesador, int memoriaRam, int memoriaInterna, String pVideo, boolean bluetooth, String mother,
            boolean perifericos, boolean wifi) {
        super(nombre, marca, precio, descripcion, color, stock, procesador, memoriaRam, memoriaInterna, pVideo,
                bluetooth, mother);
        this.perifericos = perifericos;
        this.wifi = wifi;
    }

    

    public Pc() {
    }


    public boolean isPerifericos() {
        return perifericos;
    }

    public void setPerifericos(boolean perifericos) {
        this.perifericos = perifericos;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }
 
}
