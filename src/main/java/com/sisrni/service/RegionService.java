/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.service;

import com.sisrni.dao.RegionDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Region;
import com.sisrni.service.generic.GenericService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Joao
 */
@Service(value = "regionService")
public class RegionService extends GenericService<Region, Integer>  {
    @Autowired
    private RegionDao regionDao;
    
    @Override
    public GenericDao<Region, Integer> getDao() {
      return regionDao;
    }
        public List<Region> getAllByIdDesc(){
        return regionDao.getAllByIdDesc();
    }  
    
}
