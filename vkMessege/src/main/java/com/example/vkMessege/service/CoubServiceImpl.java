package com.example.vkMessege.service;


import com.example.vkMessege.dao.CoubMongoRepository;
import com.example.vkMessege.model.Coub;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.collections4.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
@Transactional
@Service
public class CoubServiceImpl implements CoubService{

    private volatile boolean running = true;

    @Autowired
    private CoubMongoRepository coubMongoRepository;

    private List<Coub> coubs;

    @PostConstruct
    private void getCoubRepository(){ coubs=getAllRepository();
    autoClearLocal(24); }


    public void save(List<Coub> coubs){
        this.coubs=coubs;
        saveRepository(coubs);}

    public void clearLocal(){coubs=null; }


    public List<Coub> getAllLocal(){
        if(coubs!=null){ return coubs;}
        coubs=getAllRepository();
        return coubs; }

    public String getAllLocalJson(){ return new Gson().toJson(getAllLocal()); }

    @Transactional(readOnly = true)
    public List<Coub> getAllRepository(){ return coubMongoRepository.findAll(); }

    @Transactional
    public void saveRepository(List<Coub> coubs){coubMongoRepository.saveAll(coubs); }


    public void setRunningFalse(){running=false;}

    private void autoClearLocal(int hours){
        Thread thread= new Thread(()->{
            try {
                Thread.sleep(3600000);
                while (running) {
                    clearLocal();
                    Thread.sleep((hours -1)* 3600000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }


}
