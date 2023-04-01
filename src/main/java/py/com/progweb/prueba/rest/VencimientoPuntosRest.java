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

import py.com.progweb.prueba.ejb.VencimientoPuntosDAO;
import py.com.progweb.prueba.model.VencimientoPuntos;

@Path("/Vencimientopuntos")
@Consumes("application/json")
@Produces("application/json")
public class VencimientoPuntosRest {
    @Inject
    private VencimientoPuntosDAO vencimientoPuntosDAO;

    @GET
    @Path("/lista")
    public Response Listar (){
        try {
            return Response.ok(vencimientoPuntosDAO.lista()).build();
        } catch (Exception e) {
            System.out.println(e);
        }
        return Response.status(404).entity("No fue posible obtener la lista de Vencimiento de Puntos").build();
    }

    @POST
    @Path("/crear")
    public Response crear (VencimientoPuntos p){
        try {
            vencimientoPuntosDAO.agregar(p);
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
            vencimientoPuntosDAO.borrar(id);
            return Response.ok("Se elimino Correctamente").build();
        } catch (Exception e) {
            return Response.status(404).entity("No fue posible borrar el Vencimiento de Puntos").build();
        }
    }

    @PUT
    @Path("/actualizar")
    public Response actualizar(VencimientoPuntos vencimientoPuntos){
        try {
            vencimientoPuntosDAO.actualizar(vencimientoPuntos);
            return Response.ok("Se actualizo correctamente").build();
        } catch (Exception e) {
            return Response.status(404).entity("No fue posible actualizar la Asignacion de Puntos").build();
        }
    }
}
