package com.example.SpaceProject.repository;

import com.example.SpaceProject.entity.Astronaut;
import com.example.SpaceProject.entity.Craft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CraftRepository extends JpaRepository<Craft, Long> {
    Craft findByName(String name);

    @Query("SELECT c from Craft c join c.astronauts a where  a.firstName = :firstName And a.lastName = :lastName")
    Craft findCraftByAstronautsNameAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);
}
