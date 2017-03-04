/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.service;

import com.sisrni.dao.NoticiaDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Noticia;
import com.sisrni.service.generic.GenericService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Joao
 */
@Service(value = "noticiaService")
public class NoticiaService extends GenericService<Noticia, Integer> {

    @Autowired
    private NoticiaDao noticiaDao;

    @Override
    public GenericDao<Noticia, Integer> getDao() {
        return noticiaDao;
    }

    public List<Noticia> getActiveNews(Integer categoria) {
        return noticiaDao.getActiveNews(categoria);
    }

    public List<Noticia> getAllNoticiasOrderDescDate() {
        return noticiaDao.getAllNoticiasOrderDescDate();
    }

    public Long getCountNoticiasByCat(String cat, Date desde, Date hasta) {
        return noticiaDao.getCountNoticiasByCat(cat, desde, hasta);
    }

    public List<Noticia> getNoticiasDetalle(Integer categoria, Date desde, Date hasta) {
        return noticiaDao.getNoticiasDetalle(categoria, desde, hasta);
    }

    public Integer noticiasNoVisibles() {
        return noticiaDao.noticiasNoVisibles();
    }
}
