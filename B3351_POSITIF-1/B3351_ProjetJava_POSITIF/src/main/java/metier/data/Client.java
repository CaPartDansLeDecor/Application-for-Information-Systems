package metier.data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 *
 * ggribbeni hcanneddu
 */
@Entity
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long idClient; 
    private String civilte;
    private String nom;
    private String prenom;
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    private Integer codePostale ;
    private String telephone;
    @Column(unique=true)
    private String mail;
    private String signeAstro;
    private String signeChinois;
    private String couleur;
    private String animal;
    private String password;
    
    @OneToMany(mappedBy="client")
    private List<Voyance> listeVoyance;
    
    //constructeur--------------------------------------------------------------
    public Client() {
    }
    
    public Client(String civilte, String nom, String prenom, Date dateNaissance,
            Integer codePostale, String telephone, String mail, String password)
    {
        this.civilte = civilte;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.codePostale = codePostale;
        this.telephone = telephone;
        this.mail = mail;
        this.password = password;
    }
    
    //setter--------------------------------------------------------------------
    public void setProfilAstro(String signeAstro,String signeChinois,
            String couleur, String animal){
        this.signeAstro=signeAstro;
        this.signeChinois=signeChinois;
        this.couleur=couleur;
        this.animal=animal;
    }
    
    public void addVoyance(Voyance v){
        this.listeVoyance.add(v);
    }
    //getter--------------------------------------------------------------------
    public Long getIdClient() {
        return idClient;
    }

    public List<Voyance> getListeVoyance() {
        return listeVoyance;
    }

    public String getCivilte() {
        return civilte;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public Integer getCodePostale() {
        return codePostale;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getMail() {
        return mail;
    }

    public String getSigneAstro() {
        return signeAstro;
    }

    public String getSigneChinois() {
        return signeChinois;
    }

    public String getCouleur() {
        return couleur;
    }

    public String getAnimal() {
        return animal;
    }

    @Override
    public String toString() {
        return "Client{" + "idClient=" + idClient + ", civilte=" + civilte +
                ", nom=" + nom + ", prenom=" + prenom + ", dateNaissance=" +
                dateNaissance + ", codePostale=" + codePostale + ", telephone="
                + telephone + ", mail=" + mail + ", signeAstro=" + signeAstro +
                ", signeChinois=" + signeChinois + ", couleur=" + couleur +
                ", animal=" + animal + ", password=" + password +
                ", listeVoyance=" + listeVoyance + '}';
    }
}
