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
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import metier.data.Client;

/**
 *
 * @author mguilhin
 */
public class SerialisationClientProfil extends Serialisation{
    
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(true);
        Client client = (Client)session.getAttribute("Connected");
        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy"); 
       
        JsonObject jsonContainer = new JsonObject();
        
        jsonContainer.addProperty("civilite", client.getCivilte());
        jsonContainer.addProperty("nom", client.getNom());
        jsonContainer.addProperty("prenom", client.getPrenom());
        jsonContainer.addProperty("codePostal", client.getCodePostale());
        jsonContainer.addProperty("dateNaissance", dateFormat.format(client.getDateNaissance()));
        jsonContainer.addProperty("tel", client.getTelephone());
        jsonContainer.addProperty("mail", client.getMail());
        jsonContainer.addProperty("sgnAstro", client.getSigneAstro());
        jsonContainer.addProperty("sgnChinois", client.getSigneChinois());
        jsonContainer.addProperty("aniTot", client.getAnimal());
        jsonContainer.addProperty("couleurBonheur", client.getCouleur());
        
        PrintWriter out = this.getWriterWithJsonHeader(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(jsonContainer, out);
        
    }
    
}
