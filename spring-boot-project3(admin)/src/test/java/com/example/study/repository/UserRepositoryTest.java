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
        String account="Test04";
        String password="Test03";
        String status="REGISTERED";
        String email="Test03@gmail.com";
        String phoneNumber="010-1111-3333";
        LocalDateTime registeredAt=LocalDateTime.now();




        User user=new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);

        User u = User.builder().account(account).password(password).status(status).email(email).phoneNumber(phoneNumber).build();

        User newUser=userRepository.save(u);
        Assert.assertNotNull(newUser);

    }
    @Test
    @Transactional
    public void read(){

        User user= userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-1111");
        if(user != null) {
            user.getOrderGroupList().stream().forEach(orderGroup -> {
                System.out.println("----------------주문묶음------------------");
                System.out.println("수령인 : " + orderGroup.getRevName());
                System.out.println("수령지 : " + orderGroup.getRevAddress());
                System.out.println("총금약 : " + orderGroup.getTotalPrice());
                System.out.println("총수량 : " + orderGroup.getTotalQuantity());

                System.out.println("----------------주문상세------------------");
                orderGroup.getOrderDetailList().forEach(orderDetail -> {
                    System.out.println("파트너사 이름 :"+orderDetail.getItem().getPartner().getName());
                    System.out.println("파트너사 카테고리 : "+orderDetail.getItem().getPartner().getCategory().getTitle());
                    System.out.println("주문 상품 :"+orderDetail.getItem().getName());
                    System.out.println("고객센터 번호 :"+orderDetail.getItem().getPartner().getCallCenter());
                    System.out.println("주문의 상태 :"+orderDetail.getStatus());
                    System.out.println("도착예정일자 :"+orderDetail.getArrivalDate());
                });

            });
        }
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