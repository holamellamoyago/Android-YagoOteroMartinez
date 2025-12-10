package com.example.eva.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

public class GestorSharedPReferences {

    // 1. Campos Estáticos para el Singleton y el Nombre del Archivo
    private static GestorSharedPReferences instancia;
    private static SharedPreferences sharedPreferences;

    // Nombre del archivo de preferencias
    private static final String PREFS_NAME = "MisPreferenciasApp";

    // Claves de ejemplo (¡Reemplaza con tus propias claves!)
    public static final String KEY_NOMBRE_USUARIO = "nombreUsuario";
    public static final String KEY_ESTADO_LOGUEADO = "estadoLogueado";
    public static final String KEY_PUNTUACION_ALTA = "puntuacionAlta";

    // 2. Constructor Privado
    /**
     * Constructor privado para el patrón Singleton.
     * @param context Contexto de la aplicación.
     */
    private GestorSharedPReferences(Context context) {
        // Inicializa el objeto SharedPreferences con el modo privado
        sharedPreferences = context.getApplicationContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    // 3. Método Estático de Inicialización y Obtención de Instancia (Singleton)
    /**
     * Obtiene la única instancia de GestorSharedPReferences.
     * Debe ser llamado al menos una vez (ej. en la clase Application o en el onCreate de la primera Activity).
     * @param context Contexto de la aplicación.
     * @return La instancia única de GestorSharedPReferences.
     */
    public static synchronized GestorSharedPReferences getInstance(Context context) {
        if (instancia == null) {
            instancia = new GestorSharedPReferences(context);
        }
        return instancia;
    }

    // --- MÉTODOS PARA GUARDAR DATOS (WRITE) ---

    /**
     * Guarda una cadena (String) en SharedPreferences.
     */
    public void guardarString(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply(); // Usa apply() para guardar de forma asíncrona
    }

    /**
     * Guarda un booleano (Boolean) en SharedPreferences.
     */
    public void guardarBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * Guarda un entero (Int) en SharedPreferences.
     */
    public void guardarInt(String key, int value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    // --- MÉTODOS PARA LEER DATOS (READ) ---

    /**
     * Recupera una cadena (String) de SharedPreferences.
     * @param defaultValue Valor a devolver si la clave no se encuentra.
     */
    public String leerString(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    /**
     * Recupera un booleano (Boolean) de SharedPreferences.
     * @param defaultValue Valor a devolver si la clave no se encuentra.
     */
    public boolean leerBoolean(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    /**
     * Recupera un entero (Int) de SharedPreferences.
     * @param defaultValue Valor a devolver si la clave no se encuentra.
     */
    public int leerInt(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }

    // --- MÉTODOS ADICIONALES ---

    /**
     * Elimina una clave y su valor de SharedPreferences.
     */
    public void eliminar(String key) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.apply();
    }

    /**
     * Borra todas las claves y valores de SharedPreferences.
     */
    public void limpiarTodo() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}