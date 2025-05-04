package com.matiasborra.jokes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * DTO para representar un idioma.
 * Contiene validaciones para los campos.
 *
 * @author Matias Borra
 */
public class LanguageDTO {

    private Long id;

    /**
     * Código del idioma.
     * Debe tener entre 1 y 2 caracteres y no puede estar en blanco.
     */
    @NotBlank(message = "{language.code.notblank}")
    @Size(min = 1, max = 2, message = "{language.size}")
    private String code;

    /**
     * Nombre del idioma.
     * No puede estar en blanco ni ser nulo.
     */
    @NotBlank(message = "{language.notblank}")
    @NotNull
    private String language;

    /**
     * Obtiene el nombre del idioma.
     *
     * @return Nombre del idioma
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Establece el nombre del idioma.
     *
     * @param language Nombre del idioma
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * Obtiene el código del idioma.
     *
     * @return Código del idioma
     */
    public String getCode() {
        return code;
    }

    /**
     * Establece el código del idioma.
     *
     * @param code Código del idioma
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Obtiene el ID del idioma.
     *
     * @return ID del idioma
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID del idioma.
     *
     * @param id ID del idioma
     */
    public void setId(Long id) {
        this.id = id;
    }
}