package com.example.study.repository;

import com.example.study.model.entity.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
public class UserRepositoryTest {
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
        System.out.println(user);
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
    @Test
    @Transactional // 실제로 동작하지 않음 예를 들어 삭제 안됨 Rollback 해줌
    public void delete(){

        Optional<User> user=userRepository.findById(1L);

        Assert.assertTrue(user.isPresent());

        user.ifPresent(selectUser ->{
            userRepository.delete(selectUser);
            System.out.println("Delete Success");
        });

        Optional<User> deleteUser=userRepository.findById(1L);

        Assert.assertFalse(deleteUser.isPresent());





    }

}