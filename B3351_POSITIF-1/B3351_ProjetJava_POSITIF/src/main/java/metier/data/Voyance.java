package metier.data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ggribbeni hcanneddu
 */
@Entity
public class Voyance implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long idVoyance;
    @Temporal(TemporalType.DATE)
    private Date debut;
    @Temporal(TemporalType.DATE)
    private Date fin;
    @ManyToOne
    private Client client;
    @ManyToOne
    private Employe employe;
    @ManyToOne
    private Medium medium;   
    private String commentaire;
    
    //constructeur--------------------------------------------------------------
    public Voyance() {
    }

    public Voyance(Client client, Employe employe, Medium medium) {
        this.client = client;
        this.employe = employe;
        this.medium=medium;
        this.debut=null;
        this.fin= null;
        this.commentaire=null;
    }
    
    //setter--------------------------------------------------------------------
    public void commencerVoyance(){
        this.debut=new Date();
    }
    
    public void terminerVoyance() {
        this.fin = new Date();
    }
    
    public void finaliserVoyance(){
        this.employe.addVoyance(this);
        this.client.addVoyance(this);
    }
    
    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    @Override
    public String toString() {
        return "Voyance{" + "idVoyance=" + idVoyance + ", debut=" + debut +
                ", fin=" + fin + ", client=" + client + ", employe=" +
                employe + '}';
    }

    //getter--------------------------------------------------------------------
    public Client getClient() {
        return client;
    }

    public Employe getEmploye() {
        return employe;
    }

    public Medium getMedium() {
        return medium;
    }

    public Date getDebut() {
        return debut;
    }

    public Date getFin() {
        return fin;
    }   

    public String getCommentaire() {
        return commentaire;
    }
}
