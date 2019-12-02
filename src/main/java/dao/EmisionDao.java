/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import modelo.EmisionBean;
import modelo.ProgramaBean;

/**
 *
 * @author roberto.alferesusam
 */
public class EmisionDao {
    
    Conexion conexion;
    PreparedStatement ps;
    ResultSet rs;
    SimpleDateFormat formato = new SimpleDateFormat("yy-MM-dd");
    
    public EmisionDao(Conexion conexion) {
        this.conexion = conexion;
    }
    
    public boolean guardar(EmisionBean emi) {
        String sql = "INSERT INTO emison(idprograma, fecha, horainicio, duracion, repeticion) VALUES(?,?,?,?,?)";
        try {
            ps = conexion.conectar().prepareStatement(sql);
            ProgramaBean pro = emi.getIdprograma();
            ps.setInt(1, pro.getIdprograma());
            ps.setString(2, formato.format(emi.getFecha()));
            ps.setString(3, emi.getHorainicio().toString());
            ps.setString(4, emi.getDuracion().toString());
            ps.setString(5, emi.getRepeticion());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean actualizar(EmisionBean emi) {
        String sql = "UPDATE emison SET idprograma = ?, fecha = ?, horainicio = ?, "
                + "duracion = ?, repeticion = ? WHERE idemision = ?";
        try {
            ps = conexion.conectar().prepareStatement(sql);
            ProgramaBean pro = emi.getIdprograma();
            ps.setInt(1, pro.getIdprograma());
            ps.setString(2, formato.format(emi.getFecha()));
            ps.setString(3, emi.getHorainicio().toString());
            ps.setString(4, emi.getDuracion().toString());
            ps.setString(5, emi.getRepeticion());
            ps.setInt(6, emi.getIdemision());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean eliminar(int id) {
        String sql = "UPDATE emison SET estado = 0 WHERE idemision = ?";
        try {
            ps = conexion.conectar().prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public List<EmisionBean> consultar() {
        String sql = "SELECT emi.idemision, emi.idprograma, emi.fecha, "
                + " emi.horainicio, emi.duracion, pro.nombre FROM emision as emi "
                + "INNER JOIN programas as pro ON pro.idprograma = emi.idprograma "
                + " WHERE emi.estado = 1";
        try {
            List<EmisionBean> lista = new LinkedList<>();
            EmisionBean emi;
            ps = conexion.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                emi = new EmisionBean(rs.getInt("idemision"));
                ProgramaBean pro = new ProgramaBean(rs.getInt("idprograma"));
                emi.setIdprograma(pro);
                emi.setFecha(rs.getDate("fecha"));
                emi.setHorainicio(LocalTime.parse(rs.getString("horainicio")));
                emi.setDuracion(LocalTime.parse(rs.getString("duracion")));
                emi.setRepeticion(rs.getString("repeticion"));
                lista.add(emi);
            }
            return lista;
        } catch (Exception e) {
            return null;
        }
    }
    
    public List<EmisionBean> consultarById(int id) {
        String sql = "SELECT emi.idemision, emi.idprograma, emi.fecha, "
                + " emi.horainicio, emi.duracion, pro.nombre FROM emision as emi "
                + "INNER JOIN programas as pro ON pro.idprograma = emi.idprograma "
                + " WHERE emi.idemision = ?";
        try {
            List<EmisionBean> lista = new LinkedList<>();
            EmisionBean emi;
            ps = conexion.conectar().prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                emi = new EmisionBean(rs.getInt("idemision"));
                ProgramaBean pro = new ProgramaBean(rs.getInt("idprograma"));
                emi.setIdprograma(pro);
                emi.setFecha(rs.getDate("fecha"));
                emi.setHorainicio(LocalTime.parse(rs.getString("horainicio")));
                emi.setDuracion(LocalTime.parse(rs.getString("duracion")));
                emi.setRepeticion(rs.getString("repeticion"));
                lista.add(emi);
            }
            return lista;
        } catch (Exception e) {
            return null;
        }
    }
}
