package py.com.progweb.prueba.ejb;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import py.com.progweb.prueba.model.AsignacionPuntos;
import py.com.progweb.prueba.model.BolsaPuntos;
import py.com.progweb.prueba.model.Clientes;
import py.com.progweb.prueba.model.ConceptoUsoPuntos;
import py.com.progweb.prueba.model.UsoPuntosCabecera;
import py.com.progweb.prueba.model.VencimientoPuntos;

@Stateless
public class ServiciosDao {
    @PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;
    @Inject
    BolsaPuntosDAO bolsaPuntosDAO;

    public Boolean cargaPuntos (Integer idCliente, Integer montoOperacion, Integer idVencimiento, Integer idAsigPuntos){
        Boolean cargo = false;
        try {
            VencimientoPuntos vp = em.createQuery("select b from VencimientoPuntos b where b.id = :idVencimiento", VencimientoPuntos.class)
                .setParameter("idVencimiento", idVencimiento)
                .getSingleResult();

            Clientes cliente = em.createQuery("select b from Clientes b where b.id = :idCliente", Clientes.class)
                .setParameter("idClietnes", idCliente)
                .getSingleResult();

            AsignacionPuntos ap = em.createQuery("select b from AsignacionPuntos b where b.id = :idAsigPuntos ", AsignacionPuntos.class)
                .setParameter("idAsigPuntos", idAsigPuntos)
                .getSingleResult();

            BolsaPuntos bp = new BolsaPuntos();
            bp.setCliente(cliente);
            bp.setVencimientoPuntos(vp);
            bp.setMontoOperacion(montoOperacion);
            int cantPuntos = montoOperacion/ap.getEquivaleUnPunto();
            bp.setPuntajeAsignado(cantPuntos);
            bp.setSaldoPuntos(cantPuntos);
            bp.setPuntajeUtilizado(0);
            bp.setFecAsig(new SimpleDateFormat("dd/mm/yyyy").format(new Date()));
            em.persist(bp);
            cargo = true;
        } catch (Exception e) {
            System.out.println("Error al cargar puntos"+e);
        }
        return cargo;
    }

    public Boolean usoPuntos (Integer idCliente, Integer idConcepto){
        Boolean uso = false;
        try {
            List <BolsaPuntos> bolsaList = em.createQuery("select b from BolsaPuntos b where b.cliente.id = :id and b.saldoPuntos > 0 order by b.fecAsig", BolsaPuntos.class)
                .setParameter("id", idCliente)
                .getResultList();
            ConceptoUsoPuntos cu = em.createQuery("select b from ConceptoUsoPuntos b where b.id = :idConcepto", ConceptoUsoPuntos.class)
                .setParameter("idConcepto", idConcepto)
                .getSingleResult();
            int puntosNecesarios = cu.getPuntosRequeridos();
            for(BolsaPuntos b:bolsaList){
                if(b.getSaldoPuntos() >= puntosNecesarios){
                    b.setPuntajeUtilizado(puntosNecesarios);
                    b.setSaldoPuntos(b.getSaldoPuntos() - puntosNecesarios);
                    bolsaPuntosDAO.actualizar(b);
                    uso = true;
                    break;
                }else{
                    b.setPuntajeUtilizado(puntosNecesarios);
                    puntosNecesarios = puntosNecesarios - b.getSaldoPuntos();
                    b.setSaldoPuntos(0);
                    bolsaPuntosDAO.actualizar(b);
                }   
            }
        } catch (Exception e) {
            System.out.println("Error al registrar uso de puntos "+e);
        }
        return uso;
    }
}
