package com.group.libraryapp.temp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personservice){
        this.personService = personservice;
    }

    @GetMapping("/test/person")
    public void testPerson(){
        personService.savePerson();
    }
}
