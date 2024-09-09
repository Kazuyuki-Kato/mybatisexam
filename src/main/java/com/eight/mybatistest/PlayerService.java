package com.eight.mybatistest;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.SimpleTimeZone;

@Service
public class PlayerService {
    private final PlayerMapper playerMapper;

    public PlayerService(PlayerMapper playerMapper) {
        this.playerMapper = playerMapper;
    }

    public List<Player> findAll() {
        return playerMapper.findAll();
    }

    public Player findPlayer(Integer id) {
        return this.playerMapper.findById(id).orElseThrow(() -> new PlayerNotFoundException("player not found"));
    }

    public Player insert(String lastName, String firstName, String position, String uniformNumber, String prefecture) {
        Player player = new Player(lastName, firstName, position, uniformNumber, prefecture);
        playerMapper.insert(player);
        return player;
    }

    public Player update(Integer id, String lastName, String firstName, String position, String uniformNumber, String prefecture) {
        Player player = this.findPlayer(id);

        player.setLastName(lastName);
        player.setFirstName(firstName);
        player.setPosition(position);
        player.setUniformNumber(uniformNumber);
        player.setPrefecture(prefecture);
        playerMapper.update(player);

        return player;
    }

    public void deletePlayer(Integer id) {
        playerMapper.findById(id).orElseThrow(() -> new PlayerNotFoundException("Player not found"));
        playerMapper.delete(id);
    }
}
