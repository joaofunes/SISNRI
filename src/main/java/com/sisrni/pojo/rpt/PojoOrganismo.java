/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.pojo.rpt;

import java.io.Serializable;

/**
 *
 * @author Luis
 */
public class PojoOrganismo implements Serializable {

    private static final long serialVersionUID = 1113799434508676095L;

    private Integer idOrg;
    private String nombre;
    private String tipo;
    private String nPais;
    private String nRegion;
    private String direccion;
    private String correo;
    private Integer codigo;
    private String tel;
    
    public Integer getIdOrg() {
        return idOrg;
    }

    public void setIdOrg(Integer idOrg) {
        this.idOrg = idOrg;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getnPais() {
        return nPais;
    }

    public void setnPais(String nPais) {
        this.nPais = nPais;
    }

    public String getnRegion() {
        return nRegion;
    }

    public void setnRegion(String nRegion) {
        this.nRegion = nRegion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
    
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
   
}
