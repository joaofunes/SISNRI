package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.ProgramaMovilidad;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository(value = "programamovilidadDao")
public class ProgramaMovilidadDao  extends GenericDao<ProgramaMovilidad, Integer>{
    
    public List<ProgramaMovilidad> getAllProgramaMovilidadByNameAsc(){
        try{
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT prog FROM ProgramaMovilidad prog ORDER BY prog.nombreProgramaMovilidad ASC");
            return q.list();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }  
       public List<ProgramaMovilidad> getAllByIdDesc(){
       String query="Select p from ProgramaMovilidad p order by p.idProgramaMovilidad desc";
       Query q= getSessionFactory().getCurrentSession().createQuery(query);
       return q.list();
   }
    
}
