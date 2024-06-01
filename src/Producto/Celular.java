package Producto;
import java.util.Scanner;
import java.util.regex.Pattern;

import Almacenamiento.Inventario;
import Enumeradores.*;
import Excepciones.*;

public class Celular extends Producto{

    private int memoriaRam;
    private int memoriaInterna;
    private double pulgadas;
    private boolean dobleSim;
    private SoCelular so;
    private int bateria;

    protected int escanearMemoriaInterna(){
        Scanner sc = new Scanner(System.in);
        int memoria = -1;
        boolean validInput;

        do {
            try {
                System.out.print("Almacenamiento (GB): ");
                if (!sc.hasNextInt()) {
                    sc.next(); // Clear invalid input
                    throw new InvalidIntegerException("El almacenamiento debe ser un número entero.");
                }
                memoria = sc.nextInt();
                sc.nextLine(); // Consume newline
                if (memoria <= 0) {
                    throw new InvalidIntegerException("El almacenamiento debe ser un número positivo.");
                }
                validInput = true;
            } catch (InvalidIntegerException e) {
                System.out.println(e.getMessage());
                validInput = false;
            }
        } while (!validInput);
        sc.close();
        return memoria;
    }

    public int escanearMemoriaRAM(){
        Scanner sc = new Scanner(System.in);
        int memoria = -1;
        boolean validInput;
        do {
            try {
                System.out.print("RAM (GB): ");
                if (!sc.hasNextInt()) {
                    sc.next(); // Clear invalid input
                    throw new InvalidIntegerException("La memoria RAM debe ser un número entero.");
                }
                memoria = sc.nextInt();
                sc.nextLine(); // Consume newline
                if (memoria <= 0) {
                    throw new InvalidIntegerException("La memoria RAM debe ser un número positivo.");
                }
                validInput = true;
            } catch (InvalidIntegerException e) {
                System.out.println(e.getMessage());
                validInput = false;
            }
        } while (!validInput);

        sc.close();
        return memoria;
    }


    public void escanearDatosComparables(){
        marca = escanearMarca();
        nombre = escanearNombre();
        memoriaInterna = escanearMemoriaInterna();
        memoriaRam = escanearMemoriaRAM();
        color = escanearColor();
        
    }
    public void escanearDatosEspecificos(){

    }

    public <T extends Producto> boolean cargarProducto(Inventario<T> lista){
        
        escanearDatosComparables();//Pedimos al usuario ingresar los datos requeridos en el equals

        if(lista.contiene(this)){
                //podria llamarse a otro metodo de lista que haga esto
            System.out.println("El producto ya se encontraba en el sistema");
            System.out.println("Desea modificar stock?");
            System.out.println("Ingrese >0 para aumentar y <0 para decrementar");
            int nuevoStock = sc.nextInt();
            flag = lista.modificarStock(this, nuevoStock);
            //modificar archivo
        }
        else{
            System.out.println("Precio");
            System.out.println("Descripcion");
            System.out.println("Sistema Operativo");
            System.out.println("Bateria");
            System.out.println("Doble SIM");
            flag = lista.agregar(aux);
            //modificar archivo
        }
        return flag;
    
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

    @Override
    public String toString() {
        return "Celular [memoriaRam=" + memoriaRam + ", memoriaInterna=" + memoriaInterna + ", pulgadas=" + pulgadas
                + ", dobleSim=" + dobleSim + ", so=" + so + ", bateria=" + bateria + "]";
    }

    


}
