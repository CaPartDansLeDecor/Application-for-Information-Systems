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
public class ActionDonneesVoyance extends Action{
    
    @Override
    public boolean executer(HttpServletRequest request){
        
        Service service = new Service();
        
        HttpSession session = request.getSession(true);
        Employe employeConnecte = (Employe)session.getAttribute("Connected");
        Employe e = (Employe)service.connexion(employeConnecte.getMail(),employeConnecte.getPassword());
        
        Voyance voyanceActive = service.recupererVoyanceActive(e);
        if(voyanceActive == null){
            request.setAttribute("voyanceActive",false);
        } else {
            request.setAttribute("voyanceActive",true);
            request.setAttribute("voyance",voyanceActive);
        }
        
        return false;
    }
    
}