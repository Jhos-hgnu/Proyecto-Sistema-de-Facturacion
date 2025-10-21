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
  
    private final String HOST = "localhost";  
    private final String PUERTO = "1521";
    private final String SERVICIO = "FREEPDB1";     
    private final String USUARIO = "Farmacia";  
    private final String CLAVE = "Farmacia123"; 
    private final String URL;
    private Connection link;
    private PreparedStatement ps;

    public DBConnection() {
       
        this.URL = "jdbc:oracle:thin:@//" + HOST + ":" + PUERTO + "/" + SERVICIO;
    }

    public void conectar() {
        try {
            
            Class.forName("oracle.jdbc.driver.OracleDriver");

           
            this.link = DriverManager.getConnection(this.URL, this.USUARIO, this.CLAVE);
            System.out.println("✅ Conexión exitosa a Oracle");
        } catch (ClassNotFoundException e) {
            System.err.println("❌ No se encontró el driver de Oracle: " + e.getMessage());
        } catch (SQLException ex) {
            System.err.println("❌ Error al conectar con Oracle: " + ex.getMessage());
        }
    }

    public void desconectar() {
        try {
            if (link != null && !link.isClosed()) {
                link.close();
                System.out.println("🔒 Conexión cerrada.");
            }
        } catch (SQLException ex) {
            System.err.println("Error al cerrar conexión: " + ex.getMessage());
        }
    }

    public PreparedStatement preparar(String sql) {
        try {
            ps = link.prepareStatement(sql);
        } catch (SQLException ex) {
            System.err.println("Error al preparar SQL: " + ex.getMessage());
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