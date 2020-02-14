package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends StudyApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void create(){
        User user = new User();
        user.setAccount("TestUser03");
        user.setEmail("TestUser03@gamail.com");
        user.setPhoneNumber("010-3333-3");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("TestUser3");
        User newUser=userRepository.save(user);
        System.out.println(newUser);
    }
    @Test
    public void read(){
        Optional<User> user=userRepository.findById(2L);

        user.ifPresent(selectUser ->{
            System.out.println("user : "+selectUser);
            System.out.println("email :"+selectUser.getEmail());
        });

    }

    @Test
    public void update(){
        Optional<User> user=userRepository.findById(2L);
        user.ifPresent(selectUser ->{
            selectUser.setAccount("pppp");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("update method()");
            userRepository.save(selectUser);
        });

    }
    public void delete(){}
}
