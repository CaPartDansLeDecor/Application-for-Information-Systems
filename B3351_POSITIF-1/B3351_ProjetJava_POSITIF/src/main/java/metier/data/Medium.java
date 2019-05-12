package metier.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

/**
 *
 * @author ggribbeni hcanneddu
 */
@Entity
@Inheritance (strategy=InheritanceType.SINGLE_TABLE)
public class Medium implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    protected Long idMedium;
    protected String nom;
    protected String descriptif;
    protected String talent;
    
    @ManyToMany
    protected List<Employe> listeEmploye;
    
    //constructeur--------------------------------------------------------------
    public Medium() {
    }

    public Medium(String nom, String descriptif, String talent) {
        this.nom = nom;
        this.descriptif = descriptif;
        this.talent = talent;
        this.listeEmploye=new ArrayList<Employe>();
    }
    
    //setter--------------------------------------------------------------------
    public void addEmploye(Employe e){
        if(!(listeEmploye.contains(e))){
            this.listeEmploye.add(e);
            e.addMedium(this);
        }
    }

    @Override
    public String toString() {
        return "Medium{" + "idMedium=" + idMedium + ", nom=" + nom +
                ", descriptif=" + descriptif + ", talent=" + talent+ '}';
    }
    
     //getter-------------------------------------------------------------------
    public String getNom() {
        return nom;
    }

    public String getDescriptif() {
        return descriptif;
    }

    public String getTalent() {
        return talent;
    }

    public Long getIdMedium() {
        return idMedium;
    } 
}