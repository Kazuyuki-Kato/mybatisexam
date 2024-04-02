package com.eight.mybatistest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

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

    @PatchMapping("/players/{id}")
    public ResponseEntity<Map<String, String>> updatePlayer(@PathVariable Integer id, @RequestBody Player newPlayer) {
        playerService.update(id, newPlayer.getName(), newPlayer.getPosition(), newPlayer.getUniformNumber(), newPlayer.getPrefecture());
        Map<String, String> response = new HashMap<>();
        response.put("message", "player updated");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }
}
