package com.eight.mybatistest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PlayerRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String position;
    @NotNull
    private int uniform_number;
    @NotBlank
    private String prefecture;
    public PlayerRequest(String name, String position, int uniform_number, String prefecture) {
        this.name = name;
        this.position = position;
        this.uniform_number = uniform_number;
        this.prefecture = prefecture;
    }

    public String getName() {
        return name;
    }
    public String getPosition() {
        return position;
    }
    public int getUniform_number() {
        return uniform_number;
    }
    public String getPrefecture() {
        return prefecture;
    }
}