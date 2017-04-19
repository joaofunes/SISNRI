/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.service;


import com.sisrni.dao.TipoOrganismoDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.TipoOrganismo;
import com.sisrni.service.generic.GenericService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Joao
 */
@Service(value = "tipoOrganismoService")
public class TipoOrganismoService extends GenericService<TipoOrganismo, Integer>  {

    @Autowired
    private TipoOrganismoDao tipoOrganismoDao;
    @Override
    public GenericDao<TipoOrganismo, Integer> getDao() {
      return tipoOrganismoDao;
    }
    public List<TipoOrganismo> getAllByNameAsc(){
        return tipoOrganismoDao.getAllByNameAsc();
    }
    
       public List<TipoOrganismo> getAllByIdDesc(){
        return tipoOrganismoDao.getAllByIdDesc();
    }
    
}
