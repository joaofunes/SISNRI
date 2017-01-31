/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.converter;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.TipoBeca;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Joao
 */
@Repository(value = "tipoBecaDao")
public class TipoBecaDao extends GenericDao<TipoBeca, Integer>{
    
}
