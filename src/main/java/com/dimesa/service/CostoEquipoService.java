/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimesa.service;

import com.dimesa.dao.CostoEquipoDao;
import com.dimesa.dao.generic.GenericDao;
import com.dimesa.model.CostoEquipo;
import com.dimesa.service.generic.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HDEZ
 */

@Service(value="costoEquipoService")
public class CostoEquipoService  extends GenericService<CostoEquipo, Integer>{

    
    @Autowired
    private CostoEquipoDao costoEquipoDao;
    @Override
    public GenericDao<CostoEquipo, Integer> getDao() {
       return costoEquipoDao;
    }
    
}
