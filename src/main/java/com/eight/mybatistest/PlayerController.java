package com.eight.mybatistest;

import org.springframework.web.bind.annotation.*;

@RestController
public class PlayerController {
    private final PlayerService playerService;
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }
    @GetMapping("/players/{id}")
    public Player getPlayer(@PathVariable("id") Integer id) {
        return playerService.findPlayer(id);
    }

    @PostMapping("/players")
    public Player insert(@RequestBody PlayerRequest playerRequest) {
        Player player = playerService.insert(playerRequest.getName(), playerRequest.getPosition(),playerRequest.getUniform_number(),playerRequest.getPrefecture());
        return player;
    }
}