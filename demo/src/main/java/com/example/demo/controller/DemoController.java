package com.example.demo.controller;

import com.example.demo.entity.DemoObject;
import com.example.demo.service.impl.DemoObjectServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class DemoController {

    private final DemoObjectServiceImpl demoObjectService;

    public DemoController(DemoObjectServiceImpl demoObjectService) {
        this.demoObjectService = demoObjectService;
    }

    @GetMapping("/getVersion")
    public DemoObject getObjectVersion(){
        return new DemoObject(10,"Sainath");
    }

    @PostMapping("/addObject")
    public ResponseEntity<DemoObject> addObject(int version, String name){
        DemoObject demoObject = demoObjectService.createObject(version,name);
        return ResponseEntity.ok(demoObject);
    }
}
