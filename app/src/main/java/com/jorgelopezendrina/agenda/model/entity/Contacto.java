package com.jorgelopezendrina.agenda.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

/**
 * Clase Pojo del programa, que funcionará en la base de datos como una tabla, gracias al @Entity y
 * el @ColumInfo de sus campos.
 * */

@Entity(tableName = "Contacto")
public class Contacto implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    @NonNull
    @ColumnInfo(name = "nombre")
    private String nombre;

    @NonNull
    @ColumnInfo(name = "apellidos")
    private String apellidos;

    @NonNull
    @ColumnInfo(name = "tlf")
    private String tlf;

    @NonNull
    @ColumnInfo(name = "nacimiento")
    private String nacimiento;

    @NonNull
    @ColumnInfo(name = "localidad")
    private String localidad;

    @NonNull
    @ColumnInfo(name = "calle")
    private String calle;

    @NonNull
    @ColumnInfo(name = "numero")
    private String numero;

    public Contacto() {
    }

    public Contacto(@NonNull String nombre, @NonNull String apellidos, String tlf, @NonNull String nacimiento, @NonNull String localidad, @NonNull String calle, @NonNull String numero) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.tlf = tlf;
        this.nacimiento = nacimiento;
        this.localidad = localidad;
        this.calle = calle;
        this.numero = numero;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @NonNull
    public String getNombre() {
        return nombre;
    }

    @NonNull
    public String getApellidos() {
        return apellidos;
    }

    public String getTlf() {
        return tlf;
    }

    @NonNull
    public String getNacimiento() {
        return nacimiento;
    }

    @NonNull
    public String getLocalidad() {
        return localidad;
    }

    @NonNull
    public String getCalle() {
        return calle;
    }

    @NonNull
    public String getNumero() {
        return numero;
    }

    public void setNombre(@NonNull String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(@NonNull String apellidos) {
        this.apellidos = apellidos;
    }

    public void setTlf(@NonNull String tlf) {
        this.tlf = tlf;
    }

    public void setNacimiento(@NonNull String nacimiento) {
        this.nacimiento = nacimiento;
    }

    public void setLocalidad(@NonNull String localidad) {
        this.localidad = localidad;
    }

    public void setCalle(@NonNull String calle) {
        this.calle = calle;
    }

    public void setNumero(@NonNull String numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Contacto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", tlf='" + tlf + '\'' +
                ", nacimiento='" + nacimiento + '\'' +
                ", localidad='" + localidad + '\'' +
                ", calle='" + calle + '\'' +
                ", numero='" + numero + '\'' +
                '}';
    }
}

