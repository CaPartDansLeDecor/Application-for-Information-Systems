/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import metier.data.Employe;
import metier.data.Voyance;
import metier.service.Service;

/**
 *
 * @author mguilhin
 */
public class SerialisationCommencerVoyance extends Serialisation{
    
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        JsonObject jsonContainer = new JsonObject();
        
        //TEST POUR COMPRENDRE POURQUOI VOYANCE ACTIVE EST NULL
        Service service = new Service();
        
        HttpSession session = request.getSession(true);
        Employe employeConnecte = (Employe)session.getAttribute("Connected");
        
        Voyance voyanceActive = service.recupererVoyanceActive(employeConnecte);
        System.out.println("2 : " + voyanceActive);
        //FIN TEST
        
        Boolean ans = (Boolean) request.getAttribute("voyanceActive");
        jsonContainer.addProperty("voyanceActive",ans);
        if(!ans){
            jsonContainer.addProperty("voyanceDejaEnCours", (Boolean) request.getAttribute("voyanceDejaEnCours"));
        }
        PrintWriter out = this.getWriterWithJsonHeader(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(jsonContainer, out);
        
    }
    
}
