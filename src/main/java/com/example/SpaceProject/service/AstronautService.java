package com.example.SpaceProject.service;

import com.example.SpaceProject.entity.Astronaut;
import com.example.SpaceProject.repository.AstronautRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AstronautService {
    private final AstronautRepository astronautRepository;

    public Astronaut findAstronautByName(String firstName, String lastName) {
        return astronautRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    public List<Astronaut> saAllAstronauts(List<Astronaut> astronautList) {
        return astronautRepository.saveAll(astronautList);
    }

    public void deleteAstronautByName(String firstName, String lastName) {
        if (findAstronautByName(firstName, lastName) != null) {
            astronautRepository.delete(findAstronautByName(firstName, lastName));
        } else {
            System.err.println("not found in database");
        }
    }
    public Astronaut saveAstronaut(Astronaut astronaut) {
        return astronautRepository.save(astronaut);
    }
}
