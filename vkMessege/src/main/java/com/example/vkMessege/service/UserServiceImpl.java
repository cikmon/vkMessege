package com.example.vkMessege.service;

import com.example.vkMessege.dao.UserMongoRepository;
import com.example.vkMessege.model.User;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Repository
@Transactional
@Service
public class UserServiceImpl implements UserDetailsService, UserService{
    @Autowired
    UserMongoRepository userMongoRepository;
    private volatile boolean running = true;
    private List<User> users;


    @PostConstruct
    private void getUsersRepository(){
        users= new ArrayList<>(getAllRepository());
        autoClearLocal(24);
    }

    public void save(List<User> users){
        users.forEach(e-> {
            save(e);
        });
    }

    public void save(User user){
        this.users.forEach(e-> {
            if(e.getId()==user.getId()) {
                users.set(users.indexOf(e), user);
                saveRepository(user);
                return;
            }
        } );

        this.users.add(user);
        saveRepository(user);
    }

    public void delete(User user){
        this.users.remove(user);
        deleteRepository(user);
    }


    public List<User> getAllLocal(){
//        if(users!=null&& users.size()!=0){
//            return users;}
        users=new ArrayList<>(getAllRepository());
        return users; }

    public String getAllLocalJson(){
        return new Gson().toJson(getAllLocal());
    }


    public String findById(String id){ return findByIdTransaction(id).toJson();}

    @Transactional(readOnly = true)
    public User findByIdTransaction(String id){
        return userMongoRepository.findById(id).get(); }

    @Transactional(readOnly = true)
    public List<User> getAllRepository(){
        return userMongoRepository.findAll(); }

    @Transactional
    public void saveRepository(List<User> users){userMongoRepository.saveAll(users); }

    @Transactional
    public void saveRepository(User users){userMongoRepository.save(users); }

    @Transactional
    public void deleteRepository(User users){userMongoRepository.delete(users); }

    public void clearLocal(){
        users=null;
    }

    public void setRunningFalse(){running=false;}

    public void startAutoClearLocal(){
        autoClearLocal(24);
        running=true;}

    private void autoClearLocal(int hours){
        Thread thread= new Thread(()->{
            try {
                Thread.sleep(7200000);
                while (running) {
                    clearLocal();
                    Thread.sleep((hours -2)* 3600000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userMongoRepository.findByName(userId).get(0);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), getAuthority());
    }

    private List<SimpleGrantedAuthority> getAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

}
