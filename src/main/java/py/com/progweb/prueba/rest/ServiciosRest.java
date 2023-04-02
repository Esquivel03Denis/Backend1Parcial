package py.com.progweb.prueba.rest;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import py.com.progweb.prueba.ejb.ConsultasDao;
import py.com.progweb.prueba.ejb.ServiciosDao;
import py.com.progweb.prueba.model.BolsaPuntos;
import py.com.progweb.prueba.model.Clientes;
import py.com.progweb.prueba.model.UsoPuntosCabecera;

@Path("/servicios")
@Consumes("application/json")
@Produces("application/json")
public class ServiciosRest {
    
    @Inject
    ServiciosDao serviciosDao;

    @Path("/cargaPuntos")
    @POST
    public Response cargaPuntos (@QueryParam("idCliente") Integer idCliente, @QueryParam("montoOperacion") Integer montoOperacion,
                                 @QueryParam("idVencimiento") Integer idVencimiento, @QueryParam("idAsigPuntos") Integer idAsigPuntos){
        
        if(serviciosDao.cargaPuntos(idCliente, montoOperacion, idVencimiento, idAsigPuntos)){
            return Response.ok("Se cargo correctamente").build();
        }else{
            return Response.status(500).entity("Error realizando la carga de puntos").build();
        }
    }

    
}
