/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sisrni.managedbean;





import com.sisrni.model.Documento;
import com.sisrni.service.DocumentoService;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import javax.xml.crypto.Data;
import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.primefaces.context.RequestContext;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayInputStream;
import org.primefaces.model.DefaultStreamedContent;



@Named("documentacionMB")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class DocumentacionMB implements Serializable{
    
    private static final long serialVersionUID = 1L;  
    private CurrentUserSessionBean user;
    
  
    private Documento docuemnto;    
    private List<Documento> listadoDocumentos;
    
    //preguntar si el nombre del convenio sera el mismo que se pone en propuesta de convenio
    
    private String nombreConvenio;
    
    
    @Autowired
    @Qualifier(value = "documentoService")
    private DocumentoService documentoService;
    private StreamedContent content; 
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MMMMM/yyyy");
    
    
    @PostConstruct
    public void init() {
        try {
         
           //searchConvenio();
        } catch (Exception e) {
        }
    } 
    
    public void preView() throws IOException{
         try {
            //  File fl= new File("WEB-INF\\reports\\MANUAL CONVENIOS AÑO 2011-definitivo.doc");
                String filePath="C:\\Users\\Joao\\USI\\SISNRI\\src\\main\\webapp\\WEB-INF\\reports\\MANUAL CONVENIOS AÑO 2011-definitivo.doc";

          //        ByteArrayOutputStream out = new ByteArrayOutputStream();  
                  
                 
        FileInputStream fInputStream = new FileInputStream(new File(filePath));
         XWPFDocument document = new XWPFDocument(Data.class.getResourceAsStream(filePath));
       // XWPFDocument document = new XWPFDocument(fInputStream);
        

        File outFile = new File("C:\\Users\\Joao\\Desktop\\Pera ciclo II\\Doc1.pdf");
        outFile.getParentFile().mkdirs();

        OutputStream out = new FileOutputStream(outFile);
        PdfOptions options = PdfOptions.create().fontEncoding("windows-1250");
        PdfConverter.getInstance().convert(document, out, options);

        System.out.println("Sucess");
  
//            Document document = new Document();  
//            PdfWriter.getInstance(document, out);  
//            document.open();  
//  
//            for (int i = 0; i < 50; i++) {  
//                document.add(new Paragraph("All work and no play makes Jack a dull boy"));  
//            }  
//              
//            document.close();  
//            content = new DefaultStreamedContent(new ByteArrayInputStream(out.toByteArray()), "application/pdf");
    
//    ClassLoader classloader =org.apache.poi.poifs.filesystem.POIFSFileSystem.class.getClassLoader();
//    URL res = classloader.getResource("org/apache/poi/poifs/filesystem/POIFSFileSystem.class");
//    String path = res.getPath();
//   
//     Document documentPDF = new Document();
//    
//        File file = null;
//        WordExtractor extractor = null;

//
//            file = new File(ruta);
//            FileInputStream fis = new FileInputStream(file.getAbsolutePath());
//            HWPFDocument document = new HWPFDocument(fis);
//            extractor = new WordExtractor(document);
//            String[] fileData = extractor.getParagraphText();
//            
//            
//            /***/
//            
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        PdfWriter writer;
//     
//            Document documents = new Document();
//
//           
////                PdfWriter pdfWriter = PdfWriter.getInstance(documents, new FileOutputStream("HelloWorld.pdf"));
////                documents.open();
////                 Paragraph paragraph1 = new Paragraph("This is Paragraph 1");
////                Paragraph paragraph2 = new Paragraph("This is Paragraph 2");
////                paragraph1.setIndentationLeft(80);
////                paragraph1.setIndentationRight(80);
////                paragraph1.setAlignment(Element.ALIGN_CENTER);
////                paragraph1.setSpacingAfter(15);
////                paragraph2.setSpacingBefore(15);
////                paragraph2.setAlignment(Element.ALIGN_LEFT);
////                Phrase phrase = new Phrase("This is a large sentence.");
////                for(int count = 0;count<10;count++)
////                {
////
////                        paragraph1.add(phrase);
////
////                        paragraph2.add(phrase);
////
////                }
////
////                documents.add(paragraph1);
////                documents.add(paragraph2);
////
////                documents.close();
//
//           
//       
//            
//                
//            /***/
//            
//            
//            /*pdf*/
//            ByteArrayOutputStream out = new ByteArrayOutputStream();  
//            PdfWriter.getInstance(documentPDF, out);  
//            documentPDF.open();  
//            
//            
//                    for (String fileData1 : fileData) {
//                        if (fileData1 != null) {
//                            System.out.println(fileData1);
//                             documentPDF.add(new Paragraph(fileData1));
//                        }
//                       
//                    }
//           
//              
//            documentPDF.close();  
//             writer = PdfWriter.getInstance(documents, baos);
//        
//        InputStream stream = new ByteArrayInputStream(baos.toByteArray());
//            content = new DefaultStreamedContent(stream, "application/pdf");
 //           RequestContext.getCurrentInstance().execute("PF('dlg').show()");         
//            RequestContext.getCurrentInstance().update(":idPreview");         
               
         } catch (Exception e) {
             e.printStackTrace();
         }                    
     }
     

    
    
    
    public void onPrerender(ComponentSystemEvent event) {  
  
        try {  
      
            ByteArrayOutputStream out = new ByteArrayOutputStream();  
  
            Document document = new Document();  
            PdfWriter.getInstance(document, out);  
            document.open();  
  
            for (int i = 0; i < 50; i++) {  
                document.add(new Paragraph("All work and no play makes Jack a dull boy"));  
            }  
              
            document.close();  
            content = new DefaultStreamedContent(new ByteArrayInputStream(out.toByteArray()), "application/pdf");  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
    
    
    
     
     /**
      * metodo para realizar busquedas de documentacion por nombre de convcenio
      */
    public void searchConvenio(){
        try {
           listadoDocumentos = new ArrayList<Documento>();
           listadoDocumentos = documentoService.getDocumentFindCovenio(nombreConvenio);            
           RequestContext.getCurrentInstance().update(":formMenu");            
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
     
     
   
    
     public static boolean getRandomBoolean() {
       return Math.random() < 0.5;
       //I tried another approaches here, still the same result
   } 
     
     public static int getCantidad(){
       return 2 + (int)(Math.random() * ((10 - 2) + 1));
     }    
     
    public StreamedContent getContent() {
        return content;
    }

    public void setContent(StreamedContent content) {
        this.content = content;
    }

    public List<Documento> getListadoDocumentos() {
        return listadoDocumentos;
    }

    public void setListadoDocumentos(List<Documento> listadoDocumentos) {
        this.listadoDocumentos = listadoDocumentos;
    }
    
    public Documento getDocuemnto() {
        return docuemnto;
    }

    public void setDocuemnto(Documento docuemnto) {
        this.docuemnto = docuemnto;
    }

    public String getNombreConvenio() {
        return nombreConvenio;
    }

    public void setNombreConvenio(String nombreConvenio) {
        this.nombreConvenio = nombreConvenio;
    }
    
}
