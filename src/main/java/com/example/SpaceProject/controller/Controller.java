package com.example.SpaceProject.controller;

import com.example.SpaceProject.entity.AssignAstronautToCraft;
import com.example.SpaceProject.entity.Astronaut;
import com.example.SpaceProject.entity.Craft;
import com.example.SpaceProject.repository.AstronautRepository;
import com.example.SpaceProject.repository.CraftRepository;
import com.example.SpaceProject.service.AstronautService;
import com.example.SpaceProject.service.CraftService;
import com.example.SpaceProject.service.UtilServices;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@RestController
@RequiredArgsConstructor
public class Controller {

    private static final Logger log = LoggerFactory.getLogger(Controller.class);
    private final AstronautService astronautService;
    private final UtilServices utilService;
    private final CraftService craftService;

    // private final MissionService missionService;

    @GetMapping("/stranka/{name}")
    public Craft test(@PathVariable String name) {
        return Craft.builder().name(name).build();
    }

    /* @GetMapping("/getCraftByAstronaut/{name}/{lastName}")
     public String getCraftByAstronaut(@PathVariable String name,
                                       @PathVariable String lastName) {
         Craft c = craftRepository.findCraftByAstronautsNameAndLastName(name, lastName);
         craftRepository.findByName(c.getName());
         craftRepository.findByName(utilService.getSizeOfCraftCraw(c.getName()).toString());

         return craftRepository.findCraftByAstronautsNameAndLastName(c).toString();
     }
 */
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
            String craftName = astronautNode.get("craft").asText();

            Craft craft = craftService.saveCraft(Craft.builder().name(craftName).build());

            String[] nameParts = name.split(" ");
            String firstName = nameParts[0];
            String lastName = nameParts[1];

            Astronaut astronaut = Astronaut.builder()
                    .firstName(firstName)
                    .lastName(lastName)
                    .craft(craft)
                    .build();

            astronautList.add(astronaut);

//            craftRepository.save(existingCraft); todo finish when repository will be created
        }

        return astronautService.saAllAstronauts(astronautList).toString();
    }

    Astronaut astronaut = new Astronaut();

    @GetMapping("/saveAstronauts1")
    public ModelAndView card() {
        ModelAndView modelAndView = new ModelAndView("card");
        modelAndView.addObject("astronaut", astronaut);
        return modelAndView;
    }

    @GetMapping("/deleteAstronaut")
    public Astronaut delete(@RequestParam String name,
                            @RequestParam String lastName) {
        Astronaut astronaut = Astronaut.builder().firstName(name).lastName(lastName).build();
        astronautService.deleteAstronautByName(name, lastName);
        //todo localhost:8081/deleteAstronaut?name=Jozko&lastName=Mrkvicka

        return astronaut;
    }

    @PostMapping("/assignAstronautToCraft")
    public Astronaut assignAstronautToCraft(@RequestBody AssignAstronautToCraft body) {
        Astronaut astronaut = astronautService.findAstronautByName(body.getFirstName(), body.getLastName());
        Craft craft = craftService.findCraftByName(body.getCraftName());
        if (craft.getAstronauts().contains(astronaut)) {
            throw new RuntimeException("This astronaut is already assigned to this craft.");
        }
        return craftService.moveAstronaut(astronaut.getId(), craft.getId());

    }
}
