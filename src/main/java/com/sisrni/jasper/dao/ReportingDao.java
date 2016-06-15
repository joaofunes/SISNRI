package com.sisrni.jasper.dao;

//import ar.com.fdvs.dj.core.DynamicJasperHelper;
//import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
//import ar.com.fdvs.dj.domain.DynamicReport;
import com.sisrni.dao.generic.SimpleDao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.jdbc.Work;
import org.springframework.stereotype.Repository;
import com.sisrni.jasper.Reporte;

@Repository(value = "reportingDao")
public class ReportingDao extends SimpleDao {

    private final static Log log = LogFactory.getLog(ReportingDao.class);

    public Reporte executeReport(final Reporte reporte) {
        getSessionFactory().getCurrentSession().doWork(new Work() {

            @Override
            public void execute(Connection connection) throws SQLException {
                try {
                    reporte.addParameter("REPORT_CONNECTION", connection);
                    if (reporte.getDataSource() != null) {
                        reporte.setJasperPrint(JasperFillManager.fillReport(reporte.getRutaReportes() + reporte.getNombreArchivo(), reporte.getParametros(), reporte.getDataSource()));
                    } else if (reporte.getNombresArchivos() != null && !reporte.getNombresArchivos().isEmpty()) {
                        if (reporte.getJasperPrints() == null) {
                            reporte.setJasperPrints(new ArrayList<JasperPrint>());
                        }
                        for (String fname : reporte.getNombresArchivos()) {
                            reporte.getJasperPrints().add(JasperFillManager.fillReport(reporte.getRutaReportes() + fname, reporte.getParametros()));
                        }
                    } else if (reporte.getJasperPrint() == null) {
                        reporte.setJasperPrint(JasperFillManager.fillReport(reporte.getRutaReportes() + reporte.getNombreArchivo(), reporte.getParametros()));
                    }
                } catch (JRException e) {
                    log.error(e);
                }
            }
        });
        return reporte;
    }
}
