package py.com.progweb.prueba.model;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name="USO_PUNTOS_CABECERA")
public class UsoPuntosCabecera {
    public UsoPuntosCabecera() {
    }
    @Id
    @Basic(optional = false)
    @GeneratedValue(generator = "UsoPuntosCabeceraSec", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "UsoPuntosCabeceraSec", sequenceName = "USO_PUNTOS_CABECERA_ID_SEQ", allocationSize=0)
    @Column(name = "id")
    private Integer id;
    @Column(name = "PUNTAJE_UTILIZADO")
    private Integer puntajeAsignado;
    @Column(name = "FECHA")
    private String puntajeUtilizado;
    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID")
    @ManyToOne
    private Clientes cliente;
    @JoinColumn(name = "ID_CONCEPTOS_USO_PUNTOS", referencedColumnName = "ID")
    @ManyToOne
    private ConceptoUsoPuntos conceptoUso;
    @OneToMany
    private List <UsoPuntosDetalles> detalles;
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
    public String getPuntajeUtilizado() {
        return puntajeUtilizado;
    }
    public void setPuntajeUtilizado(String puntajeUtilizado) {
        this.puntajeUtilizado = puntajeUtilizado;
    }
    public Clientes getCliente() {
        return cliente;
    }
    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }
    public ConceptoUsoPuntos getConceptoUso() {
        return conceptoUso;
    }
    public void setConceptoUso(ConceptoUsoPuntos conceptoUso) {
        this.conceptoUso = conceptoUso;
    }
}
