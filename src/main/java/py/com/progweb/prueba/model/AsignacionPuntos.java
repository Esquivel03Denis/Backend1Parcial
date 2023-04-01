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
@Table(name="ASIGNACION_PUNTOS")
public class AsignacionPuntos {
    @Id
    @Basic(optional = false)
    @GeneratedValue(generator = "asignacionPuntosSec", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "asignacionPuntosSec", sequenceName = "ASIGNACION_PUNTOS_ID_SEQ", allocationSize=0)
    @Column(name = "id")
    private Integer id;
    @Column(name = "EQUIVALE_UN_PUNTO")
    private Integer equivaleUnPunto;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getEquivaleUnPunto() {
        return equivaleUnPunto;
    }
    public void setEquivaleUnPunto(Integer equivaleUnPunto) {
        this.equivaleUnPunto = equivaleUnPunto;
    }
    
}
