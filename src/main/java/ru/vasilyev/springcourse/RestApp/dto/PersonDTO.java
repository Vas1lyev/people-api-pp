package ru.vasilyev.springcourse.RestApp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class PersonDTO {
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;

    @Min(value = 0, message = "Age should be greater than 0")
    private int age;

    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    private String email;

    public @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    String getName() {
        return name;
    }

    public void setName(@NotEmpty(message = "Name should not be empty")
                        @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
                        String name) {
        this.name = name;
    }

    @Min(value = 0, message = "Age should be greater than 0")
    public int getAge() {
        return age;
    }

    public void setAge(@Min(value = 0, message = "Age should be greater than 0") int age) {
        this.age = age;
    }

    public @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    String getEmail() {
        return email;
    }

    public void setEmail(@NotEmpty(message = "Email should not be empty")
                         @Email(message = "Email should be valid") String email) {
        this.email = email;
    }
}
