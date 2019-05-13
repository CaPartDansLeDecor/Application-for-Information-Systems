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
public class SerialisationNouvelleVoyance extends Serialisation{
    
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Medium> mediums = (List<Medium>)request.getAttribute("mediums");
        JsonArray jsonArrayMediums = new JsonArray();
        for (Medium m : mediums) {
            JsonObject jsonMedium = new JsonObject();
            jsonMedium.addProperty("id", m.getIdMedium());
            jsonMedium.addProperty("medium", m.getNom());
            jsonMedium.addProperty("mediumType", m.getTalent());
            jsonMedium.addProperty("description", m.getDescriptif());
            jsonArrayMediums.add(jsonMedium);
        }
        JsonObject jsonContainer = new JsonObject();
        jsonContainer.add("mediums", jsonArrayMediums);
        PrintWriter out = this.getWriterWithJsonHeader(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(jsonContainer, out);
        
    }
}
