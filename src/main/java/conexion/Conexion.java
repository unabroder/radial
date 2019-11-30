/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author roberto.alferesusam
 */
public class Conexion {

    static String user = "root";
    static String pass = "root";
    static String base = "radial";
    static String url = "jdbc:mysql://localhost:3306/" + base + "?useSSL=false";
    Connection conexion;

    public Conexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(url, user, pass);
            if (conexion != null) {
                System.out.println("Conexion exitosa");
            }
        } catch (Exception e) {
            System.out.println("Error al conectar " + e.getMessage());
        }
    }

    public Connection conectar() {
        return conexion;
    }

    public void desconectar() throws SQLException {
        conexion.close();
    }
}
