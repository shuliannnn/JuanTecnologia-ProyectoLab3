package App;
import java.util.Scanner;

public class App {
    public static final Scanner sc = new Scanner(System.in); /// Scanner global para evitar errores de cierre.
    public static void main(String[] args) throws Exception {
        Menu.menu();   
        sc.close();
    }
}
