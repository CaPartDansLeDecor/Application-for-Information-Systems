/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import java.util.List;
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
public class ActionCommencerVoyance extends Action{
    
    @Override
    public boolean executer(HttpServletRequest request){ 

        
        Service service = new Service();
        
        HttpSession session = request.getSession(true);
        Employe employeConnecte = (Employe)session.getAttribute("Connected");
        Employe e = (Employe) service.connexion(employeConnecte.getMail(), employeConnecte.getPassword());
        
        Voyance voyanceActive = service.recupererVoyanceActive(e);
        List<Voyance> voyances = e.getListeVoyance();
        if(voyanceActive == null){
            Voyance voyance = null;
            for(Voyance v : voyances){
                if(v.getDebut()!=null && v.getFin()==null){
                    voyance = v;
                }
            }
            if(voyance!=null){
                request.setAttribute("voyanceActive",false);
                request.setAttribute("voyanceDejaEnCours",true);
            } else {
                request.setAttribute("voyanceActive",false);
                request.setAttribute("voyanceDejaEnCours",false); 
            }
        } else {
            request.setAttribute("voyanceActive",true);
            request.setAttribute("voyanceDejaEnCours",false);
            service.commencerVoyance(voyanceActive);
        }
        
        return false;
    }
    
}
