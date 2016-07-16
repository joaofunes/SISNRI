/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sisrni.managedbean;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;
import com.sisrni.pojo.rpt.DocumentosPojo;
import com.sisrni.security.AppUserDetails;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;



import java.io.IOException;

import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.io.FileInputStream;
import org.springframework.context.annotation.Scope;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;


import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import org.exolab.castor.xml.NodeType;

/**
 *
 * @author Joao
 */
@Named("documentacionMB")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class DocumentacionMB implements Serializable{
    
     private static final long serialVersionUID = 1L;  
    private CurrentUserSessionBean user;
    private AppUserDetails usuario;
  
    
    /**prueba/*/
    private List<DocumentosPojo> listadoDocumentosPojo;
    private String console;
    private Calendar unaFecha;
    final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    final java.util.Random rand = new java.util.Random();
    final Set<String> identifiers = new HashSet<String>();
    private StreamedContent content; 
     /**prueba/*/
    
    private  DocumentosPojo documentosPojo;
    
    
    @PostConstruct
    public void init() {
        try {
            //user = new CurrentUserSessionBean();
            listadoDocumentosPojo = new ArrayList<DocumentosPojo>();
            documentosPojo=new DocumentosPojo();
            preView();
        } catch (Exception e) {
        }
    } 

    
    public void iniciar(){
        int i=0;
        int f=getCantidad();
        listadoDocumentosPojo = new ArrayList<DocumentosPojo>();
        for( i=0 ;i < f ;i++){
            documentosPojo = new DocumentosPojo();
            documentosPojo.setDocumento(getRandomBoolean());
            documentosPojo.setFechaRecibo(getFecha());
            documentosPojo.setNombreDocumento("xxxxxx xxxxxxxxxxx xxxxxxx");
            documentosPojo.setPersonaRecibe("yyyyyy yyyyyyy yyyyyyy");
            documentosPojo.setTipoDocumento("zzzz zzzzzz");
            listadoDocumentosPojo.add(documentosPojo);
        }
    }
    
    
     public Date getFecha() {
        int numero = 0;
        Random aleatorio;
        aleatorio = new Random();

        unaFecha = Calendar.getInstance();
        unaFecha.set (aleatorio.nextInt(10)+2014, aleatorio.nextInt(12)+1, aleatorio.nextInt(30)+1);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MMMMM/yyyy");
        return unaFecha.getTime();
    }

     
     
     public void preView() throws IOException{
         try {
              File fl= new File("WEB-INF\\reports\\MANUAL CONVENIOS AÑO 2011-definitivo.doc");
                String ruta="C:\\Users\\Joao\\USI\\SISNRI\\src\\main\\webapp\\WEB-INF\\reports\\MANUAL CONVENIOS AÑO 2011-definitivo.doc";
                
                Path path = Paths.get(ruta);
                byte[] data = Files.readAllBytes(path);
                ByteArrayInputStream bais = new ByteArrayInputStream(data);
                BufferedImage image = ImageIO.read(bais);
                
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(image, "jpg", baos);
                byte [] bytes = baos.toByteArray();
                
                ImageIcon imageIcon = new ImageIcon(bytes);
                imageIcon.getImage();
               

            //content = new DefaultStreamedContent(new ByteArrayInputStream(baos.toByteArray()), "application/pdf"); 
            content = new DefaultStreamedContent(new ByteArrayInputStream(bytes), "application/pdf"); 
                
//                        File file = new File(ruta);
//			FileInputStream fis = new FileInputStream(file.getAbsolutePath());
//
//			HWPFDocument doc = new HWPFDocument(fis);
//
//			WordExtractor we = new WordExtractor(doc);
//
//			String[] paragraphs = we.getParagraphText();
//			
//			System.out.println("Total no of paragraph "+paragraphs.length);
//			for (String para : paragraphs) {
//				System.out.println(para.toString());
//			}
//			fis.close();
//            
           
//         doc.save(getMyDir() + "Document.Doc2PdfSave Out.pdf");
         } catch (Exception e) {
             e.printStackTrace();
         }                    
     }
     
//        public void generatePicturesAsImages(final String sourcePath) {  
//         try {  
//              Document doc = new Document(sourcePath);  
//              ImageSaveOptions options = new ImageSaveOptions(SaveFormat.JPEG);  
//              options.setJpegQuality(100);  
//              options.setResolution(100);  
//              options.setUseHighQualityRendering(true);  
//              List<ShapeRenderer> pictures = getAllPictures(doc);  
//              if (pictures != null) {  
//                   for (int i = 0; i < pictures.size(); i++) {  
//                        ShapeRenderer picture = pictures.get(i);  
//                        String imageFilePath = sourcePath + "_output_" + i + ".jpeg";  
//                        picture.save(imageFilePath, options);  
//                   }  
//              }  
//         } catch (Exception e) {  
//              e.printStackTrace();  
//         }  
//    }  
//     
//    private List<ShapeRenderer> getAllPictures(final Document document) throws Exception {  
//         List<ShapeRenderer> images = null;  
//         @SuppressWarnings("unchecked")  
//         NodeCollection<DrawingML> nodeCollection = document.getChildNodes(NodeType.DRAWING_ML, Boolean.TRUE);  
//         if (nodeCollection.getCount() > 0) {  
//              images = new ArrayList<ShapeRenderer>();  
//              for (DrawingML drawingML : nodeCollection) {  
//                   images.add(drawingML.getShapeRenderer());  
//              }  
//         }  
//         return images;  
//    } 
//     
//    
//    
//    public void generateImages(final String sourcePath) {
//        try {
//            Document doc = new Document(sourcePath);
//            ImageSaveOptions options = new ImageSaveOptions(SaveFormat.JPEG);
//            options.setJpegQuality(100);
//            options.setResolution(100);
//
//            for (int i = 0; i < doc.getPageCount(); i++) {
//                String imageFilePath = sourcePath + "_output_" + i + ".jpg";
//                options.setPageIndex(i);
//                doc.save(imageFilePath, options);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
     
     
     public static boolean getRandomBoolean() {
       return Math.random() < 0.5;
       //I tried another approaches here, still the same result
   } 
     
     public static int getCantidad(){
       return 2 + (int)(Math.random() * ((10 - 2) + 1));
     }
    
    public String getConsole() {
        return console;
    }

    public void setConsole(String console) {
        this.console = console;
    }

    public Calendar getUnaFecha() {
        return unaFecha;
    }

    public void setUnaFecha(Calendar unaFecha) {
        this.unaFecha = unaFecha;
    }

    public List<DocumentosPojo> getListadoDocumentosPojo() {
        return listadoDocumentosPojo;
    }

    public void setListadoDocumentosPojo(List<DocumentosPojo> listadoDocumentosPojo) {
        this.listadoDocumentosPojo = listadoDocumentosPojo;
    }

    public StreamedContent getContent() {
        return content;
    }

    public void setContent(StreamedContent content) {
        this.content = content;
    }
    
}
