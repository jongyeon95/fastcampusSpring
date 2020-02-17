package com.example.study.repository;

import com.example.study.model.entity.OrderDetail;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

@SpringBootTest
public class OrderDetailRepositoryTest {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void create(){
        OrderDetail orderDetail=new OrderDetail();
   //     orderDetail.setUserId(9L);
     //   orderDetail.setItemId(1L);
        OrderDetail newOderDetail=orderDetailRepository.save(orderDetail);
        Assert.assertNotNull(newOderDetail);
    }

}