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
import modelo.ProductoraBean;
import modelo.RadioBean;

/**
 *
 * @author roberto.alferesusam
 */
public class RadioDao {

    Conexion conexion;
    PreparedStatement ps;
    ResultSet rs;

    public RadioDao(Conexion conexion) {
        this.conexion = conexion;
    }

    public boolean validar(RadioBean rad) {
        String sql = "SELECT nombre FROM radio WHERE radio = ?";
        try {
            ps = conexion.conectar().prepareStatement(sql);
            ps.setString(1, rad.getNombre());
            ps.executeQuery();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean guardar(RadioBean rad) {
        String sql = "INSERT INTO radio(idproductora, idfrecuencia, nombre) VALUES(?,?,?)";
        try {
            ps = conexion.conectar().prepareStatement(sql);
            ProductoraBean pro = rad.getIdproductora();
            FrecuenciaBean fre = rad.getIdfrecuencia();
            ps.setInt(1, pro.getIdproductora());
            ps.setInt(2, fre.getIdfrecuencia());
            ps.setString(3, rad.getNombre());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean actualizar(RadioBean rad) {
        String sql = "UPDATE radio SET idproductora = ?, idfrecuencia = ?, nombre = ? WHERE idradio = ?";
        try {
            ps = conexion.conectar().prepareStatement(sql);
            ProductoraBean pro = rad.getIdproductora();
            FrecuenciaBean fre = rad.getIdfrecuencia();
            ps.setInt(1, pro.getIdproductora());
            ps.setInt(2, fre.getIdfrecuencia());
            ps.setString(3, rad.getNombre());
            ps.setInt(4, rad.getIdradio());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "UPDATE radio SET estado = 0 WHERE idradio = ?";
        try {
            ps = conexion.conectar().prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<RadioBean> consultar() {
        String sql = "SELECT rad.idradio, rad.idproductora, rad.idfrecuencia, rad.nombre, "
                + " pro.numbre, fre.frecuencia FROM radio as rad "
                + " INNER JOIN productora as pro ON pro.idproductora = rad.idproductora "
                + " INNER JOIN frecuencia as fre ON fre.idfrecuencia = rad.idfrecuencia "
                + " WHERE rad.estado = 1";
        try {
            List<RadioBean> lista = new LinkedList<>();
            RadioBean rad;
            ps = conexion.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                rad = new RadioBean(rs.getInt("idradio"));
                ProductoraBean pro = new ProductoraBean(rs.getInt("idproductora"));
                FrecuenciaBean fre = new FrecuenciaBean(rs.getInt("idfrecuencia"));
                rad.setIdproductora(pro);
                rad.setIdfrecuencia(fre);
                rad.setNombre(rs.getString("nombre"));
                pro.setNumbre(rs.getString("numbre"));
                fre.setFrecuencia(rs.getDouble("frecuencia"));
                System.out.println("radios");
                lista.add(rad);
            }
            return lista;
        } catch (Exception e) {
            return null;
        }
    }

    public List<RadioBean> consultarById(int id) {
        String sql = "SELECT rad.idradio, rad.idproductora, rad.idfrecuencia, rad.nombre, "
                + " pro.numbre, fre.frecuencia FROM radio as rad "
                + " INNER JOIN productora as pro ON pro.idproductora = rad.idproductora "
                + " INNER JOIN frecuencia as fre ON fre.idfrecuencia = rad.idfrecuencia "
                + " WHERE rad.idradio = ?";
        try {
            List<RadioBean> lista = new LinkedList<>();
            RadioBean rad;
            ps = conexion.conectar().prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                rad = new RadioBean(rs.getInt("idradio"));
                ProductoraBean pro = new ProductoraBean(rs.getInt("idproductora"));
                FrecuenciaBean fre = new FrecuenciaBean(rs.getInt("idfrecuencia"));
                rad.setIdproductora(pro);
                rad.setIdfrecuencia(fre);
                rad.setNombre(rs.getString("nombre"));
                pro.setNumbre(rs.getString("numbre"));
                fre.setFrecuencia(rs.getDouble("frecuencia"));
                lista.add(rad);
            }
            return lista;
        } catch (Exception e) {
            return null;
        }
    }

}
