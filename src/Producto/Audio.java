package Producto;


import Excepciones.InvalidCharacterException;
import Excepciones.InvalidEnumException;
import Interfaces.Conectividad;
import App.App;
import Enumeradores.ColorP;
import Enumeradores.Conexiones;
import Enumeradores.ResistenciasP;

public abstract class Audio extends Producto implements Conectividad {
    protected ResistenciasP resistencia;
    protected Conexiones conexion;
    protected boolean microfono;
    protected boolean inalambrico;

    /// Implementaciones Metodos Abstractos ------------------------------------------------------------------------------------------
    
    @Override
    public String toString() {
        return "Audio [resistencia=" + resistencia + ", conexion=" + conexion + ", microfono=" + microfono
                + ", inalambrico=" + inalambrico + "]";
    }

    public void escanearDatosComparables() {
        /// en producto
        marca = escanearMarca();
        nombre = escanearNombre();
        color = escanearColor();
        /// en audio
        conexion = escanearConexion();
        inalambrico = escanearInalambrico();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Audio other = (Audio) obj;
        if (conexion != other.conexion)
            return false;
        if (inalambrico != other.inalambrico)
            return false;
        return true;
    }

    ///Scanners -------------------------------------------------------------------------------------------------------------------------
    
    public ResistenciasP escanearResistencia() {
        ResistenciasP res = null;
        boolean validInput;
        do {
            try {
                System.out.println("Resistencias disponibles: ");
                for (ResistenciasP s : ResistenciasP.values()) {
                    System.out.print(s + ", ");
                }

                System.out.print("Resistencia: ");
                String input = App.sc.nextLine().trim().toUpperCase();

                try {
                    res = ResistenciasP.valueOf(input);
                    validInput = true;
                } catch (IllegalArgumentException e) {
                    throw new InvalidEnumException("Resistencia inválida. Por favor, ingrese un valor válido.");
                }
            } catch (InvalidEnumException e) {
                System.out.println(e.getMessage());
                validInput = false;
            }
        } while (!validInput);

         
        return res;
    }

    public boolean escanearMicrofono() {
         
        char valor;
        boolean validInput = false;
        do {
            try {
                System.out.print("Tiene Microfono (t/f): ");
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

    public Audio(String nombre, String marca, double precio, String descripcion, ColorP color, int stock,
            ResistenciasP resistencia, Conexiones conexion, boolean microfono, boolean inalambrico) {
        super(nombre, marca, precio, descripcion, color, stock);
        this.resistencia = resistencia;
        this.conexion = conexion;
        this.microfono = microfono;
        this.inalambrico = inalambrico;
    }
    
    public Audio() {
    }

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