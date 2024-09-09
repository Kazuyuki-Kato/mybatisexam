package com.eight.mybatistest;


import java.util.Objects;

public class Player {
    private Integer id;
    private String lastName;

    private String firstName;
    private String position;
    private String uniformNumber;
    private String prefecture;

    public Player(Integer id, String lastName, String firstName, String position, String uniformNumber, String prefecture) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.position = position;
        this.uniformNumber = uniformNumber;
        this.prefecture = prefecture;
    }

    public Player(String lastName, String firstName, String position, String uniformNumber, String prefecture) {
        this.id = null;
        this.lastName = lastName;
        this.firstName = firstName;
        this.position = position;
        this.uniformNumber = uniformNumber;
        this.prefecture = prefecture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(lastName, player.lastName) &&
                Objects.equals(firstName, player.firstName) &&
                Objects.equals(position, player.position) &&
                Objects.equals(uniformNumber, player.uniformNumber) &&
                Objects.equals(prefecture, player.prefecture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, position, uniformNumber, prefecture);
    }

    public Player() {
    }

    public Integer getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
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

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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
