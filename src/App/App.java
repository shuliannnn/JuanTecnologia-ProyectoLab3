package App;
import java.time.LocalDateTime;
import java.util.Scanner;

import Registros.Registro;


public class App {
    public static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws Exception {

        ///Menu.menu();   
        String fecha = LocalDateTime.now().toString();
        Registro r = new Registro(LocalDateTime.parse(fecha),"Añadio el celular al sistema Iphone 11");    
        System.out.println(r);
        sc.close();
    }
}
