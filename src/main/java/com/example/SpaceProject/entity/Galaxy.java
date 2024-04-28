package com.example.SpaceProject.entity;

import com.example.SpaceProject.helperClass.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
@AllArgsConstructor

public class Galaxy extends BaseEntity {
// Ukázka dědičnosti
}
