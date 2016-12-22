package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.ProgramaMovilidad;
import java.io.Serializable;
import org.springframework.stereotype.Repository;

@Repository(value = "programamovilidadDao")
public class ProgramaMovilidadDao  extends GenericDao<ProgramaMovilidad, Integer>{
    
}
