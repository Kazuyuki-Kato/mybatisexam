package com.eight.mybatistest;


public class PlayerRequest {
    private String name;
    private String position;
    private int uniform_number;
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
