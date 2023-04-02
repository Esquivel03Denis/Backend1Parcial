package py.com.progweb.prueba.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import py.com.progweb.prueba.model.AsignacionPuntos;

@Stateless
public class AsignacionPuntosDAO {
    @PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;

    public void agregar(AsignacionPuntos asignacionPunto){
        em.persist(asignacionPunto);
    }

    public void borrar (Integer id){
        try{
            Query q = em.createQuery("select b from AsignacionPuntos b where b.id = :id", AsignacionPuntos.class)
            .setParameter("id", id);
            AsignacionPuntos asignacionPunto = (AsignacionPuntos)q.getSingleResult();
            em.remove(asignacionPunto);
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void actualizar (AsignacionPuntos asignacionPunto){
        em.merge(asignacionPunto);
    }

    public List<AsignacionPuntos> lista (){
        try {
            Query q = em.createQuery("select b from AsignacionPuntos b", AsignacionPuntos.class);
            return (List<AsignacionPuntos>)q.getResultList();   
        } catch (Exception e) {
            return null;
        }
    }

    public AsignacionPuntos getAsignacionPuntos (Integer id){
        try{
            Query q = em.createQuery("select b from AsignacionPuntos b where b.id = :id", AsignacionPuntos.class)
            .setParameter("id", id);
            return (AsignacionPuntos)q.getSingleResult();
        }catch(Exception e){
            return null;
        }
    }
}
