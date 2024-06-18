package Registros;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Registro {

    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy | HH:mm:ss");
    private LocalDateTime fecha;
    private String cambio;
    
    public Registro(LocalDateTime fecha, String cambio) {
        this.fecha = fecha;
        this.cambio = cambio;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getCambio() {
        return cambio;
    }

    public void setCambio(String cambio) {
        this.cambio = cambio;
    }

    @Override
    public String toString() {
        return "|Registro            |           Fecha: " + fecha.format(formatter) + 
        " |\n| Accion: " + cambio + " |";
    }

    

}
