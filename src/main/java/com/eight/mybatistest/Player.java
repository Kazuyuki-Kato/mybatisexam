package com.eight.mybatistest;

public class Player {
    private Integer id;
    private String name;
    private String position;
    private String uniformNumber;
    private String prefecture;

    public Player(Integer id, String name, String position, String uniformNumber, String prefecture) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.uniformNumber = uniformNumber;
        this.prefecture = prefecture;
    }

    public Player(String name, String position, String uniformNumber, String prefecture) {
        this.id = null;
        this.name = name;
        this.position = position;
        this.uniformNumber = uniformNumber;
        this.prefecture = prefecture;
    }

    public Integer getId() {
        return id;
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