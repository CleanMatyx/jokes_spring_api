package com.matiasborra.jokes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO para representar un tipo de chiste.
 * Contiene validaciones para los campos.
 *
 * @author Matias Borra
 */
public class TypeDTO {

    private Long id;

    /**
     * Tipo de chiste.
     * No puede estar en blanco ni ser nulo.
     */
    @NotNull
    @NotBlank(message = "{type.notblank}")
    private String type;

    /**
     * Obtiene el ID del tipo de chiste.
     *
     * @return ID del tipo de chiste
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID del tipo de chiste.
     *
     * @param id ID del tipo de chiste
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el tipo de chiste.
     *
     * @return Tipo de chiste
     */
    public String getType() {
        return type;
    }

    /**
     * Establece el tipo de chiste.
     *
     * @param type Tipo de chiste
     */
    public void setType(String type) {
        this.type = type;
    }
}