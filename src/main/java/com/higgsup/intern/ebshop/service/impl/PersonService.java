package com.higgsup.intern.ebshop.service.impl;

import com.higgsup.intern.ebshop.dto.PersonDTO;
import com.higgsup.intern.ebshop.dto.PersonListDTO;
import com.higgsup.intern.ebshop.jdbc.dao.PersonDAO;
import com.higgsup.intern.ebshop.service.IPersonService;
import com.higgsup.intern.ebshop.jdbc.model.Person;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService implements IPersonService {
    private final PersonDAO personDAO;
    private final MapperFacade mapper;

    public PersonService(PersonDAO personDAO,
                         MapperFacade mapper) {
        this.personDAO = personDAO;
        this.mapper = mapper;
    }

    @Override
    public PersonDTO findById(Long id) {
        Person person = personDAO.findById(id);
        return mapper.map(person, PersonDTO.class);
    }

    @Override
    public PersonListDTO findAll() {
        List<Person> persons = personDAO.findAll();

        List<PersonDTO> personDTOs = mapper.mapAsList(persons, PersonDTO.class);

        PersonListDTO personListDTO = new PersonListDTO();
        personListDTO.setPersonDTOs(personDTOs);
        personListDTO.setResultCount(persons.size());

        return personListDTO;
    }

    @Override
    public void create(PersonDTO personDTO) {
        Person person = mapper.map(personDTO, Person.class);
        personDAO.create(person);
    }

    @Override
    public void update(PersonDTO personDTO) {
        Person person = mapper.map(personDTO, Person.class);
        personDAO.update(person);
    }

    @Override
    public void delete(Long id) {
        personDAO.delete(id);
    }
}
