package com.iesvdc.acceso;

import java.io.File;
import com.iesvdc.acceso.Modelo.Personas;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

public class UnMarshallerJson {
    public static void main(String[] args) {
        Personas lista = new Personas();
        JAXBContext jaxbContext;
        try{
            jaxbContext = JAXBContext.newInstance(lista.getClass());
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Object objeto = jaxbUnmarshaller.unmarshal(new File("personas.json"));
            lista = (Personas) objeto;
            System.out.println(lista.toString());
        }catch(JAXBException e){
            e.printStackTrace();
        }

    }
    
}
