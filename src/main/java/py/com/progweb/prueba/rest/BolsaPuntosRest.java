package py.com.progweb.prueba.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import py.com.progweb.prueba.ejb.BolsaPuntosDAO;
import py.com.progweb.prueba.model.BolsaPuntos;

@Path("/BolsaPuntos")
@Consumes("application/json")
@Produces("application/json")
public class BolsaPuntosRest {
    
    @Inject
    private BolsaPuntosDAO bolsaPuntosDAO;

    @GET
    @Path("/lista")
    public Response Listar (){
        try {
            return Response.ok(bolsaPuntosDAO.lista()).build();
        } catch (Exception e) {
            System.out.println(e);
        }
        return Response.status(404).entity("No fue posible obtener la lista BolsaPuntos").build();
    }
    
    @POST
    @Path("/crear")
    public Response crear (BolsaPuntos bp){
        try {
            bolsaPuntosDAO.agregar(bp);
            return Response.ok("Se agrego correctamente").build();
        } catch (Exception e) {
            System.out.println(e);
        }
        return Response.status(404).entity("No fue posible agregar").build();
    }

    @DELETE
    @Path("/borrar")
    public Response borrar (@QueryParam("id") Integer id){
        try {
            bolsaPuntosDAO.borrar(id);
            return Response.ok("Se elimino Correctamente").build();
        } catch (Exception e) {
            return Response.status(404).entity("No fue posible borrar la bolsa").build();
        }
    }

    @PUT
    @Path("/actualizar")
    public Response actualizar(BolsaPuntos bolsaPuntos){
        try {
            bolsaPuntosDAO.actualizar(bolsaPuntos);
            return Response.ok("Se actualizo correctamente").build();
        } catch (Exception e) {
            return Response.status(404).entity("No fue posible actualizar la bolsa").build();
        }
    }
}
