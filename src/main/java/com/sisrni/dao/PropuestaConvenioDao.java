/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.PropuestaConvenio;
import com.sisrni.model.TipoPropuestaConvenio;
import com.sisrni.pojo.rpt.PojoPropuestaConvenio;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Joao
 */

@Repository(value = "propuestaConvenioDao")
public class PropuestaConvenioDao extends GenericDao<PropuestaConvenio, Integer>{
    
    
     public List<PropuestaConvenio> getPropuestaConvenioByTipoPropuesta(TipoPropuestaConvenio tipoPropuestaConvenio) {
          try {
             Query q = getSessionFactory().getCurrentSession().createQuery("SELECT a FROM PropuestaConvenio a JOIN FETCH a.idTipoPropuestaConvenio tipo WHERE tipo.nombrePropuestaConvenio =:name");
             q.setParameter("name",tipoPropuestaConvenio.getNombrePropuestaConvenio());       
             return q.list();
       
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
     
     
 public List<PojoPropuestaConvenio> getAllPropuestaConvenioSQL() {
          
          String sql="SELECT * FROM  \n" +
                    "\n" +
                    "(SELECT P_CONVENIO.NOMBRE_PROPUESTA,P_CONVENIO.FINALIDAD_PROPUESTA,\n" +
                    "T_PRO_CONVE.NOMBRE_PROPUESTA_CONVENIO AS TIPO_CONVENIO,STA.NOMBRE_ESTADO,\n" +
                    "P_CONVENIO.ID_PROPUESTA\n" +
                    "FROM PROPUESTA_CONVENIO P_CONVENIO\n" +
                    "INNER JOIN TIPO_PROPUESTA_CONVENIO T_PRO_CONVE\n" +
                    "ON P_CONVENIO.ID_TIPO_PROPUESTA_CONVENIO = T_PRO_CONVE.ID_TIPO_PROPUESTA \n" +
                    "INNER JOIN PROPUESTA_ESTADO P_ESTADO \n" +
                    "ON P_CONVENIO.ID_PROPUESTA = P_ESTADO.ID_PROPUESTA\n" +
                    "INNER JOIN ESTADO STA\n" +
                    "ON P_ESTADO.ID_ESTADO=STA.ID_ESTADO) TB_CONVENIO\n" +
                    "\n" +
                    "LEFT JOIN\n" +
                    "\n" +
                    "(SELECT TB_SOLICITANTE.SOLICITANTE,TB_INTERNO.INTERNO,TB_EXTERNO.EXTERNO,TB_SOLICITANTE.PROPUESTA FROM \n" +
                    "(SELECT CONCAT(PRS.NOMBRE_PERSONA,' ',PRS.APELLIDO_PERSONA) AS SOLICITANTE,PRS_PROP.ID_PROPUESTA AS PROPUESTA\n" +
                    "FROM PERSONA_PROPUESTA PRS_PROP \n" +
                    "INNER JOIN PERSONA PRS\n" +
                    "ON PRS_PROP.ID_PERSONA=PRS.ID_PERSONA\n" +
                    "INNER JOIN TIPO_PERSONA TP_PRS\n" +
                    "ON PRS_PROP.ID_TIPO_PERSONA=TP_PRS.ID_TIPO_PERSONA\n" +
                    "WHERE TP_PRS.NOMBRE='SOLICITANTE') TB_SOLICITANTE\n" +
                    "INNER JOIN\n" +
                    "(SELECT CONCAT(PRS.NOMBRE_PERSONA,' ',PRS.APELLIDO_PERSONA) AS INTERNO,PRS_PROP.ID_PROPUESTA AS PROPUESTA\n" +
                    "FROM PERSONA_PROPUESTA PRS_PROP \n" +
                    "INNER JOIN PERSONA PRS\n" +
                    "ON PRS_PROP.ID_PERSONA=PRS.ID_PERSONA\n" +
                    "INNER JOIN TIPO_PERSONA TP_PRS\n" +
                    "ON PRS_PROP.ID_TIPO_PERSONA=TP_PRS.ID_TIPO_PERSONA\n" +
                    "WHERE TP_PRS.NOMBRE='REFERENTE INTERNO') TB_INTERNO\n" +
                    "ON TB_SOLICITANTE.PROPUESTA=TB_INTERNO.PROPUESTA\n" +
                    "INNER JOIN\n" +
                    "(SELECT CONCAT(PRS.NOMBRE_PERSONA,' ',PRS.APELLIDO_PERSONA) AS EXTERNO,PRS_PROP.ID_PROPUESTA AS PROPUESTA\n" +
                    "FROM PERSONA_PROPUESTA PRS_PROP \n" +
                    "INNER JOIN PERSONA PRS\n" +
                    "ON PRS_PROP.ID_PERSONA=PRS.ID_PERSONA\n" +
                    "INNER JOIN TIPO_PERSONA TP_PRS\n" +
                    "ON PRS_PROP.ID_TIPO_PERSONA=TP_PRS.ID_TIPO_PERSONA\n" +
                    "WHERE TP_PRS.NOMBRE='REFERENTE EXTERNO') TB_EXTERNO\n" +
                    "ON TB_INTERNO.PROPUESTA=TB_EXTERNO.PROPUESTA) TB_PERSONAS\n" +
                    "\n" +
                    "ON TB_CONVENIO.ID_PROPUESTA=TB_PERSONAS.PROPUESTA";
          
          try {
             Query q = getSessionFactory().getCurrentSession().createSQLQuery(sql)
                     .addScalar("NOMBRE_PROPUESTA",new StringType())
                     .addScalar("FINALIDAD_PROPUESTA",new StringType())
                     .addScalar("TIPO_CONVENIO",new StringType())
                     .addScalar("NOMBRE_ESTADO",new StringType())
                     .addScalar("ID_PROPUESTA",new StringType())
                     .addScalar("SOLICITANTE",new StringType())
                     .addScalar("INTERNO",new StringType())
                     .addScalar("EXTERNO",new StringType())
                     .addScalar("PROPUESTA",new StringType())
                     .setResultTransformer(Transformers.aliasToBean(PojoPropuestaConvenio.class));
               
             return q.list();
       
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
