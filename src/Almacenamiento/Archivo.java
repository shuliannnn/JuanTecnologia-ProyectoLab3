package Almacenamiento;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Producto.*;

public abstract class Archivo {

    public static final String ARCHIVO_ID = "id.json";
    public static final String ID_KEY = "contador";

    public static void subirProducto(Producto p, boolean esNuevo) {
        String archivo = obtenerNombreArchivo(p);
        JSONArray productos = new JSONArray();
        
        String contenido = downloadJSON(archivo);
        if (!contenido.isEmpty()) {
            try{
                productos = new JSONArray(contenido);
            } catch(JSONException e){
                e.printStackTrace();
            }
        }
        
        if (esNuevo) {
            productos.put(p.toJSON());
        } else {
            try{
                for (int i = 0; i < productos.length(); i++) {
                    JSONObject prod = productos.getJSONObject(i);
                    if (prod.getInt("id") == p.getId()) {
                        //Aca hay que modificar el stock
                        //productos.put(i, p.toJSON());
    
                        break;
                    }
                }
            }catch(JSONException e){
                e.printStackTrace();
            }
            
        }
        
        uploadJSON(productos, archivo);
    }

    public static void subirContadorId(int contId){
        JSONObject json = new JSONObject();
        try{
            json.put(ID_KEY, contId);
        }catch(JSONException e){
            e.printStackTrace();
        }
        uploadJSON(json, ARCHIVO_ID);
    }

    public static int leerContadorId(int contId){
        String res = downloadJSON(ARCHIVO_ID);
        try{
            JSONObject json = new JSONObject(res);
            return json.getInt(ID_KEY);
        }catch (JSONException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private static String obtenerNombreArchivo(Producto p) {
        return p.getClass().getName().toLowerCase() + ".json";
    }

    public static void uploadJSON(JSONArray jsonArray, String archivo) {
        try (FileWriter file = new FileWriter(archivo)) {
            file.write(jsonArray.toString(4));  // 4 es para indentación del JSON
            file.flush();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    public static void uploadJSON(JSONObject jsonObject, String archive){
        try{
            FileWriter file = new FileWriter(archive+".json");
            file.write(jsonObject.toString());
            file.flush();
            file.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static String downloadJSON(String archivo) {
        String contenido = "";
        try {
            contenido = new String(Files.readAllBytes(Paths.get(archivo)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contenido;
    }
}
