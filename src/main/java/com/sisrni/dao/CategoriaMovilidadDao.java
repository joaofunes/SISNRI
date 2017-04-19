package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.CategoriaMovilidad;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Usuario
 */
@Repository(value = "categoriamovilidadDao")
public class CategoriaMovilidadDao extends GenericDao<CategoriaMovilidad, Integer>{
    
    public List<CategoriaMovilidad> getAllCategoriasByNameAsc(){
        try{
          Query q = getSessionFactory().getCurrentSession().createQuery("SELECT cat FROM  CategoriaMovilidad cat ORDER BY cat.nombreCategoria ASC");
        return q.list();  
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
     public List<CategoriaMovilidad> getAllByIdDesc(){
       String query="Select c from CategoriaMovilidad c order by c.idCategoriaMovilidad desc";
       Query q= getSessionFactory().getCurrentSession().createQuery(query);
       return q.list();
   }
    
}
