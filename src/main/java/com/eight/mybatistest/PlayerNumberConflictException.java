package com.eight.mybatistest;

public class PlayerNumberConflictException extends RuntimeException {
    public PlayerNumberConflictException(String message){
        super(message);
    }
}
