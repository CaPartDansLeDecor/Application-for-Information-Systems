package metier.data;

import javax.persistence.Entity;

/**
 *
 * @author ggribbeni hcanneddu
 */
@Entity
public class Tarologue extends Medium{
    
    //constructeur--------------------------------------------------------------
    public Tarologue() {
    }
    
    public Tarologue(String nom, String descriptif) {
        super(nom, descriptif, "Tarologue");
    }

    @Override
    public String toString() {
        return super.toString()+"Tarologue{" + '}';
    }
}
