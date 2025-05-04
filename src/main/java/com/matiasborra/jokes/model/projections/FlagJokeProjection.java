package com.matiasborra.jokes.model.projections;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Proyección FlagJokeProjection.
 * Representa una vista parcial de un chiste con su ID, texto y el idioma asociado.
 * Utiliza anotaciones de Jackson para personalizar los nombres de las propiedades en JSON.
 *
 * @author Matias Borra
 */
public interface FlagJokeProjection {

    /**
     * Obtiene el identificador único del chiste.
     *
     * @return Identificador único del chiste
     */
    @JsonProperty("jokeId")
    Long getId();

    /**
     * Obtiene el primer texto del chiste.
     *
     * @return Primer texto del chiste
     */
    @JsonProperty("text1")
    String getText1();

    /**
     * Obtiene el idioma asociado al chiste.
     *
     * @return Idioma asociado al chiste
     */
    @JsonProperty("language")
    String getLanguageLanguage();
}