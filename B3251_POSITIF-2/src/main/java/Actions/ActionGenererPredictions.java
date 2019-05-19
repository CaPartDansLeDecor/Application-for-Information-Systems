/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.data.Client;
import metier.data.Employe;
import metier.data.Voyance;
import metier.service.Service;

/**
 *
 * @author mguilhin
 */
public class ActionGenererPredictions extends Action{
    
    @Override
    public boolean executer(HttpServletRequest request){
        
        Service service = new Service();
        String coeur = request.getParameter("coeur");
        String sante = request.getParameter("sante");
        String travail = request.getParameter("travail");
        
        //On récupère le client
        
        HttpSession session = request.getSession(true);
        Employe employeConnecte = (Employe)session.getAttribute("Connected");
        Employe e = (Employe) service.connexion(employeConnecte.getMail(), employeConnecte.getPassword());
        
        List<Voyance> voyances = e.getListeVoyance();
        Voyance voyance = null;
        for(Voyance v : voyances){
            if(v.getDebut()!=null && v.getFin()==null){
                voyance = v;
            }
        }
        //System.out.println(voyance);
        if(voyance == null){
            request.setAttribute("voyanceEnCours",false);
        } else {
            request.setAttribute("voyanceEnCours",true);
            Client c = voyance.getClient();
            List<String> listePredictions = service.genererPredictions(c,Integer.parseInt(coeur),Integer.parseInt(sante),Integer.parseInt(travail));
            request.setAttribute("coeur",listePredictions.get(0));
            request.setAttribute("sante",listePredictions.get(1));
            request.setAttribute("travail",listePredictions.get(2));
        }
        return false;
    }
    
}
