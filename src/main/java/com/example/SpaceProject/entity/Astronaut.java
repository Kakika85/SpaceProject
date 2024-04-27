package com.example.SpaceProject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jdk.jfr.DataAmount;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Astronaut {

    @Id
    @Generated
    private Long id;
    private String firstName;
    private String lastName;
}
