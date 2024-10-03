package com.iesvdc.acceso;

import org.eclipse.persistence.jaxb.MarshallerProperties;

import com.iesvdc.acceso.Modelo.Personas;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import java.io.File;

/**
 * Marshaller JSON
 */
public class MarshallerJson {
    public static void main(String[] args) {
        Personas personas = new Personas();
        personas.loadData(
            "datos/nombres-mujer.txt", 
            "datos/nombres-hombre.txt",
            "datos/apellidos.txt");
        
        try {
            //System.setProperty("javax.xml.bind.JAXBContextFactory", "org.eclipse.persistence.jaxb.JAXBContextFactory");
            personas.generaPersonas(100);
            JAXBContext jbc = JAXBContext.newInstance(
                personas.getClass());
            Marshaller marshaller = jbc.createMarshaller();
            marshaller.setProperty(
                MarshallerProperties.MEDIA_TYPE, 
                "application/json");
            marshaller.setProperty(
                    MarshallerProperties.JSON_INCLUDE_ROOT, true);
            marshaller.marshal(personas, 
                new File("datos/personas.json"));
        } catch (Exception e) {
            //System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
