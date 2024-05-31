package Producto;
import Enumeradores.*;
public abstract class Periferico extends Producto{
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

    


}
