package com.matiasborra.jokes.dto;

/**
 * DTO para representar un conjunto de idiomas.
 * Contiene información básica como el ID y el nombre del idioma.
 *
 * @author Matias Borra
 */
public class LanguagesDTO {

    private Long id;

    private String language;

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
}