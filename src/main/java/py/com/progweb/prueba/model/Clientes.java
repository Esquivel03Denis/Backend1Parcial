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
@Table(name = "clientes")
public class Clientes {
    @Id
    @Basic(optional = false)
    @GeneratedValue(generator = "clientesSec", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "clientesSec", sequenceName = "clientes_id_seq", allocationSize=0)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "num_docu")
    private String numDocu;
    @Column(name = "tipo_docu")
    private String tipoDocu;
    @Column(name = "nacionalidad")
    private String nacionalidad;
    @Column(name = "email")
    private String email;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "fec_nacimiento")
    private String fecNacimiento;
    
    public Clientes() {
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getNumDocu() {
        return numDocu;
    }
    public void setNumDocu(String numDocu) {
        this.numDocu = numDocu;
    }
    public String getTipoDocu() {
        return tipoDocu;
    }
    public void setTipoDocu(String tipoDocu) {
        this.tipoDocu = tipoDocu;
    }
    public String getNacionalidad() {
        return nacionalidad;
    }
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getFecNacimiento() {
        return fecNacimiento;
    }
    public void setFecNacimiento(String fecNacimiento) {
        this.fecNacimiento = fecNacimiento;
    }


}
