package com.matiasborra.jokes.model.entity;

import jakarta.persistence.*;
import java.io.Serializable;

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

    public JokeFlag() {
    }

    public JokeFlag(Joke joke, Flag flag) {
        this();
        setJoke(joke);
        setFlag(flag);
    }

    public JokeFlagKey getId() {
        return id;
    }

    public void setId(JokeFlagKey id) {
        this.id = id;
    }

    public Joke getJoke() {
        return joke;
    }

    public void setJoke(Joke joke) {
        this.joke = joke;
        this.id.setJokeId(joke != null ? joke.getId() : null);
    }

    public Flag getFlag() {
        return flag;
    }

    public void setFlag(Flag flag) {
        this.flag = flag;
        this.id.setFlagId(flag != null ? flag.getId() : null);
    }
}
