package App;
import java.util.Scanner;

import Almacenamiento.Inventario;

import Producto.*;

public class App {
    public static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        Inventario<Celular> lista = new Inventario<>();
        lista.leerInventario("producto.celular.json");
        lista.altaProducto(new Celular());
        lista.altaProducto(new Celular());
        System.out.println(lista);
        //Auricular au = new Auricular("auricular", "Phillips", 200, "caca", ColorP.ROJO, 43, ResistenciasP.IP68, Conexiones.AUXILIAR, false, true, FormatoAuricular.ON_EAR, CanalesAudio.E5_1);
        //Archivo.subirProducto(au);
        //Archivo.cambiarStock(au, 4);
        sc.close();
    }
}
