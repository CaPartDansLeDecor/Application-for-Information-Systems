package metier.data;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import metier.data.Employe;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-12T16:24:23")
@StaticMetamodel(Medium.class)
public class Medium_ { 

    public static volatile ListAttribute<Medium, Employe> listeEmploye;
    public static volatile SingularAttribute<Medium, String> talent;
    public static volatile SingularAttribute<Medium, String> nom;
    public static volatile SingularAttribute<Medium, String> descriptif;
    public static volatile SingularAttribute<Medium, Long> idMedium;

}