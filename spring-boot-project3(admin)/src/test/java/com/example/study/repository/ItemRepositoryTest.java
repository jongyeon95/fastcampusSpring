package com.example.study.repository;


import com.example.study.model.entity.Item;
import com.example.study.model.enumClass.ItemStatus;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void create() {
        Item item = new Item();
        item.setStatus(ItemStatus.REGISTERED);
        item.setName("삼성 노트북");
        item.setTitle("삼성 노트북 A100");
        item.setContent("2019년형 노트북 입니다");
        item.setPrice(BigDecimal.valueOf(90000.000));
        item.setBrandName("삼성");
        item.setRegisteredAt(LocalDateTime.now());
        item.setCreatedAt(LocalDateTime.now());
        item.setCreatedBy("Partner01");
        //item.setPartnerId(1L);
        Item newItem = itemRepository.save(item);
        System.out.println(newItem);
        Assert.assertNotNull(newItem);

    }

    @Test
    public void read() {
        Long id = 1L;
        Optional<Item> item = itemRepository.findById(id);
        Assert.assertTrue(item.isPresent());


    }

}