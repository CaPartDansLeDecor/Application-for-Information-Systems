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
public class ActionHistorique extends Action{
    
    @Override
    public boolean executer(HttpServletRequest request){
        
        Service service = new Service();
        String medium = request.getParameter("medium");
        String typeMedium = request.getParameter("typeMedium");
        HttpSession session = request.getSession(true);
        Client client = (Client)session.getAttribute("Connected");
        List<Voyance> voyances = client.getListeVoyance();
        List<Voyance> voyancesFiltrees = new ArrayList<>();
        if(medium.equals("") && typeMedium.equals("Choisir")){
            voyancesFiltrees = voyances;
        } else if(!medium.equals("") && typeMedium.equals("Choisir")){
            for(Voyance v : voyances){
                if(v.getMedium().getNom().equals(medium)){
                    voyancesFiltrees.add(v);
                }
            }
        } else if(medium.equals("") && !typeMedium.equals("Choisir")){
            for(Voyance v : voyances){
                if(v.getMedium().getTalent().equals(typeMedium)){
                    voyancesFiltrees.add(v);
                }
            }
        } else {
            for(Voyance v : voyances){
                if(v.getMedium().getNom().equals(medium) && v.getMedium().getTalent().equals(typeMedium) ){
                    voyancesFiltrees.add(v);
                }
            }
        }
        System.out.println(voyancesFiltrees.size());
        return false;
    }
    
}
