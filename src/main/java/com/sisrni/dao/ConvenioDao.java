/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Convenio;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Joao
 */
@Repository(value = "convenioDao")
public class ConvenioDao extends GenericDao<Convenio, Integer>{
    
}