package com.matiasborra.jokes.model.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Clase JokeFlagKey.
 * Representa la clave compuesta para la relación entre un chiste y una bandera.
 * Contiene los identificadores de ambas entidades.
 *
 * @author Matias Borra
 */
@Embeddable
public class JokeFlagKey implements Serializable {

    @Column(name = "joke_id")
    private Long jokeId;

    @Column(name = "flag_id")
    private Long flagId;

    /**
     * Constructor por defecto.
     */
    public JokeFlagKey() {}

    /**
     * Constructor con parámetros.
     *
     * @param jokeId ID del chiste
     * @param flagId ID de la bandera
     */
    public JokeFlagKey(Long jokeId, Long flagId) {
        this.jokeId = jokeId;
        this.flagId = flagId;
    }

    /**
     * Obtiene el ID del chiste.
     *
     * @return ID del chiste
     */
    public Long getJokeId() {
        return jokeId;
    }

    /**
     * Establece el ID del chiste.
     *
     * @param jokeId ID del chiste
     */
    public void setJokeId(Long jokeId) {
        this.jokeId = jokeId;
    }

    /**
     * Obtiene el ID de la bandera.
     *
     * @return ID de la bandera
     */
    public Long getFlagId() {
        return flagId;
    }

    /**
     * Establece el ID de la bandera.
     *
     * @param flagId ID de la bandera
     */
    public void setFlagId(Long flagId) {
        this.flagId = flagId;
    }

    /**
     * Verifica si dos objetos son iguales.
     *
     * @param o Objeto a comparar
     * @return true si son iguales, false en caso contrario
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JokeFlagKey)) return false;
        JokeFlagKey that = (JokeFlagKey) o;
        return Objects.equals(jokeId, that.jokeId) &&
                Objects.equals(flagId, that.flagId);
    }

    /**
     * Calcula el hash code del objeto.
     *
     * @return Hash code del objeto
     */
    @Override
    public int hashCode() {
        return Objects.hash(jokeId, flagId);
    }
}