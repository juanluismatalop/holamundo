package com.iesvdc.acceso.Modelo;
import java.util.Objects;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Direccion {
    @XmlAttribute
    private long id;
    @XmlElement
    private String nombreVia;
    @XmlElement
    private TipoVia tipoVia;
    @XmlElement
    private int numero;
    @XmlElement
    private String detalle;
    

    public Direccion() {
    }

    public Direccion(long id, String nombreVia, TipoVia tipoVia, int numero, String detalle) {
        this.id = id;
        this.nombreVia = nombreVia;
        this.tipoVia = tipoVia;
        this.numero = numero;
        this.detalle = detalle;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombreVia() {
        return this.nombreVia;
    }

    public void setNombreVia(String nombreVia) {
        this.nombreVia = nombreVia;
    }

    public TipoVia getTipoVia() {
        return this.tipoVia;
    }

    public void setTipoVia(TipoVia tipoVia) {
        this.tipoVia = tipoVia;
    }

    public int getNumero() {
        return this.numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getDetalle() {
        return this.detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Direccion id(long id) {
        setId(id);
        return this;
    }

    public Direccion nombreVia(String nombreVia) {
        setNombreVia(nombreVia);
        return this;
    }

    public Direccion tipoVia(TipoVia tipoVia) {
        setTipoVia(tipoVia);
        return this;
    }

    public Direccion numero(int numero) {
        setNumero(numero);
        return this;
    }

    public Direccion detalle(String detalle) {
        setDetalle(detalle);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Direccion)) {
            return false;
        }
        Direccion direccion = (Direccion) o;
        return id == direccion.id ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombreVia, tipoVia, numero, detalle);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nombreVia='" + getNombreVia() + "'" +
            ", tipoVia='" + getTipoVia() + "'" +
            ", numero='" + getNumero() + "'" +
            ", detalle='" + getDetalle() + "'" +
            "}";
    }
    
}

