package App;

import java.util.InputMismatchException;

import Almacenamiento.*;
import Almacenamiento.Registros.*;
import Producto.*;

public abstract class Menu {
    

/// Listas de productos ------------------------------------------------------------------------------------------------------------------------------------------------

    private static Inventario<Celular> listaCe = new Inventario<>();
    private static Inventario<Parlante> listaPa = new Inventario<>();
    private static Inventario<Pc> listaPc = new Inventario<>();
    private static Inventario<Portatil> listaPo = new Inventario<>();
    private static Inventario<Auricular> listaA = new Inventario<>();
    private static Inventario<Teclado> listaT = new Inventario<>();
    private static Inventario<Mouse> listaM = new Inventario<>();
    private static Inventario<Cable> listaCa = new Inventario<>();
    
/// Menu Principal ---------------------------------------------------------------------------------------------------------------------------------------------------------
    
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
        Historial.leerArchivo();

        int opcion;
        T e;
        Inventario<T> lista;
        do {
            clearScreen();
            System.out.println("Bienvenido al Sistema de Stock Juan Tecnologia");
            System.out.println("1. Buscar producto");
            System.out.println("2. Cargar producto");
            System.out.println("3. Modificar Stock");
            System.out.println("4. Filtrar producto");
            System.out.println("5. Mostrar Productos");/// muestra todo
            System.out.println("6. Modificar producto");
            System.out.println("7. Baja de producto");
            System.out.println("8. Ver historial de cambios");
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
                    menuMostrarProductos();
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
                case 8:
                    Historial.menuPrincipal();
                    systemPause();
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

    
/// Menues mostrar - filtrado Marca y Stock ----------------------------------------------------------------------------------------------------------------------------------
    
    @SuppressWarnings("unchecked")
    private static <T extends Producto> void menuMostrarProductos() {

        int opcion;
        T e = null;
        Inventario<T> lista;
        do {
            clearScreen();
            System.out.println("Que lista desea ver? ");
            System.out.println("1. Categoria Especifica");
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
                    System.out.println(lista);
                    systemPause();
                    clearScreen();
                    return;
                case 2:
                    Inventario<Producto> productos = new Inventario<>();
                    productos = unificarListas();
                    System.out.println(productos);
                    systemPause();
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
    private static <T extends Producto> void menuFiltrado() {

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

    public static boolean confirmar(String entradaT, String entradaF, String resT, String resF) {
        int opcion;
        do {
            System.out.println("1." + entradaT);
            System.out.println("2." + entradaF);
            System.out.print("-->");
            try {
                opcion = App.sc.nextInt();
            } catch (InputMismatchException ex) {
                opcion = -1;
            }
            App.sc.nextLine();
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

/// Manipulacion de listas ----------------------------------------------------------------------------------------------------------------------------------------
   
    @SuppressWarnings("unchecked")
    private static <T extends Producto> Inventario<T> obtenerLista(T e) {
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

    private static Inventario<Producto> unificarListas() {
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

    
    
//// Comandos de Consola ----------------------------------------------------------------------------------------------------------------------
    
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
