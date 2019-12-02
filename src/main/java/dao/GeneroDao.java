/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author roberto.alferesusam
 */
public class GeneroDao {

    Conexion conexion;
    PreparedStatement ps;
    ResultSet rs;

    public GeneroDao(Conexion conexion) {
        this.conexion = conexion;
    }

    
    
}
