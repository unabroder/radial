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
public class RadioBean {

    private int idradio;
    private ProductoraBean idproductora;
    private FrecuenciaBean idfrecuencia;
    private String nombre;

    public RadioBean(int idradio) {
        this.idradio = idradio;
    }

    public int getIdradio() {
        return idradio;
    }

    public void setIdradio(int idradio) {
        this.idradio = idradio;
    }

    public ProductoraBean getIdproductora() {
        return idproductora;
    }

    public void setIdproductora(ProductoraBean idproductora) {
        this.idproductora = idproductora;
    }

    public FrecuenciaBean getIdfrecuencia() {
        return idfrecuencia;
    }

    public void setIdfrecuencia(FrecuenciaBean idfrecuencia) {
        this.idfrecuencia = idfrecuencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
