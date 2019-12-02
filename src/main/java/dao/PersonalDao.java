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
import modelo.CargoBean;
import modelo.PersonalBean;
import modelo.ProductoraBean;

/**
 *
 * @author roberto.alferesusam
 */
public class PersonalDao {

    Conexion conexion;
    PreparedStatement ps;
    ResultSet rs;

    public PersonalDao(Conexion conexion) {
        this.conexion = conexion;
    }

    public boolean validar(PersonalBean per) {
        String sql = "SELECT  dui FROM personal WHERE dui = ?";
        try {
            ps = conexion.conectar().prepareStatement(sql);
            ps.setString(1, per.getDui());
            ps.executeQuery();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean guardar(PersonalBean per) {
        String sql = "INSERT INTO personal(idproductora, idcargo, nombre, apellido, dui) VALUES(?,?,?,?,?)";
        try {
            ProductoraBean pro = per.getIdproductora();
            CargoBean c = per.getIdcargo();
            ps = conexion.conectar().prepareStatement(sql);
            ps.setInt(1, pro.getIdproductora());
            ps.setInt(2, c.getIdcargo());
            ps.setString(3, per.getNombre());
            ps.setString(4, per.getApellido());
            ps.setString(5, per.getDui());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean actualizar(PersonalBean per) {
        String sql = "UPDATE personal SET idproductora = ?, idcargo = ?, nombre = ?, apellido = ?, dui = ? WHERE idpersonal = ?";
        try {
            ProductoraBean pro = per.getIdproductora();
            CargoBean c = per.getIdcargo();
            ps = conexion.conectar().prepareStatement(sql);
            ps.setInt(1, pro.getIdproductora());
            ps.setInt(2, c.getIdcargo());
            ps.setString(3, per.getNombre());
            ps.setString(4, per.getApellido());
            ps.setString(5, per.getDui());
            ps.setInt(6, per.getIdpersonal());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "UPDATE personal SET estado = 0 WHERE idpersonal = ?";
        try {
            ps = conexion.conectar().prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<PersonalBean> consultar() {
        String sql = "SELECT per.idpersonal, per.idproductora, per.idcargo, per.nombre,"
                + " per.apellido, per.dui, pro.numbre, c.cargo FROM personal as per "
                + " INNER JOIN productora as pro ON pro.idproductora = per.idproductora "
                + " INNER JOIN cargo as c ON c.idcargo = per.idcargo WHERE per.estado = 1 ";
        try {
            List<PersonalBean> lista = new LinkedList<>();
            PersonalBean per;
            ps = conexion.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                per = new PersonalBean(rs.getInt("idpersonal"));
                ProductoraBean pro = new ProductoraBean(rs.getInt("idproductora"));
                CargoBean c = new CargoBean(rs.getInt("idcargo"));
                per.setIdproductora(pro);
                per.setIdcargo(c);
                per.setNombre(rs.getString("nombre"));
                per.setApellido(rs.getString("apellido"));
                per.setDui(rs.getString("dui"));
                pro.setNumbre(rs.getString("numbre"));
                c.setCargo(rs.getString("cargo"));
                lista.add(per);
            }
            return lista;
        } catch (Exception e) {
            return null;
        }
    }

    public List<PersonalBean> consultarById(int id) {
        String sql = "SELECT per.idpersonal, per.idproductora, per.idcargo, per.nombre,"
                + " per.apellido, per.dui, pro.numbre, c.cargo FROM personal as per "
                + " INNER JOIN productora as pro ON pro.idproductora = per.idproductora "
                + " INNER JOIN cargo as c ON c.idcargo = per.idcargo WHERE per.idpersonal = ?";
        try {
            List<PersonalBean> lista = new LinkedList<>();
            PersonalBean per;
            ps = conexion.conectar().prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                per = new PersonalBean(rs.getInt("idpersonal"));
                ProductoraBean pro = new ProductoraBean(rs.getInt("idproductora"));
                CargoBean c = new CargoBean(rs.getInt("idcargo"));
                per.setIdproductora(pro);
                per.setIdcargo(c);
                per.setNombre(rs.getString("nombre"));
                per.setApellido(rs.getString("apellido"));
                per.setDui(rs.getString("dui"));
                pro.setNumbre(rs.getString("numbre"));
                c.setCargo(rs.getString("cargo"));
                lista.add(per);
            }
            return lista;
        } catch (Exception e) {
            return null;
        }
    }
}
