/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 *
 * @author jhosu
 */
public class DBConnection {

    private final String HOST = "sql.freedb.tech";
    private final String USUARIO = "freedb_UserPruebas";
    private final String CLAVE = "bnRB@krf4&bB#72";
    private final String BASEDATOS = "freedb_FarmaciaSocial";
    private final String URL;
    private Connection link;
    private PreparedStatement ps;

    public DBConnection() {
        this.URL = "jdbc:mysql://" + this.HOST + "/" + this.BASEDATOS;
    }

    public void conectar() {
        try {
            this.link = DriverManager.getConnection(this.URL, this.USUARIO, this.CLAVE);
        } catch (IllegalArgumentException | SecurityException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void desconectar() {
        try {
            if (link != null && !link.isClosed()) {
                this.link.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public PreparedStatement preparar(String sql) {
        try {
            ps = link.prepareStatement(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ps;
    }

    public PreparedStatement preparar(String sql, boolean retornarId) throws SQLException {
        if (this.link == null || this.link.isClosed()) {
            throw new SQLException("Conexión no establecida");
        }
        return retornarId
                ? this.link.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
                : this.link.prepareStatement(sql);
    }

    public Connection getConnection() {
        return this.link;
    }
    public Connection getConexion() {
        try {
            conectar(); 
            return getConnection();
        } catch (Exception e) {
            System.err.println("Error al obtener conexión: " + e.getMessage());
            return null;
        }
    }
    
    //---- MÉTODOS PARA TRANSACCIONES ----
    public void comenzarTransaccion() throws SQLException {
        if (this.link != null) {
            this.link.setAutoCommit(false);
        }
    }

    public void confirmarTransaccion() throws SQLException {
        if (this.link != null && !this.link.getAutoCommit()) {
            this.link.commit();
            this.link.setAutoCommit(true);
        }
    }

    public void revertirTransaccion() {
        try {
            if (this.link != null && !this.link.getAutoCommit()) {
                this.link.rollback();
                this.link.setAutoCommit(true);
            }
        } catch (SQLException ex) {
            System.err.println("Error al revertir transacción: " + ex.getMessage());
        }
    }


}
