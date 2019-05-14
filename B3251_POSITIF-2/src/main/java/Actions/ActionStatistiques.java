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
        
        List<Object[]> nbClientParEmploye = service.recupererNbClientsParMedium();
        List<Integer> nbClientParMediums = new ArrayList<Integer>();
        Integer nbClientParVoyant = 0;
        Integer nbClientParTarologue = 0;
        Integer nbClientParAstrologue = 0;
        for(Object[] o: nbClientParEmploye){
            Medium medium = (Medium) o[0];
            System.out.println(medium.getTalent());
            switch (medium.getTalent()) { 
                case "Voyant":
                    nbClientParVoyant++;
                    break;
                case "Tarologue":
                    nbClientParTarologue++;
                    break;
                case "Astrologue":
                    nbClientParAstrologue++;
                    break;
                default:
                    
                    break;
            }
        }
        // Il faut retenir a quel medium correspond le chiffre présenté dans cet ordre
        nbClientParMediums.add(nbClientParVoyant); 
        nbClientParMediums.add(nbClientParTarologue);
        nbClientParMediums.add(nbClientParAstrologue);                     
        
        
        request.setAttribute("nbClientParMedium", nbClientParMediums);
        request.setAttribute("nbClientParEmploye", nbClientParEmploye);
        // Medium debug = (Medium) nbClientParMediums.get(0)[0];
        // Long debugInt = (Long) nbClientParMediums.get(0)[1];
        // System.out.println(debugInt); 
        return false;
    }
}
