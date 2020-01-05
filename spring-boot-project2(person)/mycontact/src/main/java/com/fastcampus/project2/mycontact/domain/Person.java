package com.fastcampus.project2.mycontact.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@RequiredArgsConstructor
public class Person {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private int age;

    private String hobby;

    @NonNull
    private String bloodType;

    private String address;

    private LocalDate birthday;

    private String job;

    @ToString.Exclude
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @ToString.Exclude
    private Block block;




}


