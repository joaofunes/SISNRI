/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import com.sisrni.jasper.Reporte;
import com.sisrni.managedbean.form.CurrentUserSessionForm;
import com.sisrni.managedbean.generic.GenericManagedBean;
import com.sisrni.managedbean.lazymodel.ResumenHistorialReparacionTecnicoNoSubContratadoLazyModel;
import com.sisrni.model.CostoEquipo;
import com.sisrni.model.Empleado;
import com.sisrni.model.Equipo;
import com.sisrni.model.Evento;
import com.sisrni.service.CostoEquipoService;
import com.sisrni.service.EmpleadoService;
import com.sisrni.service.EquipoService;
import com.sisrni.service.EventoService;
import com.sisrni.service.generic.GenericService;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;
import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.context.RequestContext;

/**
 *
 * @author RAUL
 */
@Named("resumenHistorialReparacionTecnicoNoSubContratado")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class ResumenHistorialReparacionTecnicoNoSubContratado extends GenericManagedBean<Equipo, Integer> {

    @Autowired
    @Qualifier(value = "equipoService")
    private EquipoService equipoService;

    @Autowired
    @Qualifier(value = "empleadoService")
    private EmpleadoService empleadoService;

    @Autowired
    @Qualifier(value = "eventoService")
    private EventoService eventoService;

    @Autowired
    @Qualifier(value = "costoEquipoService")
    private CostoEquipoService costoEquipoService;

    private List<Equipo> equipoList;
    private List<Empleado> empleadoList;
    private List<Evento> eventodoList;/*tipo de reporte*/

    private CostoEquipo costoEquipo;
    private Evento evento;
    private Empleado empleado;
    private Equipo equipo;
    private String equipox;
    private String tecnico;
    private String tiporeporte;
    private String fecha;

    private Date date1;
    private Date date2;

    private Date dateFinal = new Date();
    private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    private int millisInDay = 24 * 60 * 60 * 1000;
    Random r = new Random();
    private String fechaFinal;
    private String reportName;

    public CostoEquipoService getCostoEquipoService() {
        return costoEquipoService;
    }

    public void setCostoEquipoService(CostoEquipoService costoEquipoService) {
        this.costoEquipoService = costoEquipoService;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    private CurrentUserSessionBean user;
    private CurrentUserSessionForm sessionForm;

    public ResumenHistorialReparacionTecnicoNoSubContratado() {
        user = new CurrentUserSessionBean();
        sessionForm = user.getForm();
    }

    @PostConstruct
    public void init() {
        equipoList = new ArrayList<Equipo>();
        empleadoList = new ArrayList<Empleado>();
        eventodoList = new ArrayList<Evento>();
        equipoList = equipoService.findAll();
        empleadoList = empleadoService.getTecnicosExterno();
        eventodoList = eventoService.findAll();
    }

    @Override
    public GenericService<Equipo, Integer> getService() {
        return equipoService;
    }

    @Override
    public LazyDataModel<Equipo> getNewLazyModel() {
        return new ResumenHistorialReparacionTecnicoNoSubContratadoLazyModel(equipoService);
    }

    public void click() {

        if (getDate1() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Fecha Inicial Vacia."));
        } else if (getDate2() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Fecha Fin Vacia."));
        } else if (getDate2().before(getDate1())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Fecha Fin es Menor que Fecha Inicio."));
        } else if (getDate2().equals(getDate1())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Fecha Inicio es Igual que Fecha Fin."));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Procesado Reporte."));
            print();
        }

    }

    public void print() {
//        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
//        List<RptIndiceDeEncarrilamiento> list = new ArrayList<RptIndiceDeEncarrilamiento>();
//        RptIndiceDeEncarrilamiento prueba = new RptIndiceDeEncarrilamiento();
//
//        for (int i = 0; i < 100; i++) {
//            prueba = new RptIndiceDeEncarrilamiento();
//            prueba.setArea("Area de prueba");
//            prueba.setEquipo("Equipo" + i);
//            prueba.setGastoDept(100 + (200 - 300) * r.nextDouble());
//            prueba.setGastoRepa(100 + (200 - 300) * r.nextDouble());
//            prueba.setTasaRep(100 + (200 - 300) * r.nextDouble());
//            Time time = new Time((long) r.nextInt(millisInDay));
//            prueba.setTiempoRe(time.toString());
//            list.add(prueba);
//        }
//        HttpServletRequest request = (HttpServletRequest) context.getRequest();
//        HttpServletResponse response = (HttpServletResponse) context.getResponse();
//        Reporte reporte = new Reporte("indicedeencarrilamiento", "rpt_encarrilamiento", request);
//        reporte.setDataSource(new JRBeanCollectionDataSource(new HashSet<RptIndiceDeEncarrilamiento>(list)));
//        reporte.addParameter("fechaInicial", formatter.format(date1));
//        reporte.addParameter("fechaFinal", formatter.format(date2));
//        reporte.addParameter("usuario", user.getSessionUser().getUsername());
//
//        reporte.setReportInSession(request, response);
//        reportName = reporte.getNombreLogico();
//        RequestContext.getCurrentInstance().addCallbackParam("reportName", reportName);

    }

    public CostoEquipo getCostoEquipo() {
        return costoEquipo;
    }

    public void setCostoEquipo(CostoEquipo costoEquipo) {
        this.costoEquipo = costoEquipo;
    }

    public Evento getEvento() {

        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Date getDateFinal() {
        return dateFinal;
    }

    public void setDateFinal(Date dateFinal) {
        this.dateFinal = dateFinal;
    }

    public String getFechaFinal() {
        fechaFinal = formatter.format(dateFinal);
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public SimpleDateFormat getFormatter() {
        return formatter;
    }

    public void setFormatter(SimpleDateFormat formatter) {
        this.formatter = formatter;
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public Date getDate2() {
        return date2;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    public List<Equipo> getEquipoList() {
        return equipoList;
    }

    public void setEquipoList(List<Equipo> equipoList) {
        this.equipoList = equipoList;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public EquipoService getEquipoService() {
        return equipoService;
    }

    public void setEquipoService(EquipoService equipoService) {
        this.equipoService = equipoService;
    }

    public String getEquipox() {
        return equipox;
    }

    public void setEquipox(String equipox) {
        this.equipox = equipox;
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

    public EventoService getEventoService() {
        return eventoService;
    }

    public void setEventoService(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    public List<Evento> getEventodoList() {
        return eventodoList;
    }

    public void setEventodoList(List<Evento> eventodoList) {
        this.eventodoList = eventodoList;
    }

    public String getTiporeporte() {
        return tiporeporte;
    }

    public void setTiporeporte(String tiporeporte) {
        this.tiporeporte = tiporeporte;
    }

    public String getFecha() {
        fecha = formatter.format(dateFinal);
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

}
