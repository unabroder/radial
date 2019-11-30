/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author roberto.alferesusam
 */
public class TelefonoBean {

    private int idtelefono;
    private String telefono;

    public TelefonoBean(int idtelefono) {
        this.idtelefono = idtelefono;
    }

    public int getIdtelefono() {
        return idtelefono;
    }

    public void setIdtelefono(int idtelefono) {
        this.idtelefono = idtelefono;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}
