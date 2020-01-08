package com.fastcampus.project2.mycontact.service;

import com.fastcampus.project2.mycontact.domain.Person;
import com.fastcampus.project2.mycontact.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PersonServiceTest {
    @Autowired
    private PersonService personService;
    @Autowired
    private PersonRepository personRepository;



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