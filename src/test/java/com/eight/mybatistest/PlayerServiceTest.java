package com.eight.mybatistest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
    void 全ての選手の情報が取得できること() {
        List<Player> expectedPlayers = Arrays.asList(
                new Player(1, "山岡", "泰輔", "投手", "19", "広島県"),
                new Player(2, "宮城", "大弥", "投手", "13", "沖縄県"),
                new Player(3, "頓宮", "裕馬", "捕手", "44", "岡山県"),
                new Player(4, "宗", "佑馬", "内野手", "6", "東京都"),
                new Player(5, "紅林", "弘太郎", "内野手", "24", "静岡県"),
                new Player(6, "岡田", "貴弘", "外野手", "55", "大阪府")
        );

        when(playerMapper.findAll()).thenReturn(expectedPlayers);

        List<Player> actualPlayers = playerService.findAll();

        assertEquals(expectedPlayers.size(), actualPlayers.size());
        assertEquals(expectedPlayers, actualPlayers);
    }

    @Test
    public void 存在するユーザーのIDを指定したときに正常にプレイヤーのデータが取得できる事() throws PlayerNotFoundException {
        doReturn(Optional.of(new Player(1, "山岡", "泰輔", "投手", "19", "広島県"))).when(playerMapper).findById(1);

        Player actual = playerService.findPlayer(1);
        assertThat(actual).isEqualTo(new Player(1, "山岡", "泰輔", "投手", "19", "広島県"));
    }

    @Test
    public void 選手の情報を入力した際に正しく情報が登録されること() {
        // Given
        String lastName = "若月";
        String firstName = "健矢";
        String position = "捕手";
        String uniformNumber = "2";
        String prefecture = "埼玉県";
        Player expectedPlayer = new Player(lastName, firstName, position, uniformNumber, prefecture);

        doNothing().when(playerMapper).insert(any(Player.class));
        Player insertedPlayer = playerService.insert(lastName, firstName, position, uniformNumber, prefecture);

        assertThat(insertedPlayer).isEqualToComparingFieldByField(expectedPlayer);

        verify(playerMapper, times(1)).insert(any(Player.class));
    }

    @Test
    public void IDで指定したデータが正しく更新されること() {
        // Given
        Integer id = 1;
        String lastName = "山岡";
        String firstName = "泰輔";
        String position = "投手";
        String uniformNumber = "19";
        String prefecture = "広島県";
        Player playerToUpdate = new Player(id, lastName, firstName, position, uniformNumber, prefecture);

        when(playerMapper.findById(id)).thenReturn(Optional.of(playerToUpdate));

        String updatedLastName = "宇田川";
        String updatedFirstName = "優希";
        String updatedPosition = "投手";
        String updatedUniformNumber = "14";
        String updatedPrefecture = "埼玉県";
        Player updatedPlayer = playerService.update(id, updatedLastName, updatedFirstName, updatedPosition, updatedUniformNumber, updatedPrefecture);

        assertThat(updatedPlayer)
                .extracting(Player::getLastName, Player::getFirstName, Player::getPosition, Player::getUniformNumber, Player::getPrefecture)
                .containsExactly(updatedLastName, updatedFirstName, updatedPosition, updatedUniformNumber, updatedPrefecture);

        verify(playerMapper, times(1)).findById(id);
        verify(playerMapper, times(1)).update(updatedPlayer);
    }

    @Test
    public void 更新時に指定したIDにデータが存在しない時設定したエラーが返ること() {
        // Given
        Integer id = 1;
        when(playerMapper.findById(id)).thenReturn(Optional.empty());

        assertThrows(PlayerNotFoundException.class, () -> playerService.update(id, "宇田川", "優希", "投手", "14", "埼玉県"));
        verify(playerMapper, times(1)).findById(id);
        verify(playerMapper, never()).update(any(Player.class));
    }

    @Test
    public void 指定したIDで登録されているデータが問題なく削除されること() {
        // Given
        Integer id = 1;
        Player player = new Player(id, "山岡", "泰輔", "投手", "19", "広島県");

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
    public void IDで指定したデータが存在しない時にハンドリングしたエラーが表示されること() {
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
