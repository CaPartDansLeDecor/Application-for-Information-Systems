package util;

import dao.ClientDao;
import dao.EmployeDao;
import dao.JpaUtil;
import dao.MediumDao;
import dao.VoyanceDao;
import javax.persistence.RollbackException;
import metier.data.Employe;
import metier.data.Medium;
import metier.data.Voyance;

/**
 *
 * @author hcann
 */
public class serviceUtil {

    public serviceUtil() {
    }
    public static void ajouterEmploye(Employe e){
        try{
            JpaUtil.creerEntityManager();
            JpaUtil.ouvrirTransaction();
            EmployeDao.ajouterEmploye(e);
            JpaUtil.validerTransaction();
        }catch (RollbackException ex){
            DebugLogger.log("Porblème avec ajouterEmploye", ex);
            JpaUtil.annulerTransaction();
        }finally{
            JpaUtil.fermerEntityManager();
        }
    }
    
    public static void ajouterMedium(Medium m){
        try{
            JpaUtil.creerEntityManager();
            JpaUtil.ouvrirTransaction();
            MediumDao.ajouterMedium(m);
            JpaUtil.validerTransaction();
        }catch (RollbackException ex){
            DebugLogger.log("Porblème avec ajouterMedium", ex);
            JpaUtil.annulerTransaction();
        }finally{
            JpaUtil.fermerEntityManager();
        }
    }
    
    public static void creerVoyance(Voyance v){
        try{
            JpaUtil.creerEntityManager();
            JpaUtil.ouvrirTransaction();
            VoyanceDao.ajouterVoyance(v);
            v.finaliserVoyance();
            v.getEmploye().updateDisponibilite();
            ClientDao.modifierClient(v.getClient());
            EmployeDao.modifierEmploye(v.getEmploye());
            JpaUtil.validerTransaction();
        }catch (RollbackException ex){
            DebugLogger.log("Porblème avec creerVoyance", ex);
            JpaUtil.annulerTransaction();
        }finally{
            JpaUtil.fermerEntityManager();
        }
    }
}
