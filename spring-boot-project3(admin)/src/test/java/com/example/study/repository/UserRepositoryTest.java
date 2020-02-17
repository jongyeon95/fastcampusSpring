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
        String account="Test01";
        String password="Test01";
        String status="REGISTERED";
        String email="Test01@gmail.com";
        String phoneNumber="010-1111-1111";
        LocalDateTime registeredAt=LocalDateTime.now();
        LocalDateTime createdAt=LocalDateTime.now();
        String createdBy="AdminServer";

        User user=new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);
        user.setCreatedAt(createdAt);
        user.setCreatedBy(createdBy);

        User newUser=userRepository.save(user);
        Assert.assertNotNull(newUser);

    }
    @Test
    @Transactional
    public void read(){

        User user= userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-1111");
        Assert.assertNotNull(user);


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