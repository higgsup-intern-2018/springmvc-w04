package com.higgsup.intern.ebshop.jpa.repo;


import com.higgsup.intern.ebshop.jpa.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
