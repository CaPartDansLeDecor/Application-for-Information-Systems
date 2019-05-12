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

/**
 *
 * @author mguilhin
 */
public class ActionConnexion extends Action{
    
    @Override
    public boolean executer(HttpServletRequest request){
        Service service = new Service();
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        HttpSession session = request.getSession(true);
        
        Client clientConnecte = null;
        Employe employeConnecte = null;
        Boolean connected = false;

        Object o = service.connexion(login,password);
        if(o != null){
            session.setAttribute("Connected",o);
            if(o instanceof Client){
                clientConnecte = (Client)o;
                connected = true;
                request.setAttribute("nature","client");
            }else if(o instanceof Employe){
                employeConnecte=(Employe)o;
                connected = true;
                request.setAttribute("nature","employe");

            }
        }else{
            connected = false;
            request.setAttribute("nature"," ");
        }
        request.setAttribute("connected",connected);
        
        return false;
    }
    
}
