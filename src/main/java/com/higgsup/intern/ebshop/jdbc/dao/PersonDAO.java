package com.higgsup.intern.ebshop.jdbc.dao;

import com.higgsup.intern.ebshop.jdbc.model.Person;

import java.util.List;

public interface PersonDAO {
    Person findById(Long id);
    List<Person> findAll();
    void create(Person person);
    void update(Person person);
    void delete(Long id);
}
