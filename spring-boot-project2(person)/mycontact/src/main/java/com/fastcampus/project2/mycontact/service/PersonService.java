package com.fastcampus.project2.mycontact.service;

import com.fastcampus.project2.mycontact.domain.Block;
import com.fastcampus.project2.mycontact.domain.Person;
import com.fastcampus.project2.mycontact.repository.BlockRepository;
import com.fastcampus.project2.mycontact.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private BlockRepository blockRepository;
    public List<Person> getPeopleExcludeBlocks() {
        List<Person> people = personRepository.findAll();
      //  List<Block> blocks = blockRepository.findAll();
       // List<String> blockNames=blocks.stream().map(Block::getName).collect(Collectors.toList());
        return people.stream().filter(person -> person.getBlock()==null).collect(Collectors.toList());
    }
}
