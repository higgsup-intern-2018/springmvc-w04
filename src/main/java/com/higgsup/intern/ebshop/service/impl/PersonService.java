package com.higgsup.intern.ebshop.service.impl;

import com.higgsup.intern.ebshop.dto.PersonDTO;
import com.higgsup.intern.ebshop.dto.PersonListDTO;
import com.higgsup.intern.ebshop.exception.ResourceNotFoundException;
import com.higgsup.intern.ebshop.exception.ServiceException;
import com.higgsup.intern.ebshop.jpa.entity.Person;
import com.higgsup.intern.ebshop.jpa.repo.PersonRepository;
import com.higgsup.intern.ebshop.service.IPersonService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonService implements IPersonService {
    private final PersonRepository personRepository;
    private final MapperFacade mapper;

    public PersonService(PersonRepository personRepository,
                         MapperFacade mapper) {
        this.personRepository = personRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public PersonDTO findById(Long id) {
        Person person = personRepository.findOne(id);
        if (person == null) {
            throw new ResourceNotFoundException(String.format("Person with id = %d does not exist!", id));
        }
        return mapper.map(person, PersonDTO.class);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public PersonListDTO findAll() {
        Iterable<Person> persons = personRepository.findAll();

        List<PersonDTO> personDTOs = mapper.mapAsList(persons, PersonDTO.class);

        PersonListDTO personListDTO = new PersonListDTO();
        personListDTO.setPersonDTOs(personDTOs);
        personListDTO.setResultCount(personDTOs.size());

        return personListDTO;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void create(PersonDTO personDTO) {
        Person person = mapper.map(personDTO, Person.class);
        personRepository.save(person);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void update(PersonDTO personDTO) {
        Long id = personDTO.getId();
        if (personRepository.findOne(id) == null) {
            throw new ServiceException(String.format("Person with id = %d does not exist!", id));
        }
        Person person = mapper.map(personDTO, Person.class);
        personRepository.save(person);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void delete(Long id) {
        if (personRepository.findOne(id) == null) {
            throw new ServiceException(String.format("Person with id = %d does not exist!", id));
        }
        personRepository.delete(id);
    }
}
