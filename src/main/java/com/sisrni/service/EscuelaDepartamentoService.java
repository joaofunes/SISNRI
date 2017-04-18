/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.service;

import com.sisrni.dao.EscuelaDepartamentoDao;
import com.sisrni.dao.FacultadDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.EscuelaDepartamento;
import com.sisrni.model.EtapaMovilidad;

import com.sisrni.model.Facultad;
import com.sisrni.service.generic.GenericService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lillian
 */
@Service(value = "escuelaDepartamentoService")
public class EscuelaDepartamentoService extends GenericService<EscuelaDepartamento, Integer> {

    @Autowired
    private EscuelaDepartamentoDao escuelaDepartamentoDao;

    @Override
    public GenericDao<EscuelaDepartamento, Integer> getDao() {
        return escuelaDepartamentoDao;
    }

    public List<EscuelaDepartamento> getEscuelasOrDeptoByFacultadId(Integer id) {
        return escuelaDepartamentoDao.getEscuelasOrDeptoByFacultadId(id);
    }

     public EscuelaDepartamento getByID(int id) {
        return escuelaDepartamentoDao.findById(id);
    }
    
         public List<EscuelaDepartamento> getAllByIdDesc(){
        return escuelaDepartamentoDao.getAllByIdDesc();
    } 
}
