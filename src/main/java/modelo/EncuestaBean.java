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
public class EncuestaBean {

    private int idencuesta;
    private EmisionBean idemision;
    private ProgramaBean idprograma;
    private int total;
    private int aprobacion;
    private int rechazo;
    private int indiferencia;

    public EncuestaBean(int idencuesta) {
        this.idencuesta = idencuesta;
    }

    public int getIdencuesta() {
        return idencuesta;
    }

    public void setIdencuesta(int idencuesta) {
        this.idencuesta = idencuesta;
    }

    public EmisionBean getIdemision() {
        return idemision;
    }

    public void setIdemision(EmisionBean idemision) {
        this.idemision = idemision;
    }

    public ProgramaBean getIdprograma() {
        return idprograma;
    }

    public void setIdprograma(ProgramaBean idprograma) {
        this.idprograma = idprograma;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getAprobacion() {
        return aprobacion;
    }

    public void setAprobacion(int aprobacion) {
        this.aprobacion = aprobacion;
    }

    public int getRechazo() {
        return rechazo;
    }

    public void setRechazo(int rechazo) {
        this.rechazo = rechazo;
    }

    public int getIndiferencia() {
        return indiferencia;
    }

    public void setIndiferencia(int indiferencia) {
        this.indiferencia = indiferencia;
    }

}
