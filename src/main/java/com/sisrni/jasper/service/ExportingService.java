package com.sisrni.jasper.service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;
import org.springframework.stereotype.Service;

@Service
public class ExportingService {

    public void generateHTMLOutput(HttpServletRequest request, JasperPrint jasperPrint, ServletOutputStream out) throws Exception {
        try {
            JRHtmlExporter exporter = new JRHtmlExporter();
            request.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jasperPrint);
            exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "ISO-8859-1");
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
            exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE);
            exporter.setParameter(JRHtmlExporterParameter.BETWEEN_PAGES_HTML, "");
            exporter.setParameter(JRHtmlExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
            exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, request.getContextPath() + "/servlets/image?image=");
            exporter.setParameter(JRHtmlExporterParameter.ZOOM_RATIO, 1.7F);
            exporter.exportReport();
        } catch (Exception e) {
            throw new Exception("Problemas con la exportacion a Html, Jasper Exception:" + e);
        }
    }

    public void generateCSVOutput(JasperPrint jasperPrint, ServletOutputStream out) throws Exception {
        try {
            JRCsvExporter exporter = new JRCsvExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
            exporter.exportReport();
        } catch (JRException jex) {
            throw new Exception("Problemas con la exportacion a excel, Jasper Exception:" + jex);
        }
    }

    public void generateXLSOutput(JasperPrint jasperPrint, ServletOutputStream out, String nombreArchivo) throws Exception {
        try {
            JRXlsxExporter excelExporter = new JRXlsxExporter();

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            excelExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            excelExporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, byteArrayOutputStream);
            excelExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
            excelExporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
            excelExporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
            excelExporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
            excelExporter.setParameter(JRXlsExporterParameter.CHARACTER_ENCODING, "ISO-8859-1");
            excelExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, nombreArchivo);
            excelExporter.exportReport();
            out.write(byteArrayOutputStream.toByteArray());
            out.flush();
        } catch (Exception ex) {
            throw new Exception("Problemas con la exportacion a excel, Jasper Exception:" + ex);
        }
    }

    public void generateRTFOutput(JasperPrint jasperPrint, ServletOutputStream out, String nombreArchivo) throws Exception {
        try {
            JRRtfExporter exporter = new JRRtfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, nombreArchivo);
            exporter.exportReport();
        } catch (JRException jex) {
            throw new Exception("Problemas con la exportacion a rtf, Jasper Exception:" + jex);
        }
    }

    public void generatePDFOutput(JasperPrint jasperPrint, ServletOutputStream out, String nombreArchivo, boolean download) throws Exception {
        try {
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRPdfExporterParameter.CHARACTER_ENCODING, "ISO-8859-1");
//            exporter.setParameter(JRPdfExporterParameter.PDF_JAVASCRIPT, "this.print({bUI: false,bSilent: true,bShrinkToFit: false});");
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, nombreArchivo);
            exporter.exportReport();
        } catch (JRException jex) {
            throw new Exception("Problemas con la exportacion a rtf, Jasper Exception:" + jex);
        }
    }

    public void generatePDFOutput(List<JasperPrint> jasperPrints, ServletOutputStream out, String nombreArchivo, boolean download) throws Exception {
        try {
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrints);
            exporter.setParameter(JRPdfExporterParameter.CHARACTER_ENCODING, "ISO-8859-1");
//            exporter.setParameter(JRPdfExporterParameter.PDF_JAVASCRIPT, "this.print({bUI: false,bSilent: true,bShrinkToFit: false});");
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, nombreArchivo);
            exporter.exportReport();
        } catch (JRException jex) {
            throw new Exception("Problemas con la exportacion a rtf, Jasper Exception:" + jex);
        }
    }
}
