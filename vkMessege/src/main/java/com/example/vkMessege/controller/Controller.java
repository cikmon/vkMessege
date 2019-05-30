package com.example.vkMessege.controller;

import com.example.vkMessege.coub.CoubGet;
import com.example.vkMessege.dao.CoubMongoRepository;
import com.example.vkMessege.dao.UserMongoRepository;
import com.example.vkMessege.model.Coub;
import com.example.vkMessege.model.User;
import com.example.vkMessege.service.CoubService;
import com.example.vkMessege.service.CoubServiceImpl;
import com.example.vkMessege.service.UserService;
import com.example.vkMessege.testsMetods.AutorizationAndRequestCoub;
import com.example.vkMessege.vk.VKSendMessages;
import com.example.vkMessege.vk.VkLogicSendMessages;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonParser;

@RestController
@RequestMapping(value = "/")
public class Controller {


    private static final Integer APP_ID = 6911986;
    private static final String CLIENT_SECRET = "DUYrAUBVo2yYRXA9UkiI" ;
   // private static final String REDIRECT_URI = "https://oauth.vk.com/blank.html";
   // private static final String REDIRECT_URI = "http://example.com/callback";
    private static final String REDIRECT_URI = "http://api.vkontakte.ru/blank.html";

   // private static final String code = "5d757cbe18dd81a9f3";
   private static final String code = "b78da2e6868c39ff7222f84431fd3c508891978204c4d412401fe87811593ed45fbdff7739eb7b2a5ccc3";
    private static final Integer groupId = 180391236;

    @Autowired
    private UserService userService;

    @Autowired
    private CoubService coubService;

    @Autowired
    private VkLogicSendMessages vkLogicSendMessages;

    public Controller() throws IOException, InterruptedException {
          //  ttt("http://www.ip-api.com/json/");
    }


    @GetMapping(value = "sendMessages")
    public String send() throws ClientException, ApiException, IOException {

        VKSendMessages vkSendMessages=new VKSendMessages();
        vkSendMessages.send("rrrrrr",1,1,"55");


        return sendREqu("http://www.ip-api.com/json/");
    }


    @Scheduled(fixedRate = 2419200000L)
    @GetMapping(value = "/saveCoubsRepository")
    public String saveCoubsRepository() throws IOException {
        List<Coub> rrr= new CoubGet().getCoubs();
        coubService.save(rrr);
        return "saved";
    }

    @GetMapping(value = "/getCoubsRepository")
    public String getCoubsRepository() throws IOException {
        return coubService.getAllLocalJson();
    }

    @GetMapping(value = "/getUsers")
    public String getUsers(){
      //  return "f";
        return userService.getAllLocalJson();
    }


    @GetMapping(value = "/getUser/{id}")
    public String getUser(@PathVariable String id){
        return userService.findById(id);
    }

    @PostMapping(value = "/adduser")
    public String addUser(@RequestBody User user){
        userService.save(user);
        return "added";
    }


    @PostMapping(value = "/deleteUser")
    public String deliteUser(@RequestBody User user){
        userService.delete(user);
        return "deleted";
    }


    @PostMapping(value = "/addUserBlokTipeCoubs")
    public String addUserBlokTipeCoubs(@RequestBody String st){

        return "fsfd";
    }

    @PostMapping(value = "/deleteUserBlokTipeCoubs")
    public String deleteUserBlokTipeCoubs(@RequestBody String st){
        System.out.println(st);

        return "fsfd";
    }

    @PostMapping(value = "/updateUserName")
    public String updateUserName(@RequestBody String st){
        return "fsfd";
    }

    @PostMapping(value = "/setUserIcon")
    public String addSetUserIcon(@RequestBody String st){
        return "fsfd";
    }

    @PostMapping(value = "/updateUser")
    public String updateUser(@RequestBody User user){
        this.userService.save(user);
        System.out.println(user);
        return "fsfd";
    }
     ///ip


    public String sendREqu(String url) throws IOException {


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
        return response.toString();
    }

    @GetMapping(value = "autoSendingMessagesRun")
    public void autoSendingMessagesRun(){
        vkLogicSendMessages.sendVkUserCoubMessages();

    }



    public void autoSendingMessages(String url) throws IOException, InterruptedException {

        Thread thread = new Thread(()->{
            while (true) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    Object obj = new JsonParser().parse(sendREqu(url));
                    JsonObject jo = (JsonObject) obj;
                    String firstName =  jo.get("query").getAsString();
                   // System.out.println(sendREqu(url));
                    System.out.println(firstName+"    "+ new Date().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }




}
