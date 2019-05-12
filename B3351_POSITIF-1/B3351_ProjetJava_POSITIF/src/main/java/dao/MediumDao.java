package dao;

import java.util.List;
import javax.persistence.Query;
import metier.data.Medium;
/**
 *
 * @author ggribbeni hcanneddu
 */
public class MediumDao {
    public static void ajouterMedium(Medium m){
        JpaUtil.obtenirEntityManager().persist(m);
    }
    
    public static void modiferMedium(Medium m){
        JpaUtil.obtenirEntityManager().merge(m);
    }
    
    public static Medium rechercherMediumParID(Long idMedium) {
        return JpaUtil.obtenirEntityManager().find(Medium.class, idMedium); 
    }
    
    public static List<Medium> rechercherMediumParTalent(String talent){
        Query query = JpaUtil.obtenirEntityManager().createQuery("Select m from"
                + " Medium m where m.talent= :talent");
        query.setParameter("talent", talent);
        List<Medium> list = query.getResultList();
        return list; 
    }
    
    public static List<Medium> recupererListeMedium(){
        Query query = JpaUtil.obtenirEntityManager().createQuery("Select m"
                + " from Medium m ");
        List<Medium> list = query.getResultList();
        return list; 
    }

    public static List<Object[]> recupererNbClientsParMedium() {
        Query query = JpaUtil.obtenirEntityManager().createQuery("Select"
                + " v.medium,count(v) from Voyance v group by v.medium");
        return query.getResultList();
    }
}



