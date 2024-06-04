package Producto;
import java.util.Scanner;

import Enumeradores.*;
import Excepciones.InvalidEnumException;
public class Auricular extends Audio {
    private FormatoAuricular formato;
    private CanalesAudio canal;
    
    public Auricular(String nombre, String marca, double precio, String descripcion, ColorP color, int stock,
            ResistenciasP resistencia, Conexiones conexion, boolean microfono, boolean inalambrico,
            FormatoAuricular formato, CanalesAudio canal) {
        super(nombre, marca, precio, descripcion, color, stock, resistencia, conexion, microfono, inalambrico);
        this.formato = formato;
        this.canal = canal;
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
    @Override
    public String toString() {
        return "Auricular [formato=" + formato + ", canal=" + canal + "]";
    }
    
    ///utiliza el equals de audio

    
    public FormatoAuricular escanearFormato(){
        Scanner sc = new Scanner(System.in);
        FormatoAuricular formato= null;
        boolean validInput;
        do {
            try {
                System.out.println("Formatos disponibles: ");
                for (FormatoAuricular s : FormatoAuricular.values()) {
                    System.out.print(s+", ");
                }
                
                System.out.print("FormatoAuricular: ");
                String input = sc.nextLine().trim().toUpperCase();
                
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
        
        sc.close();
        return formato;
    }
    
    public CanalesAudio escanearCanal(){
        Scanner sc = new Scanner(System.in);
        CanalesAudio canal= null;
        boolean validInput;
        do {
            try {
                System.out.println("Canales disponibles: ");
                for (CanalesAudio s : CanalesAudio.values()) {
                    System.out.print(s+", ");
                }
                
                System.out.print("Canal: ");
                String input = sc.nextLine().trim().toUpperCase();
                
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
        
        sc.close();
        return canal;
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
        id = asignarId();
    }
    
}
