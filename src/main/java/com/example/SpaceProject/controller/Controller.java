package com.example.SpaceProject.controller;

import com.example.SpaceProject.entity.Astronaut;
import com.example.SpaceProject.service.AstronautService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@Slf4j

public class Controller {

    private final AstronautService astronautService;
    //private final Ultiservice utilService;
    //private final AstronautRepoository astronautRepoository;
   // private final MissionService missionService;
   // private final CraftRepository craftRepository;
   // private final CraftMapper craftMapper;

    @GetMapping("/stranka/{name}/{lastName}")
    public Astronaut test(@PathVariable String name,
                          @PathVariable String lastName) {
        Astronaut astronaut = Astronaut.builder().firstName(name).lastName(lastName).build();

        return astronaut;
    }
}
