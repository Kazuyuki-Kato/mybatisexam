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

    public Player insert(String name, String position, int uniform_number, String prefecture) {
        Player player = new Player(name, position, uniform_number, prefecture);
        playerMapper.insert(player);
        return player;
    }
}