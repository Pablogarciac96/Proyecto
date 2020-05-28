package com.garcua.lobosapp;

import android.widget.EditText;

public class persona {

    private String id;
    private String nombre;
    private String apellido;
    private String ciudad;

    public persona() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

  //  public void setNombre(String nombre) {
    //    this.nombre = nombre;
    //}

    public String getApellido() {
        return apellido;
    }

  //  public void setApellido(String apellido) {
    //    this.apellido = apellido;
   // }

    public String getCiudad() {
        return ciudad;
    }

   // public void setCiudad(String ciudad) {
  //      this.ciudad = ciudad;
  //  }

    @Override
    public String toString() {
        return nombre;
    }

    public void setNombre(String mnombre) {
    }

    public void setApellido(String mapellido) {
    }

    public void setCiudad(String mciudad) {
    }
}
