package com.eight.mybatistest;


import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name) &&
                Objects.equals(position, player.position) &&
                Objects.equals(uniformNumber, player.uniformNumber) &&
                Objects.equals(prefecture, player.prefecture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, position, uniformNumber, prefecture);
    }

    public Player() {
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

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setUniformNumber(String uniformNumber) {
        this.uniformNumber = uniformNumber;
    }

    public void setPrefecture(String prefecture) {
        this.prefecture = prefecture;
    }
}