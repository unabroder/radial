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
import modelo.ProgramaBean;

/**
 *
 * @author roberto.alferesusam
 */
public class ProgramaDao {

    Conexion conexion;
    PreparedStatement ps;
    ResultSet rs;

    public ProgramaDao(Conexion conexion) {
        this.conexion = conexion;
    }

    public boolean validar(ProgramaBean pro) {
        String sql = "SELECT nombre FROM programas WHERE nombre = ?";
        try {
            ps = conexion.conectar().prepareStatement(sql);
            ps.setString(1, pro.getNombre());
            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean guardar(ProgramaBean pro) {
        String sql = "INSERT INTO programas(idgenero, nombre, descripcion) VALUES(?,?,?)";
        try {
            ps = conexion.conectar().prepareStatement(sql);
            GeneroBean gen = pro.getIdgenero();
            ps.setString(1, gen.getGenero());
            ps.setString(2, pro.getNombre());
            ps.setString(3, pro.getDescripcion());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean actualizar(ProgramaBean pro) {
        String sql = "UPDATE programas SET idgenero = ?, nombre = ?, descripcion = ? WHERE idprograma = ?";
        try {
            ps = conexion.conectar().prepareStatement(sql);
            GeneroBean gen = pro.getIdgenero();
            ps.setString(1, gen.getGenero());
            ps.setString(2, pro.getNombre());
            ps.setString(3, pro.getDescripcion());
            ps.setInt(4, pro.getIdprograma());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "UPDATE programas SET estado = 0 WHERE idprograma = ?";
        try {
            ps = conexion.conectar().prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<ProgramaBean> consultar() {
        String sql = "SELECT pro.idprograma, pro.genero, pro.nombre, "
                + " pro.descripcion, gen.genero FROM programas as pro "
                + " INNER JOIN genero as gen ON gen.idgenero = pro.idgenero "
                + " WHERE pro.estado = 1 ";
        try {
            List<ProgramaBean> lista = new LinkedList<>();
            ProgramaBean pro;
            ps = conexion.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                pro = new ProgramaBean(rs.getInt("idprograma"));
                GeneroBean gen = new GeneroBean(rs.getInt("idgenero"));
                pro.setIdgenero(gen);
                pro.setNombre(rs.getString("nombre"));
                pro.setDescripcion(rs.getString("descripcion"));
                gen.setGenero(rs.getString("genero"));
                lista.add(pro);
            }
            return lista;
        } catch (Exception e) {
            return null;
        }
    }

    public List<ProgramaBean> consultarById(int id) {
        String sql = "SELECT pro.idprograma, pro.genero, pro.nombre, "
                + " pro.descripcion, gen.genero FROM programas as pro "
                + " INNER JOIN genero as gen ON gen.idgenero = pro.idgenero "
                + " WHERE pro.idprograma = ? ";
        try {
            List<ProgramaBean> lista = new LinkedList<>();
            ProgramaBean pro;
            ps = conexion.conectar().prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                pro = new ProgramaBean(rs.getInt("idprograma"));
                GeneroBean gen = new GeneroBean(rs.getInt("idgenero"));
                pro.setIdgenero(gen);
                pro.setNombre(rs.getString("nombre"));
                pro.setDescripcion(rs.getString("descripcion"));
                gen.setGenero(rs.getString("genero"));
                lista.add(pro);
            }
            return lista;
        } catch (Exception e) {
            return null;
        }
    }

}
