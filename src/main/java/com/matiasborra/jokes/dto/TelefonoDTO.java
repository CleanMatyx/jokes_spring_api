package com.matiasborra.jokes.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO para representar un teléfono.
 * Contiene validaciones para los campos.
 *
 * @author Matias Borra
 */
public class TelefonoDTO {

    private Long id;

    /**
     * Número de teléfono.
     * No puede estar en blanco.
     */
    @NotBlank(message = "{telefono.notblank}")
    private String numero;

    /**
     * Obtiene el ID del teléfono.
     *
     * @return ID del teléfono
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID del teléfono.
     *
     * @param id ID del teléfono
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el número de teléfono.
     *
     * @return Número de teléfono
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Establece el número de teléfono.
     *
     * @param numero Número de teléfono
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }
}