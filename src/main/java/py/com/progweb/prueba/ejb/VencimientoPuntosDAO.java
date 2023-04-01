package py.com.progweb.prueba.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import py.com.progweb.prueba.model.VencimientoPuntos;

@Stateless
public class VencimientoPuntosDAO {
    @PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;

    public void agregar (VencimientoPuntos vencimientoPunto){
        em.persist(vencimientoPunto);
    }

    public void borrar (Integer id){
        try{
            Query q = em.createQuery("select b from vencimiento_puntos b where b.id = :id", VencimientoPuntos.class)
            .setParameter("id", id);
            VencimientoPuntos vencimientoPunto = (VencimientoPuntos)q.getSingleResult();
            em.remove(vencimientoPunto);
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void actualizar (VencimientoPuntos vencimientoPunto){
        em.merge(vencimientoPunto);
    }

    public List<VencimientoPuntos> lista (){
        try {
            Query q = em.createQuery("select b from vencimiento_puntos b", VencimientoPuntos.class);
            return (List<VencimientoPuntos>)q.getResultList();   
        } catch (Exception e) {
            return null;
        }
    }

    public VencimientoPuntos getvencimientoPunto (Integer id){
        try{
            Query q = em.createQuery("select b from vencimiento_puntos b where b.id = :id", VencimientoPuntos.class)
            .setParameter("id", id);
            return (VencimientoPuntos)q.getSingleResult();
        }catch(Exception e){
            return null;
        }
    }
}
