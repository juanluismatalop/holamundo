package com.iesvdc.acceso.Modelo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.iesvdc.acceso.Excepciones.DniException;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

/**
 * Clase general para cargar archivos de nombres y apellidos 
 * y generar aleatoriamente listas de personas.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Personas {
    @XmlElement
    private List<Persona> personas;
    @XmlTransient
    private List<String> listaNombresHombre;
    @XmlTransient
    private List<String> listaNombresMujer;
    @XmlTransient
    private List<String> listaApellidos;
    @XmlTransient
    private Sexo[] sexos;

    public Personas() {
        this.sexos = Sexo.values();
        this.personas = new ArrayList<Persona>();
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
     * @return un DNI generado en base al nombre de la persona
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
        p.setFechaNacimiento(generaFechaNacimiento());
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
     * @return la letra para un número de DNI válido
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
     * Genera tantas personas como se indican
     * @param numero personas a generar
     * @return lista que contiene las personas
     */
    public void generaPersonas(int numero) throws Exception{
        
        if (this.listaApellidos==null || 
            this.listaNombresHombre==null || 
            this.listaNombresMujer == null)
            {
                //System.err.println("Error no se han cargado los archivos "+
                // "con los nombres y apellidos previamente.");

                throw new Exception("Error no se han cargado los archivos "+
                    "con los nombres y apellidos previamente.");

            } else {
                for (int i = 0; i < numero; i++) {
                    this.personas.add(getRandomPersona());
                }
            }        
    }

    public List<Persona> getPersonas() {
        return this.personas;
    }

    LocalDate generaFechaNacimiento() {

        LocalDate inicio = LocalDate.of(1920, 1, 1);
        LocalDate fin = LocalDate.now();

        Long rango = fin.toEpochDay() - inicio.toEpochDay();
        Long r = new Random().nextLong(rango);

        return LocalDate.ofEpochDay(r+inicio.toEpochDay());
        
    }
}

