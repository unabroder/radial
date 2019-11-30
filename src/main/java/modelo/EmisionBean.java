/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;
import java.time.LocalTime;

/**
 *
 * @author roberto.alferesusam
 */
public class EmisionBean {

    private int idemision;
    private ProgramaBean idprograma;
    private Date fecha;
    private LocalTime horainicio;
    private LocalTime duracion;
    private String repeticion;

    public EmisionBean(int idemision) {
        this.idemision = idemision;
    }

    public int getIdemision() {
        return idemision;
    }

    public void setIdemision(int idemision) {
        this.idemision = idemision;
    }

    public ProgramaBean getIdprograma() {
        return idprograma;
    }

    public void setIdprograma(ProgramaBean idprograma) {
        this.idprograma = idprograma;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHorainicio() {
        return horainicio;
    }

    public void setHorainicio(LocalTime horainicio) {
        this.horainicio = horainicio;
    }

    public LocalTime getDuracion() {
        return duracion;
    }

    public void setDuracion(LocalTime duracion) {
        this.duracion = duracion;
    }

    public String getRepeticion() {
        return repeticion;
    }

    public void setRepeticion(String repeticion) {
        this.repeticion = repeticion;
    }

}
