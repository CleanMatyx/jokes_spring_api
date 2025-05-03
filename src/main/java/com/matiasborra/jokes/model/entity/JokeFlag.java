package com.matiasborra.jokes.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name="jokes_flags")
public class JokeFlag {

    @EmbeddedId
    private JokeFlagKey id;

    @MapsId("jokeId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="joke_id")
    private Joke joke;

    @MapsId("flagId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="flag_id")
    private Flag flag;

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
    }

    public Flag getFlag() {
        return flag;
    }

    public void setFlag(Flag flag) {
        this.flag = flag;
    }
}