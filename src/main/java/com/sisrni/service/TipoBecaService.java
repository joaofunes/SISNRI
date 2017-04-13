/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.service;

import com.sisrni.converter.TipoBecaDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.TipoBeca;
import com.sisrni.model.TipoCambio;
import com.sisrni.service.generic.GenericService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Joao
 */

@Service(value = "tipoBecaService")
public class TipoBecaService extends GenericService<TipoBeca, Integer>  {

    @Autowired
    private TipoBecaDao tipoBecaDao;
   
    @Override
    public GenericDao<TipoBeca, Integer> getDao() {
      return tipoBecaDao;
    }
    public List<TipoBeca> getAllByNameAsc(){
        return tipoBecaDao.getAllByNameAsc();
    }
    
    public List<TipoBeca> getAllByIdDesc(){
        return tipoBecaDao.getAllByIdDesc();
    }
}
