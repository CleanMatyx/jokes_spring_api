package com.matiasborra.jokes.model.entity;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 * Entidad JokeFlag.
 * Representa la relación entre un chiste y una bandera.
 * Utiliza una clave compuesta para identificar la relación.
 *
 * @author Matias Borra
 */
@Entity
@Table(name = "jokes_flags", schema = "public")
public class JokeFlag implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private JokeFlagKey id = new JokeFlagKey();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("jokeId")
    @JoinColumn(name = "joke_id", nullable = false)
    private Joke joke;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("flagId")
    @JoinColumn(name = "flag_id", nullable = false)
    private Flag flag;

    /**
     * Constructor por defecto.
     */
    public JokeFlag() {
    }

    /**
     * Constructor con entidades Joke y Flag.
     *
     * @param joke Chiste asociado
     * @param flag Bandera asociada
     */
    public JokeFlag(Joke joke, Flag flag) {
        this();
        setJoke(joke);
        setFlag(flag);
    }

    /**
     * Obtiene la clave compuesta de la relación.
     *
     * @return Clave compuesta de la relación
     */
    public JokeFlagKey getId() {
        return id;
    }

    /**
     * Establece la clave compuesta de la relación.
     *
     * @param id Clave compuesta de la relación
     */
    public void setId(JokeFlagKey id) {
        this.id = id;
    }

    /**
     * Obtiene el chiste asociado a la relación.
     *
     * @return Chiste asociado
     */
    public Joke getJoke() {
        return joke;
    }

    /**
     * Establece el chiste asociado a la relación.
     *
     * @param joke Chiste asociado
     */
    public void setJoke(Joke joke) {
        this.joke = joke;
        this.id.setJokeId(joke != null ? joke.getId() : null);
    }

    /**
     * Obtiene la bandera asociada a la relación.
     *
     * @return Bandera asociada
     */
    public Flag getFlag() {
        return flag;
    }

    /**
     * Establece la bandera asociada a la relación.
     *
     * @param flag Bandera asociada
     */
    public void setFlag(Flag flag) {
        this.flag = flag;
        this.id.setFlagId(flag != null ? flag.getId() : null);
    }
}