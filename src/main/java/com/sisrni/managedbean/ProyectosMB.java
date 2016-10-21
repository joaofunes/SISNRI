/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import com.sisrni.model.AreaConocimiento;
import com.sisrni.model.Facultad;
import com.sisrni.model.Persona;
import com.sisrni.model.Proyecto;
import com.sisrni.model.Telefono;
import com.sisrni.model.TipoProyecto;
import com.sisrni.service.AreaConocimientoService;
import com.sisrni.service.FacultadService;
import com.sisrni.service.PersonaService;
import com.sisrni.service.ProyectoService;
import com.sisrni.service.TelefonoService;
import com.sisrni.service.TipoProyectoService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Lillian
 */
@Named(value = "proyectosMB")
@RequestScoped
public class ProyectosMB {
private TipoProyecto proyectoSelected;
private List<TipoProyecto> tipoproyectolist;
private String [] areaConocimientoSelected;
private List<AreaConocimiento> areaConocimientoList;
private Proyecto proyecto;
private Persona persona;
private Persona personaAsistente;
private Persona personaExterna;
private Facultad facultad;
private Facultad facultadAsistente;
private Facultad facultadExterna;
private Telefono telefono;
private Telefono telefonofax;
private Telefono telefonorefint ;
private Telefono telefonorefintfax;
private Telefono telefonorefext;
private Telefono telefonorefextfax;
    @Autowired
    private AreaConocimientoService areaConocimientoService;
    @Autowired
    private ProyectoService proyectoService;
    @Autowired
    private PersonaService personaService;
    @Autowired
    private TipoProyectoService tipoProyectoService;
    @Autowired
    private FacultadService facultadService;
    @Autowired
    private TelefonoService telefonoService;
    
    /**
     * Creates a new instance of ProyectosMB
     */
    public void init(){
    inicializador();
    }
    @PostConstruct
    public void inicializador(){
    proyectoSelected = new TipoProyecto();
    tipoproyectolist = tipoProyectoService.findAll();
    areaConocimientoList=areaConocimientoService.findAll();
    Proyecto proyecto = new Proyecto();
    Persona persona = new Persona();
    Facultad facultad = new Facultad();
    Facultad facultadAsistente = new Facultad();
    Facultad facultadExterna = new Facultad();
    Telefono telefono = new Telefono();
    Telefono telefonofax = new Telefono();
    Telefono telefonorefint=new Telefono();
    Telefono telefonorefintfax=new Telefono();
    Telefono telefonorefext=new Telefono();
    Telefono telefonorefextfax=new Telefono();
    Persona personaAsistente=new Persona();
    Persona personaExterna=new Persona();
    
    }
    public ProyectosMB() {
    }

    public TipoProyecto getProyectoSelected() {
        return proyectoSelected;
    }

    public void setProyectoSelected(TipoProyecto proyectoSelected) {
        this.proyectoSelected = proyectoSelected;
    }

    public List<TipoProyecto> getTipoproyectolist() {
        return tipoproyectolist;
    }

    public void setTipoproyectolist(List<TipoProyecto> tipoproyectolist) {
        this.tipoproyectolist = tipoproyectolist;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Facultad getFacultad() {
        return facultad;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }

    public Telefono getTelefono() {
        return telefono;
    }

    public void setTelefono(Telefono telefono) {
        this.telefono = telefono;
    }

    public Telefono getTelefonofax() {
        return telefonofax;
    }

    public void setTelefonofax(Telefono telefonofax) {
        this.telefonofax = telefonofax;
    }

    public Telefono getTelefonorefint() {
        return telefonorefint;
    }

    public void setTelefonorefint(Telefono telefonorefint) {
        this.telefonorefint = telefonorefint;
    }

    public Telefono getTelefonorefintfax() {
        return telefonorefintfax;
    }

    public void setTelefonorefintfax(Telefono telefonorefintfax) {
        this.telefonorefintfax = telefonorefintfax;
    }

    public Telefono getTelefonorefext() {
        return telefonorefext;
    }

    public void setTelefonorefext(Telefono telefonorefext) {
        this.telefonorefext = telefonorefext;
    }

    public Telefono getTelefonorefextfax() {
        return telefonorefextfax;
    }

    public void setTelefonorefextfax(Telefono telefonorefextfax) {
        this.telefonorefextfax = telefonorefextfax;
    }

    public Persona getPersonaAsistente() {
        return personaAsistente;
    }

    public void setPersonaAsistente(Persona personaAsistente) {
        this.personaAsistente = personaAsistente;
    }

    public Persona getPersonaExterna() {
        return personaExterna;
    }

    public void setPersonaExterna(Persona personaExterna) {
        this.personaExterna = personaExterna;
    }

    public Facultad getFacultadAsistente() {
        return facultadAsistente;
    }

    public void setFacultadAsistente(Facultad facultadAsistente) {
        this.facultadAsistente = facultadAsistente;
    }

    public Facultad getFacultadExterna() {
        return facultadExterna;
    }

    public void setFacultadExterna(Facultad facultadExterna) {
        this.facultadExterna = facultadExterna;
    }

    public String[] getAreaConocimientoSelected() {
        return areaConocimientoSelected;
    }

    public void setAreaConocimientoSelected(String[] areaConocimientoSelected) {
        this.areaConocimientoSelected = areaConocimientoSelected;
    }

    public List<AreaConocimiento> getAreaConocimientoList() {
        return areaConocimientoList;
    }

    public void setAreaConocimientoList(List<AreaConocimiento> areaConocimientoList) {
        this.areaConocimientoList = areaConocimientoList;
    }
    
}
