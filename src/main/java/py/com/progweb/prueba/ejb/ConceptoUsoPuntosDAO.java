package py.com.progweb.prueba.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import py.com.progweb.prueba.model.ConceptoUsoPuntos;

@Stateless
public class ConceptoUsoPuntosDAO {
    @PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;

    public void agregar (ConceptoUsoPuntos conceptoUsoPunto){
        em.persist(conceptoUsoPunto);
    }

    public void borrar (Integer id){
        try{
            Query q = em.createQuery("select b from ConceptoUsoPuntos b where b.id = :id", ConceptoUsoPuntos.class)
            .setParameter("id", id);
            ConceptoUsoPuntos conceptoUsoPunto = (ConceptoUsoPuntos)q.getSingleResult();
            em.remove(conceptoUsoPunto);
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void actualizar (ConceptoUsoPuntos conceptoUsoPunto){
        em.merge(conceptoUsoPunto);
    }

    public List<ConceptoUsoPuntos> lista (){
        try {
            Query q = em.createQuery("select b from ConceptoUsoPuntos b", ConceptoUsoPuntos.class);
            return (List<ConceptoUsoPuntos>)q.getResultList();   
        } catch (Exception e) {
            return null;
        }
    }

    public ConceptoUsoPuntos getconceptoUsoPunto (Integer id){
        try{
            Query q = em.createQuery("select b from ConceptoUsoPuntos b where b.id = :id", ConceptoUsoPuntos.class)
            .setParameter("id", id);
            return (ConceptoUsoPuntos)q.getSingleResult();
        }catch(Exception e){
            return null;
        }
    }
}
