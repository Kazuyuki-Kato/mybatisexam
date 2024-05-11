package com.eight.mybatistest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class PlayerRequest {
    @NotBlank(message = "名前を入力してください")
    @Pattern(regexp = "^[^\\s　]+$", message = "名前には半角スペースや全角スペースは含めることができません")
    private String name;

    @NotBlank(message = "ポジションを入力してください")
    @Pattern(regexp = "^[^\\s　]+$", message = "ポジションには半角スペースや全角スペースは含めることができません")
    private String position;

    @NotNull(message = "背番号を入力してください")
    @Pattern(regexp = "^[^\\s　]+$", message = "背番号には半角スペースや全角スペースは含めることができません")
    private String uniformNumber;

    @NotBlank(message = "出身地を入力してください")
    @Pattern(regexp = "^[^\\s　]+$", message = "出身地には半角スペースや全角スペースは含めることができません")
    private String prefecture;

    public PlayerRequest(String name, String position, String uniformNumber, String prefecture) {
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

    public String getUniformNumber() {
        return uniformNumber;
    }

    public String getPrefecture() {
        return prefecture;
    }
}
