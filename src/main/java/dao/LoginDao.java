/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.Login;
import modelo.TipoUsuario;

/**
 *
 * @author roberto.alferesusam
 */
public class LoginDao {

    PreparedStatement ps;
    ResultSet rs;
    Conexion conexion;
    String tipo = "";

    public LoginDao(Conexion conexion) {
        this.conexion = conexion;
    }

    public boolean login(Login log) {
        String sql = "SELECT u.idusuario, u.idtipo, u.usuario, u.clave, tp.tipo FROM usuarios  AS u "
                + " INNER JOIN tipousuario as tp ON tp.idtipo = u.idtipo "
                + " WHERE u.usuario = ? AND u.clave = ?";
        try {
            ps = conexion.conectar().prepareStatement(sql);
            ps.setString(1, log.getUsuario());
            ps.setString(2, log.getClave());
            rs = ps.executeQuery();
            Login usu;
            while (rs.next()) {
                usu = new Login(rs.getInt("idusuario"));
                TipoUsuario tp = new TipoUsuario(rs.getInt("idtipo"));
                usu.setUsuario(rs.getString("usuario"));
                tp.setTipo(rs.getString("tipo"));
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public Login validar(Login log) {
        String sql = "SELECT u.idusuario, u.idtipo, u.usuario, u.clave, tp.tipo FROM usuarios  AS u "
                + " INNER JOIN tipousuario as tp ON tp.idtipo = u.idtipo "
                + " WHERE u.usuario = ? AND u.clave = ?";
        try {
            ps = conexion.conectar().prepareStatement(sql);
            ps.setString(1, log.getUsuario());
            ps.setString(2, log.getClave());
            rs = ps.executeQuery();
            Login usu;
            while (rs.next()) {
                usu = new Login(rs.getInt("idusuario"));
                TipoUsuario tp = new TipoUsuario(rs.getInt("idtipo"));
                usu.setUsuario(rs.getString("usuario"));
                tp.setTipo(rs.getString("tipo"));
                return usu;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
