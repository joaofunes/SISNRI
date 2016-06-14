/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimesa.managedbean;

import com.dimesa.jasper.Reporte;
import com.dimesa.managedbean.form.CurrentUserSessionForm;
import com.dimesa.model.Evento;
import com.dimesa.pojo.rpt.RptExportacionDeProcesosProyeccionTazas;
import com.dimesa.pojo.rpt.RptIndiceDeEncarrilamiento;
import com.dimesa.pojo.rpt.RptIndicePromedioDeGastoDepreciacionEquipo;
import com.dimesa.pojo.rpt.RptIndicePromedioDeGastoReparacionEquipo;
import com.dimesa.service.EventoService;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author HDEZ
 */
@Named("exportacionDeProcesosProyeccionTazasManagedBean")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class ExportacionDeProcesosProyeccionTazasManagedBean {

    private Date date1;
    private Date date2;
    private Date date3 = new Date();
    private boolean value1;
    private boolean value2;
    private boolean value3;
    private boolean value4;
    private boolean value5;
    private boolean value6;
    private String fecha;
    private String reportName;
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    private int millisInDay = 24 * 60 * 60 * 1000;
    Random r = new Random();
    private Double promediod = 0.0;
    private Double indiced = 0.0;
    private Double promediog = 0.0;
    private Double indiceg = 0.0;

    private CurrentUserSessionBean user;
    private CurrentUserSessionForm sessionForm;

    @Autowired
    @Qualifier(value = "eventoService")
    private EventoService eventoService;

    public ExportacionDeProcesosProyeccionTazasManagedBean() {
        user = new CurrentUserSessionBean();
        sessionForm = user.getForm();
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
        } else if (!value1 && !value2 && !value3 && !value4 && !value5 && !value6) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No ha seleccionado opcion."));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Procesado Reporte."));
            llenarPromedio();

        }

    }

    private void llenarPromedio() {

        if (value3) {
            /*depreciacion*/
            List<RptIndicePromedioDeGastoDepreciacionEquipo> list = new ArrayList<RptIndicePromedioDeGastoDepreciacionEquipo>();
            List<Evento> comparativoReparacionesAllUnidad = eventoService.getComparativoReparacionesAllUnidad(date1, date2);
            RptIndicePromedioDeGastoDepreciacionEquipo prueba = new RptIndicePromedioDeGastoDepreciacionEquipo();

            for (Evento item : comparativoReparacionesAllUnidad) {
                prueba = new RptIndicePromedioDeGastoDepreciacionEquipo();
                prueba.setArea(item.getUnidad());
                prueba.setEquipo(item.getPladimesa().getNombequipo());
                prueba.setFechaRep(item.getFechainicio().toString());
                prueba.setGasto(50 + (100 - 50) * r.nextDouble());
                list.add(prueba);
            }
            for (RptIndicePromedioDeGastoDepreciacionEquipo itr : list) {
                promediod = promediod + itr.getGasto();
            }
            indiced = (((promediod / list.size()) * 100) / promediod);
            promediod = promediod / list.size();
        }

        if (value2) {
            List<RptIndicePromedioDeGastoReparacionEquipo> list = new ArrayList<RptIndicePromedioDeGastoReparacionEquipo>();
            List<Evento> comparativoReparacionesDos = eventoService.getComparativoReparacionesAllUnidad(date1, date2);
            RptIndicePromedioDeGastoReparacionEquipo prueba = new RptIndicePromedioDeGastoReparacionEquipo();

            for (Evento item : comparativoReparacionesDos) {
                prueba = new RptIndicePromedioDeGastoReparacionEquipo();
                prueba.setArea(item.getUnidad());
                prueba.setEquipo(item.getPladimesa().getNombequipo());
                prueba.setFechaRep(item.getFechainicio().toString());
                prueba.setGasto(100 + (300 - 100) * r.nextDouble());
                list.add(prueba);
            }
            for (RptIndicePromedioDeGastoReparacionEquipo itr : list) {
                promediog = promediog + itr.getGasto();
            }
            indiceg = (((promediog / list.size()) * 100) / promediog);
            promediog = promediog / list.size();
        }
        print();
    }

    public void print() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        List<RptExportacionDeProcesosProyeccionTazas> list = new ArrayList<RptExportacionDeProcesosProyeccionTazas>();
        RptExportacionDeProcesosProyeccionTazas prueba = new RptExportacionDeProcesosProyeccionTazas();
        Time time = new Time((long) r.nextInt(millisInDay));
        if (value4) {
            prueba.setT(1);
            prueba.setTiempoFalla(time.toString());
            time = new Time((long) r.nextInt(millisInDay));
            prueba.setTiempoInstalacion(time.toString());
            time = new Time((long) r.nextInt(millisInDay));
            prueba.setTiempoReparacion(time.toString());

        }

        if (value5) {
            prueba.setR(1);
            List<Evento> listadoExitoso = eventoService.getListadoExitoso(date1, date2);
            int size = listadoExitoso.size();
            prueba.setNumeroReparaciones(size);
            time = new Time((long) r.nextInt(millisInDay));
        }

        if (value6) {
            prueba.setP(1);
            List<Evento> listadoPreventivo = eventoService.getListadoPreventivo(date1, date2);
            prueba.setNumeroPreventivoActual(listadoPreventivo.size());
            prueba.setNumeroPreventivo(r.nextInt(listadoPreventivo.size()));
        }
        list.add(prueba);

        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        Reporte reporte = new Reporte("archivodeexportacion", "rpt_archivo_exportacion", request);
        reporte.setDataSource(new JRBeanCollectionDataSource(new HashSet<RptExportacionDeProcesosProyeccionTazas>(list)));
        reporte.addParameter("fechaInicial", formatter.format(date1));
        reporte.addParameter("fechaFinal", formatter.format(date2));
        reporte.addParameter("usuario", user.getSessionUser().getUsername());
        reporte.addParameter("indiceg", indiceg);//double
        reporte.addParameter("indicedep", indiced);//double
        reporte.addParameter("gastoprom", promediog);//double
        reporte.addParameter("gastodep", promediod);//double
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

    public boolean isValue1() {
        return value1;
    }

    public void setValue1(boolean value1) {
        this.value1 = value1;
    }

    public boolean isValue2() {
        return value2;
    }

    public void setValue2(boolean value2) {
        this.value2 = value2;
    }

    public boolean isValue3() {
        return value3;
    }

    public void setValue3(boolean value3) {
        this.value3 = value3;
    }

    public boolean isValue4() {
        return value4;
    }

    public void setValue4(boolean value4) {
        this.value4 = value4;
    }

    public boolean isValue5() {
        return value5;
    }

    public void setValue5(boolean value5) {
        this.value5 = value5;
    }

    public boolean isValue6() {
        return value6;
    }

    public void setValue6(boolean value6) {
        this.value6 = value6;
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

    public EventoService getEventoService() {
        return eventoService;
    }

    public void setEventoService(EventoService eventoService) {
        this.eventoService = eventoService;
    }
}
