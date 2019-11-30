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
public class ProductoraBean {

    private int idproductora;
    private String numbre;
    private String rfc;

    public ProductoraBean(int idproductora) {
        this.idproductora = idproductora;
    }

    public int getIdproductora() {
        return idproductora;
    }

    public void setIdproductora(int idproductora) {
        this.idproductora = idproductora;
    }

    public String getNumbre() {
        return numbre;
    }

    public void setNumbre(String numbre) {
        this.numbre = numbre;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }
    
}
