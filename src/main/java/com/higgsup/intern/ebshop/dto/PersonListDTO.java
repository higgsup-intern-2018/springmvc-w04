package com.higgsup.intern.ebshop.dto;

import java.util.ArrayList;
import java.util.List;

public class PersonListDTO {
    private List<PersonDTO> personDTOs = new ArrayList<>();
    private Integer resultCount;

    public List<PersonDTO> getPersonDTOs() {
        return personDTOs;
    }

    public void setPersonDTOs(List<PersonDTO> personDTOs) {
        this.personDTOs = personDTOs;
    }

    public Integer getResultCount() {
        return resultCount;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }
}
