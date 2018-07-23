package com.higgsup.intern.ebshop.dto;

public class GenericResponseDTO {
    private String message;

    private GenericResponseDTO(String message) {
        this.message = message;
    }

    public static GenericResponseDTO success() {
        return new GenericResponseDTO("SUCCESS");
    }

    public static GenericResponseDTO created() {
        return new GenericResponseDTO("CREATED");
    }

    public static GenericResponseDTO updated() {
        return new GenericResponseDTO("UPDATED");
    }

    public static GenericResponseDTO deleted() {
        return new GenericResponseDTO("DELETED");
    }

    public static GenericResponseDTO of(String message) {
        return new GenericResponseDTO(message);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
