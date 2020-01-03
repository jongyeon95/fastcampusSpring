package com.fastcampus.project2.mycontact.repository;

import com.fastcampus.project2.mycontact.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    void crud(){
       Person person=new Person();
       person.setName("JongYeon");
       person.setAge(25);
       person.setBloodType("B");
       personRepository.save(person);
       System.out.println(personRepository.findAll());
        List<Person> people=personRepository.findAll();
        assertThat(people.size()).isEqualTo(1);
        assertThat(people.get(0).getName()).isEqualTo("JongYeon");
        assertThat(people.get(0).getAge()).isEqualTo(25);
        assertThat(people.get(0).getBloodType()).isEqualTo("B");
    }

}