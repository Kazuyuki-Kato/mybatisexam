package com.eight.mybatistest;

import org.springframework.stereotype.Service;

@Service
public class PlayerService {
    private final PlayerMapper playerMapper;

    public PlayerService(PlayerMapper playerMapper) {
        this.playerMapper = playerMapper;
    }

    public Player findPlayer(Integer id) {
        return this.playerMapper.findById(id).orElseThrow(() -> new PlayerNotFoundException("player not found"));
    }

    public Player insert(String name, String position, String uniformNumber, String prefecture) {
        Player player = new Player(name, position, uniformNumber, prefecture);
        playerMapper.insert(player);
        return player;
    }

    public Player update(Integer id, String name, String position, String uniformNumber, String prefecture) {
        Player player = this.findPlayer(id);
        player.setName(name);
        player.setPosition(position);
        player.setUniformNumber(uniformNumber);
        player.setPrefecture(prefecture);
        playerMapper.update(player);
        return player;
    }
}
