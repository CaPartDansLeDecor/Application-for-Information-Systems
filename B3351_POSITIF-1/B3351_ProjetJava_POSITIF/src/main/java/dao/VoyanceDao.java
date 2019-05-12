package dao;

import metier.data.Voyance;

/**
 *
 * @author ggribbeni hcanneddu
 */
public class VoyanceDao {
    public static void ajouterVoyance(Voyance v){
        JpaUtil.obtenirEntityManager().persist(v);
    }
    
    public static Voyance rechercherVoyanceParID(long idVoyance) {
        return JpaUtil.obtenirEntityManager().find(Voyance.class, idVoyance); 
    }
    
    public static void modifierVoyance(Voyance v){
        JpaUtil.obtenirEntityManager().merge(v);
    }
}