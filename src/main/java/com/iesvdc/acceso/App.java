package com.iesvdc.acceso;

import java.util.List;

/**
 * Hola mundo
 */
public class App {
    //hola
    public static void main(String[] args) {
        Personas gp = new Personas();
        gp.loadData("datos\\nombres-mujeres.txt",
         "datos\\nombres-hombre.txt",
          "datos\\apellido.txt");
        try{
            System.out.println(gp.generaPersonas(10).toString());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }

    public List<Personas> getPersonas(){
        return this.personas;
        }
}
