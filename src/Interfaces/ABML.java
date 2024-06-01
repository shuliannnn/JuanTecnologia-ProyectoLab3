package Interfaces;

public interface ABML<T> {

    boolean agregar(T e);
    T eliminar(T e);
    boolean contiene(T e);
    T buscar(String nombre);
    T buscar(int id);
}
