package com.matiasborra.jokes.model.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class JokeFlagKey implements Serializable {
    @Column(name = "joke_id")
    private Long jokeId;

    @Column(name = "flag_id")
    private Long flagId;

    public JokeFlagKey() {}
    public JokeFlagKey(Long jokeId, Long flagId) {
        this.jokeId = jokeId;
        this.flagId = flagId;
    }

    public Long getJokeId() { return jokeId; }
    public void setJokeId(Long jokeId) { this.jokeId = jokeId; }

    public Long getFlagId() { return flagId; }
    public void setFlagId(Long flagId) { this.flagId = flagId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JokeFlagKey)) return false;
        JokeFlagKey that = (JokeFlagKey) o;
        return Objects.equals(jokeId, that.jokeId) &&
                Objects.equals(flagId, that.flagId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jokeId, flagId);
    }
}
