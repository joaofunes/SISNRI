package com.sisrni.managedbean;

import com.sisrni.model.Documento;
import com.sisrni.model.TipoDocumento;
import com.sisrni.pojo.rpt.PojoMovilidadAdm;
import com.sisrni.pojo.rpt.PojoMovilidadDocumentacion;
import com.sisrni.security.AppUserDetails;
import com.sisrni.service.DocumentoService;
import com.sisrni.service.MovilidadService;
import com.sisrni.service.TipoDocumentoService;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import org.apache.commons.io.IOUtils;

import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author Usuario
 */
@Named(value = "documentacionMovilidadMB")
@Scope(WebApplicationContext.SCOPE_APPLICATION)
public class DocumentacionMovilidadMB implements Serializable {

    //Variables
    private static final long serialVersionUID = 1L;
    private CurrentUserSessionBean user;
    private AppUserDetails usuario;

    private Documento documento;
    private List<Documento> listadoDocumentos;
    private List<TipoDocumento> listTipoDocumento;
    private TipoDocumento tipoDocumento;

    private UploadedFile file;

    private StreamedContent content;
    //private SimpleDateFormat sdf = new SimpleDateFormat("dd/MMMMM/yyyy");

    //private List<PojoMovilidadAdm> listPojoMovilidad;
    //private PojoMovilidadAdm pojoMovilidadSelected;
    //private PojoMovilidadAdm pojoToShow;
    
    private List<PojoMovilidadDocumentacion> listPojoMovilidad;
    private PojoMovilidadDocumentacion pojoMovilidadSelected;
    private PojoMovilidadDocumentacion pojoToShow;
    
    private boolean visualizar;

    @Autowired
    @Qualifier(value = "documentoService")
    private DocumentoService documentoService;

    @Autowired
    @Qualifier(value = "tipoDocumentoService")
    private TipoDocumentoService tipoDocumentoService;

    @Autowired
    @Qualifier(value = "movilidadService")
    private MovilidadService movilidadService;

    @PostConstruct
    public void init() {
        try {
            inicializar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void inicializar() {
        try {
            user = new CurrentUserSessionBean();
            usuario = user.getSessionUser();
            
            documento = new Documento();
            listadoDocumentos = new ArrayList<Documento>();
            listTipoDocumento = tipoDocumentoService.getTipoDocumentosByCategory(4);
            tipoDocumento = new TipoDocumento();
           // listPojoMovilidad = movilidadService.getMovilidadAdm(0);
           // pojoMovilidadSelected = new PojoMovilidadAdm();
           // pojoToShow = new PojoMovilidadAdm();
            listPojoMovilidad = movilidadService.getMovilidadDocumentacion(0);
            pojoMovilidadSelected = new PojoMovilidadDocumentacion();
            pojoToShow = new PojoMovilidadDocumentacion();
            
            visualizar = Boolean.FALSE;
        } catch (Exception e) {

        }

    }

    public void onMovilidadChange() {
        try {
            PojoMovilidadDocumentacion pojoAux = null;
            if (pojoMovilidadSelected.getIdMovilidad() != -1) {
                pojoAux = movilidadService.getMovilidadDocumentacion(pojoMovilidadSelected.getIdMovilidad()).get(0);
            }
            searchDocumentoMovilidad(pojoMovilidadSelected.getIdMovilidad());
            if (pojoAux != null) {
                pojoToShow = pojoAux;
            } else {
                pojoToShow = new PojoMovilidadDocumentacion();
            }

        } catch (Exception e) {

        }

    }

    //Metodo para obtener una lista de los documentos asociados a una moviliadad
    public void searchDocumentoMovilidad(Integer idMovilidad) {
        try {
            listadoDocumentos = new ArrayList<Documento>();
            listadoDocumentos = documentoService.getDocumentoFindMovilidad(idMovilidad);
        } catch (Exception e) {
        }
    }

    /**
     * *
     * Metodo para cargar documneto
     *
     * @param event
     */
    public void handleFileUpload(FileUploadEvent event) {
        try {
            byte[] contenido;
            contenido = IOUtils.toByteArray(event.getFile().getInputstream());

            if (this.documento == null) {
                this.documento = new Documento();
            }
            this.documento.setDocumento(contenido);

            this.documento.setNombreDocumento(event.getFile().getFileName());
        } catch (Exception e) {
        }

    }

    /**
     * Metodo para agregar documentos a convenio
     */
    public void addDocument() {
        try {
            this.documento.setIdMovilidad(movilidadService.findById(pojoMovilidadSelected.getIdMovilidad()));
            this.documento.setFechaRecibido(new Date());
            this.documento.setIdTipoDocumento(tipoDocumento);
            this.documento.setUsuarioRecibe(usuario.getUsuario().getNombreUsuario());
            documentoService.save(documento);
            this.documento = new Documento();
            this.tipoDocumento = new TipoDocumento();
            onMovilidadChange();
        } catch (Exception e) {

        }
    }

    /**
     * Metodo para actualizar documentacion
     *
     * @param documento
     */
    public void preActualizacion(Documento documento) {
        try {
            this.documento = documento;
            this.tipoDocumento = this.documento.getIdTipoDocumento();
        } catch (Exception e) {
        }
    }

    /**
     * Metodo para agregar documentos a convenio
     */
    public void actualzarDocument() {
        try {
            documento.setIdTipoDocumento(tipoDocumento);
            documento.setFechaRecibido(new Date());
            documento.setUsuarioRecibe(usuario.getUsuario().getNombreUsuario());
            documentoService.merge(documento);
            this.documento = new Documento();
            this.tipoDocumento = new TipoDocumento();
            onMovilidadChange();
            FacesMessage message = new FacesMessage("Succesful", " Documento actualizado exitosamente");
        } catch (Exception e) {
        }
    }

    /**
     * Metodo para elimar documentos asignado a convenio
     */
    public void eliminarDocument() {
        try {
            documentoService.delete(this.documento);
            this.documento = new Documento();
            this.tipoDocumento = new TipoDocumento();
            onMovilidadChange();
            FacesMessage message = new FacesMessage("Succesful", " Documento eliminado exitosamente");
        } catch (Exception e) {
        } finally {
            this.documento = new Documento();
            this.tipoDocumento = new TipoDocumento();
        }
    }

    private static String getFileExtension(String fileName) {
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        } else {
            return "";
        }
    }

    /**
     * *
     * Metodo para previzualizar
     *
     * @param documento
     *
     */
    public void preView(Documento documento) {
        try {
            visualizar = Boolean.TRUE;
            this.documento = documento;
            System.out.println("TYPO::::::::::::::::::::::" + getFileExtension(documento.getNombreDocumento()));

            if (getFileExtension(documento.getNombreDocumento()).equals("pdf")) {
                content = new DefaultStreamedContent(new ByteArrayInputStream(documento.getDocumento()), "application/pdf");
            } else {
                System.out.println("EN TRABAJO TYPO::::::::::::::::::::::" + getFileExtension(documento.getNombreDocumento()));
            }

            RequestContext.getCurrentInstance().execute("PF('previewDialog').show()");
            RequestContext.getCurrentInstance().update(":idPreview");

        } catch (Exception e) {
        }
    }

    /**
     * Metodo para realizar las descargar de archivos
     *
     * @param documento
     */
    public void FileDownloadView(Documento documento) throws IOException {
        BufferedOutputStream out = null;
        try {
            this.documento = documento;
            String extension;
            String contentType = null;
            InputStream stream = new ByteArrayInputStream(this.documento.getDocumento());
            extension = getFileExtension(this.documento.getNombreDocumento());

            if (extension.equalsIgnoreCase("docx")) {
                contentType = "application/vnd.ms-word.document";
            } else if (extension.equalsIgnoreCase("pdf")) {
                contentType = "Application/pdf";
            } else if (extension.equalsIgnoreCase("xls")) {
                contentType = "application/vnd.ms-excel";
            } else if (extension.equalsIgnoreCase("xlsx")) {
                contentType = "application/vnd.ms-excel";
            } else if (extension.equalsIgnoreCase("doc")) {
                contentType = "application/ms-word";
            }

            content = new DefaultStreamedContent(stream, contentType, documento.getNombreDocumento());

//            
//            byte[] bytes = documento.getDocumento();    
//            String fileName = documento.getNombreDocumento();    
//
//    FacesContext faces = FacesContext.getCurrentInstance();    
//    HttpServletResponse response = (HttpServletResponse) faces.getExternalContext().getResponse();    
//    response.reset();    
//    response.setContentType(contentType);    
//    response.setContentLength(bytes.length);    
//    response.setHeader("Content-disposition", "attachment; filename=\"" + fileName + "\"");    
//
//    
//
//            
//        out = new BufferedOutputStream(response.getOutputStream());        
//        out.write(bytes);        
//        out.flush();    
//   
//        
//
//    faces.responseComplete();
        } catch (Exception e) {
        } finally {
            if (out != null) {
                out.close();
            }
        } // Gently close stream.    

    }

    /**
     * *********************************
     */
//    public void onPrerender(ComponentSystemEvent event) {  
//  
//        try {  
//            /*******prueba eliminar**/
//            String filePath="C:\\Users\\Joao\\USI\\SISNRI\\src\\main\\webapp\\WEB-INF\\reports\\MANUAL CONVENIOS AÑO 2011-definitivo.docx";
//             File file = new File(filePath);
//             FileInputStream fis = new FileInputStream(file.getAbsolutePath());
//             XWPFDocument pr = new XWPFDocument(fis);
//
//             List<XWPFParagraph> paragraphs = pr.getParagraphs();
//
//                
//            /*******prueba eliminar**/
//                    
//            
//            ByteArrayOutputStream out = new ByteArrayOutputStream();  
//  
//            //Document document = new Document();  
//            //PdfWriter.getInstance(document, out); 
//            PdfOptions options = PdfOptions.create().fontEncoding("windows-1250");
//            PdfConverter.getInstance().convert(pr, out, options);
//            //document.open();  
//  
////             for (XWPFParagraph para : paragraphs) {
////                    System.out.println(para.getText());
////                    document.add(new Paragraph(para.getText()));  
////                }
////            
////            fis.close();
////            document.close();  
//            content = new DefaultStreamedContent(new ByteArrayInputStream(out.toByteArray()), "application/pdf");  
//        } catch (Exception e) {  
//            e.printStackTrace();  
//        }  
//    }  
    public void convertImage() {
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
        }
    }

    public static Integer ConvertDocToPNG(String doc) {
        OfficeManager officeManager = null;
        Integer result = -1;
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
        } catch (Exception ex) {
        } finally {
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

    public static int getCantidad() {
        return 2 + (int) (Math.random() * ((10 - 2) + 1));
    }
  
    
    
    
    //GETTER Y SETTER
    
      public List<Documento> getListadoDocumentos() {
        return listadoDocumentos;
    }

    public void setListadoDocumentos(List<Documento> listadoDocumentos) {
        this.listadoDocumentos = listadoDocumentos;
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

    public StreamedContent getContent() {
        return content;
    }

    public void setContent(StreamedContent content) {
        this.content = content;
    }

    public boolean isVisualizar() {
        return visualizar;
    }

    public void setVisualizar(boolean visualizar) {
        this.visualizar = visualizar;
    }

    public List<PojoMovilidadDocumentacion> getListPojoMovilidad() {
        return listPojoMovilidad;
    }

    public void setListPojoMovilidad(List<PojoMovilidadDocumentacion> listPojoMovilidad) {
        this.listPojoMovilidad = listPojoMovilidad;
    }

    public PojoMovilidadDocumentacion getPojoMovilidadSelected() {
        return pojoMovilidadSelected;
    }

    public void setPojoMovilidadSelected(PojoMovilidadDocumentacion pojoMovilidadSelected) {
        this.pojoMovilidadSelected = pojoMovilidadSelected;
    }

    public PojoMovilidadDocumentacion getPojoToShow() {
        return pojoToShow;
    }

    public void setPojoToShow(PojoMovilidadDocumentacion pojoToShow) {
        this.pojoToShow = pojoToShow;
    }
    
    
}
