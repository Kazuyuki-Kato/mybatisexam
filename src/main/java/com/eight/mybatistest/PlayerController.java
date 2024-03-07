package com.eight.mybatistest;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

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
    public ResponseEntity<PlayerResponse> insert(@RequestBody @Validated PlayerRequest playerRequest, UriComponentsBuilder uriBuilder) {
        Player player = playerService.insert(playerRequest.getName(), playerRequest.getPosition(), playerRequest.getUniformNumber(), playerRequest.getPrefecture());
        URI location = uriBuilder.path("/players/{id}").buildAndExpand(player.getId()).toUri();
        PlayerResponse body = new PlayerResponse("player created");
        return ResponseEntity.created(location).body(body);
    }
}