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
import modelo.GeneroBean;

/**
 *
 * @author roberto.alferesusam
 */
public class GeneroDao {

    Conexion conexion;
    PreparedStatement ps;
    ResultSet rs;

    public GeneroDao(Conexion conexion) {
        this.conexion = conexion;
    }

    public boolean validar(GeneroBean gen) {
        String sql = "SELECT  genero FROM genero WHERE genero = ?";
        try {
            ps = conexion.conectar().prepareStatement(sql);
            ps.setString(1, gen.getGenero());
            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean guardar(GeneroBean gen) {
        String sql = "INSERT INTO genero(genero) VALUES(?)";
        try {
            ps = conexion.conectar().prepareStatement(sql);
            ps.setString(1, gen.getGenero());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean actualizar(GeneroBean gen) {
        String sql = "UPDATE genero SET genero = ? WHERE idgenero = ?";
        try {
            ps = conexion.conectar().prepareStatement(sql);
            ps.setString(1, gen.getGenero());
            ps.setInt(2, gen.getIdgenero());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "UPDATE genero SET estado = 0 WHERE idgenero = ?";
        try {
            ps = conexion.conectar().prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<GeneroBean> consultar() {
        String sql = "SELECT * FROM genero WHERE estado = 1";
        try {
            List<GeneroBean> lista = new LinkedList<>();
            GeneroBean gen;
            ps = conexion.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                gen = new GeneroBean(rs.getInt("idgenero"));
                gen.setGenero(rs.getString("genero"));
                lista.add(gen);
            }
            return lista;
        } catch (Exception e) {
            return null;
        }
    }

    public List<GeneroBean> consultarById(int id) {
        String sql = "SELECT * FROM genero WHERE idgenero = ?";
        try {
            List<GeneroBean> lista = new LinkedList<>();
            GeneroBean gen;
            ps = conexion.conectar().prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                gen = new GeneroBean(rs.getInt("idgenero"));
                gen.setGenero(rs.getString("genero"));
                lista.add(gen);
            }
            return lista;
        } catch (Exception e) {
            return null;
        }
    }
}
