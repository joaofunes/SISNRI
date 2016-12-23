/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.pojo.rpt;


import com.sisrni.model.Persona;
import com.sisrni.model.Telefono;
import java.io.Serializable;

/**
 *
 * @author Joao
 */
public class PojoPersonaTelefono implements Serializable{
    
    private static final long serialVersionUID = 1L;

    
    //persona
    private Persona persona;
    //persona
    private Telefono telefonoFijo;
    private Telefono telefonoCelular;

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Telefono getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(Telefono telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public Telefono getTelefonoCelular() {
        return telefonoCelular;
    }

    public void setTelefonoCelular(Telefono telefonoCelular) {
        this.telefonoCelular = telefonoCelular;
    }
    
    
    


   
}
