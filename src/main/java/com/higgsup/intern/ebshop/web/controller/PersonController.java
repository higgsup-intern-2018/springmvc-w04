package com.higgsup.intern.ebshop.web.controller;

import com.higgsup.intern.ebshop.dto.GenericResponseDTO;
import com.higgsup.intern.ebshop.dto.PersonDTO;
import com.higgsup.intern.ebshop.dto.PersonListDTO;
import com.higgsup.intern.ebshop.jdbc.model.Person;
import com.higgsup.intern.ebshop.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final IPersonService personService;

    public PersonController(IPersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> getPersonById(@PathVariable("id") Long id) {
        PersonDTO personDTO = personService.findById(id);
        return ResponseEntity.ok(personDTO);
    }

    @GetMapping
    public ResponseEntity<PersonListDTO> getAllPersons() {
        PersonListDTO allPersons = personService.findAll();
        return ResponseEntity.ok(allPersons);
    }

    @PostMapping
    public ResponseEntity<GenericResponseDTO> createPerson(@RequestBody PersonDTO personDTO) {
        personService.create(personDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(GenericResponseDTO.created());
    }

    @PutMapping
    public ResponseEntity<GenericResponseDTO> updatePerson(@RequestBody PersonDTO personDTO) {
        personService.update(personDTO);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponseDTO.updated());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponseDTO> deletePersonById(@PathVariable("id") Long id) {
        personService.delete(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponseDTO.deleted());
    }
}