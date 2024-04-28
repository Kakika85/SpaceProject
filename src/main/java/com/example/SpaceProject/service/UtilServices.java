package com.example.SpaceProject.service;

import com.example.SpaceProject.entity.Craft;
import com.example.SpaceProject.repository.AstronautRepository;
import com.example.SpaceProject.repository.CraftRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UtilServices {
    private final AstronautRepository astronautRepository;
    private final CraftRepository craftRepository;

    public List<Craft> getAllCrafts() {
        return craftRepository.findAll();
    }

    public Integer getSizeOfCraftCrew(String craftName) {
        return craftRepository.findByName(craftName).getAstronauts().size();
    }

    public Craft getByCraftName(String name) {
        return craftRepository.findByName(name);
    }

    public Object getSizeOfCraftCraw(String name) {
        return null;
    }
}
