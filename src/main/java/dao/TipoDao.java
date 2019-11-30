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

/**
 *
 * @author roberto.alferesusam
 */
public class TipoDao {

    PreparedStatement ps;
    ResultSet rs;
    Conexion conexion;

    public TipoDao(Conexion conexion) {
        this.conexion = conexion;
    }

    public List<TipoUsuario> consultar() {
        String sql = "SELECT * FROM tipousuario WHERE estado = 1";
        try {
            ps = conexion.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            TipoUsuario tp;
            List<TipoUsuario> lista = new LinkedList<>();
            while (rs.next()) {
                tp = new TipoUsuario(rs.getInt("idtipo"));
                tp.setTipo(rs.getString("tipo"));
                lista.add(tp);
            }
            return lista;
        } catch (Exception e) {
            return null;
        }
    }

    public List<TipoUsuario> consultarById(int id) {
        String sql = "SELECT * FROM tipousuario WHERE idtipo = ?";
        try {
            ps = conexion.conectar().prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            TipoUsuario tp;
            List<TipoUsuario> lista = new LinkedList<>();
            while (rs.next()) {
                tp = new TipoUsuario(rs.getInt("idtipo"));
                tp.setTipo(rs.getString("tipo"));
                lista.add(tp);
            }
            return lista;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean validar(TipoUsuario tp) {
        String sql = "SELECT tipo FROM tipousuario WHERE tipo = ?";
        try {
            ps = conexion.conectar().prepareStatement(sql);
            ps.setString(1, tp.getTipo());
            rs = ps.executeQuery();

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean guardar(TipoUsuario tp) {
        String sql = "INSERT INTO tipousuario(tipo) VALUES(?)";
        try {
            ps = conexion.conectar().prepareStatement(sql);
            ps.setString(1, tp.getTipo());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean actualizar(TipoUsuario tp) {
        String sql = "UPDATE tipousuario SET tipo = ? WHERE idtipo = ?";
        try {
            ps = conexion.conectar().prepareStatement(sql);
            ps.setString(1, tp.getTipo());
            ps.setInt(1, tp.getIdtipo());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM tipousuario WHERE idtipo = ? ";
        try {
            ps = conexion.conectar().prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
