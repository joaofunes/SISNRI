/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;


import com.sisrni.model.TipoDocumento;
import com.sisrni.model.CategoriaDocumento;
import com.sisrni.service.TipoDocumentoService;
import com.sisrni.service.CategoriaDocumentoService;
import com.sisrni.utils.JsfUtil;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;


/**
 *
 * @author Luis
 */
@Named(value = "tipoDocumentoMB")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class TipoDocumentoMB{
      /*Para Errores*/
    private final static Log log = LogFactory.getLog(TipoDocumentoMB.class);

    
    /*Variables */
    private TipoDocumento tipoDocumento;
    private CategoriaDocumento categoriaDocumento;
    private List<CategoriaDocumento> listCategoriaDocumento;
    private List<TipoDocumento> listTipoDocumento;
    private boolean actualizar;
    
    
    
    @Autowired
    @Qualifier(value = "tipoDocumentoService")
    private TipoDocumentoService tipoDocumentoService;
    
    
    @Autowired
    @Qualifier(value = "categoriaDocumentoService")
    private CategoriaDocumentoService categoriaDocumentoService;
    
    
    /*Constructor*/
    public TipoDocumentoMB(){
        
    }
    
    
    /*Post Constructor*/
    @PostConstruct
    public void init(){
        cargarTipoDocumento();
    }
    
    /** 
     * Metodo que crea instancias de 'TipoDocumento' y 'CategoriaDocumento' y almacena
     * en una Lista todas las instancias de 'CategoriaDocumento' que se encuentra en la
     * tabla respectiva
     */
    
    public void cargarTipoDocumento(){
        tipoDocumento = new TipoDocumento();
        categoriaDocumento = new CategoriaDocumento();
        listCategoriaDocumento = categoriaDocumentoService.getAllByIdDesc();
        listTipoDocumento = tipoDocumentoService.getAllByIdDesc();
        actualizar = false;
    }
    
    
    /** 
     * Metodo para guardar una instancia de 'TipoDocumento' en la tabla 
     * correspondiente de la base de datos
     */
    public void guardarTipoDocumento(){
        String msg ="Tipo de Documento Almacenado Exitosamente!";
        try{
           tipoDocumento.setIdTipoDocumento(Integer.MIN_VALUE);
           tipoDocumento.setIdCategoriaDoc(categoriaDocumentoService.findById(categoriaDocumento.getIdCategoriaDoc()));
           tipoDocumentoService.save(tipoDocumento);
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Guardado!!", msg));
           
        }catch(Exception e){
            JsfUtil.addErrorMessage("Error al Guardar Tipo de Documento!");
            e.printStackTrace();
        }
        cargarTipoDocumento();
    }
    
    /**
     * Metodo que se ocupa de precargar la instancia de 'TipoDocumento' a ser actualizada
     * @param tipoDocumento
     */
    public void preActualizarTipoDocumento(TipoDocumento tipoDocumento){
       try{ 
        actualizar = true;
        this.tipoDocumento = tipoDocumento;
        this.categoriaDocumento.setIdCategoriaDoc(tipoDocumento.getIdCategoriaDoc().getIdCategoriaDoc());
       }catch(Exception e){
           System.out.println(e.getMessage());
       }
    }
    
    /**
     * Metodo para actualizar las propiedades de la instancia de 'TipoDocumento'
     * seleccionada
     */
    public void actualizarTipoDocumento(){
        String msg ="Tipo de Documento Actualizado Exitosamente!";
        try{
            tipoDocumento.setIdCategoriaDoc(categoriaDocumentoService.findById(categoriaDocumento.getIdCategoriaDoc()));
            //actualizando la instancia
            tipoDocumentoService.merge(tipoDocumento);
            actualizar = false;
            cancelarTipoDocumento(); 
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Actualizacion!!", msg));
        }catch(Exception e){
            JsfUtil.addErrorMessage("Error al Actualizar Tipo de Documento");
            e.printStackTrace();
        }
        cargarTipoDocumento(); 
    }
    
 
    
    /**
     * Metodo  de Pre- borrado, que obtiene una instancia de la Entity a Borrar
     * y despliega un 'p:dialog' donde se solicita la confirmacion de la 
     * operacion de borrado
     * @param tipoDocumento
     */
    public void preBorradoTipoDocumento(TipoDocumento tipoDocumento){
        //guardando en 'tipoDocumento' la instancia de 'TipoDocumento' que se recibe como argumento
        this.tipoDocumento = tipoDocumento;
        RequestContext context = RequestContext.getCurrentInstance();
        //Desplegando el 'Dialog'
        context.execute("PF('confirmDeleteTipoDocumentoDlg').show();");
    }
    
    /**
     * Metodo que borra una instancia de 'TipoDocumento' de la Base de datos
     */
    public void borrarTipoDocumento(){ 
        String msg ="Tipo de Documento Eliminado Exitosamente!";
        try{
            //Borrando la instancia de tipoDocumento
            tipoDocumentoService.delete(tipoDocumento);
            cargarTipoDocumento();
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('confirmDeleteTipoDocumentoDlg').hide();"); 
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Eliminado!!", msg));
        }catch(Exception e){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"ERROR!!", "Error al Eliminar, verifique que no existan otros elementos vinculados a este registro de Tipo de Documento!"));
            e.printStackTrace();
        }finally{
            actualizar = false;
        }
        
        
    }
    
    
    /**
     * Metodo que se encarga de limpiar el formulario de creacion y 
     * actualizacion de TipoDocumento
     */
    public void cancelarTipoDocumento(){
        String msg ="Tipo de Documento cancelado";
        try{
        tipoDocumento = null;
        tipoDocumento = new TipoDocumento();
        categoriaDocumento=null;
        categoriaDocumento = new CategoriaDocumento();
        RequestContext.getCurrentInstance().reset(":formTipoDocumento");
        if(actualizar)
        JsfUtil.addSuccessMessage(msg);
        
        }catch(Exception e){
             System.out.println(e.getMessage());
        }
      cargarTipoDocumento();
    }
            
    
    
  
    
    /*Getters y Setters*/
       
    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    
    public CategoriaDocumento getCategoriaDocumento() {
        return categoriaDocumento;
    }

    public void setCategoriaDocumento(CategoriaDocumento categoriaDocumento) {
        this.categoriaDocumento = categoriaDocumento;
    }

    
    public List<CategoriaDocumento> getListCategoriaDocumento() {
        return listCategoriaDocumento;
    }

    public void setListCategoriaDocumento(List<CategoriaDocumento> listCategoriaDocumento) {
        this.listCategoriaDocumento = listCategoriaDocumento;
    }

    public List<TipoDocumento> getListTipoDocumento() {
        return listTipoDocumento;
    }

    public void setListTipoDocumento(List<TipoDocumento> listTipoDocumento) {
        this.listTipoDocumento = listTipoDocumento;
    }

    public boolean isActualizar() {
        return actualizar;
    }

    public void setActualizar(boolean actualizar) {
        this.actualizar = actualizar;
    }
    
   
    
}
