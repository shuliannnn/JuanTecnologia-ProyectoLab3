import App.App;
import Producto.Cable;
import Producto.Celular;

public class Menu {

    public void menu() {

        System.out.println("Bienvenido al Sistema de Stock Juan Tecnologias");
        int opcion;

        do {
            System.out.println("1. Buscar producto");
            System.out.println("2. Cargar producto");
            System.out.println("3. Modificar producto");
            System.out.println("4. Modificar Stock");
            System.out.println("5. Baja de producto");
            opcion = App.sc.nextInt();
            switch (opcion) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:
                    break;
                default:
                System.out.println("Por favor ingrese una entrada valida");
                    break;
            }
        } while (opcion != 0);

    }

    public static <T> elegirCategoria() {

        int opcion;
        do {
            System.out.println("Categorias");
            System.out.println("1. Celulares");
            System.out.println("2. Computadoras");;
            System.out.println("3. Audio");
            System.out.println("4. Perifericos");
            System.out.println("5. Cables");
            System.out.println("0. Atras");
            opcion = App.sc.nextInt();
            switch (opcion) {
                case 1:
                    return new Celular();
                    break;
                case 2:
                    
                    break;
                case 3:
                    ///parlante
                    ///auricular
                    break;
                case 4:

                    break;
                case 5:
                    return new Cable();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Por favor ingrese una entrada valida");
                    break;
            }
        } while (opcion != 0);
        return null;
    }

}
