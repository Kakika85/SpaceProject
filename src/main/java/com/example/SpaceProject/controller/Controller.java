package com.example.SpaceProject.controller;

import com.example.SpaceProject.entity.Craft;
import com.example.SpaceProject.entity.record.CraftRecord;
import com.example.SpaceProject.service.CraftService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@org.springframework.stereotype.Controller
@RestController
@RequiredArgsConstructor
public class Controller {
    private final CraftService craftService;

    @GetMapping("/stranka")
    public String test() {
        return "Moja prva stranka";
    }

    @GetMapping("/asd")
    public String myFirstEndPoint() {
        return "Můj první End Point";
    }

    @GetMapping("/createCraft/{craftName}")
    public String createCraft(@PathVariable String craftName) {
        Craft craft = craftService.saveCraftToRepository(Craft.builder().name(craftName).build());
        return "craft: " + craft + " has been created";
        //todo na vyskusanie pustite :  http://localhost:8081/createCraft/moja%20prva%20lod
    }

}
