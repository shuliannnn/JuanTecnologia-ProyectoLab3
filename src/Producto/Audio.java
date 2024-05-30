public abstract class Audio extends Producto{
    protected ResistenciasP resistencia;
    protected Conexiones conexion;
    protected boolean microfono;
    protected boolean inalambrico;


    public ResistenciasP getResistencia() {
        return resistencia;
    }
    public void setResistencia(ResistenciasP resistencia) {
        this.resistencia = resistencia;
    }
    public Conexiones getConexion() {
        return conexion;
    }
    public void setConexion(Conexiones conexion) {
        this.conexion = conexion;
    }
    public boolean tieneMicrofono() {
        return microfono;
    }
    public void setMicrofono(boolean microfono) {
        this.microfono = microfono;
    }
    public boolean esInalambrico() {
        return inalambrico;
    }
    public void setInalambrico(boolean inalambrico) {
        this.inalambrico = inalambrico;
    }
    
}