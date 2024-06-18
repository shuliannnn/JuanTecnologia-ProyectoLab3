package Registros;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedList;

import Almacenamiento.Archivo;
import Producto.Producto;

public class Historial {
    

/// Mostrar ------------------------------------------------------------------------------------------------------------------------------------------------

    public static void mostrar(){
        for (Registro registro : lista) {
            System.out.println(registro);
        }
    }
    
    public static void mostrarInvertido(){
        for (int i = lista.size() - 1; i >= 0; i--) {
            System.out.println(lista.get(i));
        }
    }


/// Agregar ------------------------------------------------------------------------------------------------------------------------------------------------
    
    public static void agregar(Registro r) {
        lista.add(r);
    }

    public static void agregarRegistroBaja(Producto borrado) {
        Registro registro = new Registro(LocalDateTime.now(), "El usuario dio de baja el producto: " + borrado);
        lista.add(registro);
        Archivo.subirRegistro(registro);
    }

    public static void agregarRegistroAlta(Producto nuevo) {
        Registro registro = new Registro(LocalDateTime.now(), "El usuario dio de alta el producto: " + nuevo);
        lista.add(registro);
        Archivo.subirRegistro(registro);
    }

    public static void agregarRegistroModificacion(Producto anterior, Producto actual) {
        Registro registro = new Registro(LocalDateTime.now(),
                "El usuario realizo estas modificaciones del producto:\n" +
                "Anterior: " + anterior + "\nActual: " + actual);
        lista.add(registro);
        Archivo.subirRegistro(registro);
    }

    public static void agregarRegistroModificacionStock(Producto modificado, int stockViejo) {
        int variacion = modificado.getStock() - stockViejo;
        Registro registro = new Registro(LocalDateTime.now(),
        "El usuario modifico el stock del producto: " + modificado +
        (variacion >= 0 ? "El stock aumento en " + variacion : "El stock disminuyo en " + Math.abs(variacion)));
        
        lista.add(registro);
        Archivo.subirRegistro(registro);
    }
    
    /// Constructores getters y setters ------------------------------------------------------------------------------------------------------------------------------------------------
   

    public static LinkedList<Registro> lista = new LinkedList<>();

    public static void leerArchivo(){
        lista = new LinkedList<>(Arrays.asList(Archivo.leerHistorial()));
    }
    public static LinkedList<Registro> getLista() {
        return lista;
    }

    public static void setLista(LinkedList<Registro> lista) {
        Historial.lista = lista;
    }
}
