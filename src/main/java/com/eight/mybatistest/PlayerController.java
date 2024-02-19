package com.eight.mybatistest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class PlayerController {
    private final PlayerService playerService;
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }
    @GetMapping("/players/{id}")
    public Player getPlayer(@PathVariable("id") int id) {
        return playerService.findPlayer(id);
    }
}