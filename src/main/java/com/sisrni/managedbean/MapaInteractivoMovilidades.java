/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import com.sisrni.model.Beca;
import com.sisrni.model.CategoriaMovilidad;
import com.sisrni.model.Pais;
import com.sisrni.model.TipoBeca;
import com.sisrni.model.TipoMovilidad;
import com.sisrni.pojo.rpt.PojoBecasByTipo;
import com.sisrni.pojo.rpt.PojoMapaInteractivoBecas;
import com.sisrni.pojo.rpt.PojoMapaMovilidad;
import com.sisrni.pojo.rpt.PojoMovilidadMapaCategoria;
import com.sisrni.service.BecaService;
import com.sisrni.service.CategoriaMovilidadService;
import com.sisrni.service.MovilidadService;
import com.sisrni.service.PaisService;
import com.sisrni.service.TipoBecaService;
import com.sisrni.service.TipoMovilidadService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.extensions.component.gchart.model.GChartModel;
import org.primefaces.extensions.component.gchart.model.GChartModelBuilder;
import org.primefaces.extensions.component.gchart.model.GChartType;
import org.primefaces.model.chart.PieChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author Cortez
 */
@Named("mapaInteractivoMovilidades")
@Scope(WebApplicationContext.SCOPE_APPLICATION)
public class MapaInteractivoMovilidades implements Serializable {

    private static final long serialVersionUID = 1L;
    private final static Log log = LogFactory.getLog(MapaInteractivoMovilidades.class);

    private TipoMovilidad tipoMovilidadSelected;
    private List<String> paisSelected;
    private List<String> categoriaMovilidadSelected;
    private String yearDesde;
    private String yearHasta;
    Integer yearActual;

//Listados
    private List<TipoMovilidad> tipoMovilidadList;
    private List<Pais> paisList;
    private List<CategoriaMovilidad> categoriaList;

    private List<PojoMapaMovilidad> movilidadesListToChart;
    private List<PojoMovilidadMapaCategoria> movilidadesListToChartType;
    //graficos
    private GChartModel chartModel;
    private PieChartModel pieModel;
    private PieChartModel pieModelType;

    private double montoMovilidades = 0;

    private boolean noHayRegistros;

    //services
    @Autowired
    @Qualifier(value = "paisService")
    PaisService paisService;

    @Autowired
    TipoMovilidadService tipoMovilidadService;

    @Autowired
    CategoriaMovilidadService categoriaMovilidadService;

    @Autowired
    MovilidadService movilidadService;

    public MapaInteractivoMovilidades() {
    }

    @PostConstruct
    public void init() {
        try {
            inicializador();
        } catch (Exception e) {
        }
    }

    public void inicializador() {

        yearActual = getYearOfDate(new Date());
        Integer menosUno = yearActual - 1;
        yearDesde = menosUno.toString() + " ";
        yearHasta = menosUno.toString() + " ";

        paisSelected = new ArrayList<String>();
        paisList = paisService.findAll();
        llenarPaises();

        tipoMovilidadSelected = tipoMovilidadService.findById(2);
        tipoMovilidadList = tipoMovilidadService.findAll();

        categoriaMovilidadSelected = new ArrayList<String>();
        categoriaList = categoriaMovilidadService.findAll();
        llenarCategorias();

        noHayRegistros = Boolean.FALSE;
        graficar();
    }

    public void graficar() {
        Boolean badyears = false;
        if (Integer.parseInt(yearDesde.trim()) > Integer.parseInt(yearHasta.trim())) {
            badyears = true;
        }
        if (tipoMovilidadSelected.getIdTipoMovilidad() != null && !paisSelected.isEmpty() && !categoriaMovilidadSelected.isEmpty() && !badyears) {
            movilidadesListToChart = movilidadService.getBecastListToCharts(tipoMovilidadSelected.getIdTipoMovilidad(), paisSelected, categoriaMovilidadSelected, yearDesde.trim(), yearHasta.trim());
            movilidadesListToChartType = movilidadService.getBecastListToChartsCate(tipoMovilidadSelected.getIdTipoMovilidad(), paisSelected, categoriaMovilidadSelected, yearDesde.trim(), yearHasta.trim());
            montoMovilidades = calcularMonto(movilidadesListToChart);
            if (!movilidadesListToChart.isEmpty()) {
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
            if (categoriaMovilidadSelected.isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe seleccionar al menos una categoria"));
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

    public void llenarCategorias() {
        for (CategoriaMovilidad cm : categoriaList) {
            categoriaMovilidadSelected.add(cm.getIdCategoriaMovilidad() + "");
        }
    }

    private void createPieModel() {
        pieModel = new PieChartModel();
        for (PojoMapaMovilidad pj : movilidadesListToChart) {
            pieModel.set(pj.getNombrePais(), pj.getMontoMovilidades());
        }
        pieModel.setTitle("Pais y Porcejaje de Inversion");
        pieModel.setLegendPosition("w");
        pieModel.setShowDataLabels(true);
    }

    private void createPieTipo() {
        pieModelType = new PieChartModel();
        List<PojoMovilidadMapaCategoria> series = movilidadesListToChartType;
        for (PojoMovilidadMapaCategoria pj : series) {
            pieModelType.set(pj.getCategoria(), pj.getCantidad());
        }
        pieModelType.setTitle("Categoria Movilidad vs Cantidad");
        pieModelType.setLegendPosition("w");
        pieModelType.setShowDataLabels(true);
    }

    public void inicializarPieUno() {

        pieModel = new PieChartModel();
        pieModel.setTitle("Pais y Porcejaje de cooperacion");
        pieModel.setLegendPosition("w");
        pieModel.setShowDataLabels(true);

    }

    private void inicializarPieDos() {
        pieModelType = new PieChartModel();
        pieModelType.setTitle("Cantidad y Tipos de Becas");
        pieModelType.setLegendPosition("w");
        pieModelType.setShowDataLabels(true);
    }

    public void crearMapa() {
        this.chartModel = null;
        try {

            Map<String, Object> colorAxis = new HashMap<String, Object>();
            colorAxis.put("colors", new String[]{"Green", "Red"});
            GChartModelBuilder chartModelBuilder = new GChartModelBuilder();
            chartModelBuilder.setChartType(GChartType.GEO);
            chartModelBuilder.addColumns("Codigo", "Pais", "Monto($)");
            for (PojoMapaMovilidad pj : movilidadesListToChart) {
                chartModelBuilder.addRow(pj.getCodigoPais(), pj.getNombrePais(), pj.getMontoMovilidades());
            }
            chartModelBuilder.addOption("colorAxis", colorAxis);
            this.chartModel = chartModelBuilder.build();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Error al crear el mapa"));
        }
    }

    public void inicializarMapa() {
        this.chartModel = null;
        try {
            Map<String, Object> colorAxis = new HashMap<String, Object>();
            colorAxis.put("colors", new String[]{"Green", "Red"});
            GChartModelBuilder chartModelBuilder = new GChartModelBuilder();
            chartModelBuilder.setChartType(GChartType.GEO);
            chartModelBuilder.addColumns("Codigo", "Pais", "Monto($)");
            chartModelBuilder.addOption("colorAxis", colorAxis);
            this.chartModel = chartModelBuilder.build();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Error al inicializar el mapa"));
        }
    }

    private double calcularMonto(List<PojoMapaMovilidad> movilidadesListToChart) {
        double total = 0L;
        for (PojoMapaMovilidad pj : movilidadesListToChart) {
            total = total + pj.getMontoMovilidades();
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

    public GChartModel getChartModel() {
        return chartModel;
    }

    public void setChartModel(GChartModel chartModel) {
        this.chartModel = chartModel;
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

    public TipoMovilidad getTipoMovilidadSelected() {
        return tipoMovilidadSelected;
    }

    public void setTipoMovilidadSelected(TipoMovilidad tipoMovilidadSelected) {
        this.tipoMovilidadSelected = tipoMovilidadSelected;
    }

    public List<String> getCategoriaMovilidadSelected() {
        return categoriaMovilidadSelected;
    }

    public void setCategoriaMovilidadSelected(List<String> categoriaMovilidadSelected) {
        this.categoriaMovilidadSelected = categoriaMovilidadSelected;
    }

    public List<TipoMovilidad> getTipoMovilidadList() {
        return tipoMovilidadList;
    }

    public void setTipoMovilidadList(List<TipoMovilidad> tipoMovilidadList) {
        this.tipoMovilidadList = tipoMovilidadList;
    }

    public List<CategoriaMovilidad> getCategoriaList() {
        return categoriaList;
    }

    public void setCategoriaList(List<CategoriaMovilidad> categoriaList) {
        this.categoriaList = categoriaList;
    }

    public List<PojoMapaMovilidad> getMovilidadesListToChart() {
        return movilidadesListToChart;
    }

    public void setMovilidadesListToChart(List<PojoMapaMovilidad> movilidadesListToChart) {
        this.movilidadesListToChart = movilidadesListToChart;
    }

    public double getMontoMovilidades() {
        return montoMovilidades;
    }

    public void setMontoMovilidades(double montoMovilidades) {
        this.montoMovilidades = montoMovilidades;
    }

    public List<PojoMovilidadMapaCategoria> getMovilidadesListToChartType() {
        return movilidadesListToChartType;
    }

    public void setMovilidadesListToChartType(List<PojoMovilidadMapaCategoria> movilidadesListToChartType) {
        this.movilidadesListToChartType = movilidadesListToChartType;
    }

}
