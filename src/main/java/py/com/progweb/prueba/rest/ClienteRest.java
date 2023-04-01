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

import py.com.progweb.prueba.ejb.ClientesDao;
import py.com.progweb.prueba.model.Clientes;

@Path("/Cliente")
@Consumes("application/json")
@Produces("application/json")
public class ClienteRest {
    
    @Inject
    private ClientesDao clientesDao;

    @GET
    @Path("/lista")
    public Response Listar (){
        try {
            return Response.ok(clientesDao.lista()).build();
        } catch (Exception e) {
            System.out.println(e);
        }
        return Response.status(404).entity("No fue posible obtener la lista de clientes").build();
    }

    @POST
    @Path("/crear")
    public Response crear (Clientes p){
        try {
            clientesDao.agregar(p);
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
            clientesDao.borrar(id);
            return Response.ok("Se elimino Correctamente").build();
        } catch (Exception e) {
            return Response.status(404).entity("No fue posible borrar el cliente").build();
        }
    }

    @PUT
    @Path("/actualizar")
    public Response actualizar(Clientes clientes){
        try {
            clientesDao.actualizar(clientes);
            return Response.ok("Se actualizo correctamente").build();
        } catch (Exception e) {
            return Response.status(404).entity("No fue posible actualizar el cliente").build();
        }
    }
}
