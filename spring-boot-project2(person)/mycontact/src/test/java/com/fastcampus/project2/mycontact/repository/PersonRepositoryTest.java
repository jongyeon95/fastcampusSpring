package com.fastcampus.project2.mycontact.repository;

import com.fastcampus.project2.mycontact.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Test
    void constructorTest(){
        Person person=new Person("JongYeon",25,"B");

    }

    @Test
    void hashCodeAndEquals(){
        Person person1=new Person("JongYeon",25,"B");
        Person person2=new Person("JongYeon",25,"B");
        System.out.println(person1.equals(person2));
        System.out.println(person1.hashCode());
        System.out.println(person2.hashCode());

        Map<Person, Integer> map = new HashMap<>();
        map.put(person1,person1.getAge());
        System.out.println(map);
        System.out.println(map.get(person2));

    }

}