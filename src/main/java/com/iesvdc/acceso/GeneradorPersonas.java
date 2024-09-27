package com.iesvdc.acceso;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

import com.iesvdc.acceso.Persona;
import com.iesvdc.acceso.Sexo;

/**
 * Clase general para cargar archivos de nombres y apellidos 
 * y generar aleatoriamente listas de personas.
 */
public class GeneradorPersonas {
    private List<String> listaNombresHombre;
    private List<String> listaNombresMujer;
    private List<String> listaApellidos;
    private Sexo[] sexos;

    public GeneradorPersonas() {
        this.sexos = Sexo.values();
    }


    /**
     * Configuración inicial para indicar dónde están 
     * los archivos que contienen los nombres y apellidos.
     * Un nombre o apellido por línea del fichero.
     * @param archivoNombreMujer El nombre del archivo con los nombres de mujer
     * @param archivoNombreHombre El nombre del archivo con los nombres de hombre
     * @param archivoApellido El nombre del archivo con los apellidos
     */
    public void loadData(
        String archivoNombreMujer, 
        String archivoNombreHombre, 
        String archivoApellido){

        try {
            this.listaApellidos = Files.readAllLines(
                Paths.get(archivoApellido));
            this.listaNombresHombre = Files.readAllLines(
                Paths.get(archivoNombreHombre));
            this.listaNombresMujer = Files.readAllLines(
                Paths.get(archivoNombreMujer));
        } catch (IOException ioe) {
            System.err.println("Error al cargar los datos");
            System.err.println(ioe.getLocalizedMessage());
        }

    }

    /**
     * Devuelve un elemento aleatorio de la lista de String 
     * que se le pasa como parámetro.
     * @param lista una lista de cadena de caracteres
     * @return un String
     */
    private String getRandomString(List<String> lista){
        return lista.get(generateRandomNumber(lista.size()));
    }

    /**
     * Genera un número entero aleatorio entre 0 y max-1
     * @param max el número máximo (excluído)
     * @return un aleatorio 0..max-1
     */
    private static int generateRandomNumber(int max) {
        Random random = new Random();
        return random.nextInt(max);
    }

    /**
     * Generador aleatorio que devuelve un sexo 
     * @return un sexo aleatorio
     */
    private Sexo getRandomSexo() {
        Sexo s = sexos[generateRandomNumber(sexos.length)];

        return s;
    }

    /**
     * El correo es:
     * primera letra del nombre
     * primer apellido sin espacios
     * segundo apellido sin espacios
     * todo en minúscula
     * quitamos acentos, letra ñ y letra ç
     * @param p
     * @return
     */
    String getEmail(Persona p){
        String email="@educaand.es";

        return email;
    }

    /**
     * Nos devuelve una persona generada aleatoriamente
     * @return un objeto de tipo persona
     */
    private Persona getRandomPersona(){
        Persona p = new Persona();

        p.setSexo(getRandomSexo());

        p.setApellido(getRandomString(listaApellidos));

        switch (p.getSexo()) {
            case HOMBRE:
                p.setNombre(getRandomString(listaNombresHombre));
                break;        
            case MUJER:
                p.setNombre(getRandomString(listaNombresMujer));
                break;
            default:
                p.setNombre(
                    generateRandomNumber(2)==0 ?
                    getRandomString(listaNombresHombre):
                    getRandomString(listaNombresMujer));
                break;
        }

        p.setEmail(getEmail(p));
        
        

        return p;
    }

    /**
     * Dado un número de DNI (número entero entre 1.000.000 y 99.999.999)
     * @param dni
     * @return
     */
    public char calcularLetra(int dni) throws DniException{
        if (dni > 99999999 || dni < 1000000) {
            throw new DniException();
        }
        String caracteres="TRWAGMYFPDXBNJZSQVHLCKE";
        int resto = dni%23;
        return caracteres.charAt(resto);
   }
}
