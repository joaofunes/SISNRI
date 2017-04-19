/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.CategoriaDocumento;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Luis
 */
@Repository(value = "categoriaDocumentoDao")
public class CategoriaDocumentoDao extends GenericDao<CategoriaDocumento, Integer >{
   
    public List<Integer> getCategoriasDocumentoProyecto(Integer idProy){
        try{
        String sql = "SELECT a.idCategoriaDocumento FROM Proyecto p JOIN p.proyectoGenerico pg JOIN pg.categoriaDocumentoList a  WHERE p.idProyecto =:proy ";
        
        Query q = getSessionFactory().getCurrentSession().createQuery(sql);
        q.setParameter("proy", idProy);
        return q.list();
        }catch(Exception e){
            e.printStackTrace();
        } 
        return null;
    }
    
    public List<CategoriaDocumento> getAllByIdDesc(){
       String query="Select c from CategoriaDocumento c order by c.idCategoriaDoc desc";
       Query q= getSessionFactory().getCurrentSession().createQuery(query);
       return q.list();
   }
    
}
