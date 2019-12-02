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
import modelo.ProductoraBean;

/**
 *
 * @author roberto.alferesusam
 */
public class ProductoraDao {

    Conexion conexion;
    PreparedStatement ps;
    ResultSet rs;

    public ProductoraDao(Conexion conexion) {
        this.conexion = conexion;
    }

    public boolean guardar(ProductoraBean pro) {
        String sql = "INSERT INTO productora(nombre, rfc) VALUES(?,?)";
        try {
            ps = conexion.conectar().prepareStatement(sql);
            ps.setString(1, pro.getNumbre());
            ps.setString(2, pro.getRfc());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<ProductoraBean> consultarById(int id) {
        String sql = "SELECT idproductora, nombre, rfc FROM productora WHERE idproductora = ?";
        try {
            List<ProductoraBean> lista = new LinkedList<>();
            ProductoraBean pro;
            ps = conexion.conectar().prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                pro = new ProductoraBean(rs.getInt("idproductora"));
                pro.setNumbre(rs.getString("nombre"));
                pro.setRfc(rs.getString("rfc"));
                lista.add(pro);
            }
            return lista;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean actualizar(ProductoraBean pro) {
        String sql = "UPDATE productora SET nombre = ?, rfc = ? WHERE idproductora = ?";
        try {
            ps = conexion.conectar().prepareStatement(sql);
            ps.setString(1, pro.getNumbre());
            ps.setString(2, pro.getRfc());
            ps.setInt(3, pro.getIdproductora());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "UPDATE productora SET estado = 0 WHERE idproductora = ?";
        try {
            ps = conexion.conectar().prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean validar(ProductoraBean pro) {
        String sql = "SELECT nombre FROM productora WHERE nombre = ?";
        try {
            ps = conexion.conectar().prepareStatement(sql);
            ps.setString(1, pro.getNumbre());
            ps.executeQuery();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<ProductoraBean> consultar() {
        String sql = "SELECT idproductora, nombre, rfc FROM productora WHERE estado = 1";
        try {
            List<ProductoraBean> lista = new LinkedList<>();
            ProductoraBean pro;
            ps = conexion.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                pro = new ProductoraBean(rs.getInt("idproductora"));
                pro.setNumbre(rs.getString("nombre"));
                pro.setRfc(rs.getString("rfc"));
                lista.add(pro);
            }
            return lista;
        } catch (Exception e) {
            return null;
        }
    }
}
