package com.sisrni.managedbean;

import com.sisrni.model.Pais;
import com.sisrni.model.TipoProyecto;
import com.sisrni.service.PaisService;
import com.sisrni.service.TipoProyectoService;
import com.sisrni.service.UnidadService;
import org.primefaces.extensions.component.gchart.model.GChartModel;
import org.primefaces.extensions.component.gchart.model.GChartModelBuilder;
import org.primefaces.extensions.component.gchart.model.GChartType;
import org.primefaces.model.chart.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;

/**
 * Created by Cortez on 2/11/2016.
 */
@Named(value = "geoChartGChartMB")
@SessionScoped
public class GeoChartGChartMB implements Serializable {
    private static final long serialVersionUID = 253762400419864192L;
    @Autowired
    PaisService paisService;

    @Autowired
    TipoProyectoService tipoProyectoService;

    @Autowired
    UnidadService unidadService;

    Locale englishLocale = Locale.ENGLISH;
    private Map<String, String> countries;
    private Pais paisSelected;
    private TipoProyecto tipoProyectoSelected;
    private Date desde;
    private Date hasta;
    List<Pais> paisList;
    List<TipoProyecto> tipoProyectosList;
    private GChartModel chartModel = null;
    private BarChartModel barModel;
    private PieChartModel pieModel;
    Map<String, Object> colorAxis = new HashMap<String, Object>();

    public GChartModel getChart() {
        return chartModel;

    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    @PostConstruct
    public void generateModel() {

        paisSelected = new Pais();
        tipoProyectoSelected = new TipoProyecto();
        paisList = paisService.findAll();
        tipoProyectosList = tipoProyectoService.findAll();
        createBarModel();

        createPieModel();
        colorAxis.put("colors", new String[]{"green", "blue"});
        chartModel = new GChartModelBuilder().setChartType(GChartType.GEO)
                .addColumns("Country", "Popularity")
                .addOption("colorAxis", colorAxis)
                .build();
    }

    private void createPieModel() {
        pieModel = new PieChartModel();
        pieModel.set("2004", 540);
        pieModel.set("2005", 325);
        pieModel.set("2006", 702);
        pieModel.set("2007", 421);
        pieModel.set("2008", 421);
        pieModel.setTitle("Grafico de Pastel");
        pieModel.setLegendPosition("w");
    }

    public void onCountryChange() {
        Pais pais = paisService.findById(paisSelected.getIdPais());
        String region = String.format("%03d", pais.getIdRegion().getIdRegion());
        chartModel = new GChartModelBuilder().setChartType(GChartType.GEO)
                .addColumns("Country", "Popularity")
                .addRow(Locale.GERMANY.getDisplayCountry(englishLocale), 1200)
                .addRow(Locale.FRANCE.getDisplayCountry(englishLocale), 1800)
                .addRow("Russia", 1800)
                .addRow("Mexico", 2800)
                .addRow(Locale.ITALY.getDisplayCountry(englishLocale), 2000)
                .addRow(Locale.CHINA.getDisplayCountry(englishLocale), 2200)
                .addRow(Locale.US.getDisplayCountry(englishLocale), 2500)
                .addOption("colorAxis", colorAxis)
                .addOption("region", region)
                .build();

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "PrimeFaces Rocks."));
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

        model.addSeries(proyectos);

        return model;
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public List<Pais> getPaisList() {
        return paisList;
    }

    public void setPaisList(List<Pais> paisList) {
        this.paisList = paisList;
    }

    public Pais getPaisSelected() {
        return paisSelected;
    }

    public void setPaisSelected(Pais paisSelected) {
        this.paisSelected = paisSelected;
    }

    public Map<String, String> getCountries() {
        return countries;
    }

    public void setCountries(Map<String, String> countries) {
        this.countries = countries;
    }

    public Date getDesde() {
        return desde;
    }

    public void setDesde(Date desde) {
        this.desde = desde;
    }

    public Date getHasta() {
        return hasta;
    }

    public void setHasta(Date hasta) {
        this.hasta = hasta;
    }

    public TipoProyecto getTipoProyectoSelected() {
        return tipoProyectoSelected;
    }

    public void setTipoProyectoSelected(TipoProyecto tipoProyectoSelected) {
        this.tipoProyectoSelected = tipoProyectoSelected;
    }

    public List<TipoProyecto> getTipoProyectosList() {
        return tipoProyectosList;
    }

    public void setTipoProyectosList(List<TipoProyecto> tipoProyectosList) {
        this.tipoProyectosList = tipoProyectosList;
    }

}
