package com.fastcampus.project2.mycontact.repository;

import com.fastcampus.project2.mycontact.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByName(String name);

    @Query(value = "select person from Person person where person.birthday.monthOfBirthday=?1")
    List<Person> findByMonthOfBirthday(int monthOfBirthday);

    @Query(value = "select * from Person person where person.deleted=true",nativeQuery = true)
    List<Person> findPeopleDeleted();

}
