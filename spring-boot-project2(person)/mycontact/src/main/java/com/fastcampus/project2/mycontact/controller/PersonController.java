package com.fastcampus.project2.mycontact.controller;

import com.fastcampus.project2.mycontact.controller.dto.PersonDto;
import com.fastcampus.project2.mycontact.domain.Person;
import com.fastcampus.project2.mycontact.exception.PersonNotFoundException;
import com.fastcampus.project2.mycontact.exception.RenameNotPermittedException;
import com.fastcampus.project2.mycontact.exception.dto.ErrorResponse;
import com.fastcampus.project2.mycontact.repository.PersonRepository;
import com.fastcampus.project2.mycontact.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(value = "/api/person")
@RestController
@Slf4j
public class PersonController {

    @Autowired
    private PersonService personService;
    @Autowired
    private PersonRepository personRepository;

    @GetMapping
    public Page<Person> getAll(@PageableDefault Pageable pageable){
        return personService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public Person getPerson(@PathVariable Long id){
        return personService.getPerson(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postPerson(@RequestBody @Valid PersonDto personDto){

        personService.put(personDto);
    }



    @PutMapping("/{id}")
    public void modifyPerson(@PathVariable Long id, @RequestBody PersonDto personDto){

            personService.modify(id,personDto);
    }

    @PatchMapping("/{id}")//일부만 업데이트 한다.
    public void modifyPerson(@PathVariable Long id, String name){
            personService.modify(id,name);
    }
    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id){
        personService.delete(id);

    }


}
