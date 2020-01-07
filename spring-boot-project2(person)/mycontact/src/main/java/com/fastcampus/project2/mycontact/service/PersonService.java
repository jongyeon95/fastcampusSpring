package com.fastcampus.project2.mycontact.service;

import com.fastcampus.project2.mycontact.controller.dto.PersonDto;
import com.fastcampus.project2.mycontact.domain.Block;
import com.fastcampus.project2.mycontact.domain.Person;
import com.fastcampus.project2.mycontact.domain.dto.Birthday;
import com.fastcampus.project2.mycontact.repository.BlockRepository;
import com.fastcampus.project2.mycontact.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private BlockRepository blockRepository;
    public List<Person> getPeopleExcludeBlocks() {
        return personRepository.findByBlockIsNull();
    }

    @Transactional(readOnly = true)
    public Person getPerson(Long id){
//        Person person=personRepository.findById(id).get();
       Person person = personRepository.findById(id).orElse(null);
        log.info("person: {}",person);

        return person;
    }

    public List<Person> getPeopleByName(String name) {

        return personRepository.findByName(name);
    }


    @Transactional
    public void put(Person person){
        personRepository.save(person);
    }
    @Transactional
    public void modify(Long id, PersonDto personDto) {
        Person personAtDb=personRepository.findById(id).orElseThrow(()->new RuntimeException("아이디가 존재하지 않습니다"));
        personAtDb.setName(personDto.getName());
        personAtDb.setAge(personDto.getAge());
        personAtDb.setBloodType(personDto.getBloodType());
        if(personDto.getBirthday()!=null){
        personAtDb.setBirthday(new Birthday(personDto.getBirthday()));
        }
        personAtDb.setAddress(personDto.getAddress());
        personAtDb.setHobby(personDto.getHobby());
        personAtDb.setPhoneNumber(personDto.getPhoneNumber());
        personAtDb.setJob(personDto.getJob());
        personRepository.save(personAtDb);
    }
}
