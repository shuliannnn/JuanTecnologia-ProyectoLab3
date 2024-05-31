package Producto;
import Enumeradores.*;
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

    
}
