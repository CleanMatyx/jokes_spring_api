package com.matiasborra.jokes.dto;

import java.util.ArrayList;
import java.util.List;

public class JokeDTO {
    private Long id;
    private String text1;
    private String text2;
    private CategoryDTO category;
    private TypeDTO type;
    private LanguageDTO language;

    // Para el API Rest → lista de objetos completos
    private List<FlagDTO> flags = new ArrayList<>();

    // Para Thymeleaf → bind de los checkbox con los IDs
    private List<Long> flagIds = new ArrayList<>();

    // --- getters / setters ---

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getText1() {
        return text1;
    }
    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getText2() {
        return text2;
    }
    public void setText2(String text2) {
        this.text2 = text2;
    }

    public CategoryDTO getCategory() {
        return category;
    }
    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public TypeDTO getType() {
        return type;
    }
    public void setType(TypeDTO type) {
        this.type = type;
    }

    public LanguageDTO getLanguage() {
        return language;
    }
    public void setLanguage(LanguageDTO language) {
        this.language = language;
    }

    public List<FlagDTO> getFlags() {
        return flags;
    }
    public void setFlags(List<FlagDTO> flags) {
        this.flags = flags;
    }

    public List<Long> getFlagIds() {
        return flagIds;
    }
    public void setFlagIds(List<Long> flagIds) {
        this.flagIds = flagIds;
    }
}
