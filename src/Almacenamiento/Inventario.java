package Almacenamiento;
import java.util.LinkedHashSet;
import Producto.Producto;
import Interfaces.ABML;
import App.App;

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

    public void altaProducto(T e){
        e.escanearDatosComparables();
        boolean confirmacion;
        if(contiene(e)){
            System.out.println("El producto ya se encontraba en el sistema");
            System.out.println("Desea modificar el stock?");
            confirmacion = confirmar("Si","No","", "Saliendo...");
            if(!confirmacion) return;

                ///vamos al objeto
            for (T t : lista) {
                if(t.getId() == e.getId()) {
                    t.modificarStock();
                    Archivo.modificarProducto(t);
                    System.out.println("Se modifico el stock correctamente");
                    return;
                }
            }
            
        }
        else{
            e.escanearDatosEspecificos(); 
            System.out.println(e);
            System.out.println("El producto quedaria cargado asi desea añadirlo al sistema?");
            confirmacion = confirmar("Añadir","Borrar","Se añadió al sistema correctamente","No se añadio al sistema");
            if(confirmacion){
                agregar(e);
                Archivo.subirProducto(e);
            }
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

    private boolean confirmar(String entradaT, String entradaF, String resT, String resF){
        int opcion;
            do {
                System.out.println("1." + entradaT);
                System.out.println("2." + entradaF);
                opcion = App.sc.nextInt();
                App.sc.nextLine();//LIMPIAR BUFFER YO HACER
                switch (opcion) {
                    case 1:
                        System.out.println(resT);
                        return true;
                    case 2:
                        System.out.println(resF);
                        return false;
                    default:
                        System.out.println("Por favor ingrese una opcion valida");
                        break;
                }
            } while (opcion != 1 && opcion != 2);
            return false;
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
