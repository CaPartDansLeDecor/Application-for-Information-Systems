/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import metier.data.Medium;
import metier.service.Service;

/**
 *
 * @author acousaert
 */
public class ActionStatistiques extends Action {
    
    @Override
    public boolean executer(HttpServletRequest request){
        Service service = new Service();
        
        List<Object[]> nbClientParMedium = service.recupererNbClientsParMedium();
        List<Object[]> nbClientParEmploye = service.recupererNbClientsParEmploye();
        
       
        request.setAttribute("nbClientParMedium", nbClientParMedium);
        request.setAttribute("nbClientParEmploye", nbClientParEmploye);
      
        return false;
    }
}
