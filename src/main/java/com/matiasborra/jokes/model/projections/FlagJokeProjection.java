package com.matiasborra.jokes.model.projections;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface FlagJokeProjection {
    @JsonProperty("jokeId")
    Long getId();

    @JsonProperty("text1")
    String getText1();

    @JsonProperty("language")
    String getLanguageLanguage();
}