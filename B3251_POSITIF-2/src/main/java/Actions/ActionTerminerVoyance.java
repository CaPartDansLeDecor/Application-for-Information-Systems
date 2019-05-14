/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.data.Client;
import metier.data.Employe;
import metier.service.Service;
import metier.data.Medium;
import metier.data.Voyance;
/**
 *
 * @author acousaert
 */
public class ActionTerminerVoyance extends Action{
    
    @Override
    public boolean executer(HttpServletRequest request){ 

        Service service = new Service();
        
        HttpSession session = request.getSession(true);
        Employe employeConnecte = (Employe)session.getAttribute("Connected");
        Employe e = (Employe)service.connexion(employeConnecte.getMail(),employeConnecte.getPassword());
        
        Voyance voyanceActive = service.recupererVoyanceActive(e);
        if(voyanceActive == null){
            request.setAttribute("voyanceEnCours",false);
        } else {
            if(voyanceActive.getDebut()!=null && voyanceActive.getFin()==null){
                request.setAttribute("voyanceEnCours",true);
                //On ne peut pas encore terminer la voyance, car le commentaire est ici inconnu
            }
            request.setAttribute("voyanceEnCours",false);
        }
        
        return false;
    }
    
}
