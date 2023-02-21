package DBs;

import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
public class Employee {


    @Id
    @Column(name = "NIF")
    private String nif;
    @Basic
    @Column(name = "NAME")
    private String name;
    @Basic
    @Column(name = "SURNAME")
    private String surname;
    @Basic
    @Column(name = "SKILLSET")
    private BigInteger skillset;

    public Employee() {

    }

    public Employee(String nif, String name, String surname, BigInteger skillset) {
        this.nif = nif;
        this.name = name;
        this.surname = surname;
        this.skillset = skillset;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public BigInteger getSkillset() {
        return skillset;
    }

    public void setSkillset(BigInteger skillset) {
        this.skillset = skillset;
    }
}
