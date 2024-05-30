import java.util.LinkedHashSet;
import java.util.Scanner;

public class Inventario<T extends Producto> {
    private LinkedHashSet<T> lista = new LinkedHashSet<>();

    public boolean contiene(T e){
        for(T l: lista){
            if(l.equals(e))
                return true;
        }
        return false;
    }

    public boolean agregar(T e){
        return lista.add(e);
    }

    public boolean altaProducto(){
        T e;
        return e.cargarProducto(this);
    }
    
}


// Este metodo de la clase celular 

// public boolean cargarProducto(Inventario<Celular> lista){
//     System.out.println("Marca: ");
//     System.out.println("nombre: ");
//     System.out.println("Almacenamiento: ");
//     //ram
//     setCosas();
//     if(lista.contiene(this)){
            //podria llamarse a otro metodo de lista que haga esto
//         System.out.println("El producto ya se encontraba en el sistema");
//         System.out.println("Desea modificar stock?");
//         System.out.println("Ingrese >0 para aumentar y <0 para decrementar");
//         int nuevoStock = sc.nextInt();
//         flag = lista.modificarStock(this, nuevoStock);
//         //modificar archivo
//     }
//     else{
//         System.out.println("Precio");
//         System.out.println("Descripcion");
//         System.out.println("Sistema Operativo");
//         System.out.println("Bateria");
//         System.out.println("Doble SIM");
//         setTODOESO();
//         flag = lista.agregar(aux);
//         //modificar archivo
//     }
//     return flag;
    
// }