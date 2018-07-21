package com.higgsup.intern.ebshop.service;

import com.higgsup.intern.ebshop.dto.PersonDTO;
import com.higgsup.intern.ebshop.dto.PersonListDTO;
import com.higgsup.intern.ebshop.jdbc.model.Person;

import java.util.List;

public interface IPersonService {
    PersonDTO findById(Long id);
    PersonListDTO findAll();
    void create(PersonDTO personDTO);
    void update(PersonDTO personDTO);
    void delete(Long id);
}
