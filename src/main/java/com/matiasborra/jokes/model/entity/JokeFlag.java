// src/main/java/com/matiasborra/jokes/model/entity/JokeFlag.java
package com.matiasborra.jokes.model.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "jokes_flags", schema = "public")
public class JokeFlag implements Serializable {
    @EmbeddedId
    private JokeFlagKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("jokeId")
    @JoinColumn(name = "joke_id", nullable = false)
    private Joke joke;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("flagId")
    @JoinColumn(name = "flag_id", nullable = false)
    private Flag flag;

    public JokeFlag() {}
    public JokeFlag(Joke joke, Flag flag) {
        this.joke = joke;
        this.flag = flag;
        this.id   = new JokeFlagKey(joke.getId(), flag.getId());
    }
    // getters / setters

    public JokeFlagKey getId() { return id; }
    public void setId(JokeFlagKey id) { this.id = id; }

    public Joke getJoke() { return joke; }
    public void setJoke(Joke joke) { this.joke = joke; }

    public Flag getFlag() { return flag; }
    public void setFlag(Flag flag) { this.flag = flag; }
}
