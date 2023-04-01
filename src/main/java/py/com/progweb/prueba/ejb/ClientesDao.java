package py.com.progweb.prueba.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import py.com.progweb.prueba.model.Clientes;

@Stateless
public class ClientesDao {
    @PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;

    public void agregar (Clientes cliente){
        em.persist(cliente);
    }

    public void borrar (Integer id){
        try{
            Query q = em.createQuery("select b from Clientes b where b.id = :id", Clientes.class)
            .setParameter("id", id);
            Clientes cliente = (Clientes)q.getSingleResult();
            em.remove(cliente);
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void actualizar (Clientes cliente){
        em.merge(cliente);
    }

    public List<Clientes> lista (){
        try {
            Query q = em.createQuery("select b from Clientes b", Clientes.class);
            return (List<Clientes>)q.getResultList();   
        } catch (Exception e) {
            return null;
        }
    }

    public Clientes getCliente (Integer id){
        try{
            Query q = em.createQuery("select b from Clientes b where b.id = :id", Clientes.class)
            .setParameter("id", id);
            return (Clientes)q.getSingleResult();
        }catch(Exception e){
            return null;
        }
    }
}
