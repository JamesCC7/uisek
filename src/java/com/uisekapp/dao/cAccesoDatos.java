/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisekapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jaime
 */
public class cAccesoDatos {
    private Connection cnn;
    private ResultSet rs;

    private Exception error;

    public cAccesoDatos() {
        this.cnn = null;
        this.error = null;
    }

    /**
     * @return the error
     */
    public Exception getError() {
        return error;
    }

    /**
     * @return the rs
     */
    public ResultSet getRs() {
        return rs;
    }

    public Byte conectar() {
        Byte result = 0;
        try {
            //Class.forName(com.SystemControl.dao.cGlobal.driverClass);
            Class.forName(com.uisekapp.dao.cGlobal.driverClass);
            result = 1;
            this.cnn = DriverManager.getConnection(com.uisekapp.dao.cGlobal.databaseURL, com.uisekapp.dao.cGlobal.usuarioDB, com.uisekapp.dao.cGlobal.claveDB);
            result = 2;
            //System.out.print("Conexion exitosa");
        } catch (SQLException e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
             System.out.print("No se ha realizado la conexión con la base de datos.");
            this.error = e;
            return result=0;
        } finally {
            return result;
        }
    }

    public Byte desconectar() {
        Byte result = 0;
        try {
            this.cnn.close();
            this.cnn = null;
            result = 1;
        } catch (SQLException e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());

            this.error = e;
        } finally {
            return result;
        }
    }

    public Byte ejecutarSelect(String SQL) {
        Byte result = 0;
        try {
            Statement smt = this.cnn.createStatement();
            this.rs=smt.executeQuery(SQL);
            if(rs!=null)
            {
                result=1;
            }
 
        } catch (SQLException e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());

            this.error = e;
        } finally {
            return result;
        }
    }
    
    public Integer ejecutarComando( String SQL ) {
        Integer result = 0;
        try {
            PreparedStatement smt = this.cnn.prepareStatement(SQL);
            result=smt.executeUpdate();            
        } catch (SQLException e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());

            this.error = e;
        } finally {
            return result;
        }
    }
    public Byte executeUpdate(String strSQL) {
        Byte result = 0;

        try {
            PreparedStatement stm = this.cnn.prepareStatement(strSQL);
            stm.executeUpdate();
            this.rs = null;
            result = 1;
        } catch (SQLException e) {
            System.err.println("ERROR: " + e.getClass().getName() + " : " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }
    
      public ResultSet ExecSQL(String strSQL) {
        Statement stmt;
        ResultSet rs = null;
        try {
            if (this.cnn == null) {
                this.conectar();
            }
            if (this.cnn != null) {
                stmt = this.cnn.createStatement();
                rs = stmt.executeQuery(strSQL);
            }
        } catch (SQLException ex) {
            Logger.getLogger(cAccesoDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
         
    public Byte ExecuteUpdate(String strSQL) {
        Byte result = 0;
        try {
            if (this.cnn == null) {
                this.conectar();
            }
            if (this.cnn != null) {
                PreparedStatement stm = this.cnn.prepareStatement(strSQL);
                stm.execute();
                result = 1;
            }

        } catch (SQLException e) {
            System.err.println("ERROR: " + e.getClass().getName() + " : " + e.getMessage());
            this.error = e;
        }
        return result;
    }
    
        // Probar conexion 2 si si conecta y 0 si no   
   public static void main(String[] args) {
       cAccesoDatos dao = new cAccesoDatos();
   int valor = -1;
   valor = dao.conectar();
    System.out.println(" Conexion:, valor: " + valor);
       System.out.println("Cerrar coneccion..." + dao.desconectar());
   }
}
