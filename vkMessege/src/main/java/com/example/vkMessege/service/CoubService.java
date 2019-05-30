package com.example.vkMessege.service;


import com.example.vkMessege.model.Coub;

import java.util.List;

public interface CoubService {
    List<Coub> getAllLocal();
    void save(List<Coub> coubs);
    String getAllLocalJson();



}
