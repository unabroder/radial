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
import modelo.EmisionBean;
import modelo.EncuestaBean;
import modelo.ProgramaBean;

/**
 *
 * @author roberto.alferesusam
 */
public class EncuestaDao {

    Conexion conexion;
    PreparedStatement ps;
    ResultSet rs;

    public EncuestaDao(Conexion conexion) {
        this.conexion = conexion;
    }

    public boolean guardar(EncuestaBean enc) {
        String sql = "INSERT INTO encuesta(idemision, idprograma, total,"
                + " aprobacion, rechazo, indiferencia) VALUES(?,?,?,?,?,?)";
        try {
            EmisionBean emi = enc.getIdemision();
            ProgramaBean pro = enc.getIdprograma();
            ps = conexion.conectar().prepareStatement(sql);
            ps.setInt(1, emi.getIdemision());
            ps.setInt(2, pro.getIdprograma());
            ps.setInt(3, enc.getTotal());
            ps.setInt(4, enc.getAprobacion());
            ps.setInt(5, enc.getRechazo());
            ps.setInt(6, enc.getIndiferencia());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean actualizar(EncuestaBean enc) {
        String sql = "UPDATE encuesta SET idemision = ?, idprograma = ?, total = ?,"
                + " aprobacion = ?, rechazo = ?, indiferencia = ? WHERE idencuesta = ?";
        try {
            EmisionBean emi = enc.getIdemision();
            ProgramaBean pro = enc.getIdprograma();
            ps = conexion.conectar().prepareStatement(sql);
            ps.setInt(1, emi.getIdemision());
            ps.setInt(2, pro.getIdprograma());
            ps.setInt(3, enc.getTotal());
            ps.setInt(4, enc.getAprobacion());
            ps.setInt(5, enc.getRechazo());
            ps.setInt(6, enc.getIndiferencia());
            ps.setInt(7, enc.getIdencuesta());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "UPDATE encuesta SET estado = 0 WHERE idencuesta = ?";
        try {
            ps = conexion.conectar().prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<EncuestaBean> consultar() {
        String sql = "SELECT enc.idencuesta, enc.idemision, enc.idprograma, "
                + " enc.total, enc.aprobacion, enc.rechazo, enc.indiferencia, "
                + " emi.fecha, pro.nombre FROM encuesta AS enc "
                + " INNER JOIN emision AS emi ON emi.idemision = enc.idemision "
                + " INNER JOIN programas AS pro ON pro.idprograma = enc.idprograma "
                + " WHERE enc.estado = 1";
        try {
            List<EncuestaBean> lista = new LinkedList<>();
            EncuestaBean enc;
            ps = conexion.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                enc = new EncuestaBean(rs.getInt("enc.idencuesta"));
                EmisionBean emi = new EmisionBean(rs.getInt("enc.idemision"));
                ProgramaBean pro = new ProgramaBean(rs.getInt("enc.idprograma"));
                enc.setIdemision(emi);
                enc.setIdprograma(pro);
                enc.setTotal(rs.getInt("enc.total"));
                enc.setAprobacion(rs.getInt("enc.aprobacion"));
                enc.setRechazo(rs.getInt("enc.rechazo"));
                enc.setIndiferencia(rs.getInt("enc.indiferencia"));
                emi.setFecha(rs.getDate("emi.fecha"));
                pro.setNombre(rs.getString("pro.nombre"));
                lista.add(enc);
            }
            return lista;
        } catch (Exception e) {
            return null;
        }
    }

    public List<EncuestaBean> consultarById(int id) {
        String sql = "SELECT enc.idencuesta, enc.idemision, enc.idprograma, "
                + " enc.total, enc.aprobacion, enc.rechazo, enc.indiferencia, "
                + " emi.fecha, pro.nombre FROM encuesta AS enc "
                + " INNER JOIN emision AS emi ON emi.idemision = enc.idemision "
                + " INNER JOIN programas AS pro ON pro.idprograma = enc.idprograma "
                + " WHERE enc.idencuesta = ?";
        try {
            List<EncuestaBean> lista = new LinkedList<>();
            EncuestaBean enc;
            ps = conexion.conectar().prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                enc = new EncuestaBean(rs.getInt("idencuesta"));
                EmisionBean emi = new EmisionBean(rs.getInt("idemision"));
                ProgramaBean pro = new ProgramaBean(rs.getInt("idprograma"));
                enc.setIdemision(emi);
                enc.setIdprograma(pro);
                enc.setTotal(rs.getInt("total"));
                enc.setAprobacion(rs.getInt("aprobacion"));
                enc.setRechazo(rs.getInt("rechazo"));
                enc.setIndiferencia(rs.getInt("indiferencia"));
                emi.setFecha(rs.getDate("fecha"));
                pro.setNombre(rs.getString("nombre"));
                lista.add(enc);
            }
            return lista;
        } catch (Exception e) {
            return null;
        }
    }

}
