/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;


import com.sisrni.model.CategoriaNoticia;
import com.sisrni.service.CategoriaNoticiaService;
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
@Named(value = "categoriaNoticiaMB")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class CategoriaNoticiaMB{
      /*Para Errores*/
    private final static Log log = LogFactory.getLog(CategoriaNoticiaMB.class);

    
    /*Variables */
    private CategoriaNoticia categoriaNoticia;
    private List<CategoriaNoticia> listCategoriaNoticia;
    private boolean actualizar;
    
    
    
    @Autowired
    @Qualifier(value = "categoriaNoticiaService")
    private CategoriaNoticiaService categoriaNoticiaService;
    
    
    /*Constructor*/
    public CategoriaNoticiaMB(){
        
    }
    
    
    /*Post Constructor*/
    @PostConstruct
    public void init(){
        cargarCategoriaNoticia();
    }
    
    /** 
     * Metodo que crea instancias de 'CategoriaNoticia' y 'Organismo' y almacena
     * en una Lista todas las instancias de 'Organismo' que se encuentra en la
     * tabla respectiva
     */
    
    public void cargarCategoriaNoticia(){
        categoriaNoticia = new CategoriaNoticia();
        listCategoriaNoticia = categoriaNoticiaService.getAllByIdDesc();
        actualizar = false;
    }
    
    
    /** 
     * Metodo para guardar una instancia de 'CategoriaNoticia' en la tabla 
     * correspondiente de la base de datos
     */
    public void guardarCategoriaNoticia(){
        String msg ="Categoria de Noticia Almacenada Exitosamente!";
        try{
           categoriaNoticia.setIdCategoria(Integer.MIN_VALUE);
           categoriaNoticiaService.save(categoriaNoticia);
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Guardada!!", msg));
           
        }catch(Exception e){
            JsfUtil.addErrorMessage("Error al Guardar Categoria de Noticia!");
            e.printStackTrace();
        }
        cargarCategoriaNoticia();
    }
    
    /**
     * Metodo que se ocupa de precargar la instancia de 'CategoriaNoticia' a ser actualizada
     * @param categoriaNoticia
     */
    public void preActualizarCategoriaNoticia(CategoriaNoticia categoriaNoticia){
       try{ 
        actualizar = true;
        this.categoriaNoticia = categoriaNoticia;
       }catch(Exception e){
           System.out.println(e.getMessage());
       }
    }
    
    /**
     * Metodo para actualizar las propiedades de la instancia de 'CategoriaNoticia'
     * seleccionada
     */
    public void actualizarCategoriaNoticia(){
        String msg ="Categoria de Noticia Actualizada Exitosamente!";
        try{
            //actualizando la instancia
            categoriaNoticiaService.merge(categoriaNoticia);
            actualizar = false;
            cancelarCategoriaNoticia(); 
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Actualizacion!!", msg));
        }catch(Exception e){
            JsfUtil.addErrorMessage("Error al Actualizar Categoria de Noticia");
            e.printStackTrace();
        }
        cargarCategoriaNoticia(); 
    }
    
    
    /**
     * Metodo  de Pre- borrado, que obtiene una instancia de la Entity a Borrar
     * y despliega un 'p:dialog' donde se solicita la confirmacion de la 
     * operacion de borrado
     * @param categoriaNoticia
     */
    public void preBorradoCategoriaNoticia(CategoriaNoticia categoriaNoticia){
        //guardando en 'categoriaNoticia' la instancia de 'CategoriaNoticia' que se recibe como argumento
        this.categoriaNoticia = categoriaNoticia;
        RequestContext context = RequestContext.getCurrentInstance();
        //Desplegando el 'Dialog'
        context.execute("PF('confirmDeleteCategoriaNoticiaDlg').show();");
    }
    
    /**
     * Metodo que borra una instancia de 'CategoriaNoticia' de la Base de datos
     */
    public void borrarCategoriaNoticia(){ 
        String msg ="Categoria de Noticia Eliminada Exitosamente!";
        try{
            //Borrando la instancia de categoriaNoticia
            categoriaNoticiaService.delete(categoriaNoticia);
            cargarCategoriaNoticia();
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('confirmDeleteCategoriaNoticiaDlg').hide();"); 
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Eliminada!!", msg));
        }catch(Exception e){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"ERROR!!", "Error al Eliminar, verifique que no existan otros elementos vinculados a este registro de Categoria de Noticia!"));
            e.printStackTrace();
        }finally{
            actualizar = false;
        }
        
        
    }
    
    
    /**
     * Metodo que se encarga de limpiar el formulario de creacion y 
     * actualizacion de CategoriaNoticia
     */
    public void cancelarCategoriaNoticia(){
        String msg ="Categoria de Noticia cancelada";
        try{
        categoriaNoticia = null;
        categoriaNoticia = new CategoriaNoticia();
        RequestContext.getCurrentInstance().reset(":formCategoriaNoticia");
         if(actualizar)
        JsfUtil.addSuccessMessage(msg);
        }catch(Exception e){
             System.out.println(e.getMessage());
        }
      cargarCategoriaNoticia();
    }
            
    

    
    /*Getters y Setters*/

    public CategoriaNoticia getCategoriaNoticia() {
        return categoriaNoticia;
    }

    public void setCategoriaNoticia(CategoriaNoticia categoriaNoticia) {
        this.categoriaNoticia = categoriaNoticia;
    }

    public List<CategoriaNoticia> getListCategoriaNoticia() {
        return listCategoriaNoticia;
    }

    public void setListCategoriaNoticia(List<CategoriaNoticia> listCategoriaNoticia) {
        this.listCategoriaNoticia = listCategoriaNoticia;
    }

    public boolean isActualizar() {
        return actualizar;
    }

    public void setActualizar(boolean actualizar) {
        this.actualizar = actualizar;
    }
    
   
    
}
