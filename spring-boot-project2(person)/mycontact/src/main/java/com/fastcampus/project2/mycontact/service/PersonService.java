package com.fastcampus.project2.mycontact.service;

import com.fastcampus.project2.mycontact.domain.Block;
import com.fastcampus.project2.mycontact.domain.Person;
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
}
