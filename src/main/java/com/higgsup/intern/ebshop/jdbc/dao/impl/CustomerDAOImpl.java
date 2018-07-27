package com.higgsup.intern.ebshop.jdbc.dao.impl;

import com.higgsup.intern.ebshop.dto.CustomerDTO;
import com.higgsup.intern.ebshop.jdbc.dao.CustomerDAO;
import com.higgsup.intern.ebshop.jdbc.mapper.CustomerDTOMapper;
import com.higgsup.intern.ebshop.jdbc.mapper.CustomerMapper;
import com.higgsup.intern.ebshop.jdbc.model.Customer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final CustomerDTOMapper customerDTOMapper;

    public CustomerDAOImpl(JdbcTemplate jdbcTemplate,
                           NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                           CustomerDTOMapper customerDTOMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.customerDTOMapper = customerDTOMapper;
    }

    @Override
    public List<Customer> findTop5BestBuyCustomers() {
        return null;
    }

    @Override
    public List<CustomerDTO> findTop5HighestOrderPriceCustomers() {    //top 5 KH co tong gia tri hoa don cao nhat
        String sql = "select customer.*, " +
                        "sum(ebook.price * order_details.quantity) TotalPrice, " +
                        "sum(order_details.quantity) countOfBooks " +
                    "from customer " +
                    "join orders on customer.id = orders.customer_id " +
                    "join order_details on orders.id = order_details.order_id " +
                    "join ebook on order_details.ebook_id = ebook.id " +
                    "group by customer.email  " +
                    "order by TotalPrice DESC " +
                    "limit 5;";
        return namedParameterJdbcTemplate.query(sql,customerDTOMapper);
    }

    @Override
    public Integer countBook(Long id) {
        return null;
    }
}
