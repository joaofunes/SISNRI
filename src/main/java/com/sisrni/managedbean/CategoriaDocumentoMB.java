/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;


import com.sisrni.model.CategoriaDocumento;
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
@Named(value = "categoriaDocumentoMB")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class CategoriaDocumentoMB{
      /*Para Errores*/
    private final static Log log = LogFactory.getLog(CategoriaDocumentoMB.class);

    
    /*Variables */
    private CategoriaDocumento categoriaDocumento;
    private List<CategoriaDocumento> listCategoriaDocumento;
    private boolean actualizar;
    
    
    
    @Autowired
    @Qualifier(value = "categoriaDocumentoService")
    private CategoriaDocumentoService categoriaDocumentoService;
    
    
    /*Constructor*/
    public CategoriaDocumentoMB(){
        
    }
    
    
    /*Post Constructor*/
    @PostConstruct
    public void init(){
        cargarCategoriaDocumento();
    }
    
    /** 
     * Metodo que crea instancias de 'CategoriaDocumento' y 'Organismo' y almacena
     * en una Lista todas las instancias de 'Organismo' que se encuentra en la
     * tabla respectiva
     */
    
    public void cargarCategoriaDocumento(){
        categoriaDocumento = new CategoriaDocumento();
        listCategoriaDocumento = categoriaDocumentoService.getAllByIdDesc();
        actualizar = false;
    }
    
    
    /** 
     * Metodo para guardar una instancia de 'CategoriaDocumento' en la tabla 
     * correspondiente de la base de datos
     */
    public void guardarCategoriaDocumento(){
        String msg ="Categoria de Documento Almacenada Exitosamente!";
        try{
           categoriaDocumento.setIdCategoriaDoc(Integer.MIN_VALUE);
           categoriaDocumentoService.save(categoriaDocumento);
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Guardada!!", msg));
           
        }catch(Exception e){
            JsfUtil.addErrorMessage("Error al Guardar Categoria de Documento!");
            e.printStackTrace();
        }
        cargarCategoriaDocumento();
    }
    
    /**
     * Metodo que se ocupa de precargar la instancia de 'CategoriaDocumento' a ser actualizada
     * @param categoriaDocumento
     */
    public void preActualizarCategoriaDocumento(CategoriaDocumento categoriaDocumento){
       try{ 
        actualizar = true;
        this.categoriaDocumento = categoriaDocumento;
       }catch(Exception e){
           System.out.println(e.getMessage());
       }
    }
    
    /**
     * Metodo para actualizar las propiedades de la instancia de 'CategoriaDocumento'
     * seleccionada
     */
    public void actualizarCategoriaDocumento(){
        String msg ="Categoria de Documento Actualizada Exitosamente!";
        try{
            //actualizando la instancia
            categoriaDocumentoService.merge(categoriaDocumento);
            actualizar = false;
            cancelarCategoriaDocumento(); 
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Actualizacion!!", msg));
        }catch(Exception e){
            JsfUtil.addErrorMessage("Error al Actualizar la Categor&iacute;a Documento");
            e.printStackTrace();
        }
        cargarCategoriaDocumento(); 
    }
    
    
    /**
     * Metodo  de Pre- borrado, que obtiene una instancia de la Entity a Borrar
     * y despliega un 'p:dialog' donde se solicita la confirmacion de la 
     * operacion de borrado
     * @param categoriaDocumento
     */
    public void preBorradoCategoriaDocumento(CategoriaDocumento categoriaDocumento){
        //guardando en 'categoriaDocumento' la instancia de 'CategoriaDocumento' que se recibe como argumento
        this.categoriaDocumento = categoriaDocumento;
        RequestContext context = RequestContext.getCurrentInstance();
        //Desplegando el 'Dialog'
        context.execute("PF('confirmDeleteCategoriaDocumentoDlg').show();");
    }
    
    /**
     * Metodo que borra una instancia de 'CategoriaDocumento' de la Base de datos
     */
    public void borrarCategoriaDocumento(){ 
        String msg ="Categoria de Documento Eliminada Exitosamente!";
        try{
            //Borrando la instancia de categoriaDocumento
            categoriaDocumentoService.delete(categoriaDocumento);
            cargarCategoriaDocumento();
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('confirmDeleteCategoriaDocumentoDlg').hide();"); 
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Eliminada!!", msg));
        }catch(Exception e){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"ERROR!!", "Error al Eliminar, verifique que no existan otros elementos vinculados a este registro de Categor&iacute;a de Documento!"));
            e.printStackTrace();
        }finally{
            actualizar = false;
        }
        
        
    }
    
    
    /**
     * Metodo que se encarga de limpiar el formulario de creacion y 
     * actualizacion de CategoriaDocumento
     */
    public void cancelarCategoriaDocumento(){
        String msg ="Categoria de Documento cancelada";
        try{
        categoriaDocumento = null;
        categoriaDocumento = new CategoriaDocumento();
        RequestContext.getCurrentInstance().reset(":formCategoriaDocumento");
        if(actualizar)
        JsfUtil.addSuccessMessage(msg);
        }catch(Exception e){
             System.out.println(e.getMessage());
        }
      cargarCategoriaDocumento();
    }
            
    

    
    /*Getters y Setters*/

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

    public boolean isActualizar() {
        return actualizar;
    }

    public void setActualizar(boolean actualizar) {
        this.actualizar = actualizar;
    }
    
   
    
}
