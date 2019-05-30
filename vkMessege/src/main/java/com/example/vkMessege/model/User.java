package com.example.vkMessege.model;


import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Data
@Document(collection = "Users")
public class User {

    @Id
    private String id;
    private boolean enable;
    private String name;
    private int vkid;
    private int countMasseges;
    private String icon;
    private ArrayList<String> blockTypeCoubs=new ArrayList<>();
    private ArrayList<String> sentMessages=new ArrayList<>();
    private ArrayList<String> sentCoubsID=new ArrayList<>();
    private String password;

    public String toJson() {
        return new Gson().toJson(this);
    }


    private void addCountMasseges(){countMasseges++;}
    public void addBlockTypeCoubs(String type){blockTypeCoubs.add(type);}

    public void sendMessagesAndCoubsID(String coubID){
        sentMessages.add(coubID);
        sentCoubsID.add(coubID);
        addCountMasseges();
    }
    public void sendMessagesAndCoubsID(String messges, String coubID){
        sentMessages.add(messges);
        sentCoubsID.add(coubID);
        addCountMasseges();
    }

}
