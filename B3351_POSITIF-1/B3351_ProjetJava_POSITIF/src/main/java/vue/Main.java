/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import dao.JpaUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import metier.data.Client;
import metier.data.Employe;
import metier.data.Medium;
import metier.data.Voyance;
import metier.service.Service;

/**
 *
 * @author ggribbeni
 */
public class Main {
    
    public static final SimpleDateFormat JSON_DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
    
    public static void main(String[] args){
        JpaUtil.init();
        Service service = new Service();
        
        service.initialisation();
        try {
            Date dateNaissance = JSON_DATE_FORMAT.parse("12-10-1998");
            Client c1 = new Client("Mr", "Canneddu", "Hugo", dateNaissance, 69100,"num Hugo", "hugo.canneddu@insa-lyon.fr", "mdpClient");
            service.insrcrireClient(c1);
            
            dateNaissance = JSON_DATE_FORMAT.parse("24-12-1998");
            Client c2 = new Client("Mr", "Bon", "Jean", dateNaissance, 69100, "num Jean", "jean.bon@insa-lyon.fr", "mdpClient");
            service.insrcrireClient(c2);
            
            dateNaissance = JSON_DATE_FORMAT.parse("29-05-1998");
            Client c3 = new Client("Mme", "Ribbeni", "Grazia", dateNaissance, 69100, "num Grazia", "grazia.ribbeni@insa-lyon.fr", "mdpClient");
            service.insrcrireClient(c3);
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //simulation des service appelé par l'IHM
        //connexion d'un client
        Client clientConnecteCoteClient = null;
        Employe employeConnecte = null;
        
        Object o = service.connexion("hugo.canneddu@insa-lyon.fr", "mdpClient");
        if(o != null){
            if(o instanceof Client){
                clientConnecteCoteClient = (Client)o;
                System.out.println("Client connecté : "+clientConnecteCoteClient);
            }else if(o instanceof Employe){
                employeConnecte=(Employe)o;
                System.out.println("Employe connecté : "+employeConnecte);
            }
        }else{
            System.out.println("Combinaison identifiant mot de passe non valide");
        }
        //recuperation des medium
        List<Medium> listeMedium = service.recupererListeMedium();
        
        //demande de voyance
        service.demanderVoyance(listeMedium.get(2), clientConnecteCoteClient);
        //si à la place de 2 on met une autre chiffre ça ne marche pas
        //demandes rajoutées
        System.out.println("*******************");
        System.out.println(listeMedium);
        System.out.println("*******************");
        service.demanderVoyance(listeMedium.get(0), clientConnecteCoteClient);
        service.demanderVoyance(listeMedium.get(16), clientConnecteCoteClient);
        

        //connexion de l'employe
        Object o2 = service.connexion("bob@marley.com", "mdpEmploye");
        if(o2 != null){
            if(o2 instanceof Client){
                clientConnecteCoteClient = (Client)o2;
                System.out.println("Client connecté : "+clientConnecteCoteClient);
            }else if(o2 instanceof Employe){
                employeConnecte=(Employe)o2;
                System.out.println("Employe connecté : "+employeConnecte);
            }
        }else{
            System.out.println("Combinaison identifiant mot de passe non valide");
        }
        
        //Ajouté pour montrer les fonctionnalités web, sinon jeu pas assez important..
        Employe e1 = (Employe) service.connexion("vincent@vanGogh.com","mdpEmploye");
        Employe e2 = (Employe) service.connexion("jacques@lacan.com","mdpEmploye");
        
        //recuperation de la voyance active
        Voyance voyanceActive=service.recupererVoyanceActive(employeConnecte);
        Voyance voyanceActive2=service.recupererVoyanceActive(e1);
        Voyance voyanceActive3=service.recupererVoyanceActive(e2);
        
        if(voyanceActive2 == null){
            System.out.println("Mauvais employé connecté");
        }
        
        System.out.println("\n Prédictions :");
        List<String> prediction = service.genererPredictions(voyanceActive.getClient(), 2, 4, 3);
        for(String s:prediction){
            System.out.println(s);
        }
        
        service.commencerVoyance(voyanceActive);
        service.terminerVoyance(voyanceActive, "Super voyance");
        
        service.commencerVoyance(voyanceActive2);
        service.terminerVoyance(voyanceActive2, "Voyance bof bof");
        
        service.commencerVoyance(voyanceActive3);
        service.terminerVoyance(voyanceActive3, "Excellent client très doux");
        
        System.out.println(voyanceActive);
        
        System.out.println("\n Test Complémentaire :");
        
        //Test multiple demande de voyance
        System.out.println("\n Test multiple demande de voyance :");
        for(int i=0;i<6;i++){
            service.demanderVoyance(listeMedium.get(2), clientConnecteCoteClient);
        }
        
        //Test des recherche
        System.out.println("\n Liste astrologue :");
        List<Medium> list = service.rechercherMediumParTalent("Astrologue");
        
        for(Medium m : list){
            System.out.println(m);
        }
        
        System.out.println("\n Liste employe :");
        List<Employe> listEmp = service.recupererListeEmployes();
        for(Employe e : listEmp){
            System.out.println(e);
        }
        
        System.out.println("\n nombre client par medium :");
        List<Object[]> listObj = service.recupererNbClientsParMedium();
        for(Object[] ob : listObj){
            Medium med = (Medium)ob[0];
            Long num = (Long)ob[1];
            System.out.println(med.getNom()+" Nombre : "+num);
        }
        
        Medium recherche = service.rechercherMediumParID(3L);
        
        System.out.println(recherche);
        
        JpaUtil.destroy();
    }
}