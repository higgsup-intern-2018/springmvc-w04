package com.higgsup.intern.ebshop.dto;

import java.util.ArrayList;
import java.util.List;

public class CustomerListDTO {
    private List<CustomerDTO> customerDTOS = new ArrayList<>();
    private Long totalPrice;

    public List<CustomerDTO> getCustomerDTOS() {
        return customerDTOS;
    }

    public void setCustomerDTOS(List<CustomerDTO> customerDTOS) {
        this.customerDTOS = customerDTOS;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }
}
