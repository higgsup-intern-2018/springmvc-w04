package com.higgsup.intern.ebshop.dto;

import java.util.ArrayList;
import java.util.List;

public class CustomerListDTO {
    private List<CustomerDTO> customerDTOList = new ArrayList<CustomerDTO>();

    public List<CustomerDTO> getCustomerDTOList() {
        return customerDTOList;
    }

    public void setCustomerDTOList(List<CustomerDTO> customerDTOList) {
        this.customerDTOList = customerDTOList;
    }
}
