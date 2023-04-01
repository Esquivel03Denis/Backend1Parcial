package py.com.progweb.prueba.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import py.com.progweb.prueba.ejb.PersonaDAO;
import py.com.progweb.prueba.model.Persona;

@Path("/persona")
@Consumes("application/json")
@Produces("application/json")
public class PersonaRest {
    @Inject
    private PersonaDAO personaDAO;

    @GET
    @Path("/getLista")
    public Response Listar (){
        return Response.ok(personaDAO.lista()).build();
    }

    @POST
    @Path("/crear")
    public Response crear (Persona p){
        personaDAO.agregar(p);
        return Response.ok().build();
    }
}
