package dao;

import java.util.List;
import javax.persistence.Query;
import metier.data.Employe;
import metier.data.Medium;
import metier.data.Voyance;
/**
 *
 * @author ggribbeni hcanneddu
 */
public class EmployeDao {
    public static void ajouterEmploye(Employe e){
        JpaUtil.obtenirEntityManager().persist(e);
    }

    public static Employe rechercherEmployeParID(long idEmploye) {
        return JpaUtil.obtenirEntityManager().find(Employe.class, idEmploye); 
    }
    
    public static Employe rechercherEmployeParMailMdp(String mail,String mdp) {
        Query query = JpaUtil.obtenirEntityManager().createQuery("Select e from"
                + " Employe e where e.mail=:mail and e.password=:mdp");
        query.setParameter("mail", mail);
        query.setParameter("mdp", mdp);
        List<Employe> list = query.getResultList();
        Employe e=null;
        if (!list.isEmpty()){
            e=list.get(0);
        }
        return e;
    }
    
    public static List<Employe> recupererListeEmployes(){
        Query query = JpaUtil.obtenirEntityManager().createQuery("Select e from"
                + " Employe e");
        List<Employe> list = query.getResultList();
        Employe e=null;
        return list; 
    }
    
    public static void modifierEmploye(Employe e){
        JpaUtil.obtenirEntityManager().merge(e);
    }
    
    public static Employe recupererEmployeMedium(Medium medium) {
        Query query = JpaUtil.obtenirEntityManager().createQuery("Select e from"
                + " Employe e where e.disponibilite=true AND :medium MEMBER OF"
                + " e.listeMedium order by e.nbAffectation asc");
        query.setParameter("medium", medium);
        List<Employe> list = query.getResultList();
        Employe e=null;
        if (!list.isEmpty()){
            e=list.get(0);
        }
        return e; 
    }

    public static Voyance recupererVoyanceActive(Employe e) {
        Query query=JpaUtil.obtenirEntityManager().createQuery("Select v from Voyance v"
                + " where v.employe=:e and v.debut is null");
        query.setParameter("e", e);
        List<Voyance> list = query.getResultList();
        Voyance v=null;
        if (!list.isEmpty()){
            v=list.get(0);
        }
        return v; 
    }
}