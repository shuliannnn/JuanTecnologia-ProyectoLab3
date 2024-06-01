package Almacenamiento;
import java.util.LinkedHashSet;
import Producto.Producto;
import Interfaces.ABML;

public class Inventario<T extends Producto> implements ABML<T>{
    private LinkedHashSet<T> lista = new LinkedHashSet<>();

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
        return e.cargarProducto(this);
    }
    
}