package com.example.vkMessege.coub;

import com.example.vkMessege.model.Coub;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * class gets a list or json of cubes from coub.com
 * @author cikmon
 */

public class CoubGet {

    private List<Coub> coubs=new ArrayList<>() ;

    public List<Coub> getCoubs() throws IOException {
        setArrayCoubs("http://coub.com/api/v2/timeline/channel/3f4accb63807e467b01f8aafe7b70897");

        return coubs;
    }


    private void setArrayCoubs(String url) throws IOException {
        JsonArray jsonArray=getRequestCoubsJson(url);

        jsonArray.forEach(e->{
            Coub coub=new Coub();
            List<String> categories=new ArrayList<>();
            List<String> tags=new ArrayList<>();


            String temp=e.getAsJsonObject().get("permalink").toString();
            coub.setPermalink(temp.replace("\"",""));
            temp=e.getAsJsonObject().get("created_at").toString();
            coub.setCreated_at(temp.replace("\"",""));


            JsonArray jsonArrayCategories=e.getAsJsonObject().get("categories").getAsJsonArray();
            jsonArrayCategories.forEach(e2->categories.add(e2.getAsJsonObject().get("title").getAsString()));

            JsonArray jsonArrayTags=e.getAsJsonObject().get("tags").getAsJsonArray();
            jsonArrayTags.forEach(e2->tags.add(e2.getAsJsonObject().get("title").getAsString()));

            coub.setTagsAndCategories(categories);
            coub.addTagsAndCategories(tags);

            coubs.add(coub);
        });


    }



    public JsonArray getRequestCoubsJson(String url) throws IOException {

        JsonArray jsonArray=new JsonArray();

        for(int i=1; i<=requestGet(url+"?page=1&per_page=25").get("total_pages").getAsInt(); i++){
            JsonObject joOtherPages = requestGet(url+"?page="+i+"&per_page=25");
            jsonArray.addAll(joOtherPages.get("coubs").getAsJsonArray());
        }
        System.out.println(jsonArray.size());

        return jsonArray;
    }

    private JsonObject requestGet(String url) throws IOException {

        URL obj = new URL(url);
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) obj.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return (JsonObject) new JsonParser().parse(response.toString());
    }

}
