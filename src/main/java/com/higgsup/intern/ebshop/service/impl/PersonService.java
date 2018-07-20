package com.higgsup.intern.ebshop.service.impl;

import com.higgsup.intern.ebshop.service.IPersonService;
import com.higgsup.intern.ebshop.model.Person;
import org.springframework.stereotype.Service;

@Service
public class PersonService implements IPersonService {
    public Person getPerson() {
        return new Person("Jon", "Snow");
    }
}
