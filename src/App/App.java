package App;
import java.util.Scanner;

import Almacenamiento.Inventario;
import Producto.Celular;

public class App {
    public static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        Inventario<Celular> lista = new Inventario<>();
        Celular juan = new Celular();
        juan.setNombre("nombre");
        lista.altaProducto(juan);
        sc.close();
    }
}
