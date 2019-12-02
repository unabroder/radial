/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public boolean guardar(ResumenBean r){
        String sql = "";
        return true;
    }
    
}
