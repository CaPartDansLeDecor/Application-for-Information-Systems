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
public class ActionCommencerVoyance extends Action{
    
    @Override
    public boolean executer(HttpServletRequest request){ 

        System.out.println("************************************");
        Service service = new Service();
        
        HttpSession session = request.getSession(true);
        Employe employeConnecte = (Employe)session.getAttribute("Connected");
        
        Voyance voyanceActive = service.recupererVoyanceActive(employeConnecte);
        System.out.println(voyanceActive);
        if(voyanceActive == null){
            request.setAttribute("voyanceActive",false);
            request.setAttribute("voyanceDejaEnCours",false);
        } else {
            if(voyanceActive.getDebut()==null){
                request.setAttribute("voyanceActive",true);
                service.commencerVoyance(voyanceActive);
                //TEST POUR VOIR QUAND VOYANCEACTIVE EST NUL
                //PROBLEME SUREMENT DU AU SERVICE RECUPERERVOYANCEACTIVE
                System.out.println("1 : " + voyanceActive);

            } else {
                request.setAttribute("voyanceActive",false);
                request.setAttribute("voyanceDejaEnCours",true);   

                
            }
        }
        
        return false;
    }
    
}
