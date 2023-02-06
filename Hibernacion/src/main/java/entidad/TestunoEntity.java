package entidad;

import jakarta.persistence.*;

@Entity
@Table(name = "testuno", schema = "ejejejercicio6_db", catalog = "ejer6")
public class TestunoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "departamento")
    private String departamento;
    @Basic
    @Column(name = "ciudad")
    private String ciudad;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestunoEntity that = (TestunoEntity) o;

        if (id != that.id) return false;
        if (departamento != null ? !departamento.equals(that.departamento) : that.departamento != null) return false;
        if (ciudad != null ? !ciudad.equals(that.ciudad) : that.ciudad != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (departamento != null ? departamento.hashCode() : 0);
        result = 31 * result + (ciudad != null ? ciudad.hashCode() : 0);
        return result;
    }
}
