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
public class FrecuenciaBean {

    private int idfrecuencia;
    private Double frecuencia;
    private String tipo;

    public FrecuenciaBean(int idfrecuencia) {
        this.idfrecuencia = idfrecuencia;
    }

    public int getIdfrecuencia() {
        return idfrecuencia;
    }

    public void setIdfrecuencia(int idfrecuencia) {
        this.idfrecuencia = idfrecuencia;
    }

    public Double getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(Double frecuencia) {
        this.frecuencia = frecuencia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
