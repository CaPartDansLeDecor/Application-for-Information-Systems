package metier.data;

import javax.persistence.Entity;

/**
 *
 * @author ggribbeni hcanneddu
 */
@Entity
public class Voyant extends Medium {
    private String specialite;

    public Voyant() {
    }

    public Voyant( String nom, String descriptif,String specialite) {
        super(nom, descriptif, "Voyant");
        this.specialite = specialite;
    }

    @Override
    public String toString() {
        return super.toString()+"Voyant{" + "specialite=" + specialite + '}';
    }

    //getter--------------------------------------------------------------------
    public String getSpecialite() {
        return specialite;
    }
}