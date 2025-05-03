package com.matiasborra.jokes.dto;

import jakarta.validation.constraints.NotBlank;

public class FlagDTO {
    private Long id;
    @NotBlank(message = "{flag.notblank}")
    private String flag;

    public FlagDTO() {}
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFlag() {
        return flag;
    }
    public void setFlag(String flag) {
        this.flag = flag;
    }
}
