/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimesa.managedbean;

import com.dimesa.jasper.Reporte;
import com.dimesa.managedbean.form.CurrentUserSessionForm;
import com.dimesa.managedbean.generic.GenericManagedBean;

import com.dimesa.managedbean.lazymodel.IndicePromedioDeGastoReparacionEventoLazyModel;

import com.dimesa.model.Evento;

import com.dimesa.pojo.rpt.RptIndicePromedioDeGastoDepreciacionEquipo;

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
 * @author Joao
 */
@Named("gastoDepreciacionEquipoManagedBean")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class IndicePromedioDeGastoDepreciacionEquipoManagedBean extends GenericManagedBean<Evento, Integer> {

    @Autowired
    @Qualifier(value = "eventoService")
    private EventoService eventoService;
    private Evento evento;

    private List<Evento> eventoList;
    private Date date3 = new Date();
    private String fecha;
    private Date date1;
    private Date date2;
    private boolean value1;
    private String reportName;
    private Evento area;
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    private Double promedio = 0.0;
    private Double indice = 0.0;
    Random r = new Random();

    private CurrentUserSessionBean user;
    private CurrentUserSessionForm sessionForm;

    public IndicePromedioDeGastoDepreciacionEquipoManagedBean() {
        user = new CurrentUserSessionBean();
        sessionForm = user.getForm();
    }

    @PostConstruct
    public void init() {
        eventoList = new ArrayList<Evento>();
        eventoList = eventoService.findAll();
    }

    @Override
    public GenericService<Evento, Integer> getService() {
        return eventoService;
    }

    @Override
    public LazyDataModel<Evento> getNewLazyModel() {
        return new IndicePromedioDeGastoReparacionEventoLazyModel(eventoService);
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
        } else {
            if (value1) {
                llenarPromedio();
            } else {
                llenarReporte();
            }

        }
    }

    private void llenarReporte() {
        List<RptIndicePromedioDeGastoDepreciacionEquipo> list = new ArrayList<RptIndicePromedioDeGastoDepreciacionEquipo>();

        List<Evento> comparativoReparacionesDos = eventoService.getComparativoReparacionesDos(getArea().getUnidad(), date1, date2);
        RptIndicePromedioDeGastoDepreciacionEquipo prueba = new RptIndicePromedioDeGastoDepreciacionEquipo();

        for (Evento item : comparativoReparacionesDos) {
            prueba = new RptIndicePromedioDeGastoDepreciacionEquipo();
            prueba.setArea(item.getUnidad());
            prueba.setEquipo(item.getPladimesa().getNombequipo());
            prueba.setFechaRep(item.getFechainicio().toString());
            prueba.setGasto(50 + (100 - 50) * r.nextDouble());
            list.add(prueba);
        }
        promedio = 0.0;
        indice = 0.0;
        print(list);
    }

    private void llenarPromedio() {
        List<RptIndicePromedioDeGastoDepreciacionEquipo> list = new ArrayList<RptIndicePromedioDeGastoDepreciacionEquipo>();

        List<Evento> comparativoReparacionesDos = eventoService.getComparativoReparacionesDos(getArea().getUnidad(), date1, date2);
        RptIndicePromedioDeGastoDepreciacionEquipo prueba = new RptIndicePromedioDeGastoDepreciacionEquipo();

        for (Evento item : comparativoReparacionesDos) {
            prueba = new RptIndicePromedioDeGastoDepreciacionEquipo();
            prueba.setArea(item.getUnidad());
            prueba.setEquipo(item.getPladimesa().getNombequipo());
            prueba.setFechaRep(item.getFechainicio().toString());
            prueba.setGasto(50 + (100 - 50) * r.nextDouble());
            list.add(prueba);
        }
        for (RptIndicePromedioDeGastoDepreciacionEquipo itr : list) {
            promedio = promedio + itr.getGasto();
        }
        indice = (((promedio / list.size()) * 100) / promedio);
        promedio = promedio / list.size();
        print(list);
    }

    public void print(List<RptIndicePromedioDeGastoDepreciacionEquipo> list) {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        Reporte reporte = new Reporte("indicepromediogastodepreciacion", "rpt_indice_promedio_gasto_depreciacion_por_area", request);
        reporte.setDataSource(new JRBeanCollectionDataSource(new HashSet<RptIndicePromedioDeGastoDepreciacionEquipo>(list)));
        reporte.addParameter("fechaInicial", formatter.format(date1));
        reporte.addParameter("fechaFinal", formatter.format(date2));
        reporte.addParameter("usuario", user.getSessionUser().getUsername());
        reporte.addParameter("indice", indice);//double
        reporte.addParameter("gastoprom", promedio);//double
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

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
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

    public Evento getArea() {
        return area;
    }

    public void setArea(Evento area) {
        this.area = area;
    }

    public Double getPromedio() {
        return promedio;
    }

    public void setPromedio(Double promedio) {
        this.promedio = promedio;
    }

    public Double getIndice() {
        return indice;
    }

    public void setIndice(Double indice) {
        this.indice = indice;
    }

}
