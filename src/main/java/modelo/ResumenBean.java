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
public class ResumenBean {

    private int idresumen;
    private String nota;

    public ResumenBean(int idresumen) {
        this.idresumen = idresumen;
    }

    public int getIdresumen() {
        return idresumen;
    }

    public void setIdresumen(int idresumen) {
        this.idresumen = idresumen;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

}
