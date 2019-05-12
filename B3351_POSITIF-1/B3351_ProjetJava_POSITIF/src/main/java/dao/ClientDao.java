package dao;

import java.util.List;
import javax.persistence.Query;
import metier.data.Client;
/**
 *
 * @author ggribbeni hcanneddu
 */
public class ClientDao {
    
    public static void ajouterClient(Client c){
        JpaUtil.obtenirEntityManager().persist(c);
    }

    public static Client rechercherClientParID(long idClient) {
        return JpaUtil.obtenirEntityManager().find(Client.class, idClient);
    }
    
    public static Client rechercherClientParMailMdp(String mail,String mdp) {
        Query query = JpaUtil.obtenirEntityManager().createQuery("Select c from "
                + "Client c where c.mail=:mail and c.password=:mdp");
        query.setParameter("mail",mail);
        query.setParameter("mdp",mdp);
        List<Client> list = query.getResultList();
        Client c=null;
        if (!list.isEmpty()){
            c=list.get(0);
        }
        return c;
    }
    
    public static void modifierClient(Client c){
        JpaUtil.obtenirEntityManager().merge(c);
    }
    
}
