package metier.modele;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-12T11:32:24")
@StaticMetamodel(Personne.class)
public class Personne_ { 

    public static volatile SingularAttribute<Personne, String> mail;
    public static volatile SingularAttribute<Personne, Date> dateNaissance;
    public static volatile SingularAttribute<Personne, String> adresse;
    public static volatile SingularAttribute<Personne, String> mdp;
    public static volatile SingularAttribute<Personne, Long> id;
    public static volatile SingularAttribute<Personne, String> nom;
    public static volatile SingularAttribute<Personne, String> prenom;
    public static volatile SingularAttribute<Personne, String> numTel;
    public static volatile SingularAttribute<Personne, String> civilite;

}