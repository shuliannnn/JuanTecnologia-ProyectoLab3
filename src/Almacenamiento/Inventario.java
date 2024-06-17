package Almacenamiento;

import java.util.LinkedHashSet;

import Producto.Producto;
import Interfaces.ABML;
import App.App;
import App.Menu;

public class Inventario<T extends Producto> implements ABML<T> {
    private LinkedHashSet<T> lista = new LinkedHashSet<>();

    public Inventario() {
    }

    public boolean estaVacia() {
        return lista.isEmpty();
    }

    public boolean contiene(T e) {
        if (lista.isEmpty())
            return false;

        for (T l : lista) {
            if (l.equals(e))
                return true;
        }
        return false;
    }

    public T eliminar(T e) {
        T eliminado = null;
        for (T l : lista) {
            if (l.equals(e))
                eliminado = e;
        }
        return lista.remove(eliminado) ? eliminado : null;
    }

    public T buscar(String nombre) {
        for (T t : lista) {
            if (t.getNombre().equals(nombre))
                return t;
        }
        return null;
    }

    public T buscar(int id) {
        for (T t : lista) {
            if (t.getId() == id)
                return t;
        }
        return null;
    }

    public boolean agregar(T e) {
        return lista.add(e);
    }

    public LinkedHashSet<T> listar(){
        return lista;
    }

    public int size(){
        return lista.size();
    }

    public void altaProducto(T e) {
        e.escanearDatosComparables();
        if (contiene(e)) {
            System.out.println("El producto ya se encontraba en el sistema");
            System.out.println("Desea modificar el stock?");
            
            if (!confirmar("Si", "No", "", "Saliendo..."))
                return;

            /// vamos al objeto
            for (T t : lista) {
                if (t.equals(e)) {
                    Menu.clearScreen();
                    System.out.println("Stock actual: " + t.getStock());
                    t.modificarStock();
                    Archivo.modificarProducto(t);
                    System.out.println("Modificado correctamente.");
                    System.out.println("Stock actual: " + t.getStock());
                    Menu.systemPause();
                    return;
                }
            }

        } else {
            e.escanearDatosEspecificos();
            Menu.clearScreen();
            System.out.println(e);
            System.out.println("El producto quedaria cargado asi desea añadirlo al sistema?");
            
            if (confirmar("Añadir", "Borrar", "Se añadió al sistema correctamente",
                    "No se añadio al sistema")) {
                e.setId(e.asignarId());
                agregar(e);
                Archivo.subirProducto(e);
            }
            Menu.systemPause();
        }
    }

    @SuppressWarnings("unchecked")
    public void modificarProducto(T e) {

        int opcion;
        int id;
        T eCopia;
        boolean flag = false;
        do {
            System.out.println("¿Como desea buscar el producto?");
            System.out.println("1. ID");
            System.out.println("2. Datos principales");
            System.out.println("0. Salir");
            opcion = App.sc.nextInt();
            App.sc.nextLine();/// buffer
            switch (opcion) {
                case 1:
                    System.out.print("Ingrese un id: ");
                    id = App.sc.nextInt();
                    App.sc.nextLine();
                    Menu.clearScreen();

                    e = buscar(id);
                    if (e == null) {
                        System.out.println("No se encontro el producto");
                        Menu.systemPause();
                        return;
                    }
                    opcion = 0;
                    break;
                case 2:
                    System.out.println("Ingrese datos principales");
                    e.escanearDatosComparables();
                    for (T t : lista) {
                        if (t.equals(e)) {
                            e = t;
                            flag = true;
                        }
                    }
                    if (!flag) {
                        System.out.println("No se encotro el producto");
                        Menu.systemPause();
                        return;
                    }
                    opcion = 0;
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Por favor ingrese una opcion valida");
                    break;
            }
        } while (opcion != 0);

        eCopia = (T) e.clone();
        eCopia.setId(e.getId());
        eCopia.modificarProducto();
        Menu.clearScreen();
        for (T t : lista) {
            if (eCopia.equals(t) && eCopia.getId() != t.getId()) {
                System.out.println("Ocurrio un error: La modificacion realizada es igual a un producto existente");
                System.out.println("Se cancelaron la modificaciones");
                Menu.systemPause();
                return;
            }
        }
        System.out.println("Producto original: ");
        System.out.println(e);
        System.out.println("Producto modificado: ");
        System.out.println(eCopia);
        System.out.println("Desea realizar estos cambios?");
        if (confirmar("Si", "No", "Producto modificado correctamente",
                "Se deshicieron las modificaciones")) { // Confirmar
            lista.remove(e);
            lista.add(eCopia);
            Archivo.modificarProducto(eCopia);
        }
            Menu.systemPause();
    }

    public void bajaProducto(T e) {

        int opcion;
        int id;
        boolean flag = false;
        do {
            Menu.clearScreen();
            System.out.println("¿Como desea buscar el producto?");
            System.out.println("1. ID");
            System.out.println("2. Datos principales");
            System.out.println("0. Salir");
            opcion = App.sc.nextInt();
            App.sc.nextLine();/// buffer
            switch (opcion) {
                case 1:
                    System.out.print("Ingrese un id: ");
                    id = App.sc.nextInt();
                    App.sc.nextLine();
                    Menu.clearScreen();

                    e = buscar(id);
                    if (e == null) {
                        System.out.println("No se encontro el producto");
                        Menu.systemPause();
                        return;
                    }
                    opcion = 0;
                    break;
                case 2:
                    System.out.println("Ingrese datos principales");
                    e.escanearDatosComparables();
                    for (T t : lista) {
                        if (t.equals(e)) {
                            e = t;
                            flag = true;
                        }
                    }
                    if (!flag) {
                        System.out.println("No se encotro el producto");
                        Menu.systemPause();
                        return;
                    }
                    opcion = 0;
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Por favor ingrese una opcion valida");
                    Menu.systemPause();
                    break;
            }
        } while (opcion != 0);

        System.out.println("Producto Encontrado");
        System.out.println(e);
        System.out.println("Desea eliminarlo definitivamente del sistema?");
        if (confirmar("Si", "No", "Producto eliminado correctamente",
                "No se elimino")) { // Confirmar
            lista.remove(e);
            Archivo.eliminarProducto(e);
        }
            Menu.systemPause();
    }

    @SuppressWarnings("unchecked")
    public boolean leerInventario(String archivo) {

        Producto[] array = Archivo.leerListaProducto(archivo);

        if (array.length == 0)
            return false;
        for (Producto p : array) {
            lista.add((T) p);
        }

        if (array.length != lista.size())
            return false;

        return true;
    }

    private boolean confirmar(String entradaT, String entradaF, String resT, String resF) {
        int opcion;
        do {
            System.out.println("1." + entradaT);
            System.out.println("2." + entradaF);
            opcion = App.sc.nextInt();
            App.sc.nextLine();// LIMPIAR BUFFER YO HACER
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
        for (T e : lista) {
            contenido += e.toString();
        }
        return contenido;
    }

    public void filtrarYMostrar() {
        int opcion;
        Menu.clearScreen();
        System.out.println("Como desea filtrar: ");
        System.out.println("1. Marca");
        System.out.println("2. Stock");
        System.out.println("0. Atras");
        System.out.print("-->");
        opcion = App.sc.nextInt();
        App.sc.nextLine();
        switch (opcion) {
            case 1:
                Menu.clearScreen();
                filtroMarca(Producto.escanearMarca());
                Menu.systemPause();
                Menu.clearScreen();
                break;
            case 2:
                Menu.clearScreen();
                System.out.println("Mostrara los productos que tengan stock menor al valor ingresado.");
                filtroStock(Producto.escanearStock());
                Menu.systemPause();
                Menu.clearScreen();
                break;
            case 0:
                break;
            default:
                System.out.println("Por favor ingrese un caracter valido.");
                Menu.systemPause();
                Menu.clearScreen();
                break;
        }

    }

    public void filtroMarca(String marca) {
        System.out.println(marca + ": ");     
        for (T t : lista) {
            if (t.getMarca().equalsIgnoreCase(marca)) {
                System.out.println(t);
            }
        }
    }
    
    public void filtroStock(int stock) {
        System.out.println("Valores con stock menor o igual a " + stock + ":");
        for (T l : lista) {
            if (l.getStock() <= stock) {
                System.out.println(l);
            }
        }
    }

    public String toStringCorto() {
        String contenido = "";
        for (T e : lista) {
            contenido += e.getId() + "  |  " + e.getMarca() + "  |  " + e.getNombre() + "  |  " + e.getColor() + "\n";
        }
        return contenido;
    }

    public String toStringSuperFachero() {
        // Anchos fijos para cada columna
        int col1Width = 5;
        int col2Width = 15;
        int col3Width = 20;
        int col4Width = 10;

        StringBuilder contenido = new StringBuilder();

        // Encabezados
        contenido.append(String.format(
                "%-" + col1Width + "s | %-" + col2Width + "s | %-" + col3Width + "s | %-" + col4Width + "s%n",
                "ID", "Marca", "Nombre", "Color"));
        contenido.append(new String(new char[col1Width + col2Width + col3Width + col4Width + 12]).replace('\0', '-'))
                .append("\n");

        // Datos
        for (T e : lista) {
            contenido.append(String.format(
                    "%-" + col1Width + "s | %-" + col2Width + "s | %-" + col3Width + "s | %-" + col4Width + "s%n",
                    e.getId(), e.getMarca(), e.getNombre(), e.getColor()));
        }

        return contenido.toString();
    }

}
