package py.com.progweb.prueba.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import py.com.progweb.prueba.model.BolsaPuntos;
import py.com.progweb.prueba.model.Clientes;

@Stateless
public class BolsaPuntosDAO {
    @PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;

    public void agregar (BolsaPuntos bolsapuntos){
        em.persist(bolsapuntos);
    }

    public void borrar (Integer id){
        try{
            Query q = em.createQuery("select b from BolsaPuntos b where b.id = :id", BolsaPuntos.class)
            .setParameter("id", id);
            BolsaPuntos bolsapuntos = (BolsaPuntos)q.getSingleResult();
            em.remove(bolsapuntos);
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void actualizar (BolsaPuntos bolsapuntos){
        em.merge(bolsapuntos);
    }

    public List<BolsaPuntos> lista (){
        try {
            Query q = em.createQuery("select b from BolsaPuntos b", BolsaPuntos.class);
            return (List<BolsaPuntos>)q.getResultList();   
        } catch (Exception e) {
            return null;
        }
    }

    public BolsaPuntos getCliente (Integer id){
        try{
            Query q = em.createQuery("select b from BolsaPuntos b where b.id = :id", BolsaPuntos.class)
            .setParameter("id", id);
            return (BolsaPuntos)q.getSingleResult();
        }catch(Exception e){
            return null;
        }
    }

}
