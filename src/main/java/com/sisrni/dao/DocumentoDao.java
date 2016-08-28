/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Documento;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Joao
 */

@Repository(value = "documentoDao")
public class DocumentoDao extends GenericDao<Documento, Integer> {

    public List<Documento> getDocumentFindCovenio(String convenio) {
        try {
             Query q = getSessionFactory().getCurrentSession().createQuery("SELECT doc FROM Documento doc JOIN FETCH doc.idPropuesta pro  JOIN FETCH doc.idTipoDocumento tipo ");
          //  WHERE pro.nombrePropuesta LIKE  :convenio q.setParameter("convenio", convenio);
             return q.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
