package py.com.progweb.prueba.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import py.com.progweb.prueba.model.UsoPuntosCabecera;

@Path("/consulta")
@Consumes("application/json")
@Produces("application/json")
public class Consultas {
    
    @Path("/usoPuntos/concepto")
    @Produces("application/json")
    @GET
    public UsoPuntosCabecera getUsoPorConcepto(@QueryParam("idConcepto") Integer idConcepto){
        
        return null;
    }

    @Path("/usoPuntos/fechaUso")
    @Produces("application/json")
    @GET
    public UsoPuntosCabecera getUsoPorFecha(@QueryParam("fecha") String fecha){
        
        return null;
    }

    @Path("/usoPuntos/cliente")
    @Produces("application/json")
    @GET
    public UsoPuntosCabecera getUsoPorCliente(@QueryParam("idCliente") Integer idCliente){
        
        return null;
    }

    
}
