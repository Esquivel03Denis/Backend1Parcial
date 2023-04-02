package py.com.progweb.prueba.ejb;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import py.com.progweb.prueba.model.AsignacionPuntos;
import py.com.progweb.prueba.model.BolsaPuntos;
import py.com.progweb.prueba.model.Clientes;
import py.com.progweb.prueba.model.UsoPuntosCabecera;
import py.com.progweb.prueba.model.VencimientoPuntos;

@Stateless
public class ServiciosDao {
    @PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;

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
            em.persist(bp);
            cargo = true;
        } catch (Exception e) {
            System.out.println("Error al cargar puntos"+e);
        }
        return cargo;
    }

    public Boolean usoPuntos (Integer idCliente, Integer idConcepto){
        Boolean uso = false;
        try{
            List <BolsaPuntos> bolsaList = em.crea
        }
    }
}
