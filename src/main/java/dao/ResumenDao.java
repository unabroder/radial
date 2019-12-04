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
import modelo.ResumenBean;

/**
 *
 * @author roberto.alferesusam
 */
public class ResumenDao {

    Conexion conexion;
    PreparedStatement ps;
    ResultSet rs;
    
    public ResumenDao(Conexion conexion) {
        this.conexion = conexion;
    }
    
    public boolean guardar(ResumenBean r) {
        String sql = "INSERT INTO resumen(nota) VALUES(?)";
        try {
            ps = conexion.conectar().prepareStatement(sql);
            ps.setString(1, r.getNota());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean actualizar(ResumenBean r) {
        String sql = "UPDATE resumen SET nota = ? WHERE idresumen = ?";
        try {
            ps = conexion.conectar().prepareStatement(sql);
            ps.setString(1, r.getNota());
            ps.setInt(2, r.getIdresumen());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean eliminar(int id) {
        String sql = "UPDATE resumen SET estado = 0 WHERE idresumen = ?";
        try {
            ps = conexion.conectar().prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public List<ResumenBean> consultar() {
        String sql = "SELECT * FROM resumen WHERE estado = 1";
        try {
            ps = conexion.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            List<ResumenBean> lista = new LinkedList<>();
            ResumenBean resumen;
            while (rs.next()) {                
                resumen = new ResumenBean(rs.getInt("idresumen"));
                resumen.setNota(rs.getString("nota"));
                lista.add(resumen);
            }
            return lista;
        } catch (Exception e) {
            return null;
        }
    }
    
    public List<ResumenBean> consultar(int id) {
        String sql = "SELECT * FROM resumen WHERE estado = 1";
        try {
            ps = conexion.conectar().prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            List<ResumenBean> lista = new LinkedList<>();
            ResumenBean resumen;
            while (rs.next()) {                
                resumen = new ResumenBean(rs.getInt("idresumen"));
                resumen.setNota(rs.getString("nota"));
                lista.add(resumen);
            }
            return lista;
        } catch (Exception e) {
            return null;
        }
    }
    
}
