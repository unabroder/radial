/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import modelo.TipoUsuario;
import modelo.UsuarioBean;

/**
 *
 * @author roberto.alferesusam
 */
public class UsuarioDao {

    PreparedStatement ps;
    ResultSet rs;
    Conexion conexion;

    public UsuarioDao(Conexion conexion) {
        this.conexion = conexion;
    }

    public boolean validar(UsuarioBean usu) {
        String sql = "SELECT usuario FROM usuarios WHERE usuario = ?";
        try {
            ps = conexion.conectar().prepareStatement(sql);
            ps.setString(1, usu.getUsuario());
            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean restablecer(UsuarioBean usu) {
        String sql = "UPDATE usuarios SET clave = ? WHERE usuario = ?";
        try {
            ps = conexion.conectar().prepareStatement(sql);
            ps.setString(1, usu.getClave());
            ps.setString(2, usu.getUsuario());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean guardar(UsuarioBean usu) {

        String sql = "INSERT INTO usuarios(idtipo, usuario, clave) VALUES(?,?,?)";
        try {
            ps = conexion.conectar().prepareStatement(sql);
            TipoUsuario tp = usu.getIdtipo();
            ps.setInt(1, tp.getIdtipo());
            ps.setString(2, usu.getUsuario());
            ps.setString(3, usu.getClave());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean actualizar(UsuarioBean usu) {
        String sql = "UPDATE  usuarios SET idtipo = ?, usuario = ?, clave = ? WHERE idusuario = ?";
        try {
            ps = conexion.conectar().prepareStatement(sql);
            TipoUsuario tp = usu.getIdtipo();
            ps.setInt(1, tp.getIdtipo());
            ps.setString(2, usu.getUsuario());
            ps.setString(3, usu.getClave());
            ps.setInt(4, usu.getIdusuario());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "UPDATE  usuarios SET estado = 0 WHERE idusuario = ?";
        try {
            ps = conexion.conectar().prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<UsuarioBean> consultar() {
        String sql = "SELECT u.idusuario, u.idtipo, u.usuario, u.clave, tp.tipo FROM usuarios as u "
                + "INNER JOIN tipousuario as tp ON tp.idtipo = u.idtipo "
                + " WHERE u.estado = 1";
        try {
            ps = conexion.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            List<UsuarioBean> lista = new LinkedList<>();
            UsuarioBean usu;
            while (rs.next()) {
                usu = new UsuarioBean(rs.getInt("idusuario"));
                TipoUsuario tp = new TipoUsuario(rs.getInt("idtipo"));
                usu.setIdtipo(tp);
                usu.setUsuario(rs.getString("usuario"));
                usu.setClave(rs.getString("clave"));
                tp.setTipo(rs.getString("tipo"));
                lista.add(usu);
            }
            return lista;
        } catch (Exception e) {
            return null;
        }
    }

    public List<UsuarioBean> consultarById(int id) {
        String sql = "SELECT u.idusuario, u.idtipo, u.usuario, u.clave, tp.tipo FROM usuarios as u "
                + " INNER JOIN tipousuario as tp ON tp.idtipo = u.idtipo "
                + " WHERE u.idusuario = ?";
        try {
            ps = conexion.conectar().prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            List<UsuarioBean> lista = new LinkedList<>();
            UsuarioBean usu;
            while (rs.next()) {
                usu = new UsuarioBean(rs.getInt("idusuario"));
                TipoUsuario tp = new TipoUsuario(rs.getInt("idtipo"));
                usu.setIdtipo(tp);
                usu.setUsuario(rs.getString("usuario"));
                usu.setClave(rs.getString("clave"));
                tp.setTipo(rs.getString("tipo"));
                lista.add(usu);
            }
            return lista;
        } catch (Exception e) {
            return null;
        }
    }
}
