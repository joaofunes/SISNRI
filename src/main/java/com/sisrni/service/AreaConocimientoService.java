/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.service;

import com.sisrni.dao.*;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.AreaConocimiento;
import com.sisrni.service.generic.GenericService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lillian
 */
@Service(value ="areaConocimientoService")
public class AreaConocimientoService extends GenericService<AreaConocimiento, Integer>{

    @Autowired
    private AreaConocimientoDao areaConocimientoDao;
    @Override
    public GenericDao<AreaConocimiento, Integer> getDao() {
        return areaConocimientoDao;
    }
    
   
     public List<Integer> getAreasConocimientoProyecto(Integer id){
        return areaConocimientoDao.getAreasConocimientoProyecto(id);
    }
    public List<AreaConocimiento> getAllByNameAsc(){
        return areaConocimientoDao.getAllByNameAsc();
    }
    
     public List<AreaConocimiento> getAllByIdDesc(){
        return areaConocimientoDao.getAllByIdDesc();
    }
}
