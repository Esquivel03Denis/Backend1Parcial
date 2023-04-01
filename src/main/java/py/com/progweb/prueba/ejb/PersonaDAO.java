package py.com.progweb.prueba.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import py.com.progweb.prueba.model.Persona;

@Stateless
public class PersonaDAO {
    @PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;
    
    public void agregar (Persona entidad){
        this.em.persist(entidad);
    }

    public List<Persona> lista (){
        Query  q = em.createQuery("SELECT p FROM Persona p");
        return (List<Persona>)q.getResultList();
    }
}
