package DBs;

import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
public class Skillset {
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "SS_ID")

    private BigInteger ssId;
    @Basic
    @Column(name = "SKILLSET")
    private BigInteger skillset;
    @Basic
    @Column(name = "SKILL")
    private BigInteger skill;
    @Basic
    @Column(name = "LVL")
    private BigInteger lvl;

    public Skillset( ) {

    }

    public Skillset(int ss_id, int skillset, int skill, int lvl) {
        this.ssId = BigInteger.valueOf(ss_id);
        this.skillset = BigInteger.valueOf(skillset);
        this.skill = BigInteger.valueOf(skill);
        this.lvl = BigInteger.valueOf(lvl);
    }

    public BigInteger getSsId() {
        return ssId;
    }

    public void setSsId(BigInteger ssId) {
        this.ssId = ssId;
    }

    public BigInteger getSkillset() {
        return skillset;
    }

    public void setSkillset(BigInteger skillset) {
        this.skillset = skillset;
    }

    public BigInteger getSkill() {
        return skill;
    }

    public void setSkill(BigInteger skill) {
        this.skill = skill;
    }

    public BigInteger getLvl() {
        return lvl;
    }

    public void setLvl(BigInteger lvl) {
        this.lvl = lvl;
    }

    @Override
    public String toString() {
        return "Skillset{" +
                "ssId=" + ssId +
                ", skillset=" + skillset +
                ", skill=" + skill +
                ", lvl=" + lvl +
                '}';
    }
}
