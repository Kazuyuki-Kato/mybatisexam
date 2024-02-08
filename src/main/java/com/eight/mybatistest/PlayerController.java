package com.eight.mybatistest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlayerController {
    private final PlayerMapper playerMapper;

    public PlayerController(PlayerMapper playerMapper) {
        this.playerMapper = playerMapper;
    }

    @GetMapping("/player_list_database")
    public List<Player> findAll() {
        return playerMapper.findAll();
    }
}