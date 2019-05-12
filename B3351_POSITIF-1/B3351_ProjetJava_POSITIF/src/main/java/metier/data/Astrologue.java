package metier.data;

import javax.persistence.Entity;

/**
 *
 * @author ggribbeni hcanneddu
 */
@Entity
public class Astrologue extends Medium{
    
    private String formation;
    private String promotion;
    
    //constructeur--------------------------------------------------------------
    public Astrologue() {
    }
    
    public Astrologue(String nom, String descriptif, String Formation,
            String Promotion) {
        super(nom, descriptif, "Astrologue");
        this.formation = Formation;
        this.promotion = Promotion;
    }

    @Override
    public String toString() {
        return super.toString()+"Astrologue{" + "formation=" + formation +
                ", promotion=" + promotion + '}';
    }
    
    //getter--------------------------------------------------------------------
    public String getFormation() {
        return formation;
    }

    public String getPromotion() {
        return promotion;
    }
}