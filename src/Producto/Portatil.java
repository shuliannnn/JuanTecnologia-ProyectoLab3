package Producto;
import org.json.JSONException;
import org.json.JSONObject;
import App.App;
import Enumeradores.*;
import Excepciones.*;
import Interfaces.Memorias;


public class Portatil extends Computadora implements Memorias{

    private double pulgadas;
    private boolean ethernet;
    private boolean microfono;


    @Override
    public void escanearDatosComparables(){
        ///en producto
        marca = escanearMarca();
        nombre = escanearNombre();
        color = escanearColor();
        ///computadora
        memoriaInterna = escanearMemoriaInterna();
    }

    @Override
    public void escanearDatosEspecificos(){
        
        ///en computadora
        procesador = escanearProcesador();
        memoriaRam = escanearMemoriaRAM();
        pVideo = escanearPlacaVideo();
        bluetooth = escanearBluetooth();
        mother = escanearMother();
        /// en portatil
        pulgadas = escanearPulgadas();
         ethernet = escanearEthernet();
        /// microfono = 
        /// en producto
        descripcion = escanearDescripcion();
        stock = escanearStock();
        precio = escanearPrecio();
    }
    
    public double escanearPulgadas(){
        double pulgadas = -1;
        boolean validInput = false;
        do {
            try {
                System.out.print("Tamaño Pantalla(pulgadas): ");
                if (!App.sc.hasNextDouble()) {
                    App.sc.next(); // Clear invalid input
                    throw new InvalidDoubleException("La pulgadas debe ser un número.");
                }
                pulgadas = App.sc.nextDouble();
                App.sc.nextLine(); // Consume newline
                if (pulgadas <= 0) {
                    throw new InvalidDoubleException("La pulgadas debe ser un número positivo.");
                }
                validInput = true;
            } catch (InvalidDoubleException e) {
                System.out.println(e.getMessage());
            }
        } while (!validInput);

         
        return pulgadas;
    }

    public boolean escanearEthernet(){
        char ethernet;
        boolean validInput = false;
        do {
            try {
                System.out.print("Tiene Ethernet (t/f): ");
                String input = App.sc.nextLine();
                
                if (input.length() != 1) {
                    throw new InvalidCharacterException("Debe ingresar solo un carácter ('t' o 'f').");
                }
                
                ethernet = input.charAt(0);
                
                if (ethernet == 't' || ethernet == 'f') {
                     
                    return ethernet == 't';
                } else {
                    throw new InvalidCharacterException("Carácter inválido. Debe ingresar 't' o 'f'.");
                }
                
            } catch (InvalidCharacterException e) {
                System.out.println(e.getMessage());
            }
        } while (!validInput);

        return false;
    }

    public boolean escanearMicrofono(){
        char microfono;
        boolean validInput = false;
        do {
            try {
                System.out.print("Tiene microfono (t/f): ");
                String input = App.sc.nextLine();
                
                if (input.length() != 1) {
                    throw new InvalidCharacterException("Debe ingresar solo un carácter ('t' o 'f').");
                }
                
                microfono = input.charAt(0);
                
                if (microfono == 't' || microfono == 'f') {
                     
                    return microfono == 't';
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
        return "Portatil [pulgadas=" + pulgadas + ", ethernet=" + ethernet + ", microfono=" + microfono + "]";
    }

    /// Archivos -------------------------------------------------------------------------------------------------------------------------------------------------------

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
    
    /// Constructores getters y setters ------------------------------------------------------------------------------------------------------------------------------------------------

    public Portatil(String nombre, String marca, double precio, String descripcion, ColorP color, int stock,
            String procesador, int memoriaRam, int memoriaInterna, String pVideo, boolean bluetooth, String mother,
            double pulgadas, boolean ethernet, boolean microfono) {
        super(nombre, marca, precio, descripcion, color, stock, procesador, memoriaRam, memoriaInterna, pVideo,
                bluetooth, mother);
        this.pulgadas = pulgadas;
        this.ethernet = ethernet;
        this.microfono = microfono;
    }

    public Portatil() {
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
	public Producto clone() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'clone'");
	}
}
