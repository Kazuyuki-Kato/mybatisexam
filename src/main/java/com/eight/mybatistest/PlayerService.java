package com.eight.mybatistest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerService {
    private final PlayerMapper playerMapper;
    public PlayerService(PlayerMapper playerMapper) {
        this.playerMapper = playerMapper;
    }
    public Player findPlayer(int id) {
        Optional<Player> player = this.playerMapper.findById(id);
        if(player.isPresent()) {
            return player.get();
        } else {
            throw new PlayerNotFoundException("player not found");
        }
    }
}
