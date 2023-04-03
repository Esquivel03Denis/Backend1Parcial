package py.com.progweb.prueba.ejb;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import py.com.progweb.prueba.model.AsignacionPuntos;
import py.com.progweb.prueba.model.BolsaPuntos;
import py.com.progweb.prueba.model.Clientes;
import py.com.progweb.prueba.model.ConceptoUsoPuntos;
import py.com.progweb.prueba.model.UsoPuntosCabecera;
import py.com.progweb.prueba.model.UsoPuntosDetalles;
import py.com.progweb.prueba.model.VencimientoPuntos;

@Stateless
public class ServiciosDao {
    @PersistenceContext(unitName = "pruebaPU")
    private EntityManager em;
    @Inject
    BolsaPuntosDAO bolsaPuntosDAO;
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    public Boolean cargaPuntos (Integer idCliente, Integer montoOperacion, Integer idVencimiento, Integer idAsigPuntos){
        Boolean cargo = false;
        
        try {
            VencimientoPuntos vp = em.createQuery("select b from VencimientoPuntos b where b.id = :idVencimiento", VencimientoPuntos.class)
                .setParameter("idVencimiento", idVencimiento)
                .getSingleResult();

            Clientes cliente = em.createQuery("select b from Clientes b where b.id = :idCliente", Clientes.class)
                .setParameter("idCliente", idCliente)
                .getSingleResult();

            AsignacionPuntos ap = em.createQuery("select b from AsignacionPuntos b where b.id = :idAsigPuntos ", AsignacionPuntos.class)
                .setParameter("idAsigPuntos", idAsigPuntos)
                .getSingleResult();

            BolsaPuntos bp = new BolsaPuntos();
            bp.setCliente(cliente);
            bp.setVencimientoPuntos(vp);
            bp.setMontoOperacion(montoOperacion);
            int cantPuntos = montoOperacion/ap.getEquivaleUnPunto();
            bp.setPuntajeAsignado(cantPuntos);
            bp.setSaldoPuntos(cantPuntos);
            bp.setPuntajeUtilizado(0);
            bp.setFecAsig(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
            Date fecVencimiento = sumarDiasAFecha(formato.parse(bp.getFecAsig()), bp.getVencimientoPuntos().getDiasDuracion());
            if (formato.parse(bp.getVencimientoPuntos().getFecFin()).after(fecVencimiento)){
                bp.setFecCaducidad(formato.format(fecVencimiento));
            }else{
                bp.setFecCaducidad(bp.getVencimientoPuntos().getFecFin());
            }
            em.persist(bp);
            cargo = true;
        } catch (Exception e) {
            System.out.println("Error al cargar puntos"+e);
        }
        return cargo;
    }

    public static Date sumarDiasAFecha(Date fecha, int dias){
        if (dias==0) return fecha;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); 
        calendar.add(Calendar.DAY_OF_YEAR, dias);  
        return calendar.getTime(); 
    }

    public int usoPuntos (Integer idCliente, Integer idConcepto){
        int uso = 1;
        try {
            List <BolsaPuntos> bolsaList = em.createQuery("select b from BolsaPuntos b where b.cliente.id = :id and b.saldoPuntos > 0 order by b.fecAsig", BolsaPuntos.class)
                .setParameter("id", idCliente)
                .getResultList();
            ConceptoUsoPuntos cu = em.createQuery("select b from ConceptoUsoPuntos b where b.id = :idConcepto", ConceptoUsoPuntos.class)
                .setParameter("idConcepto", idConcepto)
                .getSingleResult();

            Clientes cli = (Clientes) em.createQuery("select b from Clientes b where b.id = :idCliente ", Clientes.class)
                .setParameter("idCliente", idCliente)
                .getSingleResult();
                
            int puntosNecesarios = cu.getPuntosRequeridos();
            int puntosDisponibles = 0;
            for(BolsaPuntos b:bolsaList){
                puntosDisponibles += b.getSaldoPuntos();
            }
            if (puntosDisponibles > puntosNecesarios){
                List <UsoPuntosDetalles> listaDetalles = new ArrayList<UsoPuntosDetalles>();
                UsoPuntosCabecera usoPuntosCab = new UsoPuntosCabecera();
                usoPuntosCab.setCliente(cli);
                usoPuntosCab.setConceptoUso(cu);
                usoPuntosCab.setFecha(formato.format(new Date()));
                usoPuntosCab.setPuntajeAsignado(cu.getPuntosRequeridos());
                for(BolsaPuntos b:bolsaList){
                    if(b.getSaldoPuntos() >= puntosNecesarios){
                        b.setPuntajeUtilizado(puntosNecesarios);
                        b.setSaldoPuntos(b.getSaldoPuntos() - puntosNecesarios);
                        UsoPuntosDetalles usoPuntosDet = new UsoPuntosDetalles();
                        usoPuntosDet.setBolsaPuntos(b);
                        usoPuntosDet.setPuntajeAsignado(puntosNecesarios);
                        usoPuntosDet.setUsoPuntosCabecera(usoPuntosCab);
                        listaDetalles.add(usoPuntosDet);
                        bolsaPuntosDAO.actualizar(b);
                        uso = 2;
                        break;
                    }else{
                        puntosNecesarios = puntosNecesarios - b.getSaldoPuntos();
                        b.setPuntajeUtilizado(b.getSaldoPuntos());
                        b.setSaldoPuntos(0);
                        UsoPuntosDetalles usoPuntosDet = new UsoPuntosDetalles();
                        usoPuntosDet.setBolsaPuntos(b);
                        usoPuntosDet.setPuntajeAsignado(puntosNecesarios);
                        usoPuntosDet.setUsoPuntosCabecera(usoPuntosCab);
                        listaDetalles.add(usoPuntosDet);
                        bolsaPuntosDAO.actualizar(b);
                    }   
                }
                usoPuntosCab.setDetalles(listaDetalles);
                em.persist (usoPuntosCab);
            }

        } catch (Exception e) {
            System.out.println("Error al registrar uso de puntos "+e);
            uso = 3;
        }
        return uso;
    }

    public Integer cuantosPuntosEq(Integer montoCambio, Integer idAsigPuntos) {
        try {
            AsignacionPuntos ap = em.createQuery("select b from AsignacionPuntos b where b.id = :idAsigPuntos ", AsignacionPuntos.class)
                    .setParameter("idAsigPuntos", idAsigPuntos)
                    .getSingleResult();
            int puntos_corresp= montoCambio/(ap.getEquivaleUnPunto());
            return puntos_corresp;
            
        } catch (Exception e) {
            System.out.println("Error al cargar Asignacion de puntos"+e);
        }
        return -1;
    }
}
