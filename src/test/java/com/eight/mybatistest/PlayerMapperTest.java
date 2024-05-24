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
    public void 登録された選手のデータが正しく更新できること() {
        // 新しいプレイヤーオブジェクトを作成してデータベースに挿入
        Player player = new Player();
        player.setName("廣岡大志");
        player.setPosition("内野手");
        player.setUniformNumber("30");
        player.setPrefecture("大阪府");
        playerMapper.insert(player);

        // 更新する情報を設定
        player.setName("横山聖哉");
        player.setPosition("内野手");
        player.setUniformNumber("34");
        player.setPrefecture("長野県");

        // Updateを実行
        playerMapper.update(player);

        // 更新後のプレイヤーを検索して取得
        Optional<Player> updatedPlayerOptional = playerMapper.findById(player.getId());
        assertThat(updatedPlayerOptional).isPresent(); // 更新後のプレイヤーが存在することを確認

        Player updatedPlayer = updatedPlayerOptional.get();
        assertThat(updatedPlayer.getName()).isEqualTo(player.getName()); // 名前が一致することを確認
        assertThat(updatedPlayer.getPosition()).isEqualTo(player.getPosition()); // ポジションが一致することを確認
        assertThat(updatedPlayer.getUniformNumber()).isEqualTo(player.getUniformNumber()); // ユニフォーム番号が一致することを確認
        assertThat(updatedPlayer.getPrefecture()).isEqualTo(player.getPrefecture()); // 都道府県が一致することを確認
    }

    @Test
    @Sql(scripts = {"classpath:/sqlannotation/delete-players.sql", "classpath:/sqlannotation/insert-players.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Transactional
    public void 登録された選手のデータが削除できること() {
        // 新しいプレイヤーオブジェクトを作成してデータベースに挿入
        Player player = new Player();
        player.setName("鈴木博志");
        player.setPosition("投手");
        player.setUniformNumber("66");
        player.setPrefecture("静岡県");
        playerMapper.insert(player);

        // プレイヤーのIDを取得
        Integer playerId = player.getId();

        // Deleteを実行
        playerMapper.delete(playerId);

        // Delete後のプレイヤーを検索して取得
        Optional<Player> deletedPlayerOptional = playerMapper.findById(playerId);
        assertThat(deletedPlayerOptional).isEmpty(); // プレイヤーが存在しないことを確認
    }
}
