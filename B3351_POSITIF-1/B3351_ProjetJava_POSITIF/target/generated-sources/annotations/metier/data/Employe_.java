package metier.data;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import metier.data.Medium;
import metier.data.Voyance;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-13T18:05:07")
@StaticMetamodel(Employe.class)
public class Employe_ { 

    public static volatile SingularAttribute<Employe, String> password;
    public static volatile SingularAttribute<Employe, String> mail;
    public static volatile ListAttribute<Employe, Medium> listeMedium;
    public static volatile ListAttribute<Employe, Voyance> listeVoyance;
    public static volatile SingularAttribute<Employe, Boolean> disponibilite;
    public static volatile SingularAttribute<Employe, Long> nbAffectation;
    public static volatile SingularAttribute<Employe, String> telephone;
    public static volatile SingularAttribute<Employe, String> nom;
    public static volatile SingularAttribute<Employe, String> prenom;
    public static volatile SingularAttribute<Employe, Long> idEmploye;

}