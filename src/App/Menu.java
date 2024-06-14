package App;
import Almacenamiento.Inventario;
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

        System.out.println("Bienvenido al Sistema de Stock Juan Tecnologias");
        int opcion;
        T e;
        Inventario<T> lista;
        do {
            System.out.println("1. Buscar producto");
            System.out.println("2. Cargar producto");
            System.out.println("3. Modificar producto");
            System.out.println("4. Modificar Stock");
            System.out.println("5. Baja de producto");
            System.out.println("6. Mostrar Lista");
            System.out.println("0. Salir del programa");
            opcion = App.sc.nextInt();
            App.sc.nextLine();
            switch (opcion) {
                case 1:
                    clearScreen();

                    break;
                case 2:
                    e = (T) Producto.elegirCategoria();
                    if(e == null) break;
                    lista = (Inventario<T>) obtenerLista(e);
                    lista.altaProducto(e);
                    clearScreen();
                    break;
                case 3:
                    e = (T) Producto.elegirCategoria();
                    if(e == null) break;
                    lista = (Inventario<T>) obtenerLista(e);
                    lista.modificarProducto(e);
                    clearScreen();
                    break;
                case 4:
                    clearScreen();
                    
                    break;
                case 5:
                    clearScreen();

                    break;
                case 6:
                    e = (T) Producto.elegirCategoria();
                    if(e == null) break;
                    lista = (Inventario<T>) obtenerLista(e);
                    System.out.println(lista);
                    Menu.systemPause();
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
    public static <T extends Producto> Inventario<T> obtenerLista(T e){
        if(e instanceof Celular) return  (Inventario<T>)listaCe;
        if(e instanceof Auricular) return (Inventario<T>)listaA;
        if(e instanceof Parlante) return (Inventario<T>)listaPa;
        if(e instanceof Mouse) return (Inventario<T>)listaM;
        if(e instanceof Pc) return (Inventario<T>)listaPc;
        if(e instanceof Portatil) return (Inventario<T>)listaPo;
        if(e instanceof Teclado) return (Inventario<T>)listaT;
        if(e instanceof Cable) return (Inventario<T>)listaCa;
        return null;
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
