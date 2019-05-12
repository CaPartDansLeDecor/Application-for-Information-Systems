package metier.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
/**
 *
 * @author ggribbeni hcanneddu
 */
@Entity
public class Employe implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long idEmploye;
    @ManyToMany(mappedBy="listeEmploye", cascade = CascadeType.PERSIST)
    private List<Medium> listeMedium;
    @OneToMany(mappedBy="employe")
    private List<Voyance> listeVoyance;
    
    private Boolean disponibilite = true;
    private String nom;
    private String prenom;
    private String telephone;
    @Column(unique=true)
    private String mail;
    private String password;
    private Long nbAffectation;

    //constructeur--------------------------------------------------------------
    public Employe() {
    }

    public Employe(String nom, String prenom, String telephone, String mail,
            String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.mail = mail;
        this.password = password;
        this.listeMedium=new ArrayList<>();
        this.listeVoyance=new ArrayList<>();
        this.nbAffectation=0L;
    }
    
    //setter--------------------------------------------------------------------
    public void addMedium(Medium m){  
        if(!(listeMedium.contains(m))){
            this.listeMedium.add(m);
            m.addEmploye(this);
        }
    }
    
    public void addVoyance(Voyance v){
        this.listeVoyance.add(v);
        this.nbAffectation++;
    }
    
    public void updateDisponibilite(){
        disponibilite=!disponibilite;
    }

    @Override
    public String toString() {
        return "Employe{" + "idEmploye=" + idEmploye + ", disponibilite=" +
                disponibilite + ", nom=" + nom + ", prenom=" + prenom +
                ", telephone=" + telephone + ", mail=" + mail +
                ", password=" + password + '}';
    }

     //getter-------------------------------------------------------------------
    public String getTelephone() {
        return telephone;
    }

    public List<Voyance> getListeVoyance() {
        return listeVoyance;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Long getNbAffectation() {
        return nbAffectation;
    }

    public Long getIdEmploye() {
        return idEmploye;
    }
}