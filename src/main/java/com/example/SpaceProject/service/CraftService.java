package com.example.SpaceProject.service;

import com.example.SpaceProject.entity.Craft;
import com.example.SpaceProject.entity.CraftDto;
import com.example.SpaceProject.repository.CraftRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CraftService {
        private final CraftRepository craftRepository;

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
}
