package com.sisrni.managedbean;

import com.sisrni.jasper.Reporte;
import com.sisrni.model.Facultad;
import com.sisrni.model.Movilidad;
import com.sisrni.model.Persona;
import com.sisrni.model.PersonaMovilidad;
import com.sisrni.model.Telefono;
import com.sisrni.model.TipoPersona;
import com.sisrni.model.Unidad;
import com.sisrni.pojo.rpt.RptMovilidadDetalleAnioPojo;
import com.sisrni.pojo.rpt.RptMovilidadEntranteFactBeneficiadaPojo;
import com.sisrni.pojo.rpt.RptMovilidadEntranteMesEjecucionPojo;
import com.sisrni.pojo.rpt.RptMovilidadEntrantePaisPojo;
import com.sisrni.pojo.rpt.RptMovilidadSalienteFactBeneficiadaPojo;
import com.sisrni.pojo.rpt.RptMovilidadSalienteMesPojo;
import com.sisrni.pojo.rpt.RptMovilidadSalientePaisDestinoPojo;
import com.sisrni.pojo.rpt.RptMovilidadesMesPojo;
import com.sisrni.pojo.rpt.RptMovilidadesPorFacultadEjecutadasAnioPojo;
import com.sisrni.pojo.rpt.RptMovilidadesSegunEtapaPojo;
import com.sisrni.service.MovilidadService;
import com.sisrni.service.PaisService;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author Usuario
 */
@Named(value = "reporteMovilidad")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class ReporteMovilidad {

    //Variables
    private static final String personaMovilidadTipo = "DOCENTE EN MOVILIDAD";
    private static final String personaReferenteTipo = "REFERENTE FACULTAD BENEFICIADA";
    private static final String telefonoFijoTipo = "FIJO";
     private static final String telefonoCelTipo = "CELULAR";
    private int yearActual;
    private String anioDesde;
    //private String anioHasta;

    private String reportName;

    @Autowired
    MovilidadService movilidadService;
    
    @Autowired
    PaisService paisService;

    @PostConstruct
    public void init() {
        yearActual = getYearOfDate(new Date());
    }

    //metodo para cargar datos del reoporte de Detalle
    public void llenarReporte(String format) {
        List<RptMovilidadDetalleAnioPojo> listMovilidadesPojo = new ArrayList<RptMovilidadDetalleAnioPojo>();
        Integer desdeYear = Integer.parseInt(anioDesde.trim());

        List<Movilidad> listMovilidades = movilidadService.getMovilidadesAnio(desdeYear);
        RptMovilidadDetalleAnioPojo movilidadItem;

        try {
            for (Movilidad item : listMovilidades) {
                movilidadItem = new RptMovilidadDetalleAnioPojo();
                movilidadItem.setNombrePersonaMovilidad(getPersonaEnMovilidad(item.getPersonaMovilidadList(), personaMovilidadTipo));
                movilidadItem.setDatosPersonaMovilidad(getDatosPersonaEnMovilidad(item.getPersonaMovilidadList(), personaMovilidadTipo));
                movilidadItem.setProgramaMovilidad(item.getIdProgramaMovilidad().getNombreProgramaMovilidad());
                movilidadItem.setPaisOrigen(paisService.findById(item.getIdPaisOrigen()).getNombrePais());
                movilidadItem.setPaisDestino(paisService.findById(item.getIdPaisDestino()).getNombrePais());
                movilidadItem.setFechaInicio(item.getFechaInicio());
                movilidadItem.setFechaFin(item.getFechaFin());
                movilidadItem.setTotalDias(obtenerTotalDias(item.getFechaFin(), item.getFechaInicio()));
                movilidadItem.setViaticos(item.getViaticos().floatValue());
                movilidadItem.setPagoCurso(item.getPagoDeCurso().floatValue());
                movilidadItem.setTotalViaticosCurso(item.getViaticos().floatValue()+item.getPagoDeCurso().floatValue());
                movilidadItem.setBoletoAereo(item.getVoletoAereo().floatValue());
                movilidadItem.setTotal(item.getViaticos().floatValue()+item.getPagoDeCurso().floatValue()+item.getVoletoAereo().floatValue());
                movilidadItem.setFacultadUnidad(obtenerFacultadUnidadOtroBenef(item.getFacultadList(),item.getUnidadList(),item.getOtrosBeneficiados()));
                movilidadItem.setNombrePersonaReferente(getPersonaReferente(item.getPersonaMovilidadList(),personaReferenteTipo));
                movilidadItem.setDatosPersonaReferente(getDatosPersonaEnMovilidad(item.getPersonaMovilidadList(),personaReferenteTipo));
                movilidadItem.setFechaEntregaInforme(item.getFechaEntregaMined());
                movilidadItem.setEtapa(item.getIdEtapaMovilidad().getNombreEtapa());
                movilidadItem.setEntregaInforme(obtenerEntregaInforme(item));
                movilidadItem.setTipoMovilidad(item.getIdTipoMovilidad().getNombreTipoMovilidad());
                 
               listMovilidadesPojo.add(movilidadItem);
            }
            printDetalleMovilidades(listMovilidadesPojo, format);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getPersonaEnMovilidad(List<PersonaMovilidad> listPersona, String tipoPersona) {
      String nombreCompleto ="";
      for(PersonaMovilidad persona :listPersona){
          if(persona.getIdTipoPersona().getNombreTipoPersona().equals(tipoPersona)){
              nombreCompleto = persona.getPersona().getNombrePersona()+" "+persona.getPersona().getApellidoPersona();
          }
      }
      return nombreCompleto;
    }
    
    private String getPersonaReferente(List<PersonaMovilidad> listPersona, String tipoPersona) {
      String nombreCompleto ="";
      for(PersonaMovilidad persona :listPersona){
          if(persona.getIdTipoPersona().getNombreTipoPersona().equals(tipoPersona)){
              nombreCompleto = persona.getPersona().getNombrePersona()+" "+persona.getPersona().getApellidoPersona();
          }
      }
      return nombreCompleto;
    }
    
    private String getDatosPersonaEnMovilidad(List<PersonaMovilidad> listPersona, String tipoPersona){
        String datos ="";
        String telFijo = "";
        String telCel ="";
        String email = "";
        for(PersonaMovilidad persona : listPersona){
            if(persona.getIdTipoPersona().getNombreTipoPersona().equals(tipoPersona)){
                telFijo = obtenerTelefono(persona.getPersona().getTelefonoList(),telefonoFijoTipo);
                telCel = obtenerTelefono(persona.getPersona().getTelefonoList(),telefonoCelTipo);
                email = persona.getPersona().getEmailPersona();
            }
        }
        
        if(!telFijo.equals("")){
            datos = datos + "Tel: "+telFijo;
        }
        if(!telCel.equals("")){
            datos = datos + " Cel: "+ telCel;
        }
        if(!email.equals("")){
            datos = datos + " Email: "+email;
        }
        
        return datos;
            
    }  
    
    private int obtenerTotalDias(Date fInicio,Date fFin){
        int dias;
        if(fInicio !=null && fFin != null){
        dias = (int) ((fInicio.getTime() - fFin.getTime())/86400000);
        }else{
            dias = 0;
        }
        return dias;
    }
    
    private String obtenerTelefono(List<Telefono> listTelefono,String tipoTelefono){
        String tel ="";
        for(Telefono telefono : listTelefono){
            if(telefono.getIdTipoTelefono().getNombre().equals(tipoTelefono)){
                tel = telefono.getNumeroTelefono();
            }
        }
        return tel;
        
    }
    
    public String obtenerFacultadUnidadOtroBenef(List<Facultad> listFacultad, List<Unidad> listUnidad,String otroBenef){
        String facultadUnidadOtro ="";
        for(Facultad facultad:listFacultad){
            if(facultad.getNombreFacultad() != null && !facultad.getNombreFacultad().equals(""))
                facultadUnidadOtro = facultadUnidadOtro + facultad.getNombreFacultad();
        }
        
        for(Unidad unidad : listUnidad){
            if(unidad.getNombreUnidad() != null && !unidad.getNombreUnidad().equals("")){
                facultadUnidadOtro = facultadUnidadOtro + unidad.getNombreUnidad();
            }
        }
        
        if(otroBenef != null && !otroBenef.equals("")){
            facultadUnidadOtro = facultadUnidadOtro + " y "+otroBenef;
        }
        
        return facultadUnidadOtro;
    }
    
    public String obtenerEntregaInforme(Movilidad movilidad){
        String respuesta;
        if(movilidad.getEntregaDeInforme() == Boolean.TRUE){
            respuesta = "SI";
        }else{
            respuesta = "NO";
        }
        return respuesta;
    }

    //Generar reporte de Movilidades segun Etapa y tipo
    public void printTipoEtapa(String formato) {
        try {
            Integer desdeYear = Integer.parseInt(anioDesde.trim());
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            HttpServletRequest request = (HttpServletRequest) context.getRequest();
            HttpServletResponse response = (HttpServletResponse) context.getResponse();

            Reporte reporte = new Reporte("movilidadrpt", "rpt_movilidades", request);
            List<RptMovilidadesSegunEtapaPojo> dataMovilidadesEtapaTipo = movilidadService.getCantidadMovilidadesSegunEtapa(desdeYear);

            reporte.setDataSource(new JRBeanCollectionDataSource(dataMovilidadesEtapaTipo));
            reporte.addParameter("uesImageUrl", getBaseDir("ues.png"));
            reporte.addParameter("srniImageUrl", getBaseDir("srni.jpg"));
            reporte.addParameter("desde", anioDesde.trim());
            //reporte.addParameter("ItemDataSource", new JRBeanCollectionDataSource(new HashSet<BecasGestionadasPojo>(dataBecasGestionadasReportes)));
            if (!formato.equalsIgnoreCase("pdf")) {
                reporte.setTipoMime(formato);
            }
            reporte.setReportInSession(request, response);
            reportName = reporte.getNombreLogico();
            RequestContext.getCurrentInstance().addCallbackParam("reportName", reportName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Generar reporte de Movilidades ejecutadas por facultad beneficiadas
    public void printMovFacultad(String formato) {
        try {
            Integer desdeYear = Integer.parseInt(anioDesde.trim());
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            HttpServletRequest request = (HttpServletRequest) context.getRequest();
            HttpServletResponse response = (HttpServletResponse) context.getResponse();

            Reporte reporte = new Reporte("movilidadrpt", "rpt_movilidades_facultad_beneficiada", request);
            List<RptMovilidadesPorFacultadEjecutadasAnioPojo> dataMovilidadesfacultad = movilidadService.getCantidadMovilidadesFacultad(desdeYear);

            reporte.setDataSource(new JRBeanCollectionDataSource(dataMovilidadesfacultad));
            reporte.addParameter("uesImageUrl", getBaseDir("ues.png"));
            reporte.addParameter("srniImageUrl", getBaseDir("srni.jpg"));
            reporte.addParameter("desde", anioDesde.trim());
            //reporte.addParameter("ItemDataSource", new JRBeanCollectionDataSource(new HashSet<BecasGestionadasPojo>(dataBecasGestionadasReportes)));
            if (!formato.equalsIgnoreCase("pdf")) {
                reporte.setTipoMime(formato);
            }
            reporte.setReportInSession(request, response);
            reportName = reporte.getNombreLogico();
            RequestContext.getCurrentInstance().addCallbackParam("reportName", reportName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Generar reportes de movilidades ejecutadas por mes
    public void printMovilidadesMes(String formato) {
        try {
            Integer desdeYear = Integer.parseInt(anioDesde.trim());
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            HttpServletRequest request = (HttpServletRequest) context.getRequest();
            HttpServletResponse response = (HttpServletResponse) context.getResponse();

            Reporte reporte = new Reporte("movilidadrpt", "rpt_movilidades_mes", request);
            List<RptMovilidadesMesPojo> dataMovilidadesMes = movilidadService.getMovilidadesMes(desdeYear);

            reporte.setDataSource(new JRBeanCollectionDataSource(dataMovilidadesMes));
            reporte.addParameter("uesImageUrl", getBaseDir("ues.png"));
            reporte.addParameter("srniImageUrl", getBaseDir("srni.jpg"));
            reporte.addParameter("desde", anioDesde.trim());
            //reporte.addParameter("ItemDataSource", new JRBeanCollectionDataSource(new HashSet<BecasGestionadasPojo>(dataBecasGestionadasReportes)));
            if (!formato.equalsIgnoreCase("pdf")) {
                reporte.setTipoMime(formato);
            }
            reporte.setReportInSession(request, response);
            reportName = reporte.getNombreLogico();
            RequestContext.getCurrentInstance().addCallbackParam("reportName", reportName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Generar reporte de movilidades salientes por pais destino
    public void printMovilidadesSalientesPaisDestino(String formato) {
        try {
            Integer desdeYear = Integer.parseInt(anioDesde.trim());
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            HttpServletRequest request = (HttpServletRequest) context.getRequest();
            HttpServletResponse response = (HttpServletResponse) context.getResponse();

            Reporte reporte = new Reporte("movilidadrpt", "rpt_movilidad_saliente_pais_destino", request);
            List<RptMovilidadSalientePaisDestinoPojo> dataMovilidadesSalientesPais = movilidadService.getMovilidadesSalientesPaisDestino(desdeYear);

            reporte.setDataSource(new JRBeanCollectionDataSource(dataMovilidadesSalientesPais));
            reporte.addParameter("uesImageUrl", getBaseDir("ues.png"));
            reporte.addParameter("srniImageUrl", getBaseDir("srni.jpg"));
            reporte.addParameter("desde", anioDesde.trim());
            //reporte.addParameter("ItemDataSource", new JRBeanCollectionDataSource(new HashSet<BecasGestionadasPojo>(dataBecasGestionadasReportes)));
            if (!formato.equalsIgnoreCase("pdf")) {
                reporte.setTipoMime(formato);
            }
            reporte.setReportInSession(request, response);
            reportName = reporte.getNombreLogico();
            RequestContext.getCurrentInstance().addCallbackParam("reportName", reportName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Generar reporte movilidades salientes por mes
    public void printMovilidadesSalientesMes(String formato) {
        try {
            Integer desdeYear = Integer.parseInt(anioDesde.trim());
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            HttpServletRequest request = (HttpServletRequest) context.getRequest();
            HttpServletResponse response = (HttpServletResponse) context.getResponse();

            Reporte reporte = new Reporte("movilidadrpt", "rpt_movilidad_saliente_mes", request);
            List<RptMovilidadSalienteMesPojo> dataMovilidadesSalientesMes = movilidadService.getMovilidadesSalientesMes(desdeYear);

            reporte.setDataSource(new JRBeanCollectionDataSource(dataMovilidadesSalientesMes));
            reporte.addParameter("uesImageUrl", getBaseDir("ues.png"));
            reporte.addParameter("srniImageUrl", getBaseDir("srni.jpg"));
            reporte.addParameter("desde", anioDesde.trim());
            //reporte.addParameter("ItemDataSource", new JRBeanCollectionDataSource(new HashSet<BecasGestionadasPojo>(dataBecasGestionadasReportes)));
            if (!formato.equalsIgnoreCase("pdf")) {
                reporte.setTipoMime(formato);
            }
            reporte.setReportInSession(request, response);
            reportName = reporte.getNombreLogico();
            RequestContext.getCurrentInstance().addCallbackParam("reportName", reportName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Generar reporte movilidades SAlientes segun facultad beneficiada
    public void printMovSalientesFactBeneficiada(String formato) {
        try {
            Integer desdeYear = Integer.parseInt(anioDesde.trim());
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            HttpServletRequest request = (HttpServletRequest) context.getRequest();
            HttpServletResponse response = (HttpServletResponse) context.getResponse();

            Reporte reporte = new Reporte("movilidadrpt", "rpt_movilidad_saliente_factultad_benef", request);
            List<RptMovilidadSalienteFactBeneficiadaPojo> dataMoviSalientesFactBenef = movilidadService.getMoviSalientesFactBenef(desdeYear);

            reporte.setDataSource(new JRBeanCollectionDataSource(dataMoviSalientesFactBenef));
            reporte.addParameter("uesImageUrl", getBaseDir("ues.png"));
            reporte.addParameter("srniImageUrl", getBaseDir("srni.jpg"));
            reporte.addParameter("desde", anioDesde.trim());
            //reporte.addParameter("ItemDataSource", new JRBeanCollectionDataSource(new HashSet<BecasGestionadasPojo>(dataBecasGestionadasReportes)));
            if (!formato.equalsIgnoreCase("pdf")) {
                reporte.setTipoMime(formato);
            }
            reporte.setReportInSession(request, response);
            reportName = reporte.getNombreLogico();
            RequestContext.getCurrentInstance().addCallbackParam("reportName", reportName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Generar reporte movilidades Entrantes por pais de origen
    public void printMovilEntrantesPaisOrigen(String formato) {
        try {
            Integer desdeYear = Integer.parseInt(anioDesde.trim());
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            HttpServletRequest request = (HttpServletRequest) context.getRequest();
            HttpServletResponse response = (HttpServletResponse) context.getResponse();

            Reporte reporte = new Reporte("movilidadrpt", "rpt_movilidad_entrante_pais_origen", request);
            List<RptMovilidadEntrantePaisPojo> dataMoviEntrantePaisOrigen = movilidadService.getMovilEntrantesPaisOrigen(desdeYear);

            reporte.setDataSource(new JRBeanCollectionDataSource(dataMoviEntrantePaisOrigen));
            reporte.addParameter("uesImageUrl", getBaseDir("ues.png"));
            reporte.addParameter("srniImageUrl", getBaseDir("srni.jpg"));
            reporte.addParameter("desde", anioDesde.trim());
            //reporte.addParameter("ItemDataSource", new JRBeanCollectionDataSource(new HashSet<BecasGestionadasPojo>(dataBecasGestionadasReportes)));
            if (!formato.equalsIgnoreCase("pdf")) {
                reporte.setTipoMime(formato);
            }
            reporte.setReportInSession(request, response);
            reportName = reporte.getNombreLogico();
            RequestContext.getCurrentInstance().addCallbackParam("reportName", reportName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Generar reporte de movilidades entrantes segun mes
    public void printMovilidadEntranteSegunMes(String formato) {
        try {
            Integer desdeYear = Integer.parseInt(anioDesde.trim());
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            HttpServletRequest request = (HttpServletRequest) context.getRequest();
            HttpServletResponse response = (HttpServletResponse) context.getResponse();

            Reporte reporte = new Reporte("movilidadrpt", "rpt_movilidades_entrantes_mes", request);
            List<RptMovilidadEntranteMesEjecucionPojo> dataMoviEntranteMes = movilidadService.getMovilidadEntranteMes(desdeYear);

            reporte.setDataSource(new JRBeanCollectionDataSource(dataMoviEntranteMes));
            reporte.addParameter("uesImageUrl", getBaseDir("ues.png"));
            reporte.addParameter("srniImageUrl", getBaseDir("srni.jpg"));
            reporte.addParameter("desde", anioDesde.trim());
            //reporte.addParameter("ItemDataSource", new JRBeanCollectionDataSource(new HashSet<BecasGestionadasPojo>(dataBecasGestionadasReportes)));
            if (!formato.equalsIgnoreCase("pdf")) {
                reporte.setTipoMime(formato);
            }
            reporte.setReportInSession(request, response);
            reportName = reporte.getNombreLogico();
            RequestContext.getCurrentInstance().addCallbackParam("reportName", reportName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Generar reporte de movilidades entrantes segun facultad beneficiada
    public void printMovilidadEntranteFacultadBenef(String formato) {
        try {
            Integer desdeYear = Integer.parseInt(anioDesde.trim());
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            HttpServletRequest request = (HttpServletRequest) context.getRequest();
            HttpServletResponse response = (HttpServletResponse) context.getResponse();

            Reporte reporte = new Reporte("movilidadrpt", "rpt_movilidades_entrantes_facultad_benef", request);
            List<RptMovilidadEntranteFactBeneficiadaPojo> dataMovilidadEntranteFactBenef = movilidadService.getMovilidadEntranteFactBenef(desdeYear);

            reporte.setDataSource(new JRBeanCollectionDataSource(dataMovilidadEntranteFactBenef));
            reporte.addParameter("uesImageUrl", getBaseDir("ues.png"));
            reporte.addParameter("srniImageUrl", getBaseDir("srni.jpg"));
            reporte.addParameter("desde", anioDesde.trim());
            //reporte.addParameter("ItemDataSource", new JRBeanCollectionDataSource(new HashSet<BecasGestionadasPojo>(dataBecasGestionadasReportes)));
            if (!formato.equalsIgnoreCase("pdf")) {
                reporte.setTipoMime(formato);
            }
            reporte.setReportInSession(request, response);
            reportName = reporte.getNombreLogico();
            RequestContext.getCurrentInstance().addCallbackParam("reportName", reportName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //Generar reporte de detalle de movilidades
    public void printDetalleMovilidades(List<RptMovilidadDetalleAnioPojo> listMovilidades, String formato) {
        try {
            //Integer desdeYear = Integer.parseInt(anioDesde.trim());
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            HttpServletRequest request = (HttpServletRequest) context.getRequest();
            HttpServletResponse response = (HttpServletResponse) context.getResponse();

            Reporte reporte = new Reporte("movilidadrpt", "rpt_movilidad_detalle", request);
            //List<RptMovilidadEntranteFactBeneficiadaPojo> dataMovilidadEntranteFactBenef = movilidadService.getMovilidadEntranteFactBenef(desdeYear);

            reporte.setDataSource(new JRBeanCollectionDataSource(new HashSet<RptMovilidadDetalleAnioPojo>(listMovilidades)));
            reporte.addParameter("uesImageUrl", getBaseDir("ues.png"));
            reporte.addParameter("srniImageUrl", getBaseDir("srni.jpg"));
            reporte.addParameter("Desde", anioDesde.trim());
            //reporte.addParameter("ItemDataSource", new JRBeanCollectionDataSource(new HashSet<BecasGestionadasPojo>(dataBecasGestionadasReportes)));
            if (!formato.equalsIgnoreCase("pdf")) {
                reporte.setTipoMime(formato);
            }
            reporte.setReportInSession(request, response);
            reportName = reporte.getNombreLogico();
            RequestContext.getCurrentInstance().addCallbackParam("reportName", reportName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Integer getYearOfDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Integer year = cal.get(Calendar.YEAR);
        return year;
    }

    public String getBaseDir(String imagen) {
        String baseDir = "/img/" + imagen;
        try {
            return FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getResource(baseDir)
                    .getPath();
        } catch (MalformedURLException mue) {

            return null;
        }
    }

    //Getter y Setter
    public int getYearActual() {
        return yearActual;
    }

    public void setYearActual(int yearActual) {
        this.yearActual = yearActual;
    }

 //   public String getAnioHasta() {
    //       return anioHasta;
    //   }
 //   public void setAnioHasta(String anioHasta) {
    //       this.anioHasta = anioHasta;
    //   }
    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getAnioDesde() {
        return anioDesde;
    }

    public void setAnioDesde(String anioDesde) {
        this.anioDesde = anioDesde;
    }

}
