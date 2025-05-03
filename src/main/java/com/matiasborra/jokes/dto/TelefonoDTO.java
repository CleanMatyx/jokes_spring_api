package com.matiasborra.jokes.dto;

import jakarta.validation.constraints.NotBlank;

public class TelefonoDTO {
    private Long id;

    @NotBlank
    private String numero;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}