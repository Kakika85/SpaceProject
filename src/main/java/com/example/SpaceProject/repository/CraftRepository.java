package com.example.SpaceProject.repository;

import com.example.SpaceProject.entity.Craft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CraftRepository extends JpaRepository<Craft,Long> {
}
