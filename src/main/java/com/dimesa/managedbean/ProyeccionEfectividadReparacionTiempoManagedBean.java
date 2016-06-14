/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimesa.managedbean;

import com.dimesa.jasper.Reporte;
import com.dimesa.managedbean.form.CurrentUserSessionForm;
import com.dimesa.managedbean.generic.GenericManagedBean;
import com.dimesa.managedbean.lazymodel.ProyeccionEfectividadReparacionTiempoEquipoLazyModel;
import com.dimesa.model.Empleado;
import com.dimesa.model.Equipo;
import com.dimesa.model.Evento;
import com.dimesa.pojo.rpt.RptProyeccionEfectividadReparacionTiempo;
import com.dimesa.service.EmpleadoService;
import com.dimesa.service.EquipoService;
import com.dimesa.service.EventoService;
import com.dimesa.service.generic.GenericService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
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
@Named("proyeccionEfectividadReparacionTiempoManagedBean")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class ProyeccionEfectividadReparacionTiempoManagedBean extends GenericManagedBean<Equipo, Integer> {

    @Autowired
    @Qualifier(value = "equipoService")
    private EquipoService equipoService;

    @Autowired
    @Qualifier(value = "eventoService")
    private EventoService eventoService;

    @Autowired
    @Qualifier(value = "empleadoService")
    private EmpleadoService empleadoService;

    private Equipo equipo;
    private List<Equipo> equipoList;
    private List<Evento> eventoList;
    private List<Empleado> empleadoList;

    private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    private String fecha;
    private Date date3 = new Date();

    private String reportName;
    private String equipox;
    private String area;
    private String tecnico;

    private CurrentUserSessionBean user;
    private CurrentUserSessionForm sessionForm;

    public ProyeccionEfectividadReparacionTiempoManagedBean() {
        user = new CurrentUserSessionBean();
        sessionForm = user.getForm();
    }

    @PostConstruct
    public void init() {
        equipoList = new ArrayList<Equipo>();
        eventoList = new ArrayList<Evento>();
        empleadoList = new ArrayList<Empleado>();
        equipoList = equipoService.findAll();
        eventoList = eventoService.findAll();
        empleadoList = empleadoService.getTecnicos();
    }

    @Override
    public GenericService<Equipo, Integer> getService() {
        return equipoService;
    }

    @Override
    public LazyDataModel<Equipo> getNewLazyModel() {
        return new ProyeccionEfectividadReparacionTiempoEquipoLazyModel(equipoService);
    }

    public void click() {

        if (getArea() == null || getArea().equals("")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Seleccionar Area Hospitalaria"));
        } else if (getEquipox().equals(null) || getEquipox().equals("")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Seleccione Equipo ."));
        } else if (getTecnico().equals(null) || getTecnico().equals("")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Seleccione Tecnico."));
        } else {
            print();
        }

    }

    public void print() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        List<RptProyeccionEfectividadReparacionTiempo> list = new ArrayList<RptProyeccionEfectividadReparacionTiempo>();
        for (int i = 0; i < 100; i++) {
            RptProyeccionEfectividadReparacionTiempo prueba = new RptProyeccionEfectividadReparacionTiempo();
            //     prueba.setEquipox(12.2);
            //     prueba.setEquipoy(Double.NaN);
            list.add(prueba);
        }

        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        Reporte reporte = new Reporte("compgastosrep", "rpt_comparativo_gasto_reparaciones", request);

        reporte.setDataSource(new JRBeanCollectionDataSource(new HashSet<RptProyeccionEfectividadReparacionTiempo>(list)));
//        reporte.addParameter("fechaInicial", formatter.format(date1));
//        reporte.addParameter("fechaFinal", formatter.format(date2));
        reporte.addParameter("usuario", user.getSessionUser().getUsername());

        reporte.setReportInSession(request, response);
        reportName = reporte.getNombreLogico();
        RequestContext.getCurrentInstance().addCallbackParam("reportName", reportName);
    }

    public SimpleDateFormat getFormatter() {
        return formatter;
    }

    public void setFormatter(SimpleDateFormat formatter) {
        this.formatter = formatter;
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

    public List<Equipo> getEquipoList() {
        return equipoList;
    }

    public void setEquipoList(List<Equipo> equipoList) {
        this.equipoList = equipoList;
    }

    public String getFecha() {
        fecha = formatter.format(date3);
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Date getDate3() {
        return date3;
    }

    public void setDate3(Date date3) {
        this.date3 = date3;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getEquipox() {
        return equipox;
    }

    public void setEquipox(String equipox) {
        this.equipox = equipox;
    }

    public EventoService getEventoService() {
        return eventoService;
    }

    public void setEventoService(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    public List<Evento> getEventoList() {
        return eventoList;
    }

    public void setEventoList(List<Evento> eventoList) {
        this.eventoList = eventoList;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public EmpleadoService getEmpleadoService() {
        return empleadoService;
    }

    public void setEmpleadoService(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    public List<Empleado> getEmpleadoList() {
        return empleadoList;
    }

    public void setEmpleadoList(List<Empleado> empleadoList) {
        this.empleadoList = empleadoList;
    }

    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }

}
