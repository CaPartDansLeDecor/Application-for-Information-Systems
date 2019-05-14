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
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.data.Medium;

/**
 *
 * @author acousaert
 */
public class SerialisationStatistiques extends Serialisation{
    
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Object[]> objets = (List<Object[]>)request.getAttribute("nbClientParEmploye");
        List<Integer> objetsMedium = (List<Integer>)request.getAttribute("nbClientParMedium");
        
        JsonArray jsonArrayObjets = new JsonArray();
        for (Object[] o : objets) {
            JsonObject jsonObjet = new JsonObject();
            Medium medium = (Medium) o[0];
            String nbApparition = Long.toString((long) o[1]);
            jsonObjet.addProperty("nomMedium", medium.getNom());
            jsonObjet.addProperty("nbVoyanceAssocie", nbApparition);
            jsonArrayObjets.add(jsonObjet);
        }
        
        JsonArray jsonArrayObjetMedium = new JsonArray();
        
        JsonObject jsonObjet = new JsonObject();
        jsonObjet.addProperty("Voyant", objetsMedium.get(0));
        jsonObjet.addProperty("Tarologue", objetsMedium.get(1));
        jsonObjet.addProperty("Astrologue", objetsMedium.get(2));
        
        jsonArrayObjets.add(jsonObjet);
        
        
        JsonObject jsonContainer = new JsonObject();
        jsonContainer.add("objets", jsonArrayObjets);
        PrintWriter out = this.getWriterWithJsonHeader(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(jsonContainer, out);
        
    }
}
