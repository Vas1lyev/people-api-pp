package ru.vasilyev.springcourse.RestApp.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.vasilyev.springcourse.RestApp.dto.PersonDTO;
import ru.vasilyev.springcourse.RestApp.models.Person;
import ru.vasilyev.springcourse.RestApp.services.PeopleService;

@Component
public class PersonValidator implements Validator {

    private final PeopleService peopleService;

    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        PersonDTO personDTO = (PersonDTO) o;
        // валидировать поля и проверять есть ли такие же в бд

        if (peopleService.findOneByEmail(personDTO.getEmail()) != null) {
            errors.rejectValue("email", "", "Email is already in use");
        }
    }
}
