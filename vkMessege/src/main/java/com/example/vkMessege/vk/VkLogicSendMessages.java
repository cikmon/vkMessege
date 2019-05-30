package com.example.vkMessege.vk;

import com.example.vkMessege.model.Coub;
import com.example.vkMessege.model.User;
import com.example.vkMessege.service.CoubService;
import com.example.vkMessege.service.UserService;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@EnableScheduling
public class VkLogicSendMessages {

    @Autowired
     private CoubService coubService;

    @Autowired
    private UserService userService;


    public List<User> getUsers(){
        List<User> usersTemp= userService.getAllLocal();
        usersTemp.removeIf(e-> { return e.isEnable();});
        return usersTemp;}

    public List<Coub> getCoubs1(){
        List<Coub> coubsTemp= coubService.getAllLocal();
        coubsTemp.removeIf(e-> {
            try {
                return createdBeforeDate(e.getCreated_at());
            } catch (ParseException e1) {
                e1.printStackTrace();
            }finally {
                return false;
            }
        });

        return coubsTemp;}



    public List<Coub> getCoubs(){
        return coubService.getAllLocal();
    }





        @Scheduled(fixedRate = 2000000)
        public void sendVkUserCoubMessages(){
            VKSendMessages vkSendMessages=new VKSendMessages();

            List<User> usersTemp=getUsers();

            List<User> usersTempSave=new ArrayList<>();

            usersTemp.forEach(u->{
                Coub coub=getRandomCoubForUser(u);
                u.sendMessagesAndCoubsID(coub.getId());
                usersTempSave.add(u);
                try {
                    vkSendMessages.send(coub.getPermalink(),u.getVkid(),u.getCountMasseges(),coub.getId());
                } catch (ClientException e) {
                    e.printStackTrace();
                } catch (ApiException e) {
                    e.printStackTrace();
                }
            });

            if(usersTempSave.size()!=0) {
                userService.save(usersTempSave);
            }
        }



        public Coub getRandomCoubForUser(User user){
            List<Coub> coubsTemp=getCoubs();

            coubsTemp.removeIf(c->{
                AtomicBoolean tempRemove= new AtomicBoolean(false);
                user.getSentCoubsID().forEach(usc->{
                    if(usc.equals(c.getPermalink())){
                        tempRemove.set(true); }
                });

                user.getBlockTypeCoubs().forEach(btc->{
                    c.getTagsAndCategories().forEach(tac->{
                        if(btc.equals(tac)){
                            tempRemove.set(true); }
                    });
                });

                return tempRemove.get();
            });


        return coubsTemp.get(new Random().nextInt(coubsTemp.size()));}




    private boolean createdBeforeDate(String date) throws ParseException {
        String format="yyyy-MM-dd'T'HH:mm:ss'Z'";

        SimpleDateFormat dateFormat = new SimpleDateFormat(format);

        Date date2= dateFormat.parse(date);

        GregorianCalendar gregorianCalendar=new GregorianCalendar();
        gregorianCalendar.setTime(date2);

        gregorianCalendar.add(GregorianCalendar.DATE, 30);

        dateFormat.setCalendar(gregorianCalendar);

        return gregorianCalendar.before(new GregorianCalendar());
    }



}
