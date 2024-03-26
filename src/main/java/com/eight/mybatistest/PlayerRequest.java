package com.eight.mybatistest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class PlayerRequest {
    @NotBlank
    @Pattern(regexp = "^[^\\u3000]*$")
    private String name;
    @NotBlank
    @Pattern(regexp = "^[^\\u3000]*$")
    private String position;
    @NotBlank
    @Pattern(regexp = "^[^\\u3000]*$")
    private String uniformNumber;
    @NotBlank
    @Pattern(regexp = "^[^\\u3000]*$")
    private String prefecture;
    public PlayerRequest(String name, String position, String uniformNumber, String prefecture) {
        this.name = name;
        this.position = position;
        this.uniformNumber = uniformNumber;
        this.prefecture = prefecture;
    }

    public String getName() {
        return name;
    }
    public String getPosition() {
        return position;
    }
    public String getUniformNumber() {
        return uniformNumber;
    }
    public String getPrefecture() {
        return prefecture;
    }
}