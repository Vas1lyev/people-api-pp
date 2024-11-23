package ru.vasilyev.springcourse.RestApp.util;

public class PersonNotCreatedException extends RuntimeException {
    public PersonNotCreatedException(String message) {
        super(message);
    }
}
