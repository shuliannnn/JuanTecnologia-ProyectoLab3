package App;
import java.util.Scanner;

import Almacenamiento.Inventario;

import Producto.*;

public class App {
    public static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        Inventario<Celular> lista = new Inventario<>();
        Inventario<Auricular> listaA = new Inventario<>();
        Inventario<Parlante> listaPa = new Inventario<>();
        Inventario<Pc> listaPc = new Inventario<>();
        Inventario<Portatil> listaPo = new Inventario<>();
        Inventario<Teclado> listaT = new Inventario<>();
        Inventario<Mouse> listaM = new Inventario<>();
        Inventario<Cable> listaC = new Inventario<>();
        
        lista.leerInventario("celular.json");
        listaA.leerInventario("auricular.json");
        listaPa.leerInventario("parlante.json");
        listaPc.leerInventario("pc.json");
        listaPo.leerInventario("portatil.json");
        listaT.leerInventario("teclado.json");
        listaM.leerInventario("mouse.json");
        listaC.leerInventario("cable.json");
        
        
        // lista.altaProducto(new Celular());
        // System.out.println(lista);
        // listaPc.altaProducto(new Pc());
        // System.out.println(listaPc);

        // listaPo.altaProducto(new Portatil());
        // System.out.println(listaPo);
        
        //listaA.altaProducto(new Auricular());
        //listaA.altaProducto(new Auricular());
        listaA.modificarProducto(new Auricular());
        System.out.println(listaA);

        //listaPa.altaProducto(new Parlante());
        // System.out.println(listaPa);
        
        //listaM.altaProducto(new Mouse());
        // System.out.println(listaM);
        
        //listaT.altaProducto(new Teclado());
        // System.out.println(listaT);
        
        // listaC.altaProducto(new Cable());
        // System.out.println(listaC);


        
        sc.close();
    }
}
