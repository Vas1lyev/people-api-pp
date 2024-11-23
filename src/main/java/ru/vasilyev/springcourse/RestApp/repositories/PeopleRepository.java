package ru.vasilyev.springcourse.RestApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vasilyev.springcourse.RestApp.models.Person;

import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findByEmail(String email);
//
//    List<Person> findByName(String name);
//
//    List<Person> findByNameOrderByAge(String name);
//
//    List<Person> findByNameStartingWith(String startingWith);
//
//    List<Person> findByNameOrEmail(String name, String email);
}
