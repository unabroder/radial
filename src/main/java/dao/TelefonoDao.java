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
import modelo.TelefonoBean;

/**
 *
 * @author roberto.alferesusam
 */
public class TelefonoDao {

    Conexion conexion;
    PreparedStatement ps;
    ResultSet rs;

    public TelefonoDao(Conexion conexion) {
        this.conexion = conexion;
    }

    public boolean guardar(TelefonoBean tel) {
        String sql = "INSERT INTO telefono(telefono) VALUES(?)";
        try {
            ps = conexion.conectar().prepareStatement(sql);
            ps.setString(1, tel.getTelefono());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean actualizar(TelefonoBean tel) {
        String sql = "UPDATE telefono SET telefono = ? WHERE idtelefono = ?";
        try {
            ps = conexion.conectar().prepareStatement(sql);
            ps.setString(1, tel.getTelefono());
            ps.setInt(2, tel.getIdtelefono());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "UPDATE telefono SET estado = 0 WHERE idtelefono = ?";
        try {
            ps = conexion.conectar().prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean validar(TelefonoBean tel) {
        String sql = "SELECT telefono FROM telefono WHERE tel = ?";
        try {
            ps = conexion.conectar().prepareStatement(sql);
            ps.setString(1, tel.getTelefono());
            rs = ps.executeQuery();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<TelefonoBean> consultar() {
        String sql = "SELECT istelefono, telefono FROM telefono WHERE estado = 1";
        try {
            List<TelefonoBean> lista = new LinkedList<>();
            TelefonoBean tel;
            ps = conexion.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                tel = new TelefonoBean(rs.getInt("idtelefono"));
                tel.setTelefono(rs.getString("telefono"));
                lista.add(tel);
            }
            return lista;
        } catch (Exception e) {
            return null;
        }
    }

    public List<TelefonoBean> consultarById(int id) {
        String sql = "SELECT istelefono, telefono FROM telefono WHERE idtelefono = ?";
        try {
            List<TelefonoBean> lista = new LinkedList<>();
            TelefonoBean tel;
            ps = conexion.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                tel = new TelefonoBean(rs.getInt("idtelefono"));
                tel.setTelefono(rs.getString("telefono"));
                lista.add(tel);
            }
            return lista;
        } catch (Exception e) {
            return null;
        }
    }
}