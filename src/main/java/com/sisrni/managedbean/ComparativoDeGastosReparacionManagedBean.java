/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import com.sisrni.jasper.Reporte;
import com.sisrni.managedbean.form.CurrentUserSessionForm;
import com.sisrni.managedbean.generic.GenericManagedBean;
import com.sisrni.managedbean.lazymodel.IndicePromedioDeGastoReparacionEquipoLazyModel;
import com.sisrni.model.Equipo;
import com.sisrni.model.Evento;
import com.sisrni.model.SsMenus;
import com.sisrni.pojo.rpt.RptComparativoDeGastosReparacion;
import com.sisrni.service.EquipoService;
import com.sisrni.service.EventoService;
import com.sisrni.service.SsMenusService;
import com.sisrni.service.generic.GenericService;
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
 * @author Joao
 */
@Named("comparativoDeGastosReparacionManagedBean")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class ComparativoDeGastosReparacionManagedBean extends GenericManagedBean<Equipo, Integer> {

    @Autowired
    @Qualifier(value = "equipoService")
    private EquipoService equipoService;

    @Autowired
    @Qualifier(value = "eventoService")
    private EventoService eventoService;
    
    @Autowired
    @Qualifier(value = "ssMenuService")
    private SsMenusService menusService;

     private List<SsMenus> listadoMenus;
     
     
    private List<Equipo> equipoList;
    private List<Evento> eventoList;

    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    private String fecha;
    private Date date1;
    private Date date2;
    private Date date3 = new Date();
    private boolean value1;

    private Evento area;
    private Equipo equipox;
    private Equipo equipoy;
    private String reportName;
    Random r = new Random();

    private CurrentUserSessionBean user;
    private CurrentUserSessionForm sessionForm;

    public ComparativoDeGastosReparacionManagedBean() {
        user = new CurrentUserSessionBean();
        sessionForm = user.getForm();
    }

    @PostConstruct
    public void init() {
        equipoList = new ArrayList<Equipo>();
        eventoList = new ArrayList<Evento>();
        equipoList = equipoService.findAll();
        eventoList = eventoService.findAll();
        listadoMenus = menusService.getDao().findAll();      
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

        if (getArea() == null || getArea().equals("")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Seleccionar Area Hospitalaria"));
        } else if (getDate1() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Fecha Inicial Vacia."));
        } else if (getDate2() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Fecha Fin Vacia."));
        } else if (getDate2().before(getDate1())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Fecha Fin es Menor que Fecha Inicio."));
        } else if (getEquipox().equals(null) || getEquipox().equals("")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Seleccione Equipo 1."));
        } else if (getEquipoy().equals(null) || getEquipoy().equals("")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Seleccione Equipo 2."));
        } else if (getEquipox().toString().equals(getEquipoy().toString())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Equipo 1 No Debe ser Igual a Equipo 2."));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Procesado Reporte."));
            llenarReporte();

        }

    }

    private void llenarReporte() {
        List<RptComparativoDeGastosReparacion> list = new ArrayList<RptComparativoDeGastosReparacion>();

        List<Evento> comparativoReparacionesDos = eventoService.getComparativoReparacionesDos(getArea().getUnidad(), date1, date2);
        RptComparativoDeGastosReparacion prueba = new RptComparativoDeGastosReparacion();

        for (Evento item : comparativoReparacionesDos) {
            prueba = new RptComparativoDeGastosReparacion();
            prueba.setEquipox(100 + (1000 - 100) * r.nextDouble());
            prueba.setEquipoy(100 + (1000 - 100) * r.nextDouble());
            prueba.setTipoReparacion(item.getUnidad());
            list.add(prueba);
        }

        print(list);
    }

    public void print(List<RptComparativoDeGastosReparacion> list) {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        Reporte reporte = new Reporte("compgastosrep", "rpt_comparativo_gasto_reparaciones", request);
        reporte.setDataSource(new JRBeanCollectionDataSource(new HashSet<RptComparativoDeGastosReparacion>(list)));
        reporte.addParameter("fechaInicial", formatter.format(date1));
        reporte.addParameter("fechaFinal", formatter.format(date2));
        reporte.addParameter("usuario", user.getSessionUser().getUsername());
        reporte.addParameter("equipox", getEquipox().getNombequipo());
        reporte.addParameter("equipoy", getEquipoy().getNombequipo());
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

    public List<Evento> getEventoList() {
        return eventoList;
    }

    public void setEventoList(List<Evento> eventoList) {
        this.eventoList = eventoList;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public EventoService getEventoService() {
        return eventoService;
    }

    public void setEventoService(EventoService eventoService) {
        this.eventoService = eventoService;
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

    public Evento getArea() {
        return area;
    }

    public void setArea(Evento area) {
        this.area = area;
    }

    public List<SsMenus> getListadoMenus() {
        return listadoMenus;
    }

    public void setListadoMenus(List<SsMenus> listadoMenus) {
        this.listadoMenus = listadoMenus;
    }

}
