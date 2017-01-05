/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.service;

import com.sisrni.dao.BecaDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Beca;
import com.sisrni.pojo.rpt.PojoBeca;
import com.sisrni.service.generic.GenericService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lillian
 */
@Service(value = "becaService")
public class BecaService extends GenericService<Beca, Integer> {

    @Autowired
    private BecaDao becaDao;

    @Override
    public GenericDao<Beca, Integer> getDao() {
        return becaDao;
    }

    public List<PojoBeca> getBecas() {
        return becaDao.getBecas();
    }
}
