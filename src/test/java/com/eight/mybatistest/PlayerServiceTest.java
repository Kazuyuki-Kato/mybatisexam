package com.eight.mybatistest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {
    @InjectMocks
    PlayerService playerService;

    @Mock
    PlayerMapper playerMapper;

    @Test
    public void 存在するユーザーのIDを指定したときに正常にプレイヤーのデータが取得できる事() throws PlayerNotFoundException {
        doReturn(Optional.of(new Player(1, "山岡泰輔", "投手", "19", "広島県"))).when(playerMapper).findById(1);

        Player actual = playerService.findPlayer(1);
        assertThat(actual).isEqualTo(new Player(1, "山岡泰輔", "投手", "19", "広島県"));
    }
}