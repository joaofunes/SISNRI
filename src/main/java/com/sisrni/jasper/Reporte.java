package com.sisrni.jasper;

//import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import com.sisrni.config.SpringApplicationContextProvider;
import com.sisrni.config.SpringApplicationContextServiceProvider;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.apache.catalina.ssi.ByteArrayServletOutputStream;
import static org.apache.commons.lang.SystemUtils.FILE_SEPARATOR;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import com.sisrni.jasper.service.ExportingService;
import com.sisrni.jasper.service.ReportingService;

public class Reporte { // OJO: NO ES SERIALIZABLE, NO PUEDE POR SU CONTENIDO

    private final static Log log = LogFactory.getLog(Reporte.class);
    public final static String SESSION_REPORTS_PREFIX = "REPORT_";
    public final static String DYNAMIC_JASPER = "dynamicJasper";
    public final static String CURRENT_REPORT_SESSION_VAR = "CURRENT_REPORT";
    public final static String CURRENT_REPORT_NAME_SESSION_VAR = "CURRENT_REPORT_NAME";
    public final static String REPORT_PARAMETER_PREFIX = "jpr_rpt";
    public final static String REPORT_DATA_PARAMETER_PREFIX = "jpr_sql";
    public final static String REPORT_DATA_XML_PARAMETER_PREFIX = "jpr_xml";
    public final static Integer REPORT_MAX_TRIES = 10;
    public final static int MAX_NUM_ROWS_PER_REPORT = 5000;
    public final static String USUARIO_PARAM = "USUARIO";
    //data
    protected String modulo;
    protected String nombre;
    protected String nombreLogico;
    protected String nombreArchivo;
    protected String rutaImagenes;
    protected String rutaReportes;
    protected String rutaSubreporte;
    protected String tipoMime = "pdf";
    protected String extension = ".jasper";
    protected boolean descargando = false;
    protected Map parametros = new HashMap();
    protected int numeroMaximoRegistros;
    // ssc
    protected List<String> nombresArchivos;
    protected List<JasperPrint> jasperPrints;

    // dynamic
    protected List listadoDynamic;
//    protected FastReportBuilder fastReportBuilder;
    protected JRDataSource dataSource;

    protected static Map<String, String> tipoMimeTypeMap = new HashMap<String, String>();
    private JasperPrint jasperPrint;
    // servlet output stream
    protected ByteArrayServletOutputStream byteArrayOutputStream;

    public Reporte() {
    }

    public Reporte(String modulo, String nombre) {
        this();
        setModulo(modulo);
        setNombre(nombre);
        setNombreLogico(getRandomName(nombre));
        initPath();
        validateName();
    }

    public Reporte(String modulo, String nombre, HttpServletRequest request) {
        this(modulo, nombre);
        initPageParameters(request);
    }

    public Reporte(/*FastReportBuilder drb,*/ List list, Map params, String nombre, String tipoMime, boolean descargando) {
        this();
        setDescargando(descargando);
        setTipoMime(tipoMime);
        setNombre(nombre);
        setModulo(DYNAMIC_JASPER);
        setNombreLogico(getRandomName(nombre));
        setParametros(params);
//        setFastReportBuilder(drb);
        setListadoDynamic(list);

    }

    // ssc
    public Reporte(String modulo, List<String> nombres, HttpServletRequest request) {
        this();
        setModulo(modulo);
        if (nombres != null && !nombres.isEmpty()) {
            setNombresArchivos(new ArrayList<String>());
            for (String fname : nombres) {
                String name = validateName(fname);
                name = initPath(name);
                getNombresArchivos().add(name);
            }
            setNombreLogico(getRandomName(nombres.get(0)));
        }
        initPageParameters(request);
    }
    //...

    public final void initPath() {
        nombreArchivo = "/" + modulo + "/" + nombre + extension;
    }

    public final String initPath(String nombre) {
        return "/" + modulo + "/" + nombre + extension;
    }

    private void validateTipoMime() {
        if (tipoMime == null || (tipoMime != null && "".equals(tipoMime.trim()))) {
            tipoMime = "pdf";
        }
        tipoMime = tipoMime.replaceAll("\\.", "");
    }

    public String getNombreDeArchivoDescargable() {
        validateTipoMime();
        return getNombreLogico() + "." + tipoMime;
    }

    public String getContetTypeArchivoDescargable() {
        validateTipoMime();
        return getMimeType(tipoMime);
    }

    public final void validateName() {
        if (nombre == null || "".equals(nombre.trim())) {
            nombre = getRandomName("reporte");
        }
    }

    public final String validateName(String nombre) {
        if (nombre == null || "".equals(nombre.trim())) {
            nombre = getRandomName("reporte");
        }
        return nombre;
    }

    public void validateParams() {
        if (parametros == null) {
            parametros = new HashMap<String, Object>();
        }
    }

    public void addParameter(String key, Object parameter) {
        validateParams();
        getParametros().put(key, parameter);
    }

    public void addParameters(Map<String, Object> parameters) {
        validateParams();
        getParametros().putAll(parameters);
    }

    public Object getParameter(String key) {
        return getParametros().get(key);
    }

    public String getModulo() {
        return modulo;
    }

    public String getNombre() {
        return nombre;
    }

    public final void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public final void setNombre(String nombre) {
        this.nombre = nombre;
        addParameter("JASPER_REPORT_TITLE", nombre);
    }

    public String getRutaImagenes() {
        return rutaImagenes;
    }

    public void setRutaImagenes(String rutaImagenes) {
        this.rutaImagenes = rutaImagenes;
        addParameter("IMAGE_PATH", rutaImagenes);
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getRutaReportes() {
        return rutaReportes;
    }

    public void setRutaReportes(String rutaReportes) {
        this.rutaReportes = rutaReportes;
        addParameter("JASPER_REPORT_PATH", rutaReportes);
    }

    public String getRutaSubreporte() {
        return rutaSubreporte;
    }

    public void setRutaSubreporte(String rutaSubreporte) {
        this.rutaSubreporte = rutaSubreporte;
        addParameter("SUBREPORT_DIR", rutaSubreporte);
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getTipoMime() {
        return tipoMime;
    }

    public final void setTipoMime(String tipoMime) {
        this.tipoMime = tipoMime;
        validateTipoMime();
    }

    public boolean isDescargando() {
        return descargando;
    }

    public final void setDescargando(boolean descargando) {
        this.descargando = descargando;
    }

    public Map<String, Object> getParametros() {
        return parametros;
    }

    public final void setParametros(Map<String, Object> parametros) {
        this.parametros = parametros;
    }

    public String getNombreLogico() {
        return nombreLogico;
    }

    public final void setNombreLogico(String nombreLogico) {
        this.nombreLogico = nombreLogico;
    }
//
//    public FastReportBuilder getFastReportBuilder() {
//        return fastReportBuilder;
//    }
//
//    public final void setFastReportBuilder(FastReportBuilder fastReportBuilder) {
//        this.fastReportBuilder = fastReportBuilder;
//    }

    public List getListadoDynamic() {
        return listadoDynamic;
    }

    public final void setListadoDynamic(List listadoDynamic) {
        this.listadoDynamic = listadoDynamic;
    }

    public int getNumeroMaximoRegistros() {
        return numeroMaximoRegistros;
    }

    public void setNumeroMaximoRegistros(int numeroMaximoRegistros) {
        numeroMaximoRegistros = numeroMaximoRegistros > 0 ? numeroMaximoRegistros : Reporte.MAX_NUM_ROWS_PER_REPORT;
        this.numeroMaximoRegistros = numeroMaximoRegistros;
        addParameter("REPORT_MAX_COUNT", numeroMaximoRegistros);

    }

    public JRDataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(JRDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public String toString() {
        return "Reporte{" + "modulo=" + modulo + ", nombre=" + nombre + ", nombreArchivo=" + nombreArchivo + ", rutaImagenes=" + rutaImagenes + ", rutaReportes=" + rutaReportes + ", rutaSubreporte=" + rutaSubreporte + ", tipoMime=" + tipoMime + ", extension=" + extension + ", descargando=" + descargando + ", parametros=" + parametros + '}';
    }

    public void executeBeforeReport(HttpServletRequest request, HttpServletResponse response) {
        // INFO: implement executeBeforeReport method
    }

    public void executeAfterReport(HttpServletRequest request, HttpServletResponse response) {
        // INFO: implement executeAfterReport method
    }

    public void executeReport(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        ServletOutputStream out = response.getOutputStream();
        try {
            ByteArrayServletOutputStream byteOut = getByteArrayOutputStream();
            byte[] buf = byteOut.toByteArray();
            response.setContentType(getContetTypeArchivoDescargable());
            response.setHeader("Content-Type", getContetTypeArchivoDescargable() + "; charset=iso-8859-1");
            response.setHeader("Cache-Control", "no-store");
            response.setHeader("Pragma", "no-store");
            if (isDescargando() || fileMustBeDownloaded()) {
                response.setHeader("Content-Disposition", "attachment; filename=\"" + getNombreDeArchivoDescargable() + "\"");
            }
            response.setContentLength(buf.length);
            ServletOutputStream servletOut = response.getOutputStream();
            servletOut.write(buf);
            out.flush();
        } catch (Exception e) {
            log.error(e);
        } finally {
            try {
                out.close();
            } catch (Exception e) {
            }
        }

    }

    public final void export(HttpServletRequest request, HttpServletResponse response, ByteArrayServletOutputStream out) throws JRException, Exception {
        ExportingService exportingService = new ExportingService();
        if ("xml".equals(getTipoMime())) {
            JasperExportManager.exportReportToXmlStream(jasperPrint, out);
        } else if ("csv".equals(getTipoMime())) {
            exportingService.generateCSVOutput(jasperPrint, out);
        } else if ("xls".equals(getTipoMime()) || "xlsx".equals(getTipoMime())) {
            exportingService.generateXLSOutput(jasperPrint, out, getNombreDeArchivoDescargable());
        } else if ("rtf".equals(getTipoMime()) || "doc".equals(getTipoMime()) || "docx".equals(getTipoMime())) {
            exportingService.generateRTFOutput(jasperPrint, out, getNombreDeArchivoDescargable());
        } else if ("html".equals(getTipoMime())) {
            setTipoMime("html");
            exportingService.generateHTMLOutput(request, jasperPrint, out);
        } else {
            if (jasperPrints != null && !jasperPrints.isEmpty()) {
                exportingService.generatePDFOutput(jasperPrints, out, getNombreDeArchivoDescargable(), isDescargando());
            } else {
                exportingService.generatePDFOutput(jasperPrint, out, getNombreDeArchivoDescargable(), isDescargando());
            }
        }
    }

    public final void initPageParameters(HttpServletRequest request) {
        if (request != null) {
            addParameters(getPageParametersAsMap(request, REPORT_PARAMETER_PREFIX));
            addParameters(getPageParametersAsMap(request, REPORT_DATA_PARAMETER_PREFIX));
            addParameters(getParametersAsMapXml(request, REPORT_DATA_XML_PARAMETER_PREFIX));
        }
    }

    private Map<String, Object> getPageParametersAsMap(HttpServletRequest request, String prefix) {
        Map<String, Object> params = new HashMap<String, Object>(0);
        Enumeration enumerator = request.getParameterNames();

        while (enumerator.hasMoreElements()) {
            String element = enumerator.nextElement().toString();
            if (element.startsWith(prefix)) {
                String key = element.replaceAll(REPORT_PARAMETER_PREFIX, "").replaceAll(REPORT_DATA_PARAMETER_PREFIX, "");
                String val = request.getParameter(element).replaceAll(REPORT_PARAMETER_PREFIX, "").replaceAll(REPORT_DATA_PARAMETER_PREFIX, "");
                params.put(key, val);
            }
        }
        return params;
    }

    private Map<String, Object> getParametersAsMapXml(HttpServletRequest request, String prefix) {
        Map<String, Object> params = new HashMap<String, Object>(0);
        Enumeration<?> enumerator = request.getParameterNames();

        while (enumerator.hasMoreElements()) {
            String element = enumerator.nextElement().toString();
            if (element.startsWith(prefix)) {
                String key = element.replaceAll(REPORT_DATA_XML_PARAMETER_PREFIX, "");
                String val = request.getParameter(element).replaceAll(REPORT_DATA_XML_PARAMETER_PREFIX, "");
                params.put(key, val);
            }
        }
        return params;
    }

    public void setReportInSession(final HttpServletRequest request, final HttpServletResponse response) {
        try {
            String contextPath = request.getSession().getServletContext().getRealPath("");
            HttpSession session = request.getSession();

            setByteArrayOutputStream(new ByteArrayServletOutputStream());

            executeBeforeReport(request, response);
            if (getRutaImagenes() == null || (getRutaImagenes() != null && "".equals(getRutaImagenes()))) {
                setRutaImagenes(contextPath + FILE_SEPARATOR + "images");
            }
            if (getRutaReportes() == null || (getRutaReportes() != null && "".equals(getRutaReportes()))) {
                setRutaReportes(contextPath + FILE_SEPARATOR + "WEB-INF" + FILE_SEPARATOR + "reports");
            }
            if (getRutaSubreporte() == null || (getRutaSubreporte() != null && "".equals(getRutaSubreporte()))) {
                setRutaSubreporte(getRutaReportes() + FILE_SEPARATOR + getModulo() + FILE_SEPARATOR);
            }
            if (getNumeroMaximoRegistros() <= 0) {
                setNumeroMaximoRegistros(MAX_NUM_ROWS_PER_REPORT);
            }
            log.info("trying to execute report:" + getNombreArchivo() + "-" + this);
            generateReportInTransactionWithoutResult();
            export(request, response, getByteArrayOutputStream());
            ReportData reportData = new ReportData();
            reportData.setFormat(getTipoMime());
            reportData.setDownloading(isDescargando() || fileMustBeDownloaded());
            reportData.setNombreLogico(getNombreLogico());
            reportData.setBytes(getByteArrayOutputStream().toByteArray());
            setReportInSession(session, reportData);
        } catch (Exception ex) {
            log.error(ex);
        }
    }

    protected Reporte generateReportInTransactionWithoutResult() {
        return generateReportInTransactionWithoutResult(this);
    }

    protected Reporte generateReportInTransactionWithoutResult(final Reporte reporte) {
        final ApplicationContext context = SpringApplicationContextProvider.getApplicationContext();
        SpringApplicationContextServiceProvider.doInTransactionWithoutResult(context, new TransactionCallbackWithoutResult() {
            @Override
            public void doInTransactionWithoutResult(TransactionStatus status) {
                ReportingService reportingService = (ReportingService) context.getBean("reportingService");
                if (reportingService != null) {
                    Reporte result = reportingService.executeReport(reporte);
                }
            }
        });
        return reporte;
    }

    public void setReportInSession(HttpSession session, ReportData reportData) {
        setReportInSession(session, reportData, getNombreLogico());
    }

    public void setReportInSession(HttpSession session, byte[] out) {
        setReportInSession(session, out, getNombreLogico());
    }

    public static ReportData getReportInSession(HttpSession session) {
        return getReportInSession(session, (String) session.getAttribute(CURRENT_REPORT_NAME_SESSION_VAR), 0);
    }

    public static ReportData getReportInSession(HttpSession session, String nombreReporte) {
        return getReportInSession(session, nombreReporte, 0);
    }

    public static ReportData getReportInSession(HttpSession session, String nombreLogico, Integer intentos) {
        ReportData ret;
        if (nombreLogico == null || "".equals(nombreLogico.trim())) {
            if ((intentos++) >= REPORT_MAX_TRIES) {
                return null;
            }
            ret = getReportInSession(session, (String) session.getAttribute(CURRENT_REPORT_NAME_SESSION_VAR), intentos);
        } else {
            ret = getSessionReport(session, nombreLogico);
        }
        return ret;
    }

    public static void setReportInSession(HttpSession session, ReportData reportData, String nombreReporte) {
        session.setAttribute(CURRENT_REPORT_NAME_SESSION_VAR, nombreReporte);
        addSessionReport(session, reportData);
    }

    public static void setReportInSession(HttpSession session, byte[] out, String nombreReporte) {
        session.setAttribute(CURRENT_REPORT_NAME_SESSION_VAR, nombreReporte);
        addSessionReport(session, nombreReporte, out);
    }

    public static String getRandomName(String base) {
        return base + String.valueOf((Math.random() * Math.random() * 1024L)).trim().replaceAll("\\.", "9");
    }

    public static String getMimeType(String tipoMime) {
        String ret = "text/plain";
        try {
            if (tipoMimeTypeMap == null) {
                tipoMimeTypeMap = new HashMap<String, String>();
            }
            if (tipoMimeTypeMap.isEmpty()) {
                tipoMimeTypeMap.put("pdf", "application/pdf");
//                tipoMimeTypeMap.put("docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
                tipoMimeTypeMap.put("docx", "application/msword");
                tipoMimeTypeMap.put("doc", "application/msword");
                tipoMimeTypeMap.put("rtf", "application/rtf");
                tipoMimeTypeMap.put("xls", "application/vnd.ms-excel");
                tipoMimeTypeMap.put("xlsx", "application/vnd.ms-excel");
                tipoMimeTypeMap.put("csv", "text/csv");
                tipoMimeTypeMap.put("bmp", "image/bmp");
                tipoMimeTypeMap.put("bm", "image/bmp");
                tipoMimeTypeMap.put("jpg", "image/jpeg");
                tipoMimeTypeMap.put("jpeg", "image/pjpeg");
                tipoMimeTypeMap.put("tif", "image/tiff");
                tipoMimeTypeMap.put("tiff", "image/tiff");
                tipoMimeTypeMap.put("gif", "image/gif");
                tipoMimeTypeMap.put("txt", "text/plain");
                tipoMimeTypeMap.put("html", "text/html");
                tipoMimeTypeMap.put("htm", "text/html");
                tipoMimeTypeMap.put("shtml", "text/html");
                tipoMimeTypeMap.put("shtm", "text/html");
                tipoMimeTypeMap.put("htmlx", "text/html");
                tipoMimeTypeMap.put("css", "text/css");
                /*
                 Extensi&oacute;n	Mime/Tipo
                 .htm	text/html
                 .html	text/html
                 .shtml	text/html
                 .shtm	text/html
                 .css	text/css
                 .pdf	application/pdf
                 .rtf	application/rtf
                 .ps	application/postscript
                 .eps	application/postscript
                 .hqx	application/mac-binhex40
                 .js	application/javascript
                 .json	application/json
                 .txt	text/plain
                 .text	text/plain
                 .gif	image/gif
                 .jpg	image/jpeg
                 .jpeg	image/jpeg
                 .jpe	image/jpeg
                 .jfif	image/jpeg
                 .pic	image/pict
                 .pict	image/pict
                 .tif	image/tiff
                 .tiff	image/tiff
                 .mpeg	video/mpeg
                 .mpg	video/mpeg
                 .mov	video/quicktime
                 .moov	video/quicktime
                 .aif	audio/aiff
                 .aiff	audio/aiff
                 .wav	audio/wav
                 .ram	audio/x-pn-realaudio
                 .sit	application/x-stuffit
                 .bin	application/x-stuffit
                 .xml	application/xml
                 .z	application/x-zip
                 .zip	application/x-zip
                 .gz	application/x-gzip
                 .tar	application/x-tar
                 */

            }
            ret = tipoMimeTypeMap.get(tipoMime);
            if (ret == null || "".equals(ret.trim())) {
                ret = "text/plain";
            }
        } catch (Exception e) {
        }
        return ret;

    }

    public boolean fileMustBeDownloaded() {
        return tipoMime.equals("xml")
                || tipoMime.equals("csv")
                || tipoMime.equals("txt")
                || tipoMime.contains("xls")
                || tipoMime.contains("doc")
                || tipoMime.equals("rtf");
    }

    public static void addSessionReport(HttpSession session, ReportData reportData) {
        session.setAttribute(SESSION_REPORTS_PREFIX + reportData.getNombreLogico(), reportData);
    }

    public static void addSessionReport(HttpSession session, String nombreLogico, byte[] out) {
        session.setAttribute(SESSION_REPORTS_PREFIX + nombreLogico, out);
    }

    public static void removeSessionReport(HttpSession session, ReportData reportData) {
        session.setAttribute(SESSION_REPORTS_PREFIX + reportData.getNombreLogico(), null);
    }

    public static ReportData getSessionReport(HttpSession session, String nombreLogico) {
        return (ReportData) session.getAttribute(SESSION_REPORTS_PREFIX + nombreLogico);
    }

    public ByteArrayServletOutputStream getByteArrayOutputStream() {
        return byteArrayOutputStream;
    }

    public void setByteArrayOutputStream(ByteArrayServletOutputStream byteArrayOutputStream) {
        this.byteArrayOutputStream = byteArrayOutputStream;
    }

    public final List<String> getNombresArchivos() {
        return nombresArchivos;
    }

    public final void setNombresArchivos(List<String> nombresArchivos) {
        this.nombresArchivos = nombresArchivos;
    }

    public List<JasperPrint> getJasperPrints() {
        return jasperPrints;
    }

    public void setJasperPrints(List<JasperPrint> jasperPrints) {
        this.jasperPrints = jasperPrints;
    }

    public JasperPrint getJasperPrint() {
        return jasperPrint;
    }

    public void setJasperPrint(JasperPrint jasperPrint) {
        this.jasperPrint = jasperPrint;
    }

}
