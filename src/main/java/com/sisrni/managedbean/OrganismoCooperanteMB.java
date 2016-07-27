/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import com.sisrni.model.Organismo;
import com.sisrni.model.TipoOrganismo;
import com.sisrni.service.TipoOrganismoService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Lillian
 */
@Named(value = "organismoCooperanteMB")
@RequestScoped
public class OrganismoCooperanteMB {

    /**
     * Creates a new instance of OrganismoCooperanteMB
     */
    private Organismo organismoCooperante ;
    @Autowired
    TipoOrganismoService tipoOrganismoService;
    private List<TipoOrganismo> tipoOrganismoList;
    private TipoOrganismo organismoSelected;

    public OrganismoCooperanteMB() {    
    }
    @PostConstruct
    public void init(){
    inicializarVariables();
    }
    public void inicializarVariables(){
    organismoCooperante =new Organismo();
    tipoOrganismoList = tipoOrganismoService.findAll();
    organismoSelected=new TipoOrganismo();
    }    
    public List<TipoOrganismo> getTipoOrganismoList() {
        return tipoOrganismoList;
    }

    public void setTipoOrganismoList(List<TipoOrganismo> tipoOrganismoList) {
        this.tipoOrganismoList = tipoOrganismoList;
    }

        public Organismo getOrganismoCooperante() {
        return organismoCooperante;
    }

    public void setOrganismoCooperante(Organismo organismoCooperante) {
        this.organismoCooperante = organismoCooperante;
    }
    public TipoOrganismo getOrganismoSelected() {
        return organismoSelected;
    }

    public void setOrganismoSelected(TipoOrganismo organismoSelected) {
        this.organismoSelected = organismoSelected;
    }
    
}
