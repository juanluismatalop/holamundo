package com.iesvdc.acceso;

public class CodigoPostal {
    private int cp;
    private String localidad;
    private String provincia;


    public CodigoPostal(int cp, String localidad, String provincia) {
        this.cp = cp;
        this.localidad = localidad;
        this.provincia = provincia;
    }

    public int getCp() {
        return this.cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    public String getLocalidad() {
        return this.localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return this.provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
    
}
