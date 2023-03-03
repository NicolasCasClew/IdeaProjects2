package DBs;

import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
public class Skills {

    @Id
    @Column(name = "SKILL_ID")
    private BigInteger skillId;
    @Basic
    @Column(name = "SKILL_NAME")
    private String skillName;
    @Basic
    @Column(name = "DESCRIPTION")
    private String description;

    public Skills(BigInteger skillId, String skillName, String description) {
        this.skillId = skillId;
        this.skillName = skillName;
        this.description = description;
    }

    public Skills() {

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
