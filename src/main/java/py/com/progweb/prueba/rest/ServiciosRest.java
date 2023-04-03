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

    @Path("/usoPuntos")
    @POST
    public Response usoPuntos (@QueryParam("idCliente") Integer idCliente, @QueryParam("idConcepto") Integer idConcepto){
        int uso = serviciosDao.usoPuntos(idCliente, idConcepto);
        if (uso == 1){
            return Response.status(500).entity("No se cuentan con la cantidad necesaria de puntos").build();
        }else if (uso == 2){
            return Response.ok("Se utilizaron correctamente los puntos").build();
        }else{
            return Response.status(500).entity("Error al intentar utilizar los puntos").build();
        }
    }

    @Path("/cuantosPuntos")
    @GET
    public Response cuantosPuntosEq (@QueryParam("montoCambio") Integer montoCambio,
                                @QueryParam("idAsigPuntos") Integer idAsigPuntos){
        int puntosEq = serviciosDao.cuantosPuntosEq(montoCambio, idAsigPuntos);
        if(puntosEq!=-1){
            return Response.ok(puntosEq).build();
        }else{
            return Response.status(500).entity("Error realizando la equivalencia a puntos").build();
        }
    }

}
