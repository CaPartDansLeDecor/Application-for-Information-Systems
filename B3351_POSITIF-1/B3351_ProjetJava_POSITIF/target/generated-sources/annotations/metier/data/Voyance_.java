package metier.data;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import metier.data.Client;
import metier.data.Employe;
import metier.data.Medium;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-16T22:44:42")
@StaticMetamodel(Voyance.class)
public class Voyance_ { 

    public static volatile SingularAttribute<Voyance, Date> debut;
    public static volatile SingularAttribute<Voyance, Employe> employe;
    public static volatile SingularAttribute<Voyance, Client> client;
    public static volatile SingularAttribute<Voyance, Date> fin;
    public static volatile SingularAttribute<Voyance, Long> idVoyance;
    public static volatile SingularAttribute<Voyance, Medium> medium;
    public static volatile SingularAttribute<Voyance, String> commentaire;

}