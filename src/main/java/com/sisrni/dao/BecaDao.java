/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Beca;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lillian
 */
@Repository(value = "becaDao")
public class BecaDao extends GenericDao<Beca, Integer> {
    
}
