package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.OrderGroup;
import com.example.study.model.enumclass.OrderPaymentType;
import com.example.study.model.enumclass.OrderStatus;
import com.example.study.model.enumclass.OrderType;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderGroupRepositoryTest extends StudyApplicationTests {

    @Autowired
    OrderGroupRepository orderGroupRepository;

    @Test
    public void create(){
        OrderGroup orderGroup=new OrderGroup();
        orderGroup.setStatus(OrderStatus.COMPLETE);
        orderGroup.setOrderType(OrderType.EACH);
        orderGroup.setRevAddress("서울시 강남구");
        orderGroup.setRevName("홍길동");
        orderGroup.setPaymentType(OrderPaymentType.CARD);
        orderGroup.setTotalPrice(BigDecimal.valueOf(900000));
        orderGroup.setOrderAt(LocalDateTime.now().minusDays(2));
        orderGroup.setArrivalDate(LocalDateTime.now());
        orderGroup.setCreatedAt(LocalDateTime.now());
        orderGroup.setCreatedBy("AdminServer");
        //orderGroup.setUserId(1L);

        OrderGroup newOderGroup=orderGroupRepository.save(orderGroup);
        Assert.assertNotNull(newOderGroup);
    }

}