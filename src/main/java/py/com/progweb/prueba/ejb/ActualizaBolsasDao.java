package py.com.progweb.prueba.ejb;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import py.com.progweb.prueba.model.BolsaPuntos;

public class ActualizaBolsasDao implements Job{
    /*@PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;*/
    @Inject
    private BolsaPuntosDAO bolsaPuntosDAO;
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

    public void execute(JobExecutionContext context) throws JobExecutionException {
        // TODO Auto-generated method stub
        try {
            /*List <BolsaPuntos> bolsaList = em.createQuery("select b from BolsaPuntos b where b.saldoPuntos > 0 order by b.fecAsig desc", BolsaPuntos.class)
                .getResultList(); */
                List <BolsaPuntos> bolsaList = bolsaPuntosDAO.lista();  
            Date fecHoy = new Date();
            for (BolsaPuntos b:bolsaList){

                if (formato.parse(b.getFecCaducidad()).equals(fecHoy) || formato.parse(b.getFecCaducidad()).before(fecHoy)){
                    b.setSaldoPuntos(0);
                    bolsaPuntosDAO.actualizar(b);
                }
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar saldo de bolsas "+e);
        }
    }
}
