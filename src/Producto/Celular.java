package Producto;

import java.util.InputMismatchException;
import org.json.JSONException;
import org.json.JSONObject;

import Excepciones.InvalidCharacterException;
import Excepciones.InvalidDoubleException;
import Excepciones.InvalidEnumException;
import Excepciones.InvalidIntegerException;
import Interfaces.Memorias;
import App.*;
import Enumeradores.ColorP;
import Enumeradores.SoCelular;

public class Celular extends Producto implements Memorias{

    private int memoriaRam;
    private int memoriaInterna;
    private double pulgadas;
    private boolean dobleSim;
    private SoCelular so;
    private int bateria;

    /// Implementaciones Metodos Abstractos ----------------------------------------------------------------------------------------------------------------------------

    public void escanearDatosComparables(String print){
        ///en producto
        System.out.println(print + " celular:");
        marca = escanearMarca();
        nombre = escanearNombre();
        color = escanearColor();
        ///en celular
        memoriaInterna = escanearMemoriaInterna();
        memoriaRam = escanearMemoriaRAM();
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
            System.out.println("7. Memoria Ram");
            System.out.println("8. Memoria Interna");
            System.out.println("9. Pulgadas");
            System.out.println("10. Tiene doble sim");
            System.out.println("11. Bateria(mAh)");
            System.out.println("12. Sistema operativo");
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
                    memoriaRam = escanearMemoriaRAM();
                    break;
                case 8:
                    memoriaInterna = escanearMemoriaInterna();
                    break;
                case 9:
                    pulgadas = escanearPulgadas();
                    break;
                case 10:
                    dobleSim = escanearDobleSim();
                    break;
                case 11:
                    bateria = escanearBateria();
                    break;
                case 12:
                    so = escanearSo();
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
    
    @Override
    public Celular clone(){
        return new Celular(nombre, marca, precio, descripcion, color, stock, memoriaRam, memoriaInterna, pulgadas, dobleSim, so, bateria);
    }

    @Override
    public String toString() {
        return "Celular: ID: " + (getId()==0?"No asignado":getId()) + "\n | Marca: " + getMarca() + " | Nombre: " + getNombre() + " | Color: " + getColor() + " |" + "\n | Stock: " + getStock() +
         " | Precio: " + getPrecio() + " |" + " \n      Memoria RAM: " + getMemoriaRam() + "\n      Memoria Interna: " + getMemoriaInterna() + "\n      Pulgadas:" +  getPulgadas() +
        " \n      Doble SIM: " + (dobleSim ? "Si" : "No") + " \n      SO: " + getSo() + " \n      Bateria(mAh): " + getBateria() + "\n      Descripcion: " + getDescripcion() + '\n';
    }

    /// Scanners ----------------------------------------------------------------------------------------------------------------------------------------------
    
    public static SoCelular escanearSo(){
        SoCelular soCelular = null;
        boolean validInput;
        do {
            try {
                System.out.print("Sistema Operativo ( ");
                for (SoCelular s : SoCelular.values()) {
                    System.out.print(s+", ");
                }
                System.out.print("): ");
                
                try{
                    soCelular = SoCelular.valueOf(App.sc.nextLine().toUpperCase());
                    validInput = true;
                }catch(IllegalArgumentException e){
                    throw new InvalidEnumException("Sistema Operativo Invalido");
                }
            } catch (InvalidEnumException e) {
                System.out.println(e.getMessage());
                validInput = false;
            }
        } while (!validInput);
         
        return soCelular;
    }
    
    public static boolean escanearDobleSim(){
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


    public static int escanearBateria(){
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


    public static double escanearPulgadas(){
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
