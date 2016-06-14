package com.dimesa.jasper.test;


//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import java.io.ByteArrayInputStream;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.HashSet;
//import java.util.List;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
//import net.sf.jasperreports.engine.data.JsonDataSource;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.codehaus.jackson.map.ObjectMapper;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import sv.gob.mh.dgii.controller.configuration.MainController;
//import com.siapa.jasper.Reporte;
//import sv.gob.mh.dgii.model.pojos.cc.CcEstadoDeCuentaPojo;
//import sv.gob.mh.dgii.model.pojos.cc.CcSolciWebDetalleEstadoPojo;
//import sv.gob.mh.dgii.model.pojos.cc.CcSolciWebDetalleOmisosPojo;
//import sv.gob.mh.dgii.model.pojos.cc.CcSolicWebDetalleDiferenciasPojo;
//
//@Controller
//public class TestReporteController extends MainController {
//
//    private final static Log log = LogFactory.getLog(TestReporteController.class);
//
//    @RequestMapping(value = "/test")
//    public String test() {
//        return "report-tests/estadoCuentaCorriente";
//    }
//
//    @RequestMapping(value = "/test/rpt/json")
//    public @ResponseBody
//    Autor testRptJson() {
//        return Autor.getSample();
//    }
//
//    @RequestMapping(value = "/test/json")
//    public String testJson(HttpServletRequest request, HttpServletResponse response) throws IOException, FileNotFoundException, JRException {
//        Reporte reporte = new Reporte("cat", "autores", request);
//        ObjectMapper mapper = new ObjectMapper();
//        Autor autor = Autor.getSample();
//        Gson gson = new GsonBuilder().create();
//        gson.toJson(autor);
//        mapper.writeValue(new File("./autor.json"), autor);
//        InputStream is = new ByteArrayInputStream(gson.toJson(autor).getBytes());
//        JsonDataSource ds = new JsonDataSource(is);
//        reporte.setDataSource(ds);
//        reporte.setReportInSession(request, response);
//        return "redirect:/report/report?reportName=" + reporte.getNombreLogico();
//    }
//
//    @RequestMapping(value = "/test/pojo")
//    public String testPojo(HttpServletRequest request, HttpServletResponse response) throws IOException, FileNotFoundException, JRException {
//        List<Autor> list = new ArrayList<Autor>();
//        Reporte reporte = new Reporte("cat", "autores", request);
//        Autor autor = Autor.getSample();
//        list.add(autor);
//        reporte.setDataSource(new JRBeanCollectionDataSource(new HashSet<Autor>(list)));
//        reporte.setReportInSession(request, response);
//        return "redirect:/report/report?reportName=" + reporte.getNombreLogico();
//    }
//
//    @RequestMapping(value = "/test/ec")
//    public String rtpec(HttpServletRequest request, HttpServletResponse response) throws IOException, FileNotFoundException, JRException {
//        List<CcEstadoDeCuentaPojo> list = new ArrayList<CcEstadoDeCuentaPojo>();
//        Reporte reporte = new Reporte("cc", "rpt_ec", request);
//        CcEstadoDeCuentaPojo item = new CcEstadoDeCuentaPojo();
//        item.setDetalle(new ArrayList<CcSolciWebDetalleEstadoPojo>());
//        Calendar calendar = Calendar.getInstance();
//        for (int i = 0; i < 15; i++) {
//            CcSolciWebDetalleEstadoPojo det = new CcSolciWebDetalleEstadoPojo();
//            det.setCobligacion("10_" + i % 4);
//            det.setPeriodo(calendar.getTime());
//            det.setNombres("IVA");
//            det.setImpuesto(new BigDecimal(Math.random() * 10 * i));
//            det.setMulta(BigDecimal.ZERO);
//            item.getDetalle().add(det);
//            calendar.add(Calendar.YEAR, 1);
//        }
//
//        item.setOmisos(new ArrayList<CcSolciWebDetalleOmisosPojo>());
//        calendar = Calendar.getInstance();
//        for (int i = 0; i < 100; i++) {
//            CcSolciWebDetalleOmisosPojo det = new CcSolciWebDetalleOmisosPojo();
//            det.setCobligacion("10_" + i % 3);
//            det.setPeriodoFiscal(calendar.getTime());
//            det.setObligacion("Obligacion_" + i % 3);
//            item.getOmisos().add(det);
//            calendar.add(Calendar.MONTH, (int) (Math.random() * 1));
//            calendar.add(Calendar.YEAR, 1);
//        }
//        item.setDiferencias(new ArrayList<CcSolicWebDetalleDiferenciasPojo>());
//        calendar = Calendar.getInstance();
//        for (int i = 0; i < 10; i++) {
//            CcSolicWebDetalleDiferenciasPojo det = new CcSolicWebDetalleDiferenciasPojo();
//            det.setCodIngreso("01");
//            det.setNit("0101"+i);
//            det.setF910Periodo(calendar.getTime());
//            item.getDiferencias().add(det);
//            calendar.add(Calendar.MONTH, (int) (Math.random() * 1));
//            calendar.add(Calendar.YEAR, 2);
//        }
//
//        list.add(item);
//        reporte.addParameter("preliminar", "P");
//        reporte.setDataSource(new JRBeanCollectionDataSource(new HashSet<CcEstadoDeCuentaPojo>(list)));
//        reporte.setReportInSession(request, response);
//        return "redirect:/report/report?reportName=" + reporte.getNombreLogico();
//    }
//
//    @RequestMapping(value = "/qtester")
//    public String qtester(HttpServletRequest request, HttpServletResponse response) {
//        String ret = null;
//        Reporte reporte = new Reporte("rc", "rc_act_eco_consultaruc", request);
//        reporte.addParameter("P_NIT", "07021308801023");
//        reporte.setReportInSession(request, response);
//        ret = "redirect:/report/report?reportName=" + reporte.getNombreLogico();
//        return ret;
//    }
//
//    @RequestMapping(value = "/tester")
//    public String tester(HttpServletRequest request, HttpServletResponse response) {
//        String ret = null;
//        Reporte reporte = new Reporte("cc", "rpt_estado_cuenta_jdbc", request);
//        String nit = "07021308801023";
//
//        final Integer[] seq = new Integer[1];
//        seq[0] = 1024;
//
//        reporte.addParameter("P_SEQ", seq[0]);
//        reporte.addParameter("P_TOT_OMISOS", 10);
//        reporte.setReportInSession(request, response);
//
//        reporte.setTipoMime("html");
//        ret = "redirect:/report/report?reportName=" + reporte.getNombreLogico();
//        return ret;
//
//    }
//}
