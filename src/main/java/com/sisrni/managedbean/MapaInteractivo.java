/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import com.sisrni.model.Pais;
import com.sisrni.model.Proyecto;
import com.sisrni.model.TipoProyecto;
import com.sisrni.pojo.rpt.PojoMapaInteractivo;
import com.sisrni.pojo.rpt.PojoProyectosByTipo;
import com.sisrni.service.PaisService;
import com.sisrni.service.ProyectoService;
import com.sisrni.service.TipoProyectoService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.extensions.component.gchart.model.GChartModel;
import org.primefaces.extensions.component.gchart.model.GChartModelBuilder;
import org.primefaces.extensions.component.gchart.model.GChartType;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author Cortez
 */
@Named("mapaInteractivo")
//@Scope(WebApplicationContext.SCOPE_APPLICATION)
@ViewScoped
public class MapaInteractivo implements Serializable {

    private static final long serialVersionUID = 1L;
    private final static Log log = LogFactory.getLog(MapaInteractivo.class);
//parametros de busqueda
    private List<String> paisSelected;
    private List<String> tipoProyectoSelected;
    private boolean mostrarGraficos = false;
    private String yearDesde;
    private String yearHasta;
    Integer yearActual;
//Listados
    private List<Pais> paisList;
    List<TipoProyecto> tipoProyectosList;
    List<PojoMapaInteractivo> projectListToChart;
    //graficos
    private GChartModel chartModel;
    private BarChartModel barModel;
    private PieChartModel pieModel;
    private PieChartModel pieModelType;
    int numeroProyectos = 0;
    double montoProyectos = 0;
    private List<Proyecto> projectListToPopUp;

    //
    private boolean noHayRegistros;

    //services
    @Autowired
    @Qualifier(value = "paisService")
    PaisService paisService;

    @Autowired
    TipoProyectoService tipoProyectoService;

    @Autowired
    ProyectoService proyectoService;

    public MapaInteractivo() {
    }

    @PostConstruct
    public void init() {
        try {
            inicializador();
        } catch (Exception e) {
        }
    }

    public void inicializador() {
        mostrarGraficos = false;

        yearActual = getYearOfDate(new Date());
        Integer menosUno = yearActual - 1;
        yearDesde = menosUno.toString() + " ";
        yearHasta = menosUno.toString() + " ";

        paisSelected = new ArrayList<String>();
        paisList = paisService.findAll();
        llenarPaises();

        tipoProyectoSelected = new ArrayList<String>();
        tipoProyectosList = tipoProyectoService.findAll();
        llenarTipos();

        projectListToChart = new ArrayList<PojoMapaInteractivo>();
        projectListToPopUp = new ArrayList<Proyecto>();
//        inicializarMapa();
        noHayRegistros = Boolean.FALSE;
        graficar();
    }

    public void graficar() {
        Boolean badyears = false;
        if (Integer.parseInt(yearDesde.trim()) > Integer.parseInt(yearHasta.trim())) {
            badyears = true;
        }
        if (!paisSelected.isEmpty() && !tipoProyectoSelected.isEmpty()&& !badyears) {
            projectListToChart = proyectoService.getProjectListToCharts(paisSelected, tipoProyectoSelected, yearDesde.trim(), yearHasta.trim());
            montoProyectos = calcularMonto(projectListToChart);
            if (!projectListToChart.isEmpty()) {
                crearMapa();
                createPieModel();
                createPieTipo();
                noHayRegistros = Boolean.FALSE;
            } else {
                noHayRegistros = Boolean.TRUE;
                inicializarMapa();
                inicializarPieUno();
                inicializarPieDos();
            }

        } else {
            if (paisSelected.isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe seleccionar al menos un Pais"));
            }
            if (tipoProyectoSelected.isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe seleccionar al menos un tipo de proyecto"));
            }
            if (badyears) {
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Parametro Desde no puede ser mayor al parametro Hasta"));
            }
            noHayRegistros = Boolean.TRUE;
            inicializarMapa();
            inicializarPieUno();
            inicializarPieDos();
        }

    }

    public void llenarPaises() {
        for (Pais p : paisList) {
            paisSelected.add(p.getIdPais() + "");
        }
    }

    public void llenarTipos() {
        for (TipoProyecto tp : tipoProyectosList) {
            tipoProyectoSelected.add(tp.getIdTipoProyecto() + "");
        }
    }

    public void onCountryChange() {
        mostrarGraficos = true;
        createBarModel();
        createPieModel();
        crearMapa();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "PrimeFaces Rocks." + mostrarGraficos));
    }

    private void createPieModel() {
        pieModel = new PieChartModel();
        for (PojoMapaInteractivo pj : projectListToChart) {
            pieModel.set(pj.getNombrePais(), pj.getMontoCooperacion());
        }
        pieModel.setTitle("Pais y Porcejaje de cooperacion");
        pieModel.setLegendPosition("w");
        pieModel.setShowDataLabels(true);
    }

    private void createPieTipo() {
        pieModelType = new PieChartModel();
        List<PojoProyectosByTipo> series = projectListToChart.get(0).getSeries();
        for (PojoProyectosByTipo pj : series) {
            pieModelType.set(pj.getNombreTipoProyecto(), pj.getCantidadProyectos());
        }
        pieModelType.setTitle("Cantidad y Tipos de Proyecto");
        pieModelType.setLegendPosition("w");
        pieModelType.setShowDataLabels(true);
    }

    public void inicializarPieUno() {

        pieModel = new PieChartModel();
        pieModel.setTitle("País y Porcetaje de Cooperación");
        pieModel.setLegendPosition("w");
        pieModel.setShowDataLabels(true);

    }

    private void inicializarPieDos() {
        pieModelType = new PieChartModel();
        pieModelType.setTitle("Cantidad y Tipos de Proyecto");
        pieModelType.setLegendPosition("w");
        pieModelType.setShowDataLabels(true);
    }

    public void fillPopUp(Integer pais) {
        for (PojoMapaInteractivo pj : projectListToChart) {
            if (pj.getIdPais() == pais) {
                projectListToPopUp = pj.getProjectList();

            }
        }
    }

    private void createBarModel() {
        barModel = initBarModel();

        barModel.setTitle("Grafico de Barras");
        barModel.setLegendPosition("ne");

        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Tiempo");

        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Cooperacion($)");
        yAxis.setMin(0);
        yAxis.setMax(200);
    }

    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
        ChartSeries proyectos = new ChartSeries();
        proyectos.setLabel("Proyectos");
        proyectos.set("2004", 120);
        proyectos.set("2005", 100);
        proyectos.set("2006", 44);
        proyectos.set("2007", 150);
        proyectos.set("2008", 25);
        proyectos.set("2009", 25);
        proyectos.set("2010", 120);
        proyectos.set("2011", 100);
        proyectos.set("2012", 44);
        proyectos.set("2013", 150);
        proyectos.set("2014", 25);
        proyectos.set("2015", 120);
        proyectos.set("2016", 100);
        proyectos.set("2017", 44);
        proyectos.set("2018", 150);
        proyectos.set("2019", 25);
        model.addSeries(proyectos);
        return model;
    }

    public void verdadero() {
        try {
            mostrarGraficos = false;
            projectListToChart = proyectoService.getProjectListToCharts(paisSelected, tipoProyectoSelected, yearDesde.trim(), yearHasta.trim());
            numeroProyectos = projectListToChart.size();
            montoProyectos = calcularMonto(projectListToChart);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "-hasta:" + getYearHasta() + "-actual:" + getYearActual() + "-desde:" + getYearDesde()));

        } catch (Exception e) {

        }

    }

    public void crearMapa() {
        this.chartModel = null;
        try {

            Map<String, Object> colorAxis = new HashMap<String, Object>();
            colorAxis.put("colors", new String[]{"Green", "Red"});
            GChartModelBuilder chartModelBuilder = new GChartModelBuilder();
            chartModelBuilder.setChartType(GChartType.GEO);
            chartModelBuilder.addColumns("Codigo", "Pais", "Cooperacion($)");
            for (PojoMapaInteractivo pj : projectListToChart) {
                chartModelBuilder.addRow(pj.getCodigoPais(), pj.getNombrePais(), pj.getMontoCooperacion());
            }
            chartModelBuilder.addOption("colorAxis", colorAxis);
            this.chartModel = chartModelBuilder.build();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "PrimeFaces Rocks." + mostrarGraficos));
        }
    }

    public void inicializarMapa() {
        this.chartModel = null;
        try {
            Map<String, Object> colorAxis = new HashMap<String, Object>();
            colorAxis.put("colors", new String[]{"Green", "Red"});
            GChartModelBuilder chartModelBuilder = new GChartModelBuilder();
            chartModelBuilder.setChartType(GChartType.GEO);
            chartModelBuilder.addColumns("Codigo", "Pais", "Cooperacion($)");
            chartModelBuilder.addOption("colorAxis", colorAxis);
            this.chartModel = chartModelBuilder.build();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "PrimeFaces Rocks." + mostrarGraficos));
        }
    }

    private double calcularMonto(List<PojoMapaInteractivo> projectListToChart) {
        double total = 0L;
        for (PojoMapaInteractivo pj : projectListToChart) {
            total = total + pj.getMontoCooperacion();
        }
        return total;
    }

    private Integer getYearOfDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Integer year = cal.get(Calendar.YEAR);
        return year;
    }

    public boolean isNoHayRegistros() {
        return noHayRegistros;
    }

    public void setNoHayRegistros(boolean noHayRegistros) {
        this.noHayRegistros = noHayRegistros;
    }

    public List<Pais> getPaisList() {
        return paisList;
    }

    public void setPaisList(List<Pais> paisList) {
        this.paisList = paisList;
    }

    public List<String> getPaisSelected() {
        return paisSelected;
    }

    public void setPaisSelected(List<String> paisSelected) {
        this.paisSelected = paisSelected;
    }

    public List<String> getTipoProyectoSelected() {
        return tipoProyectoSelected;
    }

    public void setTipoProyectoSelected(List<String> tipoProyectoSelected) {
        this.tipoProyectoSelected = tipoProyectoSelected;
    }

    public boolean isMostrarGraficos() {
        return mostrarGraficos;
    }

    public void setMostrarGraficos(boolean mostrarGraficos) {
        this.mostrarGraficos = mostrarGraficos;
    }

    public GChartModel getChartModel() {
        return chartModel;
    }

    public void setChartModel(GChartModel chartModel) {
        this.chartModel = chartModel;
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }

    public PieChartModel getPieModelType() {
        return pieModelType;
    }

    public void setPieModelType(PieChartModel pieModelType) {
        this.pieModelType = pieModelType;
    }

    public List<TipoProyecto> getTipoProyectosList() {
        return tipoProyectosList;
    }

    public void setTipoProyectosList(List<TipoProyecto> tipoProyectosList) {
        this.tipoProyectosList = tipoProyectosList;
    }

    public List<PojoMapaInteractivo> getProjectListToChart() {
        return projectListToChart;
    }

    public void setProjectListToChart(List<PojoMapaInteractivo> projectListToChart) {
        this.projectListToChart = projectListToChart;
    }

    public int getNumeroProyectos() {
        return numeroProyectos;
    }

    public void setNumeroProyectos(int numeroProyectos) {
        this.numeroProyectos = numeroProyectos;
    }

    public double getMontoProyectos() {
        return montoProyectos;
    }

    public void setMontoProyectos(double montoProyectos) {
        this.montoProyectos = montoProyectos;
    }

    public String getYearDesde() {
        return yearDesde;
    }

    public void setYearDesde(String yearDesde) {
        this.yearDesde = yearDesde;
    }

    public String getYearHasta() {
        return yearHasta;
    }

    public void setYearHasta(String yearHasta) {
        this.yearHasta = yearHasta;
    }

    public Integer getYearActual() {
        return yearActual;
    }

    public void setYearActual(Integer yearActual) {
        this.yearActual = yearActual;
    }

    public List<Proyecto> getProjectListToPopUp() {
        return projectListToPopUp;
    }

    public void setProjectListToPopUp(List<Proyecto> projectListToPopUp) {
        this.projectListToPopUp = projectListToPopUp;
    }

}
