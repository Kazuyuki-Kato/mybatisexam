package com.eight.mybatistest;

public class Player {
    private int id;
    private String name;
    private String position;
    private int number;
    private String prefecture;

    public Player(int id, String name, String position, int number, String prefecture) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.number = number;
        this.prefecture = prefecture;


    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public int getNumber() {
        return number;
    }

    public String getPrefecture() {
        return prefecture;
    }
}
