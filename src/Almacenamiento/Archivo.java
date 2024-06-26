package Almacenamiento;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Almacenamiento.Registros.Registro;
import Producto.*;


public abstract class Archivo {

/// Constantes -----------------------------------------------------------------------------------------------------------------------------
    
    public static final String ARCHIVO_ID = "id.json";
    public static final String CONT_ID_KEY = "contador";
    public static final String ARCHIVO_REGISTRO = "registro.json";

/// Metodos de Registro ------------------------------------------------------------------------------------------------------------------------
    
    public static void subirRegistro(Registro registro){
        JSONArray registros = leerArchivoProducto(ARCHIVO_REGISTRO);
        registros.put(registro.toJSON());
        uploadJSON(registros, ARCHIVO_REGISTRO);
    }

    public static Registro[] leerHistorial(){
        JSONArray registros = leerArchivoRegistro();
        Registro[] array = new Registro[registros.length()];
        try{
            for (int i = 0; i < array.length; i++) {
                array[i] = Registro.fromJSON(registros.getJSONObject(i));
            }
        }catch(JSONException e){
            e.printStackTrace();
        }
        return array;
    }

    private static JSONArray leerArchivoRegistro(){
        JSONArray registros = new JSONArray();
        
        String contenido = downloadJSON(ARCHIVO_REGISTRO);
        if (!contenido.isEmpty()) {
            try{
                registros = new JSONArray(contenido);
            } catch(JSONException e){
                e.printStackTrace();
            }
        }
        return registros;
    }
 
//// Metodos de Producto -----------------------------------------------------------------------------------------------------------------------------------
    
    private static JSONArray leerArchivoProducto(String archivo) {
        JSONArray productos = new JSONArray();
        
        String contenido = downloadJSON(archivo);
        if (!contenido.isEmpty()) {
            try{
                productos = new JSONArray(contenido);
            } catch(JSONException e){
                e.printStackTrace();
            }
        }
        return productos;
    }

    public static void subirProducto(Producto p){
        String archivo = obtenerNombreArchivo(p);
        JSONArray productos = leerArchivoProducto(archivo);

        productos.put(p.toJSON());
        uploadJSON(productos, archivo);

    }

    public static boolean modificarProducto(Producto p){
        String archivo = obtenerNombreArchivo(p);
        JSONArray productos = leerArchivoProducto(archivo);
        JSONObject prod = new JSONObject();
        Producto aux;

        try{
            for (int i = 0; i < productos.length(); i++) {
                prod = productos.getJSONObject(i);
                aux = productoFromJSON(prod);
                if(aux.getId() == p.getId()){
                    prod = p.toJSON();
                    productos.put(i, prod);
                    break;
                }
            }
            uploadJSON(productos, archivo);
            return true;
        }
        catch(JSONException e){
            e.printStackTrace();
        }
        return false;
    }

    public static boolean eliminarProducto(Producto p){
        String archivo = obtenerNombreArchivo(p);
        JSONArray productos = leerArchivoProducto(archivo);
        JSONObject prod = new JSONObject();
        Producto aux;

        try{
            for (int i = 0; i < productos.length(); i++) {
                prod = productos.getJSONObject(i);
                aux = productoFromJSON(prod);
                if(aux.getId() == p.getId()){
                    productos.remove(i);
                    break;
                }
            }
            uploadJSON(productos, archivo);
            return true;
        }
        catch(JSONException e){
            e.printStackTrace();
        }
        return false;
    }

    public static Producto[] leerListaProducto(String archivo){
        JSONArray productos = leerArchivoProducto(archivo);
        Producto[] array = new Producto[productos.length()];
        try{
            for (int i = 0; i < array.length; i++) {
                array[i] = productoFromJSON(productos.getJSONObject(i));
            }
        }catch(JSONException e){
            e.printStackTrace();
        }
        return array;
    }

    private static Producto productoFromJSON(JSONObject json) throws JSONException{
        String tipo = "";
        try{
            tipo = json.getString("tipo");
        } catch(JSONException e){
            e.printStackTrace();
        }

        switch (tipo) {
            case "Auricular": return Auricular.fromJSON(json);
            case "Cable": return Cable.fromJSON(json);
            case "Celular": return Celular.fromJSON(json);
            case "Mouse": return Mouse.fromJSON(json);
            case "Parlante": return Parlante.fromJSON(json);
            case "PC": return Pc.fromJSON(json);
            case "Portatil": return Portatil.fromJSON(json);
            case "Teclado": return Teclado.fromJSON(json);
            
            default: throw new JSONException("No se encontro papi");
        }
    }

    public static String obtenerNombreArchivo(Producto p) {
        return p.getClass().getSimpleName().toLowerCase() + ".json";
    }


/// Metodos Id ---------------------------------------------------------------------------------------------------------------------------

    public static void subirContadorId(){
        JSONObject json = new JSONObject();
        try{
            json.put(CONT_ID_KEY, leerContadorId()+1);
        }catch(JSONException e){
            e.printStackTrace();
        }
        uploadJSON(json, ARCHIVO_ID);
    }

    public static int leerContadorId(){
        File file = new File("data/" + ARCHIVO_ID);
        try{
            if(!file.exists()){
                JSONObject jo = new JSONObject();
                jo.put(CONT_ID_KEY, 1);
                uploadJSON(jo, ARCHIVO_ID);
                return 1;
            }
            String res = downloadJSON(ARCHIVO_ID);
            JSONObject json = new JSONObject(res);

            return json.getInt(CONT_ID_KEY);
        }catch (JSONException e) {
            e.printStackTrace();
            return -1;
        }
    }

/// Metodos Generales  --------------------------------------------------------------------------------------------------------------------

    private static void uploadJSON(JSONArray jsonArray, String archivo) {
        try {
            File f = new File("data", archivo);
            FileWriter file = new FileWriter(f);
            file.write(jsonArray.toString(4));  // 4 es para indentación del JSON
            file.flush();
            file.close();
        } catch (IOException | JSONException e) {
            
        }
    }

    private static void uploadJSON(JSONObject jsonObject, String archivo){
        try{
            File f = new File("data", archivo);
            FileWriter file = new FileWriter(f);
            file.write(jsonObject.toString(4));
            file.flush();
            file.close();
        }catch(IOException | JSONException e){
            
        }
    }

    private static String downloadJSON(String archivo) {
        String contenido = "";
        try {
            File f = new File("data", archivo);
            if (!f.exists()) {
                // Crea el archivo y escribe un contenido inicial si es necesario
                f.getParentFile().mkdirs(); // Asegura que los directorios padres existen
                try (FileWriter writer = new FileWriter(f)) {
                    writer.write(""); // Puedes escribir un contenido inicial aquí
                }
            }
            else contenido = new String(Files.readAllBytes(Paths.get(f.getPath())));
        } catch (IOException e) {
            
        }
        return contenido;
    }
}
