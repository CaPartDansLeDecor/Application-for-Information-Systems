package metier.service;

import dao.ClientDao;
import dao.EmployeDao;
import dao.JpaUtil;
import dao.MediumDao;
import dao.VoyanceDao;
import java.io.IOException;
import java.util.List;
import javax.persistence.RollbackException;
import metier.data.Astrologue;
import metier.data.Client;
import metier.data.Employe;
import metier.data.Medium;
import metier.data.Tarologue;
import metier.data.Voyance;
import metier.data.Voyant;
import util.AstroTest;
import util.DebugLogger;
import util.Message;
import util.serviceUtil;

/**
 *
 * @author ggribbeni hcanneddu
 */
public class Service {

    public Service() {
    }
    //------------------Ajout Objet Metier------------------------------
    public void insrcrireClient(Client c){
        try{
        AstroTest astroApi = new AstroTest();
        List<String> profil = astroApi.getProfil(c.getPrenom(),
                c.getDateNaissance());
        c.setProfilAstro(profil.get(0),profil.get(1) , profil.get(2),
                profil.get(3));
        }catch(IOException ex){
            DebugLogger.log("Porblème avec astroTest dans insrcrireClient", ex);
        }
        try{
            JpaUtil.creerEntityManager();
            JpaUtil.ouvrirTransaction();
            ClientDao.ajouterClient(c);
            JpaUtil.validerTransaction();
            Message.envoyerMail("contact@posit.if", c.getMail(), "Bienvenue chez POSIT'IF",
                     "Bonjour "+c.getPrenom()+", nous vous confirmons votre inscription au service POSIT'IF."
                             + " Votre numéro de client est "+c.getIdClient()+".");
        }catch (RollbackException ex){
            DebugLogger.log("Porblème avec persitance dans insrcrireClient",ex);
            JpaUtil.annulerTransaction();
            Message.envoyerMail("contact@posit.if", c.getMail(), "Bienvenue "
                    + "chez POSIT'IF",
                     "Bonjour "+c.getPrenom()+", votre inscription au service "
                             + "POSIT'IF a malencontreusement échoué... Merci "
                             + "de recommencer ultérieurement.");
        }finally{
            JpaUtil.fermerEntityManager();
        }
    }
    
    public void initialisation(){
        try{
            //création des mediums 
            Voyant m1=new Voyant("Gweanël","Spécialiste des grandes conversation au-delà de TOUTES les frontières.",
                    "Boule de Cristal");
            Voyant m2=new Voyant("Professeur Maxwell","Votre avenir est devant vous : regardons le ensemble !",
                    "Marc de Café");
            Tarologue m3= new Tarologue("Mme Irma","Comprenez votre entourage grâce à mes cartes !"
                    + " Résultats rapides.");
            Tarologue m4= new Tarologue("Endora", "Mes cartes répondront à toutes vos questions personnelles.");
            Astrologue m5= new Astrologue("Serena", "Basé à Champigny-sur-Marne, Serena vous révèlera"
                    + " votre avenir pour éclairer votre passé.", "Ecole Normale Supérieur "
                    + "d'Astrologie (ENS-Astro)", "2006");
            Astrologue m6=new Astrologue("Mr M. Histaire-Hyeux", "Avenir, avenir, que nous réserves-tu?"
                    + " N'attendez plus, demandez à me consulter!", "Institut des Nouveaux Savoirs "
                    + "Astrologique", "2010");
            Voyant m7=new Voyant("Grace","Lancez-vous à la recherche de l'inconnu, vous ne serez pas deçus.",
                      "Miroir magique");
            Voyant m8=new Voyant("Mike", "Prévoir l'imprévisible est possible, decouvrez comment avec moi.", 
                     "Lunettes de Venus");
            Tarologue m9= new Tarologue("Cynthia","La vie est comme une main de cartes, il faut bien "
                    + "connaître ce qu'on a pour en tirer le mieux.");
            Tarologue m10= new Tarologue("Maxime","Le passé est à nous, le futur est aux meilleurs. Consultez mes"
                    + " cartes pour en savoir plus!");
            Astrologue m11= new Astrologue("Astrid", "On n'est que ce qui reste d'une grosse explosion cosmique."
                    + ", retrouvons notre lien avec l'Univers qui nous a générés.", "Ecole Normale Supérieur "
                    + "d'Astrologie (ENS-Astro)", "2008");
            Astrologue m12=new Astrologue("Julien", "Poser une question c'est le premier pas vers la réponse.", 
                    "Institut des Nouveaux Savoirs "
                    + "Astrologique", "2015");
            Voyant m13=new Voyant("David","Jamais sous-estimer son avenir.",
                    "Montre qui montre");
            Voyant m14=new Voyant("Vincent","L'avenir est un tableau que l'on paint bien que si on sait bien."
                    + "où l'on veut arriver. !",
                    "Pinceau de rêves");
            Tarologue m15= new Tarologue("Lilly","Les cartes nous connaissent mieux que nous mêmes !");
            Tarologue m16= new Tarologue("Fiona", "Qui ne prend pas de risques ne verra jamais au délà"
                    + " de ses convinctions");
            Astrologue m17= new Astrologue("Albus", "Les étoiles nous conseillent siléncieusement, écoutons-les.",
                    "Ecole Normale Supérieur d'Astrologie (ENS-Astro)", "2002");
            Astrologue m18=new Astrologue("Wendy", "Nos rêves définissent nos envies et nos peurs. ", 
                    "Institut des Nouveaux Savoirs "
                    + "Astrologique", "2013");
            Voyant m19=new Voyant("Ketty","Demain, après-demain, toujours à vos côtés.",
                    "Boule de Cristal");
            Voyant m20=new Voyant("Nina","Un jour tout sera plus clair, venez le découvrir avec moi !",
                    "Pinceau de rêves");
            
            serviceUtil.ajouterMedium(m1);serviceUtil.ajouterMedium(m2);serviceUtil.ajouterMedium(m3);
            serviceUtil.ajouterMedium(m4);serviceUtil.ajouterMedium(m5);serviceUtil.ajouterMedium(m6);
            serviceUtil.ajouterMedium(m7);serviceUtil.ajouterMedium(m8);serviceUtil.ajouterMedium(m9);
            serviceUtil.ajouterMedium(m10);serviceUtil.ajouterMedium(m11);serviceUtil.ajouterMedium(m12);
            serviceUtil.ajouterMedium(m13);serviceUtil.ajouterMedium(m14);serviceUtil.ajouterMedium(m15);
            serviceUtil.ajouterMedium(m16);serviceUtil.ajouterMedium(m17);serviceUtil.ajouterMedium(m18);
            serviceUtil.ajouterMedium(m19);serviceUtil.ajouterMedium(m20);  
            
            //création des employees 
            Employe e1=new Employe("Albert","Einstein","num Albert","albert@einstein.com","mdpEmploye");
            Employe e2=new Employe("Jeanne","d'arc","num Jeanne","jeanne@darc.com","mdpEmploye");
            Employe e3=new Employe("Gost","Buster","num Gost","gost@buster.com","mdpEmploye");
            Employe e4=new Employe("Guy","Tarre","num Guy","guy@tarre.com","mdpEmploye");
            Employe e5=new Employe("Bob","Marley","num Bob","bob@marley.com","mdpEmploye");
            Employe e6=new Employe("Albert","Camus","num Albert","albert@camus.com","mdpEmploye");
            Employe e7=new Employe("Immanuel","Kant","num Immanuel","immanuel@kant.com","mdpEmploye");
            Employe e8=new Employe("Freddy","Mercury","num Freddy","fredde@mercury.com","mdpEmploye");
            Employe e9=new Employe("Auguste","Renoir","num Auguste","auguste@renoir.com","mdpEmploye");
            Employe e10=new Employe("Bon","Jovi","num Bon","bon@jovi.com","mdpEmploye");
            Employe e11=new Employe("Friedrich","Nietzsche","num Friedrich","friedrich@nietzsche.com","mdpEmploye");
            Employe e12=new Employe("Jacques","Lacan","num Jcques","jacques@lacan.com","mdpEmploye");
            Employe e13=new Employe("Vincent","Van Gogh","num Vincent","vincent@vanGogh.com","mdpEmploye");
            Employe e14=new Employe("Marie","Curie","num Marie","marie@curie.com","mdpEmploye");
            Employe e15=new Employe("Céline","Dion","num Céline","celine@dion.com","mdpEmploye");
            
            //ajout à la base de donné
            serviceUtil.ajouterEmploye(e1);serviceUtil.ajouterEmploye(e2);serviceUtil.ajouterEmploye(e3);
            serviceUtil.ajouterEmploye(e4);serviceUtil.ajouterEmploye(e5);serviceUtil.ajouterEmploye(e6);
            serviceUtil.ajouterEmploye(e7);serviceUtil.ajouterEmploye(e8);serviceUtil.ajouterEmploye(e9);
            serviceUtil.ajouterEmploye(e10);serviceUtil.ajouterEmploye(e11);serviceUtil.ajouterEmploye(e12);
            serviceUtil.ajouterEmploye(e13);serviceUtil.ajouterEmploye(e14);serviceUtil.ajouterEmploye(e15);
            
            //affectation des mediums aux employees
            try {
                JpaUtil.creerEntityManager();
                JpaUtil.ouvrirTransaction();
                e1.addMedium(m1);e1.addMedium(m3);e1.addMedium(m5);
                e2.addMedium(m2);e2.addMedium(m3);
                e3.addMedium(m4);e3.addMedium(m5);e3.addMedium(m6);
                e4.addMedium(m1);e4.addMedium(m2);e4.addMedium(m4);
                e5.addMedium(m1);e5.addMedium(m2);e5.addMedium(m3);e5.addMedium(m6);
                e6.addMedium(m7);e6.addMedium(m8);e6.addMedium(m9);
                e7.addMedium(m8);e7.addMedium(m9);e8.addMedium(m10);
                e8.addMedium(m11);e8.addMedium(m12);e8.addMedium(m13);
                e9.addMedium(m13);e9.addMedium(m14);e9.addMedium(m15);
                e10.addMedium(m15);e6.addMedium(m16);e6.addMedium(m17);
                e11.addMedium(m16);e11.addMedium(m18);e11.addMedium(m19);
                e12.addMedium(m17);e11.addMedium(m20);e11.addMedium(m19);
                e13.addMedium(m20);e13.addMedium(m1);e13.addMedium(m5);
                e14.addMedium(m5);e14.addMedium(m2);e14.addMedium(m11);
                e15.addMedium(m16);e15.addMedium(m15);e15.addMedium(m13);
                
                EmployeDao.modifierEmploye(e1);EmployeDao.modifierEmploye(e2);EmployeDao.modifierEmploye(e3);
                EmployeDao.modifierEmploye(e4);EmployeDao.modifierEmploye(e5);EmployeDao.modifierEmploye(e6);
                EmployeDao.modifierEmploye(e7);EmployeDao.modifierEmploye(e8);EmployeDao.modifierEmploye(e9);
                EmployeDao.modifierEmploye(e10);EmployeDao.modifierEmploye(e11);EmployeDao.modifierEmploye(e12);
                EmployeDao.modifierEmploye(e13);EmployeDao.modifierEmploye(e14);EmployeDao.modifierEmploye(e15);

                MediumDao.modiferMedium(m1);MediumDao.modiferMedium(m2);MediumDao.modiferMedium(m3);
                MediumDao.modiferMedium(m4);MediumDao.modiferMedium(m5);MediumDao.modiferMedium(m6);
                MediumDao.modiferMedium(m7);MediumDao.modiferMedium(m8);MediumDao.modiferMedium(m9);
                MediumDao.modiferMedium(m10);MediumDao.modiferMedium(m11);MediumDao.modiferMedium(m12);
                MediumDao.modiferMedium(m13);MediumDao.modiferMedium(m14);MediumDao.modiferMedium(m15);
                MediumDao.modiferMedium(m16);MediumDao.modiferMedium(m17);MediumDao.modiferMedium(m18);
                MediumDao.modiferMedium(m19);MediumDao.modiferMedium(m20);
                
                JpaUtil.validerTransaction();
            } catch (RollbackException ex) {
             DebugLogger.log("Porblème avec affectation des mediums "
                            + "aux employees dans initialisation", ex);
            } finally {
               JpaUtil.fermerEntityManager(); 
            }
        }catch(Exception ex){
            DebugLogger.log("Porblème avec initialisation", ex);
        }
    }
    
    //------------------Recherche------------------------------
    public List<Medium> rechercherMediumParTalent(String talent) {
       List<Medium> l = null;
        try {
            JpaUtil.creerEntityManager();
            l = MediumDao.rechercherMediumParTalent(talent);
        } catch (RollbackException ex) {
            DebugLogger.log("Porblème avec rechercherMediumParTalent", ex);
        } finally {
            JpaUtil.fermerEntityManager(); 
        }
        return l; 
    }
    
    public List<Medium> recupererListeMedium() {
       List<Medium> l = null;
        try {
            JpaUtil.creerEntityManager();
            l = MediumDao.recupererListeMedium();
        } catch (RollbackException ex) {
            DebugLogger.log("Porblème avec recupererListeMedium", ex);
        } finally {
            JpaUtil.fermerEntityManager(); 
        }
        return l; 
    }
    
    public List<Employe> recupererListeEmployes(){
     List<Employe> l = null;
        try {
            JpaUtil.creerEntityManager();
            l = EmployeDao.recupererListeEmployes();
        } catch (RollbackException ex) {
            DebugLogger.log("Porblème avec recupererListeEmployes", ex);
        } finally {
            JpaUtil.fermerEntityManager(); 
        }
        return l;
    }
    
    public List<Object[]> recupererNbClientsParMedium(){
        List<Object[]> result=null;
        try {
            JpaUtil.creerEntityManager();
            result=MediumDao.recupererNbClientsParMedium();
        } catch (RollbackException ex) {
            DebugLogger.log("Porblème avec recupererNbClientsParMedium", ex);
        } finally {
            JpaUtil.fermerEntityManager(); 
        }
        return result;
    }
    
    public Voyance recupererVoyanceActive(Employe e){
        Voyance result=null;
        try{
            JpaUtil.creerEntityManager();
            result=EmployeDao.recupererVoyanceActive(e);
        } catch (RollbackException ex) {
            DebugLogger.log("Porblème avec recupererVoyanceActive", ex);
        } finally {
            JpaUtil.fermerEntityManager(); 
        }
        return result;
    }
    
    //------------------Service connexion------------------------------
    public Object connexion(String mail, String mdp){
        Object o=null;
        try {
            JpaUtil.creerEntityManager();
            o = ClientDao.rechercherClientParMailMdp(mail,mdp);
            if(o==null){
                o = EmployeDao.rechercherEmployeParMailMdp(mail,mdp);
            }
        } catch (RollbackException ex) {
            DebugLogger.log("Porblème de connexion", ex);
        } finally {
            JpaUtil.fermerEntityManager(); 
        }
        return o;
    }
    
    //------------------Service Voyance------------------------------
    public void terminerVoyance(Voyance v, String commentaire) {
        try {
            JpaUtil.creerEntityManager();
            JpaUtil.ouvrirTransaction();
            
            v.terminerVoyance();
            v.setCommentaire(commentaire);
            v.getEmploye().updateDisponibilite();
            VoyanceDao.modifierVoyance(v);
            EmployeDao.modifierEmploye(v.getEmploye());
            
            JpaUtil.validerTransaction();
        } catch (RollbackException ex) {
            DebugLogger.log("Porblème terminerVoyance", ex);
        } finally {
            JpaUtil.fermerEntityManager(); 
        }
    }
    
    public void commencerVoyance(Voyance v) {
        try {
            JpaUtil.creerEntityManager();
            JpaUtil.ouvrirTransaction();
            v.commencerVoyance();
            VoyanceDao.modifierVoyance(v);
  
            JpaUtil.validerTransaction();
            String mesCli=v.getMedium().getNom()+" est à l'écoute, vous pouvez le contacter"
                    + " en appellant le "+v.getEmploye().getTelephone();
            Message.envoyerNotification(v.getClient().getTelephone(), mesCli);
        } catch (RollbackException ex) {
            DebugLogger.log("Porblème commencerVoyance", ex);
        } finally {
            JpaUtil.fermerEntityManager(); 
        }
    }
    
    public void demanderVoyance(Medium m, Client c){
        Employe e = null;
        try {
            JpaUtil.creerEntityManager();
            e = EmployeDao.recupererEmployeMedium(m);//affectation de la voyance
        } catch (RollbackException ex) {
            DebugLogger.log("Porblème demanderVoyance", ex);
        } finally {
            JpaUtil.fermerEntityManager(); 
        }
        
        if(e!=null){
                serviceUtil.creerVoyance(new Voyance (c,e,m));
                String mesEmp="Vous avez une nouvelle voyance à effectuer";
                Message.envoyerNotification(e.getTelephone(), mesEmp);
                
        }else{
                String mesCli=m.getNom()+" Le medium n'est pas disponible"
                        + " maintenant, veuillez réssayer plus tard";
                Message.envoyerNotification(c.getTelephone(), mesCli);
        }
    }
    
    public List<String> genererPredictions(Client c, int amour, int sante,
            int travail){
        List<String> result=null;
        try{
            AstroTest astroApi = new AstroTest();
            result= astroApi.getPredictions(c.getCouleur(), c.getAnimal(),
                    amour, sante, travail);
        }catch(IOException ex){
            DebugLogger.log("Problème genererPredictions", ex);
        }
        return result;
    }
    
    public Medium rechercherMediumParID(Long id){
        Medium result=null;
        try {
            JpaUtil.creerEntityManager();
            JpaUtil.ouvrirTransaction();
               
            result = MediumDao.rechercherMediumParID(id);
            
            JpaUtil.validerTransaction();
        } catch (RollbackException ex) {
            DebugLogger.log("Porblème terminerVoyance", ex);
        } finally {
            JpaUtil.fermerEntityManager(); 
        }
        return result;
    }
}