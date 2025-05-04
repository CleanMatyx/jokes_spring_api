package com.matiasborra.jokes.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

/**
 * DTO para representar una categoría.
 * Contiene validaciones para los campos.
 *
 * @author Matias Borra
 */
public class CategoryDTO {

    private Long id;

    /**
     * Nombre de la categoría.
     * No puede estar en blanco.
     */
    @NotBlank(message = "{category.notblank}")
    @Valid
    private String category;

    /**
     * Obtiene el ID de la categoría.
     *
     * @return ID de la categoría
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID de la categoría.
     *
     * @param id ID de la categoría
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de la categoría.
     *
     * @return Nombre de la categoría
     */
    public String getCategory() {
        return category;
    }

    /**
     * Establece el nombre de la categoría.
     *
     * @param category Nombre de la categoría
     */
    public void setCategory(String category) {
        this.category = category;
    }
}