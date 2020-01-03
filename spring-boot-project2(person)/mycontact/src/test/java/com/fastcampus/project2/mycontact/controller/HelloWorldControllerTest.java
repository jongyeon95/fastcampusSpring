package com.fastcampus.project2.mycontact.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest//해당 클래스가 스프링 부트 태스트를 표시하는 애노테이션
class HelloWorldControllerTest {
    @Autowired // bean 주입
    private HelloWorldController helloWorldController;

    private MockMvc mockMvc;
    @Test
    void helloWorld(){
//        System.out.println("Test");
        System.out.println(helloWorldController.helloWorld());
        assertThat(helloWorldController.helloWorld()).isEqualTo("Hello World");
    }

    @Test
    void mockMvcTest() throws Exception {
        mockMvc= MockMvcBuilders.standaloneSetup(helloWorldController).build();
        //perform 실제 동작하도록 요청
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/helloWorld")
        ).andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().string("Hello World"));//Response의 body내용이 helloWorld인지 확인 하는 로직
    }
}