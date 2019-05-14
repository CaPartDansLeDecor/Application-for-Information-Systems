/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import metier.data.Astrologue;
import metier.data.Client;
import metier.data.Tarologue;
import metier.data.Voyance;
import metier.data.Voyant;

/**
 *
 * @author mguilhin
 */
public class SerialisationDonneesVoyance extends Serialisation{
    
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        
        JsonObject jsonContainer = new JsonObject();
        
        Boolean ans = (Boolean) request.getAttribute("voyanceActive");
        jsonContainer.addProperty("voyanceActive",ans);
        Voyance voyance = (Voyance)request.getAttribute("voyance");
        
        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
        
        if(ans){
            Client clientConcerne = voyance.getClient();
            jsonContainer.addProperty("clientCivilite",clientConcerne.getCivilte());
            jsonContainer.addProperty("clientNom",clientConcerne.getNom());
            jsonContainer.addProperty("clientPrenom",clientConcerne.getPrenom());
            jsonContainer.addProperty("clientDateNaissance",dateFormat.format(clientConcerne.getDateNaissance()));
            jsonContainer.addProperty("clientTel",clientConcerne.getTelephone());
            jsonContainer.addProperty("clientMail",clientConcerne.getMail());
            
            JsonArray jsonArrayVoyances = new JsonArray();
            List<Voyance> voyances = clientConcerne.getListeVoyance();
            for(Voyance v : voyances){
                if(v.getCommentaire()!=null){
                    JsonObject jsonVoyance = new JsonObject();

                    jsonVoyance.addProperty("medium",v.getMedium().getNom());
                    jsonVoyance.addProperty("typeMedium",v.getMedium().getTalent());
                    jsonVoyance.addProperty("commentaire",v.getCommentaire());

                    jsonArrayVoyances.add(jsonVoyance); 
                }
            }
            jsonContainer.add("voyances",jsonArrayVoyances);
            jsonContainer.addProperty("clientSigneAstro",clientConcerne.getSigneAstro());
            jsonContainer.addProperty("clientSigneChinois",clientConcerne.getSigneChinois());
            jsonContainer.addProperty("clientAnimalTotem",clientConcerne.getAnimal());
            jsonContainer.addProperty("clientCouleur",clientConcerne.getCouleur());
            jsonContainer.addProperty("mediumNom",voyance.getMedium().getNom());
            String specialite = "";
            if(voyance.getMedium() instanceof Voyant){
                specialite = "Voyant";
                Voyant voyant = (Voyant)voyance.getMedium();
                jsonContainer.addProperty("voyantSpecialite",voyant.getTalent());
            } else if(voyance.getMedium() instanceof Tarologue){
                specialite = "Tarologue";
            } else if(voyance.getMedium() instanceof Astrologue){
                specialite = "Astrologue";
                Astrologue astrologue = (Astrologue)voyance.getMedium();
                jsonContainer.addProperty("astrologueFormation",astrologue.getFormation());
                jsonContainer.addProperty("astrologuePromotion",astrologue.getPromotion());
            }
            jsonContainer.addProperty("mediumSpecialite",specialite);
            jsonContainer.addProperty("mediumDescription",voyance.getMedium().getDescriptif());
            
        }
        
        PrintWriter out = this.getWriterWithJsonHeader(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(jsonContainer, out);
        
    }
    
}
