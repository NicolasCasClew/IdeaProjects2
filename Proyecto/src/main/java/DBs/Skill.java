package DBs;

import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
public class Skill {

    @Id
    @Column(name = "SKILL_ID", insertable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "quoteSeq")
    @SequenceGenerator(name = "quoteSeq", sequenceName = "ISM_TEST", allocationSize = 1)

    private BigInteger skillId;
    @Basic
    @Column(name = "SKILL_NAME")
    private String skillName;
    @Basic
    @Column(name = "DESCR")
    private String descr;


    public Skill(String skillName, String descr) {
        this.skillName = skillName;
        this.descr = descr;
    }

    public Skill() {
    }

    public BigInteger getSkillId() {
        return skillId;
    }

    public void setSkillId(BigInteger skillId) {
        this.skillId = skillId;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }
}
