package com.example.SpaceProject.service;

import com.example.SpaceProject.entity.Astronaut;
import com.example.SpaceProject.repository.AstronautRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AstronautService {
    private final AstronautRepository astronautRepository;



    Astronaut findAstronautByName(String firstName, String lastName){
        return astronautRepository.findByFirstNameAndLastName(firstName,lastName);
    }
}
