package com.iesvdc.acceso;

/**
 * Hola mundo
 */
public class App {
    public static void main(String[] args) {
        GeneradorPersonas gp = new GeneradorPersonas();
        gp.loadData("datos\\nombres-mujeres.txt",
         "datos\\nombres-hombre.txt",
          "datos\\apellido.txt");
        try{
            System.out.println(gp.generaPersonas(10).toString());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
          
    }
}
