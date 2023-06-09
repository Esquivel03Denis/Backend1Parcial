package py.com.progweb.prueba.rest;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import py.com.progweb.prueba.ejb.ConsultasDao;
import py.com.progweb.prueba.model.BolsaPuntos;
import py.com.progweb.prueba.model.Clientes;
import py.com.progweb.prueba.model.UsoPuntosCabecera;

@Path("/consulta")
@Consumes("application/json")
@Produces("application/json")
public class ConsultasRest {

    @Inject
    private ConsultasDao consultaDao;

    @Path("/usoPuntos")
    @Produces("application/json")
    @GET
    public Response getUsoPorConcepto(@QueryParam("idConcepto") Integer idConcepto, @QueryParam("fecha") String fecha,
                                               @QueryParam("idCliente") Integer idCliente){
        List <UsoPuntosCabecera> listaPuntos;
        listaPuntos = consultaDao.obtenerPuntos(idConcepto, fecha, idCliente);
        if(listaPuntos != null){
            return Response.ok(listaPuntos).build();
        }else{
            return Response.status(500).entity("Error realizando la consulta de uso de puntos").build();
        }
    }
    
    @Path("/bolsaPuntos")
    @Produces("application/json")
    @GET
    public Response getBolsaPuntos (@QueryParam("idcliente") Integer idCliente, @QueryParam("rangoInicial") Integer rangoInicial,
                                               @QueryParam("rangoFinal") Integer rangoFinal){
        List <BolsaPuntos> listaBolsas = new ArrayList<BolsaPuntos>();
        listaBolsas = consultaDao.obtenerBolsas(idCliente, rangoInicial, rangoFinal);
        if(listaBolsas != null){
            return Response.ok(listaBolsas).build();
        }else{
            return Response.status(500).entity("Error realizando la consulta de uso de puntos").build();
        }
    }

    @Path("/puntosaVencer")
    @Produces("application/json")
    @GET
    public Response getClientesPtsaVencer (@QueryParam("dias") Integer dias){
        
        List <Clientes> listaclClientes = new ArrayList<Clientes>();
        listaclClientes = consultaDao.obtenerClientes(dias);

        if(listaclClientes != null){
            return Response.ok(listaclClientes).build();
        }else{
            return Response.status(500).entity("Error realizando la consulta de clientes con puntos a vencer").build();
        }
    }

    @Path("/clientes")
    @Produces("application/json")
    @GET
    public Response getClientes (@QueryParam("nombre") String nombre, @QueryParam("apellido") String apellido,
                                               @QueryParam("cumple") String cumple){
        
        List <Clientes> listaclClientes = new ArrayList<Clientes>();
        listaclClientes = consultaDao.obtenerClientes(nombre,apellido,cumple);

        if(listaclClientes != null){
            return Response.ok(listaclClientes).build();
        }else{
            return Response.status(500).entity("Error realizando la consulta de uso de puntos").build();
        }
    }
}
