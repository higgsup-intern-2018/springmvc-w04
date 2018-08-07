package com.higgsup.intern.ebshop.jpa.repo;

import com.higgsup.intern.ebshop.dto.CustomerDTO;
import com.higgsup.intern.ebshop.jpa.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Customer findByEmail(String email);

    @Query("select c from OrderDetails od join od.ebook e join od.orders o join o.customer c " +
            "group by c.email order by sum(e.price * od.quantity) desc")
    List<Customer> getTop5HighestPrice(Pageable pageable);

    @Query("select c from OrderDetails od join od.ebook e join od.orders o join o.customer c " +
            "group by c.email order by sum(od.quantity) desc")
    List<Customer> getTop5MostBuying(Pageable pageable);

    @Query("select sum(od.quantity) from OrderDetails od join od.orders o join o.customer c where c.id = :id")
    Integer quantity(@Param("id") Long id);

    @Query("select sum(e.price * od.quantity) from OrderDetails od join od.ebook e join od.orders o join o.customer c where c.id = :id")
    Double totalPrice(@Param("id") Long id);
}
