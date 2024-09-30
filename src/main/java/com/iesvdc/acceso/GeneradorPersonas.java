package com.iesvdc.acceso;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        String email=p.nombre.charAt(0)+p.apellido+"@educaand.es";
        return email.toLowerCase();
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

        p.setNumeroDNI(gnerarDNIRandom());

        try{
            p.setLetraDNI(calcularLetra(gnerarDNIRandom()));
        }catch(DniException e){}

        p.setFechaNacimiento(generarFechasNacimiento());
        
        

        return p;
    }

    /**
     * Dado un número de DNI (número entero entre 1.000.000 y 99.999.999)
     * @param dni
     * @return  devuelve un la letra del DNI valido
     */
    public char calcularLetra(int dni) throws DniException{
        if (dni > 99999999 || dni < 1000000) {
            throw new DniException("DNI fuera de rango");
        }
        String caracteres="TRWAGMYFPDXBNJZSQVHLCKE";
        int resto = dni%23;
        return caracteres.charAt(resto);
    }
    /**
     * Un metodo que genera una lista de personas que vaya recorriendo la lista y añadiendo automaticamente
     * @param numero
     * @return lista de personas
     */
    public List<Persona> generaPersonas(int numero)throws Exception{
        List<Persona> lista = new ArrayList<Persona>();
        if (this.listaApellidos==null ||this.listaNombresHombre==null || this.listaNombresMujer==null) {
            //System.err.println("No se han podido acceder a esos archivos");
            throw new Exception("Error no se han cargado los archivos con los nombres y apellidos previamente.");
            
        }
        else
            for (int i = 0; i < numero; i++){
                lista.add(getRandomPersona());
        }
        return lista;
    }
    /**
     * Metodo que genera una fecha aleatoria desde 1920 hasta hoy 
     * @return la fecha de nacimiento
     */
    private LocalDate generarFechasNacimiento(){
        //long [nombre] = dd-MM-yyyy HH:mm:ss
        LocalDate startingDate = LocalDate.of(1920, 1, 1);
        long startingDateLong = startingDate.toEpochDay();
        LocalDate endingDate = LocalDate.now();
        long endingDateLong = endingDate.toEpochDay();
        long aleatorio = new Random().nextLong(endingDateLong - startingDateLong);        
        LocalDate fechaRandom = LocalDate.ofEpochDay(aleatorio + startingDateLong);
        return fechaRandom;
    } 

    private static int gnerarDNIRandom(){
        Random rd = new Random();
        int dni = rd.nextInt(1000000 , 10000000);
        return dni;
    }
}
