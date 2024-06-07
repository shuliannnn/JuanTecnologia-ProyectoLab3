package Almacenamiento;
import java.util.LinkedHashSet;
import Producto.Producto;
import Interfaces.ABML;

public class Inventario<T extends Producto> implements ABML<T>{
    private LinkedHashSet<T> lista = new LinkedHashSet<>();

    public Inventario() {
    }

    public boolean contiene(T e){
        for(T l: lista){
            if(l.equals(e))
                return true;
        }
        return false;
    }

    public T eliminar(T e){
        T eliminado = null;
        for(T l: lista){
            if(l.equals(e))
                eliminado = e;
        }
        return lista.remove(eliminado) ? eliminado : null;
    }

    public T buscar(String nombre){
        for (T t : lista) {
            if(t.getNombre().equals(nombre))
                return t;
        }
        return null;
    }

    public T buscar(int id){
        for (T t : lista) {
            if(t.getId() == id)
                return t;
        }
        return null;
    }

    public boolean agregar(T e){
        return lista.add(e);
    }

    public boolean altaProducto(T e){
        e.escanearDatosComparables();
        boolean res = false;
        if(contiene(e)){
            System.out.println("El producto ya se encontraba en el sistema");
            System.out.println("Desea modificar el stock?");
            //res = stock
            //lista
            //archivo
            return res;
        }
        else{
            e.escanearDatosEspecificos(); //agregar contador id al archivo
            System.out.println("Se agrego el objeto correctamente");
            System.out.println(e);

            Archivo.subirProducto(e);
            return agregar(e);
        }
    }

    
    @SuppressWarnings("unchecked")
    public boolean leerInventario(String archivo){

        Producto[] array = Archivo.leerListaProducto(archivo);

        for (Producto p : array) {
            lista.add((T) p);
        }

        if(array.length != lista.size()) return false;
        
        return true;
    }
    
    @Override
    public String toString() {
        String contenido = "";
        for(T e: lista){
            contenido += e.getMarca() + "  |  " + e.getNombre() + "  |  " + e.getColor() + "\n";
        }
        return contenido;
    }
}
