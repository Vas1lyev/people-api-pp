package ru.vasilyev.springcourse.RestApp.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import ru.vasilyev.springcourse.RestApp.dto.PersonDTO;
import ru.vasilyev.springcourse.RestApp.models.Person;
import ru.vasilyev.springcourse.RestApp.services.PeopleService;
import ru.vasilyev.springcourse.RestApp.util.PersonErrorResponse;
import ru.vasilyev.springcourse.RestApp.util.PersonNotCreatedException;
import ru.vasilyev.springcourse.RestApp.util.PersonNotFoundException;
import ru.vasilyev.springcourse.RestApp.util.PersonValidator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/people")
public class PeopleController {
    private final PeopleService peopleService;
    private final PersonValidator personValidator;
    private final ModelMapper modelMapper;


    @Autowired
    public PeopleController(PeopleService peopleService,
                            PersonValidator personValidator,
                            ModelMapper modelMapper) {
        this.peopleService = peopleService;
        this.personValidator = personValidator;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public List<PersonDTO> getPeople() {
        return peopleService.findAll()
                .stream()
                .map(this::convertToPersonDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PersonDTO getPerson(@PathVariable int id) {
        Person p = peopleService.findOneById(id);
        if (p == null) {
            throw new PersonNotFoundException();
        }
        return convertToPersonDTO(p);
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotFoundException e) {
        PersonErrorResponse response = new PersonErrorResponse(
                "Person with this id wasn't found!",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid PersonDTO personDTO,
                                             BindingResult bindingResult) {
        personValidator.validate(personDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(": ")
                        .append(error.getDefaultMessage())
                        .append("; ");
            }

            throw new PersonNotCreatedException(errorMsg.toString());

        }

        peopleService.save(convertToPerson(personDTO));
        return ResponseEntity.ok(HttpStatus.CREATED);

    }


    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotCreatedException e) {
        PersonErrorResponse response = new PersonErrorResponse(e.getMessage(),
                System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Person convertToPerson(PersonDTO personDTO) {
        return modelMapper.map(personDTO, Person.class);
    }

    private PersonDTO convertToPersonDTO(Person person) {
        return modelMapper.map(person, PersonDTO.class);
    }
}
