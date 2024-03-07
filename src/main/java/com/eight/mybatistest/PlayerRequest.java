package com.eight.mybatistest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PlayerRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String position;
    @NotNull
    private int uniformNumber;
    @NotBlank
    private String prefecture;
    public PlayerRequest(String name, String position, int uniformNumber, String prefecture) {
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
    public int getUniformNumber() {
        return uniformNumber;
    }
    public String getPrefecture() {
        return prefecture;
    }
}