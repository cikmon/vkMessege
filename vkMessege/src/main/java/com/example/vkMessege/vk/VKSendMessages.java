package com.example.vkMessege.vk;

import com.example.vkMessege.dao.UserMongoRepository;
import com.example.vkMessege.model.User;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class VKSendMessages {

    private static final Integer APP_ID = 6911986;
    private static final String CLIENT_SECRET = "DUYrAUBVo2yYRXA9UkiI" ;
    private static final String REDIRECT_URI = "http://api.vkontakte.ru/blank.html";
    private static final String code = "b78da2e6868c39ff7222f84431fd3c508891978204c4d412401fe87811593ed45fbdff7739eb7b2a5ccc3";
    private static final String token="3815ffc7ea756d84598de00aa742beeb07ae3410e708baeee42b16cc27de612d43405cc31a060798f9fc1";
    private static final Integer groupId = 180391236;



    public void send(String messages, int vkid, int randomid, String coubid) throws ClientException, ApiException {

        TransportClient transportClient = HttpTransportClient.getInstance();
        VkApiClient vk = new VkApiClient(transportClient);
        GroupActor actor = new GroupActor(groupId, token);


        vk.messages().send(actor).message(messages).attachment("https://coub.com/view/"+coubid).userId(vkid).
                randomId(randomid+1000).execute();

    }





}
