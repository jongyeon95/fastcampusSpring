package com.fastcampus.project2.mycontact.repository;

import com.fastcampus.project2.mycontact.domain.Person;
import com.fastcampus.project2.mycontact.dto.Birthday;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    void crud(){
       Person person=new Person();
       person.setName("Jong");
       person.setAge(25);
       person.setBloodType("B");
       personRepository.save(person);
        List<Person> result=personRepository.findByName("Jong");
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getName()).isEqualTo("Jong");
        assertThat(result.get(0).getAge()).isEqualTo(25);
        assertThat(result.get(0).getBloodType()).isEqualTo("B");
    }

    @Test
    void constructorTest(){
        Person person=new Person("JongYeon",25,"B");

    }

    @Test
    void findByBloodType(){

        List<Person> result=personRepository.findByBloodType("A");
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getName()).isEqualTo("martin");
        assertThat(result.get(1).getName()).isEqualTo("minwoo");


    }

    @Test
    void findByBirthdayBetween(){

        List<Person> result=personRepository.findByMonthOfBirthday(8);
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getName()).isEqualTo("martin");
        assertThat(result.get(1).getName()).isEqualTo("minwoo");

    }

}