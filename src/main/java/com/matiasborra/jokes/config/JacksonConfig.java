package com.matiasborra.jokes.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        // Estrategia estrita: coincidencia exacta de nombres
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);

        // Mapeo personalizado: de Joke.jokeFlags -> List<FlagDTO>
        mapper.typeMap(com.matiasborra.jokes.model.entity.Joke.class, com.matiasborra.jokes.dto.JokeDTO.class)
                .addMappings(map -> map.skip(com.matiasborra.jokes.dto.JokeDTO::setFlags))
                .setPostConverter(context -> {
                    com.matiasborra.jokes.model.entity.Joke source = context.getSource();
                    com.matiasborra.jokes.dto.JokeDTO dest = context.getDestination();
                    if (source.getJokeFlags() != null) {
                        dest.setFlags(
                                source.getJokeFlags().stream()
                                        .map(jf -> mapper.map(jf.getFlag(), com.matiasborra.jokes.dto.FlagDTO.class))
                                        .collect(java.util.stream.Collectors.toList())
                        );
                    }
                    return context.getDestination();
                });

        return mapper;
    }
}