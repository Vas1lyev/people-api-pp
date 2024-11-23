package ru.vasilyev.springcourse.RestApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vasilyev.springcourse.RestApp.models.Person;
import ru.vasilyev.springcourse.RestApp.repositories.PeopleRepository;
import ru.vasilyev.springcourse.RestApp.util.PersonNotFoundException;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findOneById(int id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);

        return foundPerson.orElse(null);
    }

    public Person findOneByEmail(String email) {
        Optional<Person> foundPerson = peopleRepository.findByEmail(email);

        return foundPerson.orElse(null);
    }

    @Transactional
    public void save(Person person) {
        enrichPerson(person);
        peopleRepository.save(person);
    }

    public void enrichPerson(Person person) {
        person.setCreatedAt(LocalDateTime.now());
        person.setUpdatedAt(LocalDateTime.now());
        person.setCreatedWho("ADMIN");
    }

//    public Person findOne(String email) {
//        Optional<Person> foundPerson = peopleRepository.findByEmail(email);
//
//        return foundPerson.orElse(null);
//    }
//
//    @Transactional
//    public void save(Person person) {
//        person.setCreatedAt(new Date());
//        person.setMood(Mood.CALM);
//        peopleRepository.save(person);
//    }
//
//    @Transactional
//    public void update(int id, Person updatedPerson) {
//        updatedPerson.setId(id);
//        peopleRepository.save(updatedPerson);
//    }
//
//    @Transactional
//    public void delete(int id) {
//        peopleRepository.deleteById(id);
//    }
//
//    public void test() {
//        System.out.println("Testing with debug inside transaction");
//    }
}
