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
public class ActionNouvelleVoyance extends Action {
    
    @Override
    public boolean executer(HttpServletRequest request){
        String typeMedium = request.getParameter("typeMedium");
        Service service = new Service();
        List<Medium> mediums = service.rechercherMediumParTalent(typeMedium);
        if (typeMedium.equals("Choisir")) {
            mediums = service.recupererListeMedium();
        }
        request.setAttribute("mediums", mediums);
        return false;
    }
}
