package Producto;
import Enumeradores.*;
public class Parlante extends Audio { 
    private Radios radio;
    private short potencia;

    public Parlante(String nombre, String marca, double precio, String descripcion, ColorP color, int stock,
            ResistenciasP resistencia, Conexiones conexion, boolean microfono, boolean inalambrico, Radios radio,
            short potencia) {
        super(nombre, marca, precio, descripcion, color, stock, resistencia, conexion, microfono, inalambrico);
        this.radio = radio;
        this.potencia = potencia;
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
