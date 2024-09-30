package com.iesvdc.acceso;

import java.time.LocalDate;
import java.util.Objects;

public class Persona {

    String nombre;
    String apellido;
    String email;
    int numeroDNI;
    char letraDNI;
    LocalDate fechaNacimiento;
    Sexo sexo;

    public Persona() {
    }

    public Persona(String nombre, String apellido, String email, int numeroDNI, char letraDNI, LocalDate fechaNacimiento, Sexo sexo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.numeroDNI = numeroDNI;
        this.letraDNI = letraDNI;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
    }


    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumeroDNI() {
        return this.numeroDNI;
    }

    public void setNumeroDNI(int numeroDNI) {
        this.numeroDNI = numeroDNI;
    }

    public char getLetraDNI() {
        return this.letraDNI;
    }

    public void setLetraDNI(char letraDNI) {
        this.letraDNI = letraDNI;
    }

    public LocalDate getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Sexo getSexo() {
        return this.sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }
    

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Persona)) {
            return false;
        }
        Persona perosona = (Persona) o;
        return Objects.equals(nombre, perosona.nombre) && Objects.equals(apellido, perosona.apellido) && Objects.equals(email, perosona.email) && numeroDNI == perosona.numeroDNI && letraDNI == perosona.letraDNI && Objects.equals(fechaNacimiento, perosona.fechaNacimiento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, apellido, email, numeroDNI, letraDNI, fechaNacimiento);
    }

    @Override
    public String toString() {
        return "nombre=" + getNombre() +
            ", apellido=" + getApellido() +
            ", email=" + getEmail() +
            ", numeroDNI=" + getNumeroDNI() +
            ", letraDNI=" + getLetraDNI() +
            ", fechaNacimiento="+ getFechaNacimiento() +
            ", sexo=" + getSexo() +"\n\n"
;
    }

    
}
