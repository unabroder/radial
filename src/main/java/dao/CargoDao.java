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

/**
 *
 * @author roberto.alferesusam
 */
public class CargoDao {

    Conexion conexion;
    PreparedStatement ps;
    ResultSet rs;

    public CargoDao(Conexion conexion) {
        this.conexion = conexion;
    }

    public boolean guardar(CargoBean c) {
        String sql = "INSERT INTO cargo(cargo) VALUES(?)";
        try {
            ps = conexion.conectar().prepareStatement(sql);
            ps.setString(1, c.getCargo());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean actualizar(CargoBean c) {
        String sql = "UPDATE cargo cargo = ? WHERE idcargo = ?";
        try {
            ps = conexion.conectar().prepareStatement(sql);
            ps.setString(1, c.getCargo());
            ps.setInt(2, c.getIdcargo());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "UPDATE cargo SET estado = 0 WHERE idcargo = ?";
        try {
            ps = conexion.conectar().prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean validar(CargoBean c) {
        String sql = "SELECT cargo FROM cargo WHERE cargo = ?";
        try {
            ps = conexion.conectar().prepareStatement(sql);
            ps.setString(1, c.getCargo());
            ps.executeQuery();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<CargoBean> consultar() {
        String sql = "SELECT idcargo, cargo FROM cargo WHERE cargo = ?";
        try {
            List<CargoBean> lista = new LinkedList<>();
            CargoBean c;
            ps = conexion.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                c = new CargoBean(rs.getInt("idcargo"));
                c.setCargo(rs.getString("cargo"));
                lista.add(c);
            }
            return lista;
        } catch (Exception e) {
            return null;
        }
    }

    public List<CargoBean> consultarById(int id) {
        String sql = "SELECT idcargo, cargo FROM cargo WHERE idcargo = ?";
        try {
            List<CargoBean> lista = new LinkedList<>();
            CargoBean c;
            ps = conexion.conectar().prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                c = new CargoBean(rs.getInt("idcargo"));
                c.setCargo(rs.getString("cargo"));
                lista.add(c);
            }
            return lista;
        } catch (Exception e) {
            return null;
        }
    }
}
