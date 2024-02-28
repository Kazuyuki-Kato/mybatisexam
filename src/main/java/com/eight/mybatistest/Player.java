package com.eight.mybatistest;

public class Player {
    private Integer id;
    private String name;
    private String position;
    private int uniform_number;
    private String prefecture;

    public Player(Integer id,String name, String position, Integer uniform_number, String prefecture) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.uniform_number = uniform_number;
        this.prefecture = prefecture;
    }

    public Player(String name, String position, Integer uniform_number, String prefecture) {
        this.id = null;
        this.name = name;
        this.position = position;
        this.uniform_number = uniform_number;
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
    public Integer getUniform_number() {
        return uniform_number;
    }
    public String getPrefecture() {
        return prefecture;
    }
}
