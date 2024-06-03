package Producto;

import Enumeradores.*;

public class Mouse extends Periferico{
    private int dpi;
    private Sensores sensor;
    public Mouse(String nombre, String marca, double precio, String descripcion, ColorP color, int stock,
            boolean inalambrico, boolean rgb, Conexiones conexion, int dpi, Sensores sensor) {
        super(nombre, marca, precio, descripcion, color, stock, inalambrico, rgb, conexion);
        this.dpi = dpi;
        this.sensor = sensor;
    }
    public int getDpi() {
        return dpi;
    }
    public void setDpi(int dpi) {
        this.dpi = dpi;
    }
    public Sensores getSensor() {
        return sensor;
    }
    public void setSensor(Sensores sensor) {
        this.sensor = sensor;
    }
    @Override
    public String toString() {
        return "Mouse [dpi=" + dpi + ", sensor=" + sensor + "]";
    }
    @Override
    public void escanearDatosComparables() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'escanearDatosComparables'");
    }
    @Override
    public void escanearDatosEspecificos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'escanearDatosEspecificos'");
    }

    
}
