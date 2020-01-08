package com.fastcampus.project2.mycontact.service;

import com.fastcampus.project2.mycontact.domain.Block;
import com.fastcampus.project2.mycontact.domain.Person;
import com.fastcampus.project2.mycontact.repository.BlockRepository;
import com.fastcampus.project2.mycontact.repository.PersonRepository;
import net.minidev.json.JSONUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonServiceTest {
    @Autowired
    private PersonService personService;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private BlockRepository blockRepository;

    @Test
    void getPeopleExcludeBlocks(){

        List<Person> result=personService.getPeopleExcludeBlocks();
        assertThat(result.size()).isEqualTo(3);
        assertThat(result.get(0).getName()).isEqualTo("martin");
        assertThat(result.get(1).getName()).isEqualTo("david");
        assertThat(result.get(2).getName()).isEqualTo("JongYeon");

    }

    @Test
    void getPeopleByName(){
        List<Person> result=personService.getPeopleByName("JongYeon");
       assertThat(result.size()).isEqualTo(1);
       assertThat(result.get(0).getName()).isEqualTo("JongYeon");
    }



    @Test
    void getPerson(){
        Person person=personService.getPerson(3L);
        assertThat(person.getName()).isEqualTo("wonku");
    }





}