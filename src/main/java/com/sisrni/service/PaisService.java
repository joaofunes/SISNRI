/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.service;

import com.sisrni.dao.PaisDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Pais;
import com.sisrni.pojo.rpt.PojoPais;
import com.sisrni.service.generic.GenericService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Joao
 */
@Service(value = "paisService")
public class PaisService extends GenericService<Pais, Integer> {

    @Autowired
    private PaisDao paisDao;

    @Override
    public GenericDao<Pais, Integer> getDao() {
        return paisDao;
    }

    public List<PojoPais> getPaises(Integer idPaisSearch) {
        return paisDao.getPaises(idPaisSearch);
    }

    public List<Pais> getPaisesByRegionId(Integer id) {
        return paisDao.getPaisesByRegionId(id);
    }

    public List<Pais> getCountriesOrderByNameAsc() {
        return paisDao.getCountriesOrderByNameAsc();
    }

    public List<Pais> getAllByNameAsc(){
        return paisDao.getAllByNameAsc();
    }

     public Pais getPaisCodigoPais(String codPais){
         return paisDao.getPaisCodigoPais(codPais);
     }
     
     public List<Pais> getAllByIdDesc(){
        return paisDao.getAllByIdDesc();
    }  

}
