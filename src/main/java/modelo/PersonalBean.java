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
public class PersonalBean {

    private int idpersonal;
    private ProductoraBean idproductora;
    private CargoBean idcargo;
    private String nombre;
    private String apellido;
    private String dui;

    public PersonalBean(int idpersonal) {
        this.idpersonal = idpersonal;
    }

    public int getIdpersonal() {
        return idpersonal;
    }

    public void setIdpersonal(int idpersonal) {
        this.idpersonal = idpersonal;
    }

    public ProductoraBean getIdproductora() {
        return idproductora;
    }

    public void setIdproductora(ProductoraBean idproductora) {
        this.idproductora = idproductora;
    }

    public CargoBean getIdcargo() {
        return idcargo;
    }

    public void setIdcargo(CargoBean idcargo) {
        this.idcargo = idcargo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

}
