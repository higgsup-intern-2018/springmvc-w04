package com.higgsup.intern.ebshop.dto;

public class GenericResponseDTO {
    private String message;

    private GenericResponseDTO(String message) {
        this.message = message;
    }

    public static GenericResponseDTO of(String message) {
        return new GenericResponseDTO(message);
    }

    public static GenericResponseDTO success() {
        return of("SUCCESS");
    }

    public static GenericResponseDTO created() {
        return of("CREATED");
    }

    public static GenericResponseDTO updated() {
        return of("UPDATED");
    }

    public static GenericResponseDTO deleted() {
        return of("DELETED");
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
