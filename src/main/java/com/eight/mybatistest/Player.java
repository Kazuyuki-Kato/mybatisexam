package com.eight.mybatistest;

public class Player {
    private int id;
    private String name;
    private int number;
    private String prefecture;

    public Player(int id, String name, int number, String prefecture) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.prefecture = prefecture;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public String getPrefecture() {
        return prefecture;
    }
}
