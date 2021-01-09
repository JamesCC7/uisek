/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisekapp.controllers;

import com.uisekapp.dao.cAccesoDatos;
import com.uisekapp.models.cMateria;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Jaime
 */
public class fMateria {
    
    public boolean ingresarMateria(cMateria materia){
        boolean result = false;
        String query;
        
        query = "SELECT * from public.ingreso_materia("
                + "'" + materia.getNombre_materia() + "', "
                + materia.getCreditos() + ");";
    
        cAccesoDatos ad = new cAccesoDatos();
        
        try {
                        
            if (ad.conectar() != 0) {
                if (ad.ejecutarSelect(query) != 0) {
                    result = true;
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
        } finally {
            ad.desconectar();
        }
        return result;
    };
    
    
    public ArrayList<cMateria> listarMaterias(){
        String query;
        ArrayList<cMateria> materias = new ArrayList<>();
        
        query = "SELECT * from public.lista_materias();";
        
        cAccesoDatos ad = new cAccesoDatos();
        
        try {
            if (ad.conectar() != 0) {
                if (ad.ejecutarSelect(query) != 0) {
                    ResultSet rs = ad.getRs();
                    while (rs.next()) {
                        materias.add(convertir(rs));
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
        } finally {
            ad.desconectar();
        }
        
        return materias;
    };

    private cMateria convertir(ResultSet rs) throws SQLException {
        cMateria materia = new cMateria();
        materia.setIdMateria(rs.getInt(1));
        materia.setNombre_materia(rs.getString(2));
        materia.setCreditos(rs.getInt(3));
        return materia;
    }
      
     public static void main(String[] args) {
        fMateria obj = new fMateria();
        
         for (cMateria elem : obj.listarMaterias()) {
             System.out.println(elem.getIdMateria()+ " "+ elem.getNombre_materia() + " " + elem.getCreditos());
         }
        
    }
}
