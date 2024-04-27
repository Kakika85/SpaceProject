package com.example.SpaceProject.controller;

import com.example.SpaceProject.entity.Astronaut;
import com.example.SpaceProject.entity.Craft;
import com.example.SpaceProject.repository.CraftRepository;
import com.example.SpaceProject.service.AstronautService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Controller
@RestController
@RequiredArgsConstructor
public class Controller {

    private final AstronautService astronautService;

    //private final Ultiservice utilService;
    //private final AstronautRepoository astronautRepoository;
    // private final MissionService missionService;
     private final CraftRepository craftRepository;
    // private final CraftMapper craftMapper;

    @GetMapping("/stranka/{name}")
    public Craft test(@PathVariable String name) {
        return Craft.builder().name(name).build();
    }

    @GetMapping("/saveAstronauts")
    public String saveAstronauts() throws JsonProcessingException {
        String apiUrl = "http://api.open-notify.org/astros.json";

        RestTemplate restTemplate = new RestTemplate();
        String jsonResponse = restTemplate.getForObject(apiUrl, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonResponse);
        JsonNode peopleNode = jsonNode.get("people");
        List<Astronaut> astronautList = new ArrayList<>();

        for (JsonNode astronautNode : peopleNode) {
            String name = astronautNode.get("name").asText();

            String[] nameParts = name.split(" ");
            String firstName = nameParts[0];
            String lastName = nameParts[1];

            Astronaut astronaut = Astronaut.builder()
                    .firstName(firstName)
                    .lastName(lastName)
                    .build();
            astronautList.add(astronaut);

//            craftRepository.save(existingCraft); todo finish when repository will be created
        }

        return astronautService.saAllAstronauts(astronautList).toString();
    }
}
