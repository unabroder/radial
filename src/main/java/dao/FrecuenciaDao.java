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
import modelo.FrecuenciaBean;

/**
 *
 * @author roberto.alferesusam
 */
public class FrecuenciaDao {

    Conexion conexion;
    PreparedStatement ps;
    ResultSet rs;

    public FrecuenciaDao(Conexion conexion) {
        this.conexion = conexion;
    }

    public boolean validar(FrecuenciaBean fre) {
        String sql = "SELECT frecuencia FROM frecuencia WHERE frecuencia = ?";
        try {
            ps = conexion.conectar().prepareStatement(sql);
            ps.setDouble(1, fre.getFrecuencia());
            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean guardar(FrecuenciaBean fre) {
        String sql = "INSERT INTO frecuencia(frecuencia, tipo) VALUES(?,?)";
        try {
            ps = conexion.conectar().prepareStatement(sql);
            ps.setDouble(1, fre.getFrecuencia());
            ps.setString(2, fre.getTipo());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean actualizar(FrecuenciaBean fre) {
        String sql = "UPDATE frecuencia SET frecuencia = ?, tipo = ? WHERE idfrecuencia = ?";
        try {
            ps = conexion.conectar().prepareStatement(sql);
            ps.setDouble(1, fre.getFrecuencia());
            ps.setString(2, fre.getTipo());
            ps.setInt(3, fre.getIdfrecuencia());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "UPDATE frecuencia SET estado = 0 WHERE idfrecuencia = ?";
        try {
            ps = conexion.conectar().prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<FrecuenciaBean> consultar() {
        String sql = "SELECT * FROM frecuencia WHERE estado = 1";
        try {
            ps = conexion.conectar().prepareStatement(sql);;
            rs = ps.executeQuery();
            List<FrecuenciaBean> lista = new LinkedList<>();
            FrecuenciaBean fre;
            while (rs.next()) {
                fre = new FrecuenciaBean(rs.getInt("idfrecuencia"));
                fre.setFrecuencia(rs.getDouble("frecuencia"));
                fre.setTipo(rs.getString("tipo"));
                lista.add(fre);
            }
            return lista;
        } catch (Exception e) {
            return null;
        }
    }

    public List<FrecuenciaBean> consultarById(int id) {
        String sql = "SELECT * FROM frecuencia WHERE idfrecuencia = ?";
        try {
            ps = conexion.conectar().prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            List<FrecuenciaBean> lista = new LinkedList<>();
            FrecuenciaBean fre;
            while (rs.next()) {
                fre = new FrecuenciaBean(rs.getInt("idfrecuencia"));
                fre.setFrecuencia(rs.getDouble("frecuencia"));
                fre.setTipo(rs.getString("tipo"));
                lista.add(fre);
            }
            return lista;
        } catch (Exception e) {
            return null;
        }
    }
}
