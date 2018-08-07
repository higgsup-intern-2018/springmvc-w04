package com.higgsup.intern.ebshop.dto;

import java.util.ArrayList;
import java.util.List;

public class AuthorListDTO {
    private List<AuthorDTO> authorDTOs = new ArrayList<>();

    public List<AuthorDTO> getAuthorDTOs() {
        return authorDTOs;
    }

    public void setAuthorDTOs(List<AuthorDTO> authorDTOs) {
        this.authorDTOs = authorDTOs;
    }

}
