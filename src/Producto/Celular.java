package Producto;
import java.util.Scanner;
import Enumeradores.*;
import Excepciones.*;

public class Celular extends Producto{

    private int memoriaRam;
    private int memoriaInterna;
    private double pulgadas;
    private boolean dobleSim;
    private SoCelular so;
    private int bateria;

    public int escanearMemoriaInterna(){
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
    public SoCelular escanearSo(){
        Scanner sc = new Scanner(System.in);
        SoCelular so= null;
        boolean validInput;
        do {
            try {
                System.out.println("Sistemas Operativos disponibles: ");
                for (SoCelular s : SoCelular.values()) {
                    System.out.print(s+", ");
                }
                
                System.out.print("Sistema Operativo: ");
                String input = sc.nextLine().trim().toUpperCase();
                
                try {
                    so = SoCelular.valueOf(input);
                    validInput = true;
                } catch (IllegalArgumentException e) {
                    throw new InvalidSoException("Sistema Operativo inválido. Por favor, ingrese un valor válido.");
                }
                
            } catch (InvalidSoException e) {
                System.out.println(e.getMessage());
                validInput = false;
            }
        } while (!validInput);
        
        sc.close();
        return so;
    }
    public boolean escanearDobleSim(){
        Scanner sc = new Scanner(System.in);
        char valor;
        boolean validInput = false;
        do {
            try {
                System.out.print("Tiene Doble Sim (t/f): ");
                String input = sc.next();
                
                if (input.length() != 1) {
                    throw new InvalidCharacterException("Debe ingresar solo un carácter ('t' o 'f').");
                }
                
                valor = input.charAt(0);
                
                if (valor == 't' || valor == 'f') {
                    sc.close();
                    return valor == 't';
                } else {
                    throw new InvalidCharacterException("Carácter inválido. Debe ingresar 't' o 'f'.");
                }
                
            } catch (InvalidCharacterException e) {
                System.out.println(e.getMessage());
            }
        } while (!validInput);
        sc.close();
        return false;
    }
    public int escanearBateria(){
        Scanner sc = new Scanner(System.in);
        int bateria = -1;
        boolean validInput;
        do {
            try {
                System.out.print("Bateria (mAh): ");
                if (!sc.hasNextInt()) {
                    sc.next(); // Clear invalid input
                    throw new InvalidIntegerException("La bateria debe ser un número entero.");
                }
                bateria = sc.nextInt();
                sc.nextLine(); // Consume newline
                if (bateria <= 0) {
                    throw new InvalidIntegerException("La bateria debe ser un número positivo.");
                }
                validInput = true;
            } catch (InvalidIntegerException e) {
                System.out.println(e.getMessage());
                validInput = false;
            }
        } while (!validInput);
        sc.close();
        return bateria;
    }
    public double escanearPulgadas(){
        Scanner sc = new Scanner(System.in);
        double pulgadas = -1;
        boolean validInput;
        do {
            try {
                System.out.print("Tamaño Pantalla(pulgadas): ");
                if (!sc.hasNextDouble()) {
                    sc.next(); // Clear invalid input
                    throw new InvalidDoubleException("La pulgadas debe ser un número.");
                }
                pulgadas = sc.nextDouble();
                sc.nextLine(); // Consume newline
                if (pulgadas <= 0) {
                    throw new InvalidDoubleException("La pulgadas debe ser un número positivo.");
                }
                validInput = true;
            } catch (InvalidDoubleException e) {
                System.out.println(e.getMessage());
                validInput = false;
            }
        } while (!validInput);

        sc.close();
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
