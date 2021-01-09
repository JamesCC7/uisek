/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisekapp.models;

/**
 *
 * @author Jaime
 */
public class cMateria {
    
    private int idMateria;
    private String nombre_materia;
    private Integer creditos;

    public cMateria() {
    }

    public cMateria(int idMateria, String nombre_materia, Integer creditos) {
        this.idMateria = idMateria;
        this.nombre_materia = nombre_materia;
        this.creditos = creditos;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public String getNombre_materia() {
        return nombre_materia;
    }

    public void setNombre_materia(String nombre_materia) {
        this.nombre_materia = nombre_materia;
    }

    public Integer getCreditos() {
        return creditos;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }
}
