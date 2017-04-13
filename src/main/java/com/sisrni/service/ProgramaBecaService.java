/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.service;

import com.sisrni.dao.ProgramaBecaDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.ProgramaBeca;
import com.sisrni.service.generic.GenericService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Cortez
 */
@Service(value = "programaBecaService")
public class ProgramaBecaService extends GenericService<ProgramaBeca, Integer> {

    @Autowired
    ProgramaBecaDao programaBecaDoa;

    @Override
    public GenericDao<ProgramaBeca, Integer> getDao() {
        return programaBecaDoa;
    }

    public List<ProgramaBeca> getAllByNameAsc() {
        return programaBecaDoa.getAllByNameAsc();
    }
    
      public List<ProgramaBeca> getAllByIdDesc(){
        return programaBecaDoa.getAllByIdDesc();
    } 
}
