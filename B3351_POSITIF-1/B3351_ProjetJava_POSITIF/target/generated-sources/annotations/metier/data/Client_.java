package metier.data;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import metier.data.Voyance;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-15T09:49:24")
@StaticMetamodel(Client.class)
public class Client_ { 

    public static volatile SingularAttribute<Client, String> civilte;
    public static volatile SingularAttribute<Client, Long> idClient;
    public static volatile SingularAttribute<Client, String> mail;
    public static volatile SingularAttribute<Client, Date> dateNaissance;
    public static volatile ListAttribute<Client, Voyance> listeVoyance;
    public static volatile SingularAttribute<Client, String> couleur;
    public static volatile SingularAttribute<Client, String> telephone;
    public static volatile SingularAttribute<Client, String> nom;
    public static volatile SingularAttribute<Client, String> signeAstro;
    public static volatile SingularAttribute<Client, Integer> codePostale;
    public static volatile SingularAttribute<Client, String> password;
    public static volatile SingularAttribute<Client, String> animal;
    public static volatile SingularAttribute<Client, String> prenom;
    public static volatile SingularAttribute<Client, String> signeChinois;

}