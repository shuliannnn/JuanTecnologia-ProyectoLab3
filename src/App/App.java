package App;
import java.util.Scanner;

import Almacenamiento.Inventario;
import Producto.*;

public class App {
    public static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        Inventario<Celular> lista = new Inventario<>();
        lista.altaProducto(new Celular());
        sc.close();
    }
}
