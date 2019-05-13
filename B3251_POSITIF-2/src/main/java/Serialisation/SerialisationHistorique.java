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
import metier.data.Voyance;

/**
 *
 * @author mguilhin
 */
public class SerialisationHistorique extends Serialisation{
    
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        
        JsonObject jsonContainer = new JsonObject();
        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
        DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        
        JsonArray jsonArrayVoyances = new JsonArray();
        List<Voyance> voyancesFiltrees = (List<Voyance>) request.getAttribute("voyances");
        for(Voyance voyance : voyancesFiltrees){
            if(voyance.getCommentaire()!=null){
                JsonObject jsonVoyance = new JsonObject();
                
                jsonVoyance.addProperty("medium",voyance.getMedium().getNom());
                jsonVoyance.addProperty("typeMedium",voyance.getMedium().getTalent());
                jsonVoyance.addProperty("date",dateFormat.format(voyance.getDebut()));
                jsonVoyance.addProperty("debut",timeFormat.format(voyance.getDebut()));
                jsonVoyance.addProperty("fin",timeFormat.format(voyance.getFin()));

                jsonArrayVoyances.add(jsonVoyance); 
            }
        }
        jsonContainer.add("voyances",jsonArrayVoyances);
        
        PrintWriter out = this.getWriterWithJsonHeader(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(jsonContainer, out);
        
    }
    
}
