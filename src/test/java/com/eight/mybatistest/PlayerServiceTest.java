package com.eight.mybatistest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

    @Test
    public void 選手の情報を入力した際に正しく情報が登録されること() {
        // Given
        String name = "若月健矢";
        String position = "捕手";
        String uniformNumber = "2";
        String prefecture = "埼玉県";
        Player expectedPlayer = new Player(name, position, uniformNumber, prefecture);

        // When
        doNothing().when(playerMapper).insert(any(Player.class));
        Player insertedPlayer = playerService.insert(name, position, uniformNumber, prefecture);

        // Then
        assertThat(insertedPlayer).isEqualToComparingFieldByField(expectedPlayer);

        // Verify that playerMapper.insert() method is called once
        verify(playerMapper, times(1)).insert(any(Player.class));
    }

    @Test
    public void IDで指定したデータが正しく更新されること() {
        // Given
        Integer id = 1;
        String name = "山岡泰輔";
        String position = "投手";
        String uniformNumber = "19";
        String prefecture = "広島県";
        Player playerToUpdate = new Player(id, name, position, uniformNumber, prefecture);

        when(playerMapper.findById(id)).thenReturn(Optional.of(playerToUpdate));

        // When
        String updatedName = "宇田川優希";
        String updatedPosition = "投手";
        String updatedUniformNumber = "14";
        String updatedPrefecture = "埼玉県";
        Player updatedPlayer = playerService.update(id, updatedName, updatedPosition, updatedUniformNumber, updatedPrefecture);

        // Then
        assertThat(updatedPlayer)
                .extracting(Player::getName, Player::getPosition, Player::getUniformNumber, Player::getPrefecture)
                .containsExactly(updatedName, updatedPosition, updatedUniformNumber, updatedPrefecture);

        verify(playerMapper, times(1)).findById(id);
        verify(playerMapper, times(1)).update(updatedPlayer);
    }

    @Test
    public void 更新時に指定したIDにデータが存在しない時設定したエラーが返ること() {
        // Given
        Integer id = 1;
        when(playerMapper.findById(id)).thenReturn(Optional.empty());

        // When / Then
        assertThrows(PlayerNotFoundException.class, () -> playerService.update(id, "宇田川優希", "投手", "14", "埼玉県"));
        verify(playerMapper, times(1)).findById(id);
        verify(playerMapper, never()).update(any(Player.class));
    }

    @Test
    public void testDeletePlayer() {
        // Given
        Integer id = 1;
        Player player = new Player(id, "山岡泰輔", "投手", "19", "広島県");

        // When findById returns a player
        when(playerMapper.findById(id)).thenReturn(java.util.Optional.of(player));

        // Then
        assertDoesNotThrow(() -> {
            playerService.deletePlayer(id);
            verify(playerMapper, times(1)).findById(id);
            verify(playerMapper, times(1)).delete(id);
        });
    }

    @Test
    public void testDeletePlayer_PlayerNotFoundException() {
        // Given
        Integer id = 1;

        // When findById returns empty
        when(playerMapper.findById(id)).thenReturn(java.util.Optional.empty());

        // Then
        assertThrows(PlayerNotFoundException.class, () -> {
            playerService.deletePlayer(id);
        });
        verify(playerMapper, times(1)).findById(id);
        verify(playerMapper, never()).delete(id);
    }
}
