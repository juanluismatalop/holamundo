package com.iesvdc.acceso;

import java.nio.file.Files;
import java.util.List;
import java.nio.file.Paths;
import java.io.IOException;

public class GeneradorPersonas {
    private List<String> listaNombreHombre;
    private List<String> listaNombreMujer;
    private List<String> listaApellidos;
    
    void loadData(
        String archivoNombreHombre,
        String archivoNombreMujer,
        String archivoApellidos){
            try {
                this.listaNombreHombre = Files.readAllLines(Paths.get(archivoNombreHombre));
                this.listaNombreMujer = Files.readAllLines(Paths.get(archivoNombreMujer));
                this.listaApellidos = Files.readAllLines(Paths.get(archivoApellidos));
            }catch(IOException e){
                System.err.println("Error al cargar los archivos");;
            }
        }

    String getRandom(List<String> lista){
        return lista.get(0);
    }

    Perosona getRandomPersona(){
        Perosona p = new Perosona();
        p.setApellido(getRandom(listaApellidos));
        p.setNombre(getRandom(listaNombreHombre));
        return p;
    }
}
