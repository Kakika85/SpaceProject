package com.example.SpaceProject.repository;

import com.example.SpaceProject.entity.Astronaut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AstronautRepository extends JpaRepository<Astronaut,Long> {
    Astronaut findAstronautByName(String firstname,String lastName);
}
