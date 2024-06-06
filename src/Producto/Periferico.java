package Producto;

import Enumeradores.*;
import Excepciones.InvalidCharacterException;
import Interfaces.Conectividad;
import App.App;

public abstract class Periferico extends Producto implements Conectividad{
    protected boolean inalambrico;
    protected boolean rgb;
    protected Conexiones conexion;
    
    
    public Periferico(String nombre, String marca, double precio, String descripcion, ColorP color, int stock,
            boolean inalambrico, boolean rgb, Conexiones conexion) {
        super(nombre, marca, precio, descripcion, color, stock);
        this.inalambrico = inalambrico;
        this.rgb = rgb;
        this.conexion = conexion;
    }

    public boolean isInalambrico() {
        return inalambrico;
    }

    public void setInalambrico(boolean inalambrico) {
        this.inalambrico = inalambrico;
    }

    public boolean isRgb() {
        return rgb;
    }

    public void setRgb(boolean rgb) {
        this.rgb = rgb;
    }

    public Conexiones getConexion() {
        return conexion;
    }

    public void setConexion(Conexiones conexion) {
        this.conexion = conexion;
    }

    @Override
    public String toString() {
        return "Periferico [inalambrico=" + inalambrico + ", rgb=" + rgb + ", conexion=" + conexion + "]";
    }

    public boolean escanearRgb(){
        char valor;
        boolean validInput = false;
        do {
            try {
                System.out.print("Es RGB (t/f): ");
                String input = App.sc.next();
                
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
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (rgb ? 1231 : 1237);
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
        Periferico other = (Periferico) obj;
        if (rgb != other.rgb)
            return false;
        return true;
    }

    @Override
    public void escanearDatosComparables() {
        ///en producto
        marca = escanearMarca();
        nombre = escanearNombre();
        color = escanearColor();
        ///en periferico
        rgb = escanearRgb();
    }

    
}
