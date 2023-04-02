package py.com.progweb.prueba.model;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name="BOLSA_PUNTOS")
public class BolsaPuntos {
    @Id
    @Basic(optional = false)
    @GeneratedValue(generator = "bolsaPuntosSec", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "bolsaPuntosSec", sequenceName = "BOLSA_PUNTOS_ID_SEQ", allocationSize=0)
    @Column(name = "id")
    private Integer id;
    @Column(name = "PUNTAJE_ASIGNADO")
    private Integer puntajeAsignado;
    @Column(name = "PUNTAJE_UTILIZADO")
    private Integer puntajeUtilizado;
    @Column(name = "SALDO_PUNTOS")
    private Integer saldoPuntos;
    @Column(name = "MONTO_OPERACION")
    private Integer montoOperacion;
    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID")
    @ManyToOne
    private Clientes cliente;
    @JoinColumn(name = "ID_VENCIMIENTO_PUNTOS", referencedColumnName = "ID")
    @ManyToOne
    private VencimientoPuntos vencimientoPuntos;

    public BolsaPuntos() {
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getPuntajeAsignado() {
        return puntajeAsignado;
    }
    public void setPuntajeAsignado(Integer puntajeAsignado) {
        this.puntajeAsignado = puntajeAsignado;
    }
    public Integer getPuntajeUtilizado() {
        return puntajeUtilizado;
    }
    public void setPuntajeUtilizado(Integer puntajeUtilizado) {
        this.puntajeUtilizado = puntajeUtilizado;
    }
    public Integer getSaldoPuntos() {
        return saldoPuntos;
    }
    public void setSaldoPuntos(Integer saldoPuntos) {
        this.saldoPuntos = saldoPuntos;
    }
    public Integer getMontoOperacion() {
        return montoOperacion;
    }
    public void setMontoOperacion(Integer montoOperacion) {
        this.montoOperacion = montoOperacion;
    }
    public Clientes getCliente() {
        return cliente;
    }
    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }
    public VencimientoPuntos getVencimientoPuntos() {
        return vencimientoPuntos;
    }
    public void setVencimientoPuntos(VencimientoPuntos vencimientoPuntos) {
        this.vencimientoPuntos = vencimientoPuntos;
    }
}
