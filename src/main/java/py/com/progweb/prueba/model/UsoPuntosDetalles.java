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
@Table(name="USO_PUNTOS_DETALLES")
public class UsoPuntosDetalles {
    @Id
    @Basic(optional = false)
    @GeneratedValue(generator = "UsoPuntosDetallesSec", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "UsoPuntosDetallesSec", sequenceName = "USO_PUNTOS_DETALLES_ID_SEQ", allocationSize=0)
    @Column(name = "id")
    private Integer id;
    @Column(name = "PUNTAJE_UTILIZADO")
    private Integer puntajeAsignado;
    @JoinColumn(name = "ID_CABECERA", referencedColumnName = "ID")
    @ManyToOne
    private UsoPuntosCabecera usoPuntosCabecera;
    @JoinColumn(name = "ID_BOLSA_PUNTOS", referencedColumnName = "ID")
    @ManyToOne
    private BolsaPuntos bolsaPuntos;
    public UsoPuntosDetalles() {
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
    public UsoPuntosCabecera getUsoPuntosCabecera() {
        return usoPuntosCabecera;
    }
    public void setUsoPuntosCabecera(UsoPuntosCabecera usoPuntosCabecera) {
        this.usoPuntosCabecera = usoPuntosCabecera;
    }
    public BolsaPuntos getBolsaPuntos() {
        return bolsaPuntos;
    }
    public void setBolsaPuntos(BolsaPuntos bolsaPuntos) {
        this.bolsaPuntos = bolsaPuntos;
    }
}
