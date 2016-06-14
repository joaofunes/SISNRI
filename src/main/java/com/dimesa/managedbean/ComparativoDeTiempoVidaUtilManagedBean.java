/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimesa.managedbean;

import com.dimesa.jasper.Reporte;
import com.dimesa.managedbean.form.CurrentUserSessionForm;
import com.dimesa.managedbean.generic.GenericManagedBean;
import com.dimesa.managedbean.lazymodel.IndicePromedioDeGastoReparacionEquipoLazyModel;
import com.dimesa.model.Equipo;
import com.dimesa.model.Evento;
import com.dimesa.pojo.rpt.RptComparativoDeTiempoVidaUtil;
import com.dimesa.service.EquipoService;
import com.dimesa.service.EventoService;
import com.dimesa.service.generic.GenericService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author HDEZ
 */
@Named("comparativoDeTiempoVidaUtilManagedBean")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class ComparativoDeTiempoVidaUtilManagedBean extends GenericManagedBean<Equipo, Integer> {

    @Autowired
    @Qualifier(value = "equipoService")
    private EquipoService equipoService;
    private Equipo equipo;

    @Autowired
    @Qualifier(value = "eventoService")
    private EventoService eventoService;

    public EventoService getEventoService() {
        return eventoService;
    }

    public void setEventoService(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    private List<Equipo> equipoList;
    private Date date3 = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    private String fecha;
    private Equipo equipox;
    private Equipo equipoy;
    private boolean value1;
    private String reportName;
    Random r = new Random();

    private CurrentUserSessionBean user;
    private CurrentUserSessionForm sessionForm;

    public ComparativoDeTiempoVidaUtilManagedBean() {
        user = new CurrentUserSessionBean();
        sessionForm = user.getForm();
    }

    @PostConstruct
    public void init() {
        equipoList = new ArrayList<Equipo>();
        equipoList = equipoService.findAll();
    }

    @Override
    public GenericService<Equipo, Integer> getService() {
        return equipoService;
    }

    @Override
    public LazyDataModel<Equipo> getNewLazyModel() {
        return new IndicePromedioDeGastoReparacionEquipoLazyModel(equipoService);
    }

    public void click() {

        if (getEquipox().equals(null) || getEquipox().equals("")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Seleccione Equipo 1."));
        } else if (getEquipoy().equals(null) || getEquipoy().equals("")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Seleccione Equipo 2."));
        } else if (getEquipox().toString().equals(getEquipoy().toString())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Equipo 1 No Debe ser Igual a Equipo 2."));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Procesado Reporte."));

            llenar();

        }

    }

    private void llenar() {
        List<RptComparativoDeTiempoVidaUtil> list = new ArrayList<RptComparativoDeTiempoVidaUtil>();

        List<Evento> listadoFallos = eventoService.getListadoVidaUtil(getEquipox().getPladimesa(),getEquipoy().getPladimesa());
        RptComparativoDeTiempoVidaUtil prueba = new RptComparativoDeTiempoVidaUtil();      
        prueba = new RptComparativoDeTiempoVidaUtil();
        prueba.setCostoinicialA(listadoFallos.get(0).getServicio().equals("INSTALACION") ? listadoFallos.get(0).getIdcostoequipo().getCosto() : Double.parseDouble("0.0"));
        prueba.setCostoinicialB(listadoFallos.get(1).getServicio().equals("INSTALACION") ? listadoFallos.get(1).getIdcostoequipo().getCosto() : Double.parseDouble("0.0"));
        prueba.setCostoAnualOpreacionA(100 + (1000 - 100) * r.nextDouble());
        prueba.setCostoAnualOpreacionB(100 + (1000 - 100) * r.nextDouble());
        prueba.setInteresA(10.0);
        prueba.setInteresB(10.0);
        prueba.setValorSalvamentoA(0.1 * listadoFallos.get(0).getIdcostoequipo().getCosto());
        prueba.setValorSalvamentoB(0.1 * listadoFallos.get(1).getIdcostoequipo().getCosto());
        prueba.setVidaUtilA(5);
        prueba.setVidaUtilB(5);
        prueba.setUsuario(user.getSessionUser().getUsername());
        list.add(prueba);
        print(list);
    }

    public void print(List<RptComparativoDeTiempoVidaUtil> list) {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        Reporte reporte = new Reporte("vidautil", "rpt_comparativo_vida_util", request);
        reporte.setDataSource(new JRBeanCollectionDataSource(new HashSet<RptComparativoDeTiempoVidaUtil>(list)));
        reporte.setReportInSession(request, response);
        reportName = reporte.getNombreLogico();
        RequestContext.getCurrentInstance().addCallbackParam("reportName", reportName);
    }

  
    public Date getDate3() {
        return date3;
    }

    public void setDate3(Date date3) {
        this.date3 = date3;
    }

    public List<Equipo> getEquipoList() {
        return equipoList;
    }

    public void setEquipoList(List<Equipo> equipoList) {
        this.equipoList = equipoList;
    }

    public EquipoService getEquipoService() {
        return equipoService;
    }

    public void setEquipoService(EquipoService equipoService) {
        this.equipoService = equipoService;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public boolean isValue1() {
        return value1;
    }

    public void setValue1(boolean value1) {
        this.value1 = value1;
    }

    public String getFecha() {
        fecha = formatter.format(date3);
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

 

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public Equipo getEquipox() {
        return equipox;
    }

    public void setEquipox(Equipo equipox) {
        this.equipox = equipox;
    }

    public Equipo getEquipoy() {
        return equipoy;
    }

    public void setEquipoy(Equipo equipoy) {
        this.equipoy = equipoy;
    }

}
