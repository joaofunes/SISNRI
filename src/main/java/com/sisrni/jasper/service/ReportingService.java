package com.sisrni.jasper.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.ssi.ByteArrayServletOutputStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sisrni.jasper.ReportData;
import com.sisrni.jasper.Reporte;
import com.sisrni.jasper.dao.ReportingDao;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "reportingService")
@Transactional
public class ReportingService {

    @Autowired
    private ReportingDao reportingDao;

    private final static Log log = LogFactory.getLog(ReportingService.class);

    public void executeReport(HttpServletRequest request, HttpServletResponse response) {
        try {
            Reporte reporte = initParams(request, response);
            if (reporte != null) {
                reporte.executeReport(request, response);
                reporte.executeAfterReport(request, response);
            } else {
                throw new RuntimeException("No se pudieron cargar los parametros del reporte.");
            }
        } catch (Exception e) {
            try {
                response.sendError(404, e.getMessage());
            } catch (Exception ex) {
                log.error(ex);
            }
            throw new RuntimeException(e);
        }
    }

    protected Reporte initParams(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Reporte reporte = null;
        try {
            String reportName = request.getParameter("reportName");
            if (reportName == null) {
                reportName = (String) request.getAttribute("reportName");
            }
            if (reportName != null && !reportName.trim().equals("")) {
                ReportData reportData = Reporte.getReportInSession(request.getSession(), reportName);
                if (reportData != null) {
                    reporte = new Reporte();
                    byte[] bytes = reportData.getBytes();
                    if (bytes != null && bytes.length > 0) {
                        ByteArrayServletOutputStream bytearray = new ByteArrayServletOutputStream();
                        bytearray.write(bytes);
                        reporte.setByteArrayOutputStream(bytearray);
                    }
                    reporte.setDescargando(reportData.isDownloading());
                    reporte.setNombreLogico(reportData.getNombreLogico());
                    reporte.setTipoMime(reportData.getFormat());
                    log.info("trying to execute report:" + reportName + "-" + reporte);
                } else {
                    throw new RuntimeException("No se encuentra en sesion el reporte \"" + request.getParameter("reportName") + "\"");
                }
            } else {
                throw new RuntimeException("El reporte no puede ser nulo.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return reporte;
    }

    public Reporte executeReport(final Reporte reporte) {
        return reportingDao.executeReport(reporte);
    }

}
