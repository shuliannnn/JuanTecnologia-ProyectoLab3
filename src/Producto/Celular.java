package Producto;

import org.json.JSONException;
import org.json.JSONObject;

import Enumeradores.*;
import Excepciones.*;
import Interfaces.Memorias;
import App.App;

public class Celular extends Producto implements Memorias{

    private int memoriaRam;
    private int memoriaInterna;
    private double pulgadas;
    private boolean dobleSim;
    private SoCelular so;
    private int bateria;

    
    public SoCelular escanearSo(){
        SoCelular soCelular = null;
        boolean validInput;
        do {
            try {
                System.out.print("Sistema Operativo: ");
                soCelular = SoCelular.valueOf(App.sc.nextLine().toUpperCase());
                validInput = true;
            } catch (IllegalArgumentException e) {
                System.out.println("El sistema operativo debe ser uno de los valores especificados.");
                validInput = false;
            }
        } while (!validInput);
         
        return soCelular;
    }
    
    public boolean escanearDobleSim(){
        char valor;
        boolean validInput = false;
        do {
            try {
                System.out.print("Tiene Doble Sim (t/f): ");
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


    public int escanearBateria(){
        int bateria = -1;
        boolean validInput = false;
        do {
            try {
                System.out.print("Bateria (mAh): ");
                if (!App.sc.hasNextInt()) {
                    App.sc.next(); // Clear invalid input
                    throw new InvalidIntegerException("La bateria debe ser un número entero.");
                }
                bateria = App.sc.nextInt();
                App.sc.nextLine(); // Consume newline
                if (bateria <= 0) {
                    throw new InvalidIntegerException("La bateria debe ser un número positivo.");
                }
                validInput = true;

            } catch (InvalidIntegerException e) {
                System.out.println(e.getMessage());
            }
        } while (!validInput);
         
        return bateria;
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
    
    public void escanearDatosComparables(){
        ///en producto
        marca = escanearMarca();
        nombre = escanearNombre();
        color = escanearColor();
        ///en celular
        memoriaInterna = escanearMemoriaInterna();
        memoriaRam = escanearMemoriaRAM();
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + memoriaRam;
        result = prime * result + memoriaInterna;
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
        Celular other = (Celular) obj;
        if (memoriaRam != other.memoriaRam)
            return false;
        if (memoriaInterna != other.memoriaInterna)
            return false;
        return true;
    }
    
    public void escanearDatosEspecificos(){
        ///en celular
        dobleSim = escanearDobleSim();
        so = escanearSo();
        bateria = escanearBateria();
        pulgadas = escanearPulgadas();
        ///en producto
        descripcion = escanearDescripcion();
        stock = escanearStock();
        precio = escanearPrecio();
        id = asignarId();
    }


    @Override
    public String toString() {
        return "Celular [memoriaRam=" + memoriaRam + ", memoriaInterna=" + memoriaInterna + ", pulgadas=" + pulgadas
                + ", dobleSim=" + dobleSim + ", so=" + so + ", bateria=" + bateria + "]";
    }

    /// Archivos -------------------------------------------------------------------------------------------------------------------------------------------------------
    
    @Override
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        try{
            json.put("tipo", "Celular");
            json.put("nombre", nombre);
            json.put("marca", marca);
            json.put("precio", precio);
            json.put("descripcion", descripcion);
            json.put("id", id);
            json.put("color", color.toString());
            json.put("memoriaInterna", memoriaInterna);
            json.put("memoriaRam", memoriaRam);
            json.put("pulgadas", pulgadas);
            json.put("bateria", bateria);
            json.put("dobleSim", dobleSim);
            json.put("so", so);
            json.put("stock", stock);

        } catch(JSONException e){
            e.printStackTrace();
        }
        return json;
    }

    public static Celular fromJSON(JSONObject json) {
        try {
            String nombre = json.getString("nombre");
            String marca = json.getString("marca");
            double precio = json.getDouble("precio");
            String descripcion = json.getString("descripcion");
            ColorP color = ColorP.valueOf(json.getString("color"));
            int stock = json.getInt("stock");
            SoCelular sistemaOperativo = SoCelular.valueOf(json.getString("so"));
            int memoriaInterna = json.getInt("memoriaInterna");
            int memoriaRam = json.getInt("memoriaRam");
            double pulgadas = json.getDouble("pulgadas");
            int bateria = json.getInt("bateria");
            boolean dobleSim = json.getBoolean("dobleSim");
            int id = json.getInt("id");
            Celular aux = new Celular(nombre, marca, precio, descripcion, color, stock, memoriaRam, memoriaInterna, pulgadas, dobleSim, sistemaOperativo, bateria);
            aux.setId(id);
            return aux;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    
    /// Constructores getters y setters ------------------------------------------------------------------------------------------------------------------------------------------------

    public Celular(){
        super();
    }

    public Celular(String nombre, String marca, double precio, String descripcion, ColorP color, int stock,
            int memoriaRam, int memoriaInterna, double pulgadas, boolean dobleSim, SoCelular so, int bateria) {
        super(nombre, marca, precio, descripcion, color, stock);
        this.memoriaRam = memoriaRam;
        this.memoriaInterna = memoriaInterna;
        this.pulgadas = pulgadas;
        this.dobleSim = dobleSim;
        this.so = so;
        this.bateria = bateria;
    }

    public int getMemoriaRam() {
        return memoriaRam;
    }

    public void setMemoriaRam(int memoriaRam) {
        this.memoriaRam = memoriaRam;
    }

    public int getMemoriaInterna() {
        return memoriaInterna;
    }

    public void setMemoriaInterna(int memoriaInterna) {
        this.memoriaInterna = memoriaInterna;
    }

    public double getPulgadas() {
        return pulgadas;
    }

    public void setPulgadas(double pulgadas) {
        this.pulgadas = pulgadas;
    }

    public boolean isDobleSim() {
        return dobleSim;
    }

    public void setDobleSim(boolean dobleSim) {
        this.dobleSim = dobleSim;
    }

    public SoCelular getSo() {
        return so;
    }

    public void setSo(SoCelular so) {
        this.so = so;
    }

    public int getBateria() {
        return bateria;
    }

    public void setBateria(int bateria) {
        this.bateria = bateria;
    }
}
