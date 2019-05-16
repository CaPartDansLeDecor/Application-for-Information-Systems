package Servlet;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Actions.ActionClientProfil;
import Actions.ActionCommencerVoyance;
import Actions.ActionConnexion;
import Actions.ActionDemanderVoyance;
import Actions.ActionDonneesVoyance;
import Actions.ActionEnvoyerCommentaire;
import Actions.ActionGenererPredictions;
import Actions.ActionHistorique;
import Actions.ActionInscription;
import Actions.ActionNouvelleVoyance;
import Actions.ActionStatistiques;
import Actions.ActionTerminerVoyance;
import Serialisation.SerialisationClientProfil;
import Serialisation.SerialisationCommencerVoyance;
import Serialisation.SerialisationConnexion;
import Serialisation.SerialisationDemanderVoyance;
import Serialisation.SerialisationDonneesVoyance;
import Serialisation.SerialisationEnvoyerCommentaire;
import Serialisation.SerialisationGenererPredictions;
import Serialisation.SerialisationHistorique;
import Serialisation.SerialisationInscription;
import Serialisation.SerialisationNouvelleVoyance;
import Serialisation.SerialisationStatistiques;
import Serialisation.SerialisationTerminerVoyance;
import dao.JpaUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import metier.service.Service;
/**
 *
 * @author mguilhin
 */
@WebServlet(urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    Service service = new Service();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession(true);
        String action = request.getParameter("action");
        response.setCharacterEncoding("UTF-8");

        switch(action){
            
            case "connecter":
                ActionConnexion actionConnexion = new ActionConnexion();
                SerialisationConnexion serialisationConnexion = new SerialisationConnexion();
                actionConnexion.executer(request);
                serialisationConnexion.serialiser(request, response);
            break;
            
            case "inscription":
                ActionInscription actionInscription = new ActionInscription();
                SerialisationInscription serialisationInscription = new SerialisationInscription();
                actionInscription.executer(request);
                serialisationInscription.serialiser(request, response);
            break;
            
            case "afficherProfil":
                ActionClientProfil actionClientProfil = new ActionClientProfil();
                SerialisationClientProfil serialisationClientProfil = new SerialisationClientProfil();
                actionClientProfil.executer(request);
                serialisationClientProfil.serialiser(request, response);
            break;
                
            case "historique":
                ActionHistorique actionHistorique = new ActionHistorique();
                SerialisationHistorique serialisationHistorique = new SerialisationHistorique();
                actionHistorique.executer(request);
                serialisationHistorique.serialiser(request, response);
            break;
            
            case "donneesVoyance":
                ActionDonneesVoyance actionDonneesVoyance = new ActionDonneesVoyance();
                SerialisationDonneesVoyance serialisationDonneesVoyance = new SerialisationDonneesVoyance();
                actionDonneesVoyance.executer(request);
                serialisationDonneesVoyance.serialiser(request, response);
            break;
            
             case "nouvelleVoyance":
                ActionNouvelleVoyance actionNouvelleVoyance = new ActionNouvelleVoyance();
                SerialisationNouvelleVoyance serialisationNouvelleVoyance = new SerialisationNouvelleVoyance();
                actionNouvelleVoyance.executer(request);
                serialisationNouvelleVoyance.serialiser(request, response);
            break;
            
            case "demanderVoyance":
                ActionDemanderVoyance actionDemanderVoyance = new ActionDemanderVoyance();
                SerialisationDemanderVoyance serialisationDemanderVoyance = new SerialisationDemanderVoyance();
                actionDemanderVoyance.executer(request);
                serialisationDemanderVoyance.serialiser(request, response);
            break;
            
            case"commencerVoyance":
                ActionCommencerVoyance actionCommencerVoyance = new ActionCommencerVoyance();
                SerialisationCommencerVoyance serialisationCommencerVoyance = new SerialisationCommencerVoyance();
                actionCommencerVoyance.executer(request);
                serialisationCommencerVoyance.serialiser(request, response);
            break;
            
            case"terminerVoyance":
                ActionTerminerVoyance actionTerminerVoyance = new ActionTerminerVoyance();
                SerialisationTerminerVoyance serialisationTerminerVoyance = new SerialisationTerminerVoyance();
                actionTerminerVoyance.executer(request);
                serialisationTerminerVoyance.serialiser(request, response);
            break;
            
            case"genererPredictions":
                ActionGenererPredictions actionGenererPredictions = new ActionGenererPredictions();
                SerialisationGenererPredictions serialisationGenererPredictions = new SerialisationGenererPredictions();
                actionGenererPredictions.executer(request);
                serialisationGenererPredictions.serialiser(request, response);
            break;
            
            case"envoyerCommentaire":
                ActionEnvoyerCommentaire actionEnvoyerCommentaire = new ActionEnvoyerCommentaire();
                SerialisationEnvoyerCommentaire serialisationEnvoyerCommentaire = new SerialisationEnvoyerCommentaire();
                actionEnvoyerCommentaire.executer(request);
                serialisationEnvoyerCommentaire.serialiser(request, response);
            break;
            
            
            case "statistiques":
                ActionStatistiques actionStatistiques = new ActionStatistiques();
                SerialisationStatistiques serialisationStatistiques = new SerialisationStatistiques();
                actionStatistiques.executer(request);
                serialisationStatistiques.serialiser(request, response);
            break;
                
        }
    }

        
        

    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    @Override
    public void init() throws ServletException {
        super.init();
        JpaUtil.init();
    }

    @Override
    public void destroy() {
        JpaUtil.destroy();
        super.destroy();
  }
        
}


