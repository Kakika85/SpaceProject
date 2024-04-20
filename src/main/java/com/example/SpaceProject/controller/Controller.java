package com.example.SpaceProject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@org.springframework.stereotype.Controller
@RestController
public class Controller {
    @GetMapping("/stranka")
    public String test(){
        return "Moja prva stranka";
    }

@GetMapping("/asd")
    public String myFirstEndPoint() {
        return "Můj první End Point";
}

}
