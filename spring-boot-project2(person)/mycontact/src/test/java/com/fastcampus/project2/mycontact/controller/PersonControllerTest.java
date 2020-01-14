package com.fastcampus.project2.mycontact.controller;
import com.fastcampus.project2.mycontact.controller.dto.PersonDto;
import com.fastcampus.project2.mycontact.domain.Person;
import com.fastcampus.project2.mycontact.domain.dto.Birthday;
import com.fastcampus.project2.mycontact.exception.handler.GlobalExceptionHandler;
import com.fastcampus.project2.mycontact.repository.PersonRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.filter;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Slf4j
@Transactional
class PersonControllerTest {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private WebApplicationContext wac;


    private MockMvc mockMvc;

    @BeforeEach//해당 메소드는 매 테스트마다 한번씩 실행
    void beforeEach(){
        mockMvc=MockMvcBuilders
                .webAppContextSetup(wac)
                .alwaysDo(print())
                .build();
    }
    @Test
    void getAll() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/person")
                    .param("page","1")
                    .param("size","2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalPages").value(3))
                .andExpect(jsonPath("$.totalElements").value(6))
                .andExpect(jsonPath("$.numberOfElements").value(2))
                .andExpect(jsonPath("$.content.[0].name").value("wonku"))
                .andExpect(jsonPath("$.content.[1].name").value("minwoo"));



    }
    @Test
    void getPerson() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/person/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("martin"))
                .andExpect(jsonPath("hobby").isEmpty())
                .andExpect(jsonPath("address").isEmpty())
                .andExpect(jsonPath("$.birthday").value("1991-08-15"))
                .andExpect(jsonPath("$.job").isEmpty())
                .andExpect(jsonPath("$.phoneNumber").isEmpty())
                .andExpect(jsonPath("$.deleted").value(false))
                .andExpect(jsonPath("$.age").isNumber())
                .andExpect(jsonPath("$.birthdayToday").isBoolean());

    }

    @Test
    void postPerson() throws Exception {
        PersonDto dto= PersonDto.of("martin","gaming","Seoul"
                ,LocalDate.now(),"job seeker","010-0000-0000");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJsonString(dto)))
                .andExpect(status().isCreated());
        Person result= personRepository.findAll(Sort.by(Sort.Direction.DESC,"id")).get(0);
        assertAll(
                ()->assertThat(result.getName()).isEqualTo("martin"),
                ()-> assertThat(result.getHobby()).isEqualTo("gaming"),
                ()-> assertThat(result.getJob()).isEqualTo("job seeker"),
                ()->assertThat(result.getBirthday()).isEqualTo(Birthday.of(LocalDate.now())),
                ()->assertThat(result.getAddress()).isEqualTo("Seoul"),
                ()-> assertThat(result.getPhoneNumber()).isEqualTo("010-0000-0000")
        );

    }

    @Test
    void modifyPerson() throws Exception{
        PersonDto dto= PersonDto.of("martin","gaming","Seoul"
                ,LocalDate.now(),"job seeker","010-0000-0000");
        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/person/1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(toJsonString(dto)))
                .andExpect(status().isOk());

        Person result= personRepository.findById(1L).get();
        log.info("HERE!!!!!!!!!!"+result.toString());
        assertAll(
                ()->assertThat(result.getName()).isEqualTo("martin"),
                ()-> assertThat(result.getHobby()).isEqualTo("gaming"),
                ()-> assertThat(result.getJob()).isEqualTo("job seeker"),
                ()->assertThat(result.getBirthday()).isEqualTo(Birthday.of(LocalDate.now())),
                ()->assertThat(result.getAddress()).isEqualTo("Seoul"),
                ()-> assertThat(result.getPhoneNumber()).isEqualTo("010-0000-0000")
        );
    }

    @Test
    void modifyPersonIfNameIsDifferent() throws Exception{
        PersonDto dto= PersonDto.of("james","gaming","seoul"
                ,LocalDate.now(),"job seeker","010-0000-0000");
                 mockMvc.perform(
                MockMvcRequestBuilders.put("/api/person/1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(toJsonString(dto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                 .andExpect(jsonPath("$.code").value(400))
                 .andExpect(jsonPath("$.message").value("이름 변경이 혀용되지 않습니다."));

    }

    @Test
    void modifyNamePerson() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.patch("/api/person/1")
                .param("name","martinModify"))
                .andExpect(status().isOk());
        assertThat(personRepository.findById(1L).get().getName()).isEqualTo("martinModify");

    }

    @Test
    void modifyPersonIfPersonNotFound()throws Exception{
        PersonDto dto= PersonDto.of("martin","gaming","seoul"
                ,LocalDate.now(),"job seeker","010-0000-0000");
        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/person/10")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(toJsonString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(400))
                .andExpect(jsonPath("$.message").value("Person Entity가 존재하지 않습니다."));

    }

    @Test
    void deletePerson() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/person/1"))
                .andExpect(status().isOk());
        assertTrue(personRepository.findPeopleDeleted().stream().anyMatch(person -> person.getId().equals(1L)));

    }

    @Test
    void postPersonIfNameIsNull() throws Exception{
        PersonDto dto=new PersonDto();
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/person")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(toJsonString(dto)))
                .andExpect(jsonPath("$.code").value(400))
                .andExpect(jsonPath("$.message").value("이름은 필수값입니다."));
    }

    @Test
    void postPersonIfNameIsEmpty() throws Exception{
        PersonDto dto=new PersonDto();
        dto.setName("");
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJsonString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(400))
                .andExpect(jsonPath("$.message").value("이름은 필수값입니다."));

    }

    @Test
    void postPersonIfNameIsBlankString() throws Exception{
        PersonDto dto=new PersonDto();
        dto.setName(" ");
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJsonString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(400))
                .andExpect(jsonPath("$.message").value("이름은 필수값입니다."));

    }



    private String toJsonString(PersonDto personDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(personDto);
    }
}

