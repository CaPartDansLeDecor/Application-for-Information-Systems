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
import metier.data.Employe;

/**
 *
 * @author acousaert
 */
public class SerialisationStatistiques extends Serialisation{
    
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Object[]> objetsMedium = (List<Object[]>)request.getAttribute("nbClientParMedium");
        List<Object[]> objetsEmploye = (List<Object[]>)request.getAttribute("nbClientParEmploye");
        
        JsonArray jsonArrayObjetsMedium = new JsonArray();
        for (Object[] o : objetsMedium) {
            JsonObject jsonObjet = new JsonObject();
            Medium medium = (Medium) o[0];
            String nbApparition = Long.toString((long) o[1]);
            jsonObjet.addProperty("nomMedium", medium.getNom());
            jsonObjet.addProperty("MediumNbVoyanceAssocie", nbApparition);
            jsonArrayObjetsMedium.add(jsonObjet);
        }
        
        JsonArray jsonArrayObjetsEmploye = new JsonArray();
        for (Object[] o : objetsEmploye) {
            JsonObject jsonObjet = new JsonObject();
            Employe employe = (Employe) o[0];
            String nbApparition = Long.toString((long) o[1]);
            jsonObjet.addProperty("nomEmploye", employe.getNom());
            jsonObjet.addProperty("EmployeNbVoyanceAssocie", nbApparition);
            jsonArrayObjetsEmploye.add(jsonObjet);
        }
        
        JsonObject jsonContainer = new JsonObject();
        jsonContainer.add("objetsMedium", jsonArrayObjetsMedium);
        jsonContainer.add("objetsEmploye", jsonArrayObjetsEmploye);
        PrintWriter out = this.getWriterWithJsonHeader(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(jsonContainer, out);
        
    }
}
