package com.example.spring;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "students")
public class Student {

    @Id
    private String id;

    private String firstName;
    private String lastName;

    @Indexed(unique = true)
    private String email;
    private Gender gender;
    private Address address;
    private List<String> subjects;
    private BigDecimal totalSpent;
    private LocalDateTime createdAt;

    public Student(String firstName, String lastName, String email, Gender gender, Address address,
                   List<String> subjects, BigDecimal totalSpent, LocalDateTime createdAt) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.address = address;
        this.subjects = subjects;
        this.totalSpent = totalSpent;
        this.createdAt = createdAt;
    }

}
