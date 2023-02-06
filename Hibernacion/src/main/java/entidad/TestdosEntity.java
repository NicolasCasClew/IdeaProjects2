package entidad;

import jakarta.persistence.*;

@Entity
@Table(name = "testdos", schema = "ejejejercicio6_db", catalog = "ejer6")
public class TestdosEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "dni")
    private String dni;
    @Basic
    @Column(name = "nombre_emp")
    private String nombreEmp;
    @Basic
    @Column(name = "telefono")
    private String telefono;

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombreEmp() {
        return nombreEmp;
    }

    public void setNombreEmp(String nombreEmp) {
        this.nombreEmp = nombreEmp;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestdosEntity that = (TestdosEntity) o;

        if (dni != null ? !dni.equals(that.dni) : that.dni != null) return false;
        if (nombreEmp != null ? !nombreEmp.equals(that.nombreEmp) : that.nombreEmp != null) return false;
        if (telefono != null ? !telefono.equals(that.telefono) : that.telefono != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = dni != null ? dni.hashCode() : 0;
        result = 31 * result + (nombreEmp != null ? nombreEmp.hashCode() : 0);
        result = 31 * result + (telefono != null ? telefono.hashCode() : 0);
        return result;
    }
}
