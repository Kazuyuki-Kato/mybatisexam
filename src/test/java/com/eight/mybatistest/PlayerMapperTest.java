package com.eight.mybatistest;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PlayerMapperTest {
    @Autowired
    PlayerMapper playerMapper;

    @Test
    @Sql(scripts = {"classpath:/sqlannotation/delete-players.sql", "classpath:/sqlannotation/insert-players.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Transactional
    void 全てのプレイヤーの情報が取得できること() {
        List<Player> players = playerMapper.findAll();
        assertThat(players)
                .hasSize(6)
                .contains(
                        new Player("山岡泰輔", "投手", "19", "広島県"),
                        new Player("宮城大弥", "投手", "13", "沖縄県"),
                        new Player("頓宮裕馬", "捕手", "44", "岡山県"),
                        new Player("宗佑馬", "内野手", "6", "東京都"),
                        new Player("紅林弘太郎", "内野手", "24", "静岡県"),
                        new Player("T-岡田", "外野手", "55", "大阪府")
                );
    }

    @Test
    @Sql(scripts = {"classpath:/sqlannotation/delete-players.sql", "classpath:/sqlannotation/insert-players.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Transactional
    public void IDで指定した選手の情報が正しく取得されること() {
        // テスト: IDが1のプレイヤーを検索
        Optional<Player> playerOptional = playerMapper.findById(1);
        assertThat(playerOptional).isPresent(); // プレイヤーが存在することを確認
        assertThat(playerOptional.get().getName()).isEqualTo("山岡泰輔"); // 名前が正しいことを確認
        assertThat(playerOptional.get().getPosition()).isEqualTo("投手"); // ポジションが正しいことを確認
        assertThat(playerOptional.get().getUniformNumber()).isEqualTo("19"); // ユニフォーム番号が正しいことを確認
        assertThat(playerOptional.get().getPrefecture()).isEqualTo("広島県"); // 都道府県が正しいことを確認
    }

    @Test
    @Sql(scripts = {"classpath:/sqlannotation/delete-players.sql", "classpath:/sqlannotation/insert-players.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Transactional
    public void 選手のデータを新しく登録できること() {
        // 新しいプレイヤーオブジェクトを作成
        Player player = new Player();
        player.setName("西野真弘");
        player.setPosition("内野手");
        player.setUniformNumber("5");
        player.setPrefecture("東京都");

        // Insertを実行
        playerMapper.insert(player);

        // InsertされたプレイヤーをIDで検索して取得
        Optional<Player> insertedPlayerOptional = playerMapper.findById(player.getId());
        assertThat(insertedPlayerOptional).isPresent(); // Insertされたプレイヤーが存在することを確認

        Player insertedPlayer = insertedPlayerOptional.get();
        assertThat(insertedPlayer.getName()).isEqualTo(player.getName()); // 名前が一致することを確認
        assertThat(insertedPlayer.getPosition()).isEqualTo(player.getPosition()); // ポジションが一致することを確認
        assertThat(insertedPlayer.getUniformNumber()).isEqualTo(player.getUniformNumber()); // ユニフォーム番号が一致することを確認
        assertThat(insertedPlayer.getPrefecture()).isEqualTo(player.getPrefecture()); // 都道府県が一致することを確認
    }

    @Test
    @Sql(scripts = {"classpath:/sqlannotation/delete-players.sql", "classpath:/sqlannotation/insert-players.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Transactional
    public void IDで指定した選手の情報が正しく更新されること() {
        // テスト: IDが2のプレイヤーの情報を更新
        Player player = new Player(2, "阿部翔太", "投手", "20", "大阪府");
        playerMapper.update(player);

        // 更新後のプレイヤーを検索して取得
        Optional<Player> updatedPlayerOptional = playerMapper.findById(2);
        assertThat(updatedPlayerOptional).isPresent(); // プレイヤーが存在することを確認
        assertThat(updatedPlayerOptional.get().getName()).isEqualTo("阿部翔太"); // 名前が更新されていることを確認
        assertThat(updatedPlayerOptional.get().getPosition()).isEqualTo("投手"); // ポジションが更新されていることを確認
        assertThat(updatedPlayerOptional.get().getUniformNumber()).isEqualTo("20"); // ユニフォーム番号が更新されていることを確認
        assertThat(updatedPlayerOptional.get().getPrefecture()).isEqualTo("大阪府"); // 都道府県が更新されていることを確認
    }

    @Test
    @Sql(scripts = {"classpath:/sqlannotation/delete-players.sql", "classpath:/sqlannotation/insert-players.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
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
