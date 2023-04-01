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
@Table(name="CONCEPTOS_USO_PUNTOS")
public class ConceptoUsoPuntos {
    @Id
    @Basic(optional = false)
    @GeneratedValue(generator = "conceptoUsoPuntosSec", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "conceptoUsoPuntosSec", sequenceName = "CONCEPTOS_USO_PUNTOS_ID_SEQ", allocationSize=0)
    @Column(name = "id")
    private Integer id;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "PUNTOS_REQUERIDOS")
    private Integer puntosRequeridos;
    
    public ConceptoUsoPuntos() {
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Integer getPuntosRequeridos() {
        return puntosRequeridos;
    }
    public void setPuntosRequeridos(Integer puntosRequeridos) {
        this.puntosRequeridos = puntosRequeridos;
    }
}
