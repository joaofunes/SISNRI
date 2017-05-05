/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Documento;
import com.sisrni.model.Movilidad;
import com.sisrni.model.Proyecto;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Joao
 */
@Repository(value = "documentoDao")
public class DocumentoDao extends GenericDao<Documento, Integer> {

    /**
     * Metodo para obtener los documentos adjunto a convenio
     *
     * @param propuestaConvenio
     * @return
     */
    public List<Documento> getDocumentFindCovenio(int propuestaConvenio) {
        try {
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT doc FROM Documento doc JOIN FETCH doc.idTipoDocumento tipo WHERE doc.idPropuesta.idPropuesta =:id");
            q.setParameter("id", propuestaConvenio);
            return q.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Documento> getDocumentFindBeca(Integer idBeca) {
        try {
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT doc FROM Documento doc JOIN FETCH doc.idTipoDocumento tipo WHERE doc.idBeca.idBeca =:idBeca");
            q.setParameter("idBeca", idBeca);
            return q.list();
        } catch (Exception e) {
        }
        return null;
    }
    public List<Documento> getDocumentFindProyecto(Integer idProyecto) {
        try {
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT doc FROM Documento doc JOIN FETCH doc.idTipoDocumento tipo WHERE doc.idProyecto.idProyecto =:idProyecto");
            q.setParameter("idProyecto", idProyecto);
            return q.list();
        } catch (Exception e) {
        }
        return null;
    }
    
    public List<Documento> getDocumentoFindMovilidad(Integer idMovilidad){
        try{
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT doc FROM Documento doc JOIN FETCH doc.idTipoDocumento tipo WHERE doc.idMovilidad.idMovilidad =:idmov");
            q.setParameter("idmov", idMovilidad);
            return q.list();
        }catch(Exception e){
            
        }
        return null;
    }
    
    public void eliminarDocumento(Proyecto proyecto) {
        String query = "Delete from DOCUMENTO WHERE ID_PROYECTO=" + proyecto.getIdProyecto();
        Query q = getSessionFactory().getCurrentSession().createSQLQuery(query);
        q.executeUpdate();
    }
    
    public void eliminarDocumentosMovilidad(Movilidad movilidad){
        String query = "DELETE FROM DOCUMENTO WHERE ID_MOVILIDAD = "+movilidad.getIdMovilidad();
        Query q = getSessionFactory().getCurrentSession().createSQLQuery(query);
        q.executeUpdate();
    }
    
     public int deleteDocumentosPropuestas(int propuesta){
        try {
            
            String sql="DELETE FROM DOCUMENTO WHERE ID_PROPUESTA ="+propuesta;
            Query q = getSessionFactory().getCurrentSession().createSQLQuery(sql);
            getSessionFactory().getCurrentSession().flush();
            getSessionFactory().getCurrentSession().clear();
            int executeUpdate = q.executeUpdate();
            return executeUpdate;
        } catch (Exception e) {
          e.printStackTrace();
        }
        return 0;
    }
}
