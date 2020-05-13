package com.fastcampus.project2.mycontact.service;

import com.fastcampus.project2.mycontact.controller.dto.PersonDto;
import com.fastcampus.project2.mycontact.domain.Person;
import com.fastcampus.project2.mycontact.exception.PersonNotFoundException;
import com.fastcampus.project2.mycontact.exception.RenameNotPermittedException;
import com.fastcampus.project2.mycontact.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
       return personRepository.findById(id).orElse(null);
    }

    public List<Person> getPeopleByName(String name) {

        return personRepository.findByName(name);
    }


    @Transactional
    public void put(PersonDto personDto){
        Person person=new Person();
        person.set(personDto);
        person.setName(personDto.getName());
        personRepository.save(person);
    }


    @Transactional
    public void modify(Long id, PersonDto personDto) {
        Person person=personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        if(!person.getName().equals(personDto.getName())){
            throw new RenameNotPermittedException();
        }
        person.set(personDto);
        personRepository.save(person);
    }


    @Transactional//처리한 쿼리를 자동 rollback 해주기 위해 사용된다.
    public void modify(Long id, String name) {

        Person person=personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        person.setName(name);
        personRepository.save(person);
    }

    @Transactional
    public void delete(Long id) {
        Person person=personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        person.setDeleted(true);
        personRepository.save(person);
    }

    public Page<Person> getAll(Pageable pageable) {
       return personRepository.findAll(pageable);
    }
}
