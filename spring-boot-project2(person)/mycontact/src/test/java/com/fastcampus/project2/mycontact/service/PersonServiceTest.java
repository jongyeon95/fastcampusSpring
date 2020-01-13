package com.fastcampus.project2.mycontact.service;

import com.fastcampus.project2.mycontact.controller.dto.PersonDto;
import com.fastcampus.project2.mycontact.domain.Person;
import com.fastcampus.project2.mycontact.domain.dto.Birthday;
import com.fastcampus.project2.mycontact.exception.PersonNotFoundException;
import com.fastcampus.project2.mycontact.exception.RenameNotPermittedException;
import com.fastcampus.project2.mycontact.repository.PersonRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class PersonServiceTest {
    @InjectMocks
    private PersonService personService;
    @Mock
    private PersonRepository personRepository;

    private PersonDto mockPersonDto() {
     return   PersonDto.of("martin", "gaming", "Seoul"
                , LocalDate.now(), "job seeker", "010-0000-0000");
    }

    @Test
    void getPeopleByName(){
       when(personRepository.findByName("martin"))
               .thenReturn(Lists.newArrayList(new Person("martin")));
       List<Person> result=personService.getPeopleByName("martin");
       assertThat(result.size()).isEqualTo(1);
       assertThat(result.get(0).getName()).isEqualTo("martin");
    }



    @Test
    void getPerson(){
        when(personRepository.findById(1L))
                .thenReturn(Optional.of(new Person("martin")));
        Person person=personService.getPerson(1L);
        assertThat(person.getName()).isEqualTo("martin");
    }

    @Test
    void getPersonIfNotFound(){
        when(personRepository.findById(1L))
                .thenReturn(Optional.empty());
        Person person=personService.getPerson(1L);
        assertThat(person).isNull();
    }

    @Test
    void put(){
        personService.put(mockPersonDto());
        verify(personRepository,times(1)).save(argThat(new IsPersonWillBeInserted()));
    }

    @Test
    void modifyIfPersonNotFound(){
        when(personRepository.findById(1L))
                .thenReturn(Optional.empty());
       assertThrows(PersonNotFoundException.class,()-> personService.modify(1L,mockPersonDto()));
    }
    @Test
    void modifyIfNameIsDifferent(){
        when(personRepository.findById(1L))
                .thenReturn(Optional.of(new Person("tony")));
        assertThrows(RenameNotPermittedException.class,()->personService.modify(1L,mockPersonDto()));

    }
    @Test
    void modify(){
        when(personRepository.findById(1L))
                .thenReturn(Optional.of(new Person("martin")));
        personService.modify(1L,mockPersonDto());
        verify(personRepository,times(1)).save(any(Person.class));
        verify(personRepository,times(1)).save(argThat(new IsPersonWillBeUpdated()));
    }
    @Test
    void modifyByNameIfPersonNotFound(){
        when(personRepository.findById(1L))
                .thenReturn(Optional.empty());
        assertThrows(PersonNotFoundException.class,()->personService.modify(1L,"daniel"));
    }
    @Test
    void modifyByName(){
        when(personRepository.findById(1L))
                .thenReturn(Optional.of(new Person("martin")));
        personService.modify(1L,"daniel");
        verify(personRepository,times(1)).save(argThat(new IsNameWillBeUpdate()));
    }

    @Test
    void deleteIfPersonNotFound(){
        when(personRepository.findById(1L))
                .thenReturn(Optional.empty());
        assertThrows(PersonNotFoundException.class,()->personService.delete(1L));
    }

    @Test
    void delete(){
        when(personRepository.findById(1L))
                .thenReturn(Optional.of(new Person("martin")));
        personService.delete(1L);
        verify(personRepository,times(1)).save(argThat(new IsPersonWillBeDeleted()));
    }

    private static class IsPersonWillBeInserted implements ArgumentMatcher<Person>{

        @Override
        public boolean matches(Person person) {
            return equals(person.getName(),"martin")
                    && equals(person.getHobby(),"gaming")
                    && equals(person.getAddress(),"Seoul")
                    && equals(person.getBirthday(),Birthday.of(LocalDate.now()))
                    && equals(person.getJob(),"job seeker")
                    && equals(person.getPhoneNumber(),"010-0000-0000");
       }
        private  boolean equals(Object actual,Object expected){
            return expected.equals(actual);
        }
    }

    private static class IsPersonWillBeUpdated implements ArgumentMatcher<Person>{

        @Override
        public boolean matches(Person person) {
            return equals(person.getName(),"martin")
                    && equals(person.getHobby(),"gaming")
                    && equals(person.getAddress(),"Seoul")
                    && equals(person.getBirthday(),Birthday.of(LocalDate.now()))
                    && equals(person.getJob(),"job seeker")
                    && equals(person.getPhoneNumber(),"010-0000-0000");
        }
        private  boolean equals(Object actual,Object expected){
            return expected.equals(actual);
        }
    }

    private static class IsNameWillBeUpdate implements ArgumentMatcher<Person>{

        @Override
        public boolean matches(Person person) {
            return person.getName().equals("daniel");
        }
    }

    private static class IsPersonWillBeDeleted implements ArgumentMatcher<Person>{

        @Override
        public boolean matches(Person person) {
            return person.isDeleted();
        }
    }


}