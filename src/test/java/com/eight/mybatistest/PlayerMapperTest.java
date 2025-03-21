package com.eight.mybatistest;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DBRider
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PlayerMapperTest {
    @Autowired
    PlayerMapper playerMapper;

    @Test
    @DataSet(value = "datasets/players.yml")
    @Transactional
    void 全てのプレイヤーの情報が取得できること() {
        List<Player> players = playerMapper.findAll();
        assertThat(players)
                .hasSize(6)
                .contains(
                        new Player("山岡", "泰輔", "投手", "19", "広島県"),
                        new Player("宮城", "大弥", "投手", "13", "沖縄県"),
                        new Player("頓宮", "裕馬", "捕手", "44", "岡山県"),
                        new Player("宗", "佑馬", "内野手", "6", "東京都"),
                        new Player("紅林", "弘太郎", "内野手", "24", "静岡県"),
                        new Player("岡田", "貴弘", "外野手", "55", "大阪府")
                );
    }

    @Test
    @DataSet(value = "datasets/players.yml")
    @Transactional
    public void IDで指定した選手の情報が正しく取得されること() {
        // テスト: IDが1のプレイヤーを検索
        Optional<Player> playerOptional = playerMapper.findById(1);
        assertTrue(playerOptional.isPresent()); // プレイヤーが存在することを確認

        Player expectedPlayer = new Player(1, "山岡", "泰輔", "投手", "19", "広島県");
        assertThat(playerOptional.get()).isEqualTo(expectedPlayer);
    }

    @Test
    @DataSet(value = "datasets/players.yml")
    @Transactional
    public void 選手のデータを新しく登録できること() {
        // 新しいプレイヤーオブジェクトを作成
        Player player = new Player();
        player.setLastName("西野");
        player.setFirstName("真弘");
        player.setPosition("内野手");
        player.setUniformNumber("5");
        player.setPrefecture("東京都");

        // Insertを実行
        playerMapper.insert(player);

        // InsertされたプレイヤーをIDで検索して取得
        Optional<Player> insertedPlayerOptional = playerMapper.findById(player.getId());
        assertTrue(insertedPlayerOptional.isPresent()); // Insertされたプレイヤーが存在することを確認

        assertThat(insertedPlayerOptional.get()).isEqualTo(player);
    }

    @Test
    @DataSet(value = "datasets/players.yml")
    @Transactional
    public void IDで指定した選手の情報が正しく更新されること() {
        // テスト: IDが2のプレイヤーの情報を更新
        Player player = new Player(2, "阿部", "翔太", "投手", "20", "大阪府");
        playerMapper.update(player);

        // 更新後のプレイヤーを検索して取得
        Optional<Player> updatedPlayerOptional = playerMapper.findById(2);
        assertTrue(updatedPlayerOptional.isPresent()); // プレイヤーが存在することを確認

        assertThat(updatedPlayerOptional.get()).isEqualTo(player);
    }

    @Test
    @DataSet(value = "datasets/players.yml")
    @Transactional
    public void IDで指定した選手の情報が削除されること() {
        // テスト: IDが3のプレイヤーを削除
        playerMapper.delete(3);

        // プレイヤーが削除されたことを確認
        Optional<Player> deletedPlayerOptional = playerMapper.findById(3);
        assertThat(deletedPlayerOptional).isEmpty(); // プレイヤーが存在しないことを確認

        // プレイヤーが削除された後のリストのサイズが1つ減少していることを確認
        List<Player> playersAfterDelete = playerMapper.findAll();
        assertThat(playersAfterDelete.size()).isEqualTo(5); // プレイヤーの数が5であることを確認
    }
}
