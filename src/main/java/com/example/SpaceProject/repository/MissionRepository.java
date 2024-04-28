package com.example.SpaceProject.repository;

import com.example.SpaceProject.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {
    Mission findByName(String name);
}
