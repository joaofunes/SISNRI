/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import com.sisrni.model.Organismo;
import com.sisrni.model.TipoOrganismo;
import com.sisrni.service.OrganismoService;
import com.sisrni.service.TipoOrganismoService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Lillian
 */
@Named(value = "organismoCooperanteMB")
@ViewScoped
public class OrganismoCooperanteMB {

    /**
     * Creates a new instance of OrganismoCooperanteMB
     */
    private Organismo organismoCooperante ;
    @Autowired
    TipoOrganismoService tipoOrganismoService;
    private List<TipoOrganismo> tipoOrganismoList;
    private TipoOrganismo organismoSelected;
    private List<Organismo> organismosList;
    private boolean actualizar;
    @Autowired
    OrganismoService organismoService;

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
    organismosList=organismoService.findAll();
    actualizar=false;
    }
    public void guardarOrganismo(){
        try {
            //seteamos el tipo organismo seleccionado el cual buscamos en la base para ver si existe utilizando el tipoorganismoservice
            organismoCooperante.setIdTipoOrganismo(tipoOrganismoService.findById(organismoSelected.getIdTipoOrganismo()));
            organismoCooperante.setIdOrganismo(Integer.MIN_VALUE);
            organismoService.save(organismoCooperante);
            inicializarVariables();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "La Informacion se ha registrado correctamente!"));
            
                    
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "el organismo no se puede ingresar."));
        }
    }
    public void updateorganismo() {
        String msg = " Organismo Actualizado Exitosamente!";       
        try { 
            organismoCooperante.setIdTipoOrganismo(tipoOrganismoService.findById(organismoSelected.getIdTipoOrganismo()));
            organismoService.merge(organismoCooperante);
            actualizar=false;
            //cancelarTipoOrganismo();
            inicializarVariables();
            RequestContext.getCurrentInstance().update(":formAdmin");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", msg));
            
        } catch (Exception e) {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "el organismo no se puede actualizar."));
        }
        inicializarVariables();
    }
   
    public void preUpdate(Organismo organismoCooperante){
        try {        
            this.organismoCooperante = organismoCooperante; 
            this.organismoSelected.setIdTipoOrganismo(organismoCooperante.getIdTipoOrganismo().getIdTipoOrganismo());
            actualizar=true;      
        } catch (Exception e) {
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Al precargar registro para ser actualizado"));
        }
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

    public List<Organismo> getOrganismosList() {
        return organismosList;
    }

    public void setOrganismosList(List<Organismo> organismosList) {
        this.organismosList = organismosList;
    }

    public boolean isActualizar() {
        return actualizar;
    }

    public void setActualizar(boolean actualizar) {
        this.actualizar = actualizar;
    }
    
}
