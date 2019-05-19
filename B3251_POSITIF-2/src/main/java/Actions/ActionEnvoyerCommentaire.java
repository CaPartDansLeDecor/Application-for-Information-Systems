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
public class ActionEnvoyerCommentaire extends Action{
    
    @Override
    public boolean executer(HttpServletRequest request){
        
        Service service = new Service();
        
        HttpSession session = request.getSession(true);
        Employe employeConnecte = (Employe)session.getAttribute("Connected");
        Employe e = (Employe) service.connexion(employeConnecte.getMail(), employeConnecte.getPassword());
        
        String commentaire = request.getParameter("commentaire");
        Boolean champNonRempli = false;
        Boolean error = false;
        
        if(!"".equals(commentaire)){
            List<Voyance> voyances = e.getListeVoyance();
            Voyance voyance = null;
            for(Voyance v : voyances){
                if(v.getDebut()!=null && v.getFin()==null){
                    voyance = v;
                }
            }
            if(voyance!=null){
                service.terminerVoyance(voyance,commentaire);
            } else {
                error = true;
            }
        } else {
            champNonRempli = true;
        }
        request.setAttribute("champNonRempli",champNonRempli);
        request.setAttribute("error",error);

        
        return false;
    }
    
}
