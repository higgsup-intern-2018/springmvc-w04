package com.higgsup.intern.ebshop.web;

import com.higgsup.intern.ebshop.model.Person;
import com.higgsup.intern.ebshop.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final IPersonService personService;

    @Autowired
    public PersonController(IPersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public Person getPerson() {
        return personService.getPerson();
    }
}