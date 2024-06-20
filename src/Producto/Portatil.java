package Producto;

import java.util.InputMismatchException;
import org.json.JSONException;
import org.json.JSONObject;
import App.*;
import Enumeradores.*;
import Excepciones.*;

public class Portatil extends Computadora {

    private double pulgadas;
    private boolean ethernet;
    private boolean microfono;

    /// Implementaciones Metodos Abstractos ----------------------------------------------------------------------------------------------------------------------------

    @Override
    public void escanearDatosComparables(String print) {
        System.out.println(print + " portatil: ");
        /// en producto
        marca = escanearMarca();
        nombre = escanearNombre();
        color = escanearColor();
        /// computadora
        memoriaInterna = escanearMemoriaInterna();
    }

    @Override
    public void escanearDatosEspecificos() {

        /// en computadora
        procesador = escanearProcesador();
        memoriaRam = escanearMemoriaRAM();
        pVideo = escanearPlacaVideo();
        bluetooth = escanearBluetooth();
        mother = escanearMother();
        /// en portatil
        pulgadas = escanearPulgadas();
        ethernet = escanearEthernet();
        /// microfono =
        /// en producto
        descripcion = escanearDescripcion();
        stock = escanearStock();
        precio = escanearPrecio();
    }
    
    @Override
    public void modificarProducto() {
        int opcion;

        do {
            Menu.clearScreen();
            System.out.println("Producto con modificaciones actuales: ");
            System.out.println(this.toString() + '\n');
            System.out.println("Ingrese campo a modificar");
            System.out.println("1. Nombre");
            System.out.println("2. Marca");
            System.out.println("3. Precio");
            System.out.println("4. Descripcion");
            System.out.println("5. Color");
            System.out.println("6. Stock");
            System.out.println("7. Procesador");
            System.out.println("8. RAM");
            System.out.println("9. Almacenamiento");
            System.out.println("10. Placa de Video");
            System.out.println("11. Bluetooth");
            System.out.println("12. Motherboard");
            System.out.println("13. Pulgadas");
            System.out.println("14. Ethernet");
            System.out.println("15. Microfono");
            System.out.println("0. Listo");
            System.out.print("-->");
            try {
                opcion = App.sc.nextInt();
            } catch (InputMismatchException ex) {
                opcion = -1;
            }
            App.sc.nextLine();

            switch (opcion) {
                case 1:
                    nombre = escanearNombre();
                    break;
                case 2:
                    marca = escanearMarca();
                    break;
                case 3:
                    precio = escanearPrecio();
                    break;
                case 4:
                    descripcion = escanearDescripcion();
                    break;
                case 5:
                    color = escanearColor();
                    break;
                case 6:
                    stock = escanearStock();
                    break;
                case 7:
                    procesador = escanearProcesador();
                    break;
                case 8:
                    memoriaRam = escanearMemoriaRAM();
                    break;
                case 9:
                    memoriaInterna = escanearMemoriaInterna();
                    break;
                case 10:
                    pVideo = escanearPlacaVideo();
                    break;
                case 11:
                    bluetooth = escanearBluetooth();
                    break;
                case 12:
                    mother = escanearMother();
                    break;
                case 13:
                    pulgadas = escanearPulgadas();
                    break;
                case 14:
                    ethernet = escanearEthernet();
                    break;
                case 15:
                    microfono = escanearMicrofono();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Ingrese un caracter válido");
                    break;
            }

        } while (opcion != 0);

    }

    @Override
    public Portatil clone() {
        return new Portatil(nombre, marca, precio, descripcion, color, stock, procesador, memoriaInterna,
                memoriaInterna, pVideo, bluetooth, mother, pulgadas, ethernet, microfono);
    }

    @Override
    public String toString() {
        return "Portatil: ID: " + (getId()==0?"No asignado":getId()) + "\n  | Marca: " + getMarca() + " | Nombre: " + getNombre() + " | Color: " + getColor() + " |" + "\n  | Stock: " + getStock() + " | Precio: " + getPrecio() + " |" +
          "\n      Procesador: " + getProcesador() + " \n      RAM: " + getMemoriaRam() + "\n      Almacenamiento: " + getMemoriaInterna() +
            "\n      Placa de Video: " + getpVideo() + "\n      Bluetooth: " + (bluetooth ? "Si" : "No") + "\n      Mother: " + getMother() +
            "\n      Pulgadas: " + getPulgadas() + "\n      Entrada Ethernet: " + (ethernet ? "Si" : "No") + "\n      Microfono: " + (microfono ? "Si" : "No") + "\n      Descripcion: " + getDescripcion() + '\n';
    }

/// Scanners  ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    
    public double escanearPulgadas() {
        double pulgadas = -1;
        boolean validInput = false;
        do {
            try {
                System.out.print("Tamaño Pantalla (pulgadas): ");
                if (!App.sc.hasNextDouble()) {
                    App.sc.nextLine(); // Clear invalid input
                    throw new InvalidDoubleException("La pulgadas debe ser un número.");
                }
                pulgadas = App.sc.nextDouble();
                App.sc.nextLine(); // Consume newline
                if (pulgadas <= 0) {
                    throw new InvalidDoubleException("La pulgadas debe ser un número positivo.");
                }
                validInput = true;
            } catch (InvalidDoubleException e) {
                System.out.println(e.getMessage());
            }
        } while (!validInput);

        return pulgadas;
    }

    public boolean escanearEthernet() {
        char ethernet;
        boolean validInput = false;
        do {
            try {
                System.out.print("Tiene Ethernet (t/f): ");
                String input = App.sc.nextLine();

                if (input.length() != 1) {
                    throw new InvalidCharacterException("Debe ingresar solo un carácter ('t' o 'f').");
                }

                ethernet = input.charAt(0);

                if (ethernet == 't' || ethernet == 'f') {

                    return ethernet == 't';
                } else {
                    throw new InvalidCharacterException("Carácter inválido. Debe ingresar 't' o 'f'.");
                }

            } catch (InvalidCharacterException e) {
                System.out.println(e.getMessage());
            }
        } while (!validInput);

        return false;
    }

    public boolean escanearMicrofono() {
        char microfono;
        boolean validInput = false;
        do {
            try {
                System.out.print("Tiene microfono (t/f): ");
                String input = App.sc.nextLine();

                if (input.length() != 1) {
                    throw new InvalidCharacterException("Debe ingresar solo un carácter ('t' o 'f').");
                }

                microfono = input.charAt(0);

                if (microfono == 't' || microfono == 'f') {

                    return microfono == 't';
                } else {
                    throw new InvalidCharacterException("Carácter inválido. Debe ingresar 't' o 'f'.");
                }

            } catch (InvalidCharacterException e) {
                System.out.println(e.getMessage());
            }
        } while (!validInput);

        return false;
    }

    ///Archivos -------------------------------------------------------------------------------------------------------------------------------------------------------

    @Override
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        try {
            json.put("tipo", "Portatil");
            json.put("nombre", nombre);
            json.put("marca", marca);
            json.put("precio", precio);
            json.put("descripcion", descripcion);
            json.put("id", id);
            json.put("color", color.toString());
            json.put("stock", stock);
            json.put("procesador", procesador);
            json.put("memoriaRam", memoriaRam);
            json.put("memoriaInterna", memoriaInterna);
            json.put("pVideo", pVideo);
            json.put("bluetooth", bluetooth);
            json.put("mother", mother);
            json.put("pulgadas", pulgadas);
            json.put("ethernet", ethernet);
            json.put("microfono", microfono);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static Portatil fromJSON(JSONObject json) {
        try {
            String nombre = json.getString("nombre");
            String marca = json.getString("marca");
            double precio = json.getDouble("precio");
            String descripcion = json.getString("descripcion");
            ColorP color = ColorP.valueOf(json.getString("color"));
            int stock = json.getInt("stock");
            String procesador = json.getString("procesador");
            int memoriaRam = json.getInt("memoriaRam");
            int memoriaInterna = json.getInt("memoriaInterna");
            String pVideo = json.getString("pVideo");
            boolean bluetooth = json.getBoolean("bluetooth");
            String mother = json.getString("mother");
            double pulgadas = json.getDouble("pulgadas");
            boolean ethernet = json.getBoolean("ethernet");
            boolean microfono = json.getBoolean("microfono");
            int id = json.getInt("id");

            Portatil aux = new Portatil(nombre, marca, precio, descripcion, color, stock, procesador, memoriaRam,
                    memoriaInterna, pVideo, bluetooth, mother, pulgadas, ethernet, microfono);
            aux.setId(id);
            return aux;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /// Constructores getters y setters
    /// ------------------------------------------------------------------------------------------------------------------------------------------------

    public Portatil(String nombre, String marca, double precio, String descripcion, ColorP color, int stock,
            String procesador, int memoriaRam, int memoriaInterna, String pVideo, boolean bluetooth, String mother,
            double pulgadas, boolean ethernet, boolean microfono) {
        super(nombre, marca, precio, descripcion, color, stock, procesador, memoriaRam, memoriaInterna, pVideo,
                bluetooth, mother);
        this.pulgadas = pulgadas;
        this.ethernet = ethernet;
        this.microfono = microfono;
    }

    public Portatil() {
    }

    public double getPulgadas() {
        return pulgadas;
    }

    public void setPulgadas(double pulgadas) {
        this.pulgadas = pulgadas;
    }

    public boolean isEthernet() {
        return ethernet;
    }

    public void setEthernet(boolean ethernet) {
        this.ethernet = ethernet;
    }

    public boolean isMicrofono() {
        return microfono;
    }

    public void setMicrofono(boolean microfono) {
        this.microfono = microfono;
    }

}
