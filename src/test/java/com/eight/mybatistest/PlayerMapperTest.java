package com.eight.mybatistest;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
}
