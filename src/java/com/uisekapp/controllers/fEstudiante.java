/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisekapp.controllers;

import com.uisekapp.dao.cAccesoDatos;
import com.uisekapp.models.cEstudiante;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Jaime
 */
public class fEstudiante {
    
    public boolean ingresarEstudiante(cEstudiante estudiante){
        boolean result = false;
        String query;
        
        query = "SELECT public.ingreso_estudiante("
                + "'" + estudiante.getCedula() + "', '" 
                + estudiante.getNombres() + ", '" 
                + estudiante.getApellidos()+ "', '" 
                + estudiante.getTelefono() + "', '" 
                + estudiante.getCorreo() + "');" ;
        
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
    }
    
    public ArrayList<cEstudiante> listarEstudiantes(){
        String query;
        ArrayList<cEstudiante> materias = new ArrayList<>();
        
        query = "SELECT * from public.lista_estudiantes();";
        
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

    private cEstudiante convertir(ResultSet rs) throws SQLException {
        cEstudiante estudiante = new cEstudiante();
        estudiante.setIdEstudiante(rs.getInt(1));
        estudiante.setCedula(rs.getString(2));
        estudiante.setNombres(rs.getString(3));
        estudiante.setApellidos(rs.getString(4));
        return estudiante;
    }
    
    
     public static void main(String[] args) {
        fEstudiante obj = new fEstudiante();
        
         for (cEstudiante elem : obj.listarEstudiantes()) {
             System.out.println(elem.getIdEstudiante()+ " "+ elem.getCedula() + " " + elem.getNombres() + " "+ elem.getApellidos());
         }
        
    }
    
}
