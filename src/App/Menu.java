package App;

import java.util.InputMismatchException;

import Almacenamiento.*;
import Producto.*;

public class Menu {
    static Inventario<Celular> listaCe = new Inventario<>();
    static Inventario<Parlante> listaPa = new Inventario<>();
    static Inventario<Pc> listaPc = new Inventario<>();
    static Inventario<Portatil> listaPo = new Inventario<>();
    static Inventario<Auricular> listaA = new Inventario<>();
    static Inventario<Teclado> listaT = new Inventario<>();
    static Inventario<Mouse> listaM = new Inventario<>();
    static Inventario<Cable> listaCa = new Inventario<>();

    @SuppressWarnings("unchecked")
    public static <T extends Producto> void menu() {
        listaCe.leerInventario("celular.json");
        listaPa.leerInventario("parlante.json");
        listaA.leerInventario("auricular.json");
        listaPo.leerInventario("portatil.json");
        listaPc.leerInventario("pc.json");
        listaM.leerInventario("mouse.json");
        listaT.leerInventario("teclado.json");
        listaCa.leerInventario("cable.json");

        int opcion;
        T e;
        Inventario<T> lista;
        do {
            System.out.println("Bienvenido al Sistema de Stock Juan Tecnologia");
            System.out.println("1. Buscar producto");
            System.out.println("2. Cargar producto");
            System.out.println("3. Modificar Stock");
            System.out.println("4. Filtrar producto");
            System.out.println("5. Mostrar Productos");/// muestra todo
            System.out.println("6. Modificar producto");
            System.out.println("7. Baja de producto");
            System.out.println("0. Salir del programa");
            System.out.print("-->");
            try {
                opcion = App.sc.nextInt();
                App.sc.nextLine();
            } catch (InputMismatchException ex) {
                App.sc.nextLine();
                opcion = -1;
            }

            switch (opcion) {
                case 1:
                    e = (T) Producto.elegirCategoria();
                    if (e == null) break;
                    lista = (Inventario<T>) obtenerLista(e);
                    lista.buscarProducto(e);
                    clearScreen();
                    break;
                case 2:
                    e = (T) Producto.elegirCategoria();
                    if (e == null) break;
                    lista = (Inventario<T>) obtenerLista(e);
                    lista.altaProducto(e);
                    clearScreen();
                    break;
                case 3:
                    e = (T) Producto.elegirCategoria();
                    if (e == null)
                        break;
                    lista = (Inventario<T>) obtenerLista(e);
                    lista.modificarStock(e);
                    break;
                case 4:
                    menuFiltrado();
                    clearScreen();
                    break;
                case 5:
                    clearScreen();
                    System.out.println("Todos los productos");
                    System.out.println(unificarListas());
                    systemPause();
                    clearScreen();
                    break;
                case 6:
                    e = (T) Producto.elegirCategoria();
                    if (e == null)
                        break;
                    lista = (Inventario<T>) obtenerLista(e);
                    lista.modificarProducto(e);
                    clearScreen();
                    break;
                case 7:
                    e = (T) Producto.elegirCategoria();
                    if (e == null)
                        break;
                    lista = (Inventario<T>) obtenerLista(e);
                    lista.bajaProducto(e);
                    clearScreen();
                    break;
                case 0:
                    System.out.println("Saliendo....");
                    systemPause();
                    clearScreen();
                    break;
                default:
                    System.out.println("Por favor ingrese una entrada valida");
                    systemPause();
                    clearScreen();
                    break;
            }
        } while (opcion != 0);

    }

    @SuppressWarnings("unchecked")
    public static <T extends Producto> void menuFiltrado() {

        int opcion;
        T e = null;
        Inventario<T> lista;
        do {
            clearScreen();
            System.out.println("Donde desea aplicar los filtros: ");
            System.out.println("1. Categoria");
            System.out.println("2. Todos");
            System.out.println("0. Atras");
            System.out.print("-->");
            try {
                opcion = App.sc.nextInt();
            } catch (InputMismatchException ex) {
                opcion = -1;
            }
            App.sc.nextLine();
            switch (opcion) {
                case 1:
                    e = (T) Producto.elegirCategoria();
                    if (e == null)
                        break;
                    lista = (Inventario<T>) obtenerLista(e);
                    lista.filtrarYMostrar();
                    clearScreen();
                    return;
                case 2:
                    Inventario<Producto> productos = new Inventario<>();
                    productos = unificarListas();
                    productos.filtrarYMostrar();
                    clearScreen();
                    return;
                case 0:
                    return;
                default:
                    System.out.println("Por favor ingrese un caracter valido");
                    systemPause();
                    clearScreen();
                    break;
            }

        } while (opcion != 0);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Producto> Inventario<T> obtenerLista(T e) {
        if (e instanceof Celular)
            return (Inventario<T>) listaCe;
        if (e instanceof Auricular)
            return (Inventario<T>) listaA;
        if (e instanceof Parlante)
            return (Inventario<T>) listaPa;
        if (e instanceof Mouse)
            return (Inventario<T>) listaM;
        if (e instanceof Pc)
            return (Inventario<T>) listaPc;
        if (e instanceof Portatil)
            return (Inventario<T>) listaPo;
        if (e instanceof Teclado)
            return (Inventario<T>) listaT;
        if (e instanceof Cable)
            return (Inventario<T>) listaCa;
        return null;
    }

    public static Inventario<Producto> unificarListas() {
        Inventario<Producto> productos = new Inventario<>();
        productos.leerInventario(Archivo.obtenerNombreArchivo(new Celular()));
        productos.leerInventario(Archivo.obtenerNombreArchivo(new Auricular()));
        productos.leerInventario(Archivo.obtenerNombreArchivo(new Parlante()));
        productos.leerInventario(Archivo.obtenerNombreArchivo(new Teclado()));
        productos.leerInventario(Archivo.obtenerNombreArchivo(new Mouse()));
        productos.leerInventario(Archivo.obtenerNombreArchivo(new Pc()));
        productos.leerInventario(Archivo.obtenerNombreArchivo(new Portatil()));
        productos.leerInventario(Archivo.obtenerNombreArchivo(new Cable()));

        return productos;

    }

    public static void clearScreen() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void systemPause() {
        try {
            new ProcessBuilder("cmd", "/c", "pause").inheritIO().start().waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
