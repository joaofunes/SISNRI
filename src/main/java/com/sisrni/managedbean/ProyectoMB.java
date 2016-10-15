/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import com.sisrni.model.Facultad;
import com.sisrni.model.Organismo;
import com.sisrni.model.Persona;
import com.sisrni.service.FacultadService;
import com.sisrni.service.OrganismoService;
import com.sisrni.service.PersonaService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author Usuario
 */
@Named(value = "proyectoMB")
@ViewScoped
public class ProyectoMB {
   /*Variables*/
    private List<Facultad> listFacultad;
    private List<SelectItem> listFacultadItem;
    
    private List<Organismo> listOrganismos;
    private List<SelectItem> listOrganismosItem;
    
    private List<Persona> listInterno;
    private List<SelectItem> listInternoItem;
    
    private List<Persona> listAsistenteInterno;
    private List<SelectItem> listAsistenteInternoItem;
    
    @Autowired
    @Qualifier(value = "facultadService")
    private FacultadService facultadService;
    
    @Autowired
    @Qualifier(value = "organismoService")
    private OrganismoService organismoService;
    
    @Autowired
    @Qualifier(value = "personaService")
    private PersonaService personaService;
    

    /**
     * constructor
     */
    public ProyectoMB() {
    }
    
    /**
     * Post Constructor
     */
    
    @PostConstruct
    public void init(){
      listFacultad = facultadService.findAll();
      listOrganismos = organismoService.findAll();
      listInterno = personaService.findAll();
      listAsistenteInterno =personaService.findAll();
    }

    public List<Facultad> getListFacultad() {
        return listFacultad;
    }

    public void setListFacultad(List<Facultad> listFacultad) {
        this.listFacultad = listFacultad;
    }

    public List<SelectItem> getListFacultadItem() {
        this.listFacultadItem = new ArrayList<SelectItem>();
        listFacultadItem.clear();
        
        for(Facultad f :listFacultad ){
         SelectItem facultadItem = new SelectItem(f.getIdFacultad(),f.getNombreFacultad());
         this.listFacultadItem.add(facultadItem);
    }
        return listFacultadItem;
    }

    public void setListFacultadItem(List<SelectItem> listFacultadItem) {
        this.listFacultadItem = listFacultadItem;
    }

    public List<Organismo> getListOrganismos() {
        return listOrganismos;
    }

    public void setListOrganismos(List<Organismo> listOrganismos) {
        this.listOrganismos = listOrganismos;
    }

    public List<SelectItem> getListOrganismosItem() {
         this.listOrganismosItem = new ArrayList<SelectItem>();
         listOrganismosItem.clear();
        
        for(Organismo o :listOrganismos ){
          SelectItem organismoItem = new SelectItem(o.getIdOrganismo(),o.getNombreOrganismo());
          this.listOrganismosItem.add(organismoItem);
    }
        return listOrganismosItem;
    }

    public void setListOrganismosItem(List<SelectItem> listOrganismosItem) {
        this.listOrganismosItem = listOrganismosItem;
    }

    
    
    public List<Persona> getListInterno() {
        return listInterno;
    }

    public void setListInterno(List<Persona> listInterno) {
        this.listInterno = listInterno;
    }

    public List<SelectItem> getListInternoItem() {
         this.listInternoItem = new ArrayList<SelectItem>();
         listInternoItem.clear();
        
        for(Persona p :listInterno ){
          SelectItem internoItem = new SelectItem(p.getIdPersona(),p.getNombrePersona() + " "+ p.getApellidoPersona());
          this.listInternoItem.add(internoItem);
    }
        return listInternoItem;
    }

    public void setListInternoItem(List<SelectItem> listInternoItem) {
        this.listInternoItem = listInternoItem;
    }

    public List<Persona> getListAsistenteInterno() {
        return listAsistenteInterno;
    }

    public void setListAsistenteInterno(List<Persona> listAsistenteInterno) {
        this.listAsistenteInterno = listAsistenteInterno;
    }

    public List<SelectItem> getListAsistenteInternoItem() {
         this.listAsistenteInternoItem = new ArrayList<SelectItem>();
         listAsistenteInternoItem.clear();
        
        for(Persona pa :listAsistenteInterno ){
          SelectItem asistenteInternoItem = new SelectItem(pa.getIdPersona(),pa.getNombrePersona() + " " + pa.getApellidoPersona());
          this.listAsistenteInternoItem.add(asistenteInternoItem);
    }
        
        return listAsistenteInternoItem;
    }

    public void setListAsistenteInternoItem(List<SelectItem> listAsistenteInternoItem) {
        this.listAsistenteInternoItem = listAsistenteInternoItem;
    }

    
    
    
      
    
}
