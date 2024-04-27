package com.example.SpaceProject.repository;

import com.example.SpaceProject.entity.Galaxy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GalaxyRepository extends JpaRepository<Galaxy, Long> {
    Galaxy findByGalaxyName(String name);
}
