package py.com.progweb.prueba.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import py.com.progweb.prueba.model.BolsaPuntos;
import py.com.progweb.prueba.model.Clientes;
import py.com.progweb.prueba.model.UsoPuntosCabecera;

@Stateless
public class ConsultasDao {
    @PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;

    public List<UsoPuntosCabecera> obtenerPuntos (Integer idConcepto, String fecha, Integer idCliente){
        List <UsoPuntosCabecera> listaPuntos = null;
        try {
            Query q = em.createQuery("select b from UsoPuntosCabecera b where (b.conceptoUso.id = :idConcepto or :idConcepto is null)" +
                                 "and (b.fecha = :fecha or fecha is null) and (b.cliente.id = :idCliente or :idCliente is null)", UsoPuntosCabecera.class)
                                 .setParameter("idConcepto", idConcepto)
                                 .setParameter("fecha", fecha)
                                 .setParameter("idCliente", idCliente);
            listaPuntos = (List <UsoPuntosCabecera>) q.getResultList();                
        } catch (Exception e) {
            System.out.println("Error al realizar consulta de uso de puntos" + e);
            listaPuntos = null;
        }
        return listaPuntos;
    }

    public List<BolsaPuntos> obtenerBolsas (Integer idCliente, Integer rangoIni, Integer rangoFin){
        List <BolsaPuntos> listaBolsas = null;
        try {
            Query q = em.createQuery("select b from BolsaPuntos b where (b.cliente.id = :idCliente or :idCliente is null)" +
                                 "and (b.saldoPuntos >= :min or :min is null) and (b.saldoPuntos <= :max or :max is null)", BolsaPuntos.class)
                                 .setParameter("idCliente", idCliente)
                                 .setParameter("min", rangoIni)
                                 .setParameter("max", rangoFin);
                                 listaBolsas = (List <BolsaPuntos>) q.getResultList();                
        } catch (Exception e) {
            System.out.println("Error al realizar consulta de bolsa de puntos" + e);
            listaBolsas = null;
        }
        return listaBolsas;
    }

    public List<Clientes> obtenerClientes (Integer dias){
        List <Clientes> listaClientes = null;
        try {
            Query q = em.createQuery("select distinc(b.cliente)"+
                                    "from BolsaPuntos b"+
                                    "WHERE"+
                                    "b.saldoPuntos > 0"+
                                    "and (to_date('b.vencimientoPuntos.fecInicio', 'dd/mm/yyyy') + CAST('b.vencimientoPuntos.diasDuracion days' AS INTERVAL)) < to_date(b.vencimientoPuntos.fecFin, 'dd/mm/yyyy')"+
                                    "and to_date(b.vencimientoPuntos.fecFin, 'dd/mm/yyyy') - sysdate <= :dias", BolsaPuntos.class)
                                 .setParameter("dias", dias);
                                 listaClientes = (List <Clientes>) q.getResultList();                
        } catch (Exception e) {
            System.out.println("Error al realizar consulta de clientes con puntos a vencer en x dias" + e);
            listaClientes = null;
        }
        return listaClientes;
    }

    public List<Clientes> obtenerClientes (String nombre, String  apellido, String cumple){
        List <Clientes> listaClientes = null;
        try {
            Query q = em.createQuery("select b from Clientes b where upper(b.nombre) like %upper(:nombre)% and "+
                                     "upper(b.apellido) like %upper(:apellido)% and "+
                                     "(to_char(to_date(b.fecNacimiento, 'dd/mm'),'dd/mm') = :cumple or :cumple is null)", BolsaPuntos.class)
                                 .setParameter("nombre", nombre)
                                 .setParameter("apellido", apellido)
                                 .setParameter("cumple", cumple);
                                 listaClientes = (List <Clientes>) q.getResultList();                
        } catch (Exception e) {
            System.out.println("Error al realizar consulta de bolsa de puntos" + e);
            listaClientes = null;
        }
        return listaClientes;
    }
}
