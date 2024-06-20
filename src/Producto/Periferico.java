package Producto;

import Enumeradores.*;
import Excepciones.InvalidCharacterException;
import Interfaces.Conectividad;
import App.App;

public abstract class Periferico extends Producto implements Conectividad{
    protected boolean inalambrico;
    protected boolean rgb;
    protected Conexiones conexion;
    
/// Implementaciones Metodos Abstractos ----------------------------------------------------------------------------------------------------------------------------
    
    public void escanearDatosComparables() {
        ///en producto
        marca = escanearMarca();
        nombre = escanearNombre();
        color = escanearColor();
        ///en periferico
        rgb = escanearRgb();
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


/// Scanners ------------------------------------------------------------------------------------------------------------------------------------

    public boolean escanearRgb(){
        char valor;
        boolean validInput = false;
        do {
            try {
                System.out.print("Es RGB (t/f): ");
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

    /// Constructores getters y setters ------------------------------------------------------------------------------------------------------------------------------------------------
    
    public Periferico(String nombre, String marca, double precio, String descripcion, ColorP color, int stock,
            boolean inalambrico, boolean rgb, Conexiones conexion) {
        super(nombre, marca, precio, descripcion, color, stock);
        this.inalambrico = inalambrico;
        this.rgb = rgb;
        this.conexion = conexion;
    }
    

    public Periferico() {
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
    
}
