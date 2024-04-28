package com.example.SpaceProject.service;

import com.example.SpaceProject.entity.Astronaut;
import com.example.SpaceProject.entity.Craft;
import com.example.SpaceProject.entity.CraftDto;
import com.example.SpaceProject.repository.AstronautRepository;
import com.example.SpaceProject.repository.CraftRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.internal.AbstractScrollableResults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CraftService {
    private final CraftRepository craftRepository;
    private final AstronautRepository astronautRepository;

    public CraftDto mapCraftToCraftDTO(Craft craft) {
        return new CraftDto(craft.getName(), craft.getAstronauts().size());
    }

    public Craft saveCraft(Craft craft) {
        Craft craft1 = craftRepository.findByName(craft.getName());

        if (craft1 == null) {
            return craftRepository.save(craft);
        } else {
            log.error("Craft is exists.");
            return craft1;
        }
    }
    public Astronaut moveAstronaut(Long astronautId, Long targetCraftId) {
        Astronaut astronaut = astronautRepository.findById(astronautId).orElse(null);
        Craft targetCraft = craftRepository.findById(targetCraftId).orElse(null);

        if (astronaut == null || targetCraft == null) {
            throw new IllegalArgumentException("Astronaut or target craft not found");
        }

        if (astronaut.getCraft() != null) {
            astronaut.getCraft().getAstronauts().remove(astronaut);
        }

        astronaut.setCraft(targetCraft);
        targetCraft.getAstronauts().add(astronaut);

       Astronaut savedAstronaut =  astronautRepository.save(astronaut);
        craftRepository.save(targetCraft);
        return savedAstronaut;
    }

    public Craft findCraftByName(String name) {
        return craftRepository.findByName(name);
    }
}
