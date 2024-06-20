package Almacenamiento.Registros;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.LinkedList;

import Almacenamiento.Archivo;
import Producto.Producto;
import App.App;
import App.Menu;

public abstract class Historial {
    
    public static LinkedList<Registro> lista = new LinkedList<>();
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

/// Menu ------------------------------------------------------------------------------------------------------------------------------------------------

    public static void menuPrincipal(){
        int opcion;
        do {
            Menu.clearScreen();
            System.out.println("Historial de cambios");
            System.out.println("    1. Mostrar todos");
            System.out.println("    2. Mostrar por fecha");
            System.out.println("    3. Filtrar por fechas");
            System.out.println("    0. Atras");
            System.out.print("-->");
            try {
                opcion = App.sc.nextInt();
            } catch (InputMismatchException ex) {
                opcion = -1;
            }
            App.sc.nextLine();
            Menu.clearScreen();
            switch (opcion) {
                case 1:
                    System.out.println("Historial de todos los movimientos en el sistema");
                    mostrarInvertido();
                    return;
                case 2:
                    menuMostrarPorFecha();
                    return;
                case 3: 
                    menuFiltrarPorFechas();
                    return;
                case 0:
                    return;
                default:
                    System.out.println("Por favor ingrese un caracter valido");
                    Menu.systemPause();
                    break;
            }

        } while (opcion != 0);
    }

    private static void menuMostrarPorFecha(){
        String fechaString;
        int opcion = -1; 
        do {
            try {
                System.out.println("Mostrar por fecha");
                Menu.clearScreen();

                System.out.println("Ingrese fecha(dd/mm/yyyy)");
                System.out.print("-->");
                fechaString = App.sc.nextLine();
                LocalDate fecha = LocalDate.parse(fechaString, FORMATTER);


                LinkedList<Registro> filtrado = mostrarPorFecha(fecha);
                System.out.println();
                System.out.println("Registros del dia " + fecha.format(FORMATTER));
                for(Registro r: filtrado){
                    System.out.println(r);
                }

                opcion = 0;
            } catch (DateTimeParseException ex) {
                System.out.println("Formato de fecha incorrecto");
                Menu.systemPause();
            }
            
        } while (opcion != 0);
    }
    private static void menuFiltrarPorFechas(){
        String fechaDString;
        String fechaHString;
        int opcion = -1; 
        do {
            try {
                Menu.clearScreen();
                System.out.println("Filtrado por fechas");

                System.out.println("Ingrese fecha desde(dd/mm/yyyy)");
                System.out.print("-->");
                fechaDString = App.sc.nextLine();
                LocalDate fechaDesde = LocalDate.parse(fechaDString, FORMATTER);

                System.out.println("Ingrese fecha hasta(dd/mm/yyyy)");
                System.out.print("-->");
                fechaHString = App.sc.nextLine();
                LocalDate fechaHasta = LocalDate.parse(fechaHString, FORMATTER);

                LinkedList<Registro> filtrado = filtrarPorFechas(fechaDesde, fechaHasta);
                System.out.println();
                System.out.println("Registros filtrados desde " + fechaDesde.format(FORMATTER) + " hasta " + fechaHasta.format(FORMATTER));
                for(Registro r: filtrado){
                    System.out.println(r);
                }

                opcion = 0;
            } catch (DateTimeParseException ex) {
                System.out.println("Formato de fecha incorrecto");
                Menu.systemPause();
            }
            
        } while (opcion != 0);
    }

/// Mostrar ------------------------------------------------------------------------------------------------------------------------------------------------
    
    private static void mostrarInvertido(){
        for (int i = lista.size() - 1; i >= 0; i--) {
            System.out.println(lista.get(i));
        }
    }

/// Filtros ------------------------------------------------------------------------------------------------------------------------------------------------

    private static LinkedList<Registro> filtrarPorFechas(LocalDate fechaD, LocalDate fechaH){
        LinkedList<Registro> filtrado = new LinkedList<>();
        for (Registro registro : lista) {
            LocalDate fechaRegistro = registro.getFecha().toLocalDate();
            if(!fechaRegistro.isBefore(fechaD) && !fechaRegistro.isAfter(fechaH)){ //se niega la condicion contraria para que se incluya la fecha(>= <=)
                filtrado.add(registro);
            }
        }
        Collections.reverse(filtrado);
        return filtrado;
    }

    private static LinkedList<Registro> mostrarPorFecha(LocalDate fecha){
        LinkedList<Registro> filtrado = new LinkedList<>();
        for (Registro registro : lista) {
            LocalDate fechaRegistro = registro.getFecha().toLocalDate();
            if(fechaRegistro.isEqual(fecha)){ 
                filtrado.add(registro);
            }
        }
        Collections.reverse(filtrado);
        return filtrado;
    }

/// Agregar ------------------------------------------------------------------------------------------------------------------------------------------------

    public static void agregarRegistroBaja(Producto borrado) {
        Registro registro = new Registro(LocalDateTime.now(), "El usuario dio de baja el producto: " + borrado);
        lista.add(registro);
        Archivo.subirRegistro(registro);
    }

    public static void agregarRegistroAlta(Producto nuevo) {
        Registro registro = new Registro(LocalDateTime.now(), "El usuario dio de alta el producto: " + nuevo);
        lista.add(registro);
        Archivo.subirRegistro(registro);
    }

    public static void agregarRegistroModificacion(Producto anterior, Producto actual) {
        Registro registro = new Registro(LocalDateTime.now(),
                "El usuario realizo estas modificaciones del producto:\n" +
                "Anterior: " + anterior + "\nActual: " + actual);
        lista.add(registro);
        Archivo.subirRegistro(registro);
    }

    public static void agregarRegistroModificacionStock(Producto modificado, int stockViejo) {
        int variacion = modificado.getStock() - stockViejo;
        Registro registro = new Registro(LocalDateTime.now(),
        "El usuario modifico el stock del producto: " + modificado +
        (variacion >= 0 ? "El stock aumento en " + variacion : "El stock disminuyo en " + Math.abs(variacion)));
        
        lista.add(registro);
        Archivo.subirRegistro(registro);
    }
    
    /// Constructores getters y setters ------------------------------------------------------------------------------------------------------------------------------------------------

    public static void leerArchivo(){
        lista = new LinkedList<>(Arrays.asList(Archivo.leerHistorial()));
    }
    public static LinkedList<Registro> getLista() {
        return lista;
    }

    public static void setLista(LinkedList<Registro> lista) {
        Historial.lista = lista;
    }
}
