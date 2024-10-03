package com.iesvdc.acceso;

import com.iesvdc.acceso.Modelo.Personas;
/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Personas personas = new Personas();
        personas.loadData(
            "datos/nombres-mujer.txt", 
            "datos/nombres-hombre.txt",
            "datos/apellidos.txt");
        
        try {
            personas.generaPersonas(100);
            System.out.println(personas.getPersonas().toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
       

