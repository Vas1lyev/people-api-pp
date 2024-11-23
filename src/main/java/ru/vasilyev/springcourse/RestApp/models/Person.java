package ru.vasilyev.springcourse.RestApp.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name = "Person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;

    @Column(name = "age")
    @Min(value = 0, message = "Age should be greater than 0")
    private int age;

    @Column(name = "email")
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    private String email;

    @Column(name = "created_at")
//    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
//    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDateTime updatedAt;

    @Column(name = "created_who")
    @NotEmpty
    private String createdWho;

//    @Pattern(regexp = "[A-Z]\\w+, [A-Z]\\w+, \\d{6}", message = "Address should be in format: Country, City, Index (6 digits)")
//    @Column(name = "address")
//    private String address;
//
//    @OneToMany(mappedBy = "owner")
//    private List<Item> items;
//
//    @Enumerated(EnumType.STRING)
//    private Mood mood;
//
//    @Column(name = "date_of_birth")
//    @Temporal(TemporalType.DATE)
//    @DateTimeFormat(pattern = "dd/MM/yyyy")
//    private Date dateOfBirth;
//
//    @Column(name = "created_at")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date createdAt;

    public Person() {

    }

    public Person(int id, String name, int age, String email, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
//        this.address = address;
    }
//
//    public List<Item> getItems() {
//        return items;
//    }
//
//    public void setItems(List<Item> items) {
//        this.items = items;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public @NotEmpty String getCreatedWho() {
        return createdWho;
    }

    public void setCreatedWho(@NotEmpty String createdWho) {
        this.createdWho = createdWho;
    }

    //    // Country, City, Index (6 digits)
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public Mood getMood() {
//        return mood;
//    }
//
//    public void setMood(Mood mood) {
//        this.mood = mood;
//    }
//
//    public Date getDateOfBirth() {
//        return dateOfBirth;
//    }
//
//    public void setDateOfBirth(Date dateOfBirth) {
//        this.dateOfBirth = dateOfBirth;
//    }
//
//    public Date getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(Date createdAt) {
//        this.createdAt = createdAt;
//    }

//    @Override
//    public String toString() {
//        return "Person{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", age=" + age +
//                ", email='" + email + '\'' +
//                ", address='" + address + '\'' +
//                '}';
//    }
//
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Person person = (Person) o;
//        return id == person.id && age == person.age
//                && Objects.equals(name, person.name)
//                && Objects.equals(email, person.email)
//                && Objects.equals(address, person.address)
//                && Objects.equals(dateOfBirth, person.dateOfBirth);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, name, age, email, address, dateOfBirth);
//    }
}
