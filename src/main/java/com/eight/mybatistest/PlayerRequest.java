package com.eight.mybatistest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class PlayerRequest {
    @NotBlank(message = "苗字を入力してください")
    @Pattern(regexp = "^[^\\s　]+$", message = "苗字には半角スペースや全角スペースは含めることができません")
    private String lastName;

    @NotBlank(message = "名前を入力してください")
    @Pattern(regexp = "^[^\\s　]+$", message = "名前には半角スペースや全角スペースは含めることができません")
    private String firstName;

    @NotBlank(message = "ポジションを入力してください")
    @Pattern(regexp = "^[^\\s　]+$", message = "ポジションには半角スペースや全角スペースは含めることができません")
    private String position;

    @NotNull(message = "背番号を入力してください")
    @Pattern(regexp = "^[^\\s　]+$", message = "背番号には半角スペースや全角スペースは含めることができません")
    private String uniformNumber;

    @NotBlank(message = "出身地を入力してください")
    @Pattern(regexp = "^[^\\s　]+$", message = "出身地には半角スペースや全角スペースは含めることができません")
    private String prefecture;

    public PlayerRequest(String lastName, String firstName, String position, String uniformNumber, String prefecture) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.position = position;
        this.uniformNumber = uniformNumber;
        this.prefecture = prefecture;
    }

    public PlayerRequest() {

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
