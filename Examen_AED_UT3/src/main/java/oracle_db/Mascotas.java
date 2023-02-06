package oracle_db;

import jakarta.persistence.*;

@Entity
public class Mascotas {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CHIP")
    private String chip;
    @Basic
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic
    @Column(name = "RAZA")
    private String raza;
    @Basic
    @Column(name = "TELEFONO")
    private String telefono;

    public String getChip() {
        return chip;
    }

    public void setChip(String chip) {
        this.chip = chip;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
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

        Mascotas mascotas = (Mascotas) o;

        if (chip != null ? !chip.equals(mascotas.chip) : mascotas.chip != null) return false;
        if (nombre != null ? !nombre.equals(mascotas.nombre) : mascotas.nombre != null) return false;
        if (raza != null ? !raza.equals(mascotas.raza) : mascotas.raza != null) return false;
        if (telefono != null ? !telefono.equals(mascotas.telefono) : mascotas.telefono != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = chip != null ? chip.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (raza != null ? raza.hashCode() : 0);
        result = 31 * result + (telefono != null ? telefono.hashCode() : 0);
        return result;
    }
}
