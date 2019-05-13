/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.data.Client;
import metier.service.Service;
import metier.data.Medium;
/**
 *
 * @author acousaert
 */
public class ActionDemanderVoyance extends Action{
    
    @Override
    public boolean executer(HttpServletRequest request){ 

        long idMedium = Long.parseLong(request.getParameter("idMedium"));
        Service service = new Service();
        Medium medium = service.rechercherMediumParID(idMedium);
        HttpSession session = request.getSession(true);
        Client client = (Client)session.getAttribute("Connected");
        service.demanderVoyance(medium, client);
        return false;
    }
    
}
