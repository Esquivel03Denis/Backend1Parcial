package py.com.progweb.prueba.model;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="VENCIMIENTO_PUNTOS")
public class VencimientoPuntos {
    @Id
    @Basic(optional = false)
    @GeneratedValue(generator = "vencimientoPuntosSec", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "vencimientoPuntosSec", sequenceName = "VENCIMIENTO_PUNTOS_ID_SEQ", allocationSize=0)
    @Column(name = "id")
    private Integer id;
    @Column(name = "FEC_INICIO")
    private String fecInicio;
    @Column(name = "FEC_FIN")
    private String fecFin;
    @Column(name = "DIAS_DURACION")
    private Integer diasDuracion;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getFecInicio() {
        return fecInicio;
    }
    public void setFecInicio(String fecInicio) {
        this.fecInicio = fecInicio;
    }
    public String getFecFin() {
        return fecFin;
    }
    public void setFecFin(String fecFin) {
        this.fecFin = fecFin;
    }
    public Integer getDiasDuracion() {
        return diasDuracion;
    }
    public void setDiasDuracion(Integer diasDuracion) {
        this.diasDuracion = diasDuracion;
    }
}
