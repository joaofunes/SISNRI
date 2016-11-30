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
import java.io.IOException;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ComponentSystemEvent;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.primefaces.context.RequestContext;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.sisrni.model.PropuestaConvenio;
import com.sisrni.model.TipoDocumento;
import com.sisrni.pojo.rpt.PojoPropuestaConvenio;
import com.sisrni.security.AppUserDetails;
import com.sisrni.service.PropuestaConvenioService;
import com.sisrni.service.TipoDocumentoService;
import java.io.ByteArrayInputStream;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.primefaces.model.DefaultStreamedContent;
import org.apache.poi.xwpf.converter.pdf.PdfConverter;

import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;


@ManagedBean
@RequestScoped
public class DocumentacionMB implements Serializable{
    
    private static final long serialVersionUID = 1L;  
    private CurrentUserSessionBean user;
    private AppUserDetails usuario;
  
    private Documento documento;    
    private List<Documento> listadoDocumentos;
    
    //preguntar si el nombre del convenio sera el mismo que se pone en propuesta de convenio
    
    private String nombreConvenio;
    private PojoPropuestaConvenio pojoPropuestaConvenio;
    
    
    @Autowired
    @Qualifier(value = "documentoService")
    private DocumentoService documentoService;
   
    @Autowired
    @Qualifier(value = "propuestaConvenioService")
    private PropuestaConvenioService propuestaConvenioService;
 
    @Autowired
    @Qualifier(value = "tipoDocumentoService")
    private TipoDocumentoService tipoDocumentoService;
    
    private  List<PropuestaConvenio> listPropuestaConvenio;
    private  List<TipoDocumento> listTipoDocumento;
    private PropuestaConvenio propuestaConvenio;
    private TipoDocumento tipoDocumento;
    
    private UploadedFile file;
    
    
    private StreamedContent content; 
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MMMMM/yyyy");
    
    
    @PostConstruct
    public void init() {
        try {
           iniciliazar();
           //searchConvenio();
        } catch (Exception e) {
        }
    } 
    
    public void iniciliazar(){
        try {
            
               FacesContext facesContext = FacesContext.getCurrentInstance();
                if (!facesContext.isPostback() && !facesContext.isValidationFailed()) {
                   user = new CurrentUserSessionBean();
                    usuario = user.getSessionUser();
                    listPropuestaConvenio = new ArrayList<PropuestaConvenio>();
                    propuestaConvenio = new PropuestaConvenio();
                    listPropuestaConvenio = propuestaConvenioService.findAll();
                    listTipoDocumento= tipoDocumentoService.findAll();
                }
           
        } catch (Exception e) {
          e.printStackTrace();
        }
    }
    
    
     
     /**
      * metodo para realizar busquedas de documentacion por nombre de convcenio
      */
    public void searchDocumentoConvenio(int idPropuestaConvenio){
        try {
           listadoDocumentos = new ArrayList<Documento>();
           listadoDocumentos = documentoService.getDocumentFindCovenio(idPropuestaConvenio);                            
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
    
     
    
    /**
      * metodo para realizar busquedas de convenio por nombre
      */
     public List<PropuestaConvenio> completePropuestaConvenio(String query) {        
      List<PropuestaConvenio> filteredThemes = new ArrayList<PropuestaConvenio>();      
        for(int i=0 ; i<listPropuestaConvenio.size();i++ ){
            PropuestaConvenio skin=listPropuestaConvenio.get(i);
            if(skin.getNombrePropuesta().toLowerCase().startsWith(query)){
               filteredThemes.add(skin);
            }
        }        
        return filteredThemes;
    }
    
    /**
     * metodo para cagar de convenio
     */ 
    public void getDataConvenio(){
        try {
            pojoPropuestaConvenio = propuestaConvenioService.getAllPropuestaConvenioSQLByID(propuestaConvenio.getIdPropuesta());                    
            searchDocumentoConvenio(propuestaConvenio.getIdPropuesta());        
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
    
    
    
    /**
     * metodo para cagar de convenio desde otros MB
     * @param idConvenio 
     */ 
    public void getDataConvenio(int idConvenio){
        try {
            pojoPropuestaConvenio = propuestaConvenioService.getAllPropuestaConvenioSQLByID(idConvenio);
            searchDocumentoConvenio(propuestaConvenio.getIdPropuesta());        
            RequestContext.getCurrentInstance().update(":idDataConevnio");          
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
     
    /***
     * Metodo para cargar documneto
     * @param event 
     */
     public void handleFileUpload(FileUploadEvent event) {
          try {
        byte[] content = IOUtils.toByteArray(event.getFile().getInputstream());  
        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        documento = new Documento();      
        documento.setDocumento(content); 
        documento.setNombreDocumento(event.getFile().getFileName());
          } catch (Exception e) {
              e.printStackTrace();
          }

    }
    
    
   /**
    * Metodo para agregar documentos a convenio
    */
    public void addDocument(){
        try {     
             documento.setIdPropuesta(propuestaConvenio);
             documento.setFechaRecibido(new Date());
             documento.setIdTipoDocumento(tipoDocumento); 
             documento.setUsuarioRecibe(usuario.getUsuario().getNombreUsuario());
             documentoService.save(documento);
             getDataConvenio();
             FacesMessage message = new FacesMessage("Succesful", " Documento agregado exitosamente");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    /*************************************/
    
    public void preView() throws IOException{
         try {
            //  File fl= new File("WEB-INF\\reports\\MANUAL CONVENIOS AÑO 2011-definitivo.doc");
//                String filePath="C:\\Users\\Joao\\USI\\SISNRI\\src\\main\\webapp\\WEB-INF\\reports\\MANUAL CONVENIOS AÑO 2011-definitivo.doc";
//
//          //        ByteArrayOutputStream out = new ByteArrayOutputStream();  
//                  
//                 
//        FileInputStream fInputStream = new FileInputStream(new File(filePath));
//         XWPFDocument document = new XWPFDocument(Data.class.getResourceAsStream(filePath));
//       // XWPFDocument document = new XWPFDocument(fInputStream);
//        
//
//        File outFile = new File("C:\\Users\\Joao\\Desktop\\Pera ciclo II\\Doc1.pdf");
//        outFile.getParentFile().mkdirs();
//
//        OutputStream out = new FileOutputStream(outFile);
//        PdfOptions options = PdfOptions.create().fontEncoding("windows-1250");
//        PdfConverter.getInstance().convert(document, out, options);

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
           RequestContext.getCurrentInstance().execute("PF('dlg').show()");         
            RequestContext.getCurrentInstance().update(":idPreview");         
               
         } catch (Exception e) {
             e.printStackTrace();
         }                    
     }
     

    
    
    
    public void onPrerender(ComponentSystemEvent event) {  
  
        try {  
            /*******prueba eliminar**/
            String filePath="C:\\Users\\Joao\\USI\\SISNRI\\src\\main\\webapp\\WEB-INF\\reports\\MANUAL CONVENIOS AÑO 2011-definitivo.docx";
             File file = new File(filePath);
             FileInputStream fis = new FileInputStream(file.getAbsolutePath());
             XWPFDocument pr = new XWPFDocument(fis);

             List<XWPFParagraph> paragraphs = pr.getParagraphs();

                
            /*******prueba eliminar**/
                    
            
            ByteArrayOutputStream out = new ByteArrayOutputStream();  
  
            //Document document = new Document();  
            //PdfWriter.getInstance(document, out); 
            PdfOptions options = PdfOptions.create().fontEncoding("windows-1250");
            PdfConverter.getInstance().convert(pr, out, options);
            //document.open();  
  
//             for (XWPFParagraph para : paragraphs) {
//                    System.out.println(para.getText());
//                    document.add(new Paragraph(para.getText()));  
//                }
//            
//            fis.close();
//            document.close();  
            content = new DefaultStreamedContent(new ByteArrayInputStream(out.toByteArray()), "application/pdf");  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
    
    
    
    
    public void convertImage(){
        try {
//       
//               String sourcePath="C:\\Users\\Joao\\USI\\SISNRI\\src\\main\\webapp\\WEB-INF\\reports\\MANUAL CONVENIOS AÑO 2011-definitivo.docx";
//               Document doc = new Document(sourcePath);  
//               ImageSaveOptions options = new ImageSaveOptions(SaveFormat.JPEG);  
//               options.setJpegQuality(100);  
//               options.setResolution(100);  
//               options.setUseHighQualityRendering(true);  
//               for (int i = 0; i < doc.getPageCount(); i++) {  
//                    String imageFilePath = "E://"+ "images" + File.separator + "img_" + i + ".jpeg";  
//                    options.setPageIndex(i);  
//                    doc.save(imageFilePath, options);  
//               }  
//               System.out.println("Done...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
     public static Integer ConvertDocToPNG(String doc){
        OfficeManager officeManager = null;
        Integer result=-1;
        try {
            officeManager = new DefaultOfficeManagerConfiguration()
                    //.setOfficeHome("/home/dryize/openoffice4")
                    //.setConnectionProtocol(OfficeConnectionProtocol.PIPE)
                    .buildOfficeManager();
            officeManager.start();

            // 2) Create JODConverter converter
            OfficeDocumentConverter converter = new OfficeDocumentConverter(
                    officeManager);

            File pdf = new File(doc + "raw.pdf");
            converter.convert(new File(doc), pdf);
/*
            PDFDocument document = new PDFDocument();
            document.load(pdf);
            SimpleRenderer renderer = new SimpleRenderer();

            // set resolution (in DPI)
            renderer.setResolution(72);
            java.util.List<Image> images = renderer.render(document);


            new File(doc.path() + "png/").mkdirs();
            for (int i = 0; i < images.size(); i++) {

                ImageIO.write((RenderedImage) images.get(i), "jpg", new File(doc.path() + "png/" + (i + 1) + ".jpg"));
            }

            result =images.size();
*/
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            // 4) Stop LibreOffice in headless mode.
            if (officeManager != null) {
                officeManager.stop();
            }
        }

        return result;
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
        return documento;
    }

    public void setDocuemnto(Documento docuemnto) {
        this.documento = docuemnto;
    }

    public String getNombreConvenio() {
        return nombreConvenio;
    }

    public void setNombreConvenio(String nombreConvenio) {
        this.nombreConvenio = nombreConvenio;
    }

    public PojoPropuestaConvenio getPojoPropuestaConvenio() {
        return pojoPropuestaConvenio;
    }

    public void setPojoPropuestaConvenio(PojoPropuestaConvenio pojoPropuestaConvenio) {
        this.pojoPropuestaConvenio = pojoPropuestaConvenio;
    }

    public List<PropuestaConvenio> getListPropuestaConvenio() {
        return listPropuestaConvenio;
    }

    public void setListPropuestaConvenio(List<PropuestaConvenio> listPropuestaConvenio) {
        this.listPropuestaConvenio = listPropuestaConvenio;
    }

    public PropuestaConvenio getPropuestaConvenio() {
        return propuestaConvenio;
    }

    public void setPropuestaConvenio(PropuestaConvenio propuestaConvenio) {
        this.propuestaConvenio = propuestaConvenio;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public List<TipoDocumento> getListTipoDocumento() {
        return listTipoDocumento;
    }

    public void setListTipoDocumento(List<TipoDocumento> listTipoDocumento) {
        this.listTipoDocumento = listTipoDocumento;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

      public AppUserDetails getUsuario() {
        return usuario;
    }

    public void setUsuario(AppUserDetails usuario) {
        this.usuario = usuario;
    }

    public CurrentUserSessionBean getUser() {
        return user;
    }

    public void setUser(CurrentUserSessionBean user) {
        this.user = user;
    }
    
    
    
}
