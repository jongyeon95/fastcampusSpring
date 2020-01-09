package com.fastcampus.project2.mycontact.service;

import com.fastcampus.project2.mycontact.controller.dto.PersonDto;
import com.fastcampus.project2.mycontact.domain.Person;
import com.fastcampus.project2.mycontact.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class PersonService {
    @Autowired
    private PersonRepository personRepository;



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
        Person person=personRepository.findById(id).orElseThrow(()->new RuntimeException("아이디가 존재하지 않습니다"));
        if(!person.getName().equals(personDto.getName())){
            throw new RuntimeException("이름이 다릅니다.");
        }
        log.info("Person 왜 이상한게 넘어오는데 ?- > "+personDto.toString());
        person.set(personDto);
        personRepository.save(person);
        log.info("Person 이거냐 ?- > "+person.toString());
    }


    @Transactional
    public void modify(Long id, String name) {

        Person person=personRepository.findById(id).orElseThrow(() -> new RuntimeException("아이디가 존재하지 않습니다"));
        person.setName(name);
        personRepository.save(person);
    }

    @Transactional
    public void delete(Long id) {
        Person person=personRepository.findById(id).orElseThrow(() -> new RuntimeException("아이디가 존재하지 않습니다"));
        person.setDeleted(true);
        personRepository.save(person);
    }
}
