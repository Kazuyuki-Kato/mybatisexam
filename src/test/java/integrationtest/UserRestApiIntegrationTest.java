package integrationtest;

import com.eight.mybatistest.MybatisTestApplication;
import com.eight.mybatistest.Player;
import com.eight.mybatistest.PlayerMapper;
import com.eight.mybatistest.PlayerRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = MybatisTestApplication.class)
@AutoConfigureMockMvc
@DBRider
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRestApiIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    PlayerMapper playerMapper;

    @Test
    @DataSet(value = "datasets/players.yml")
    @Transactional
    void 全てのプレイヤーの情報が取得できること() throws Exception {
        String response = mockMvc.perform(get("/players"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [
                            {
                                "id": 1,
                                "name": "山岡泰輔",
                                "position": "投手",
                                "uniformNumber": "19",
                                "prefecture": "広島県"
                            },
                            {
                                "id": 2,
                                "name": "宮城大弥",
                                "position": "投手",
                                "uniformNumber": "13",
                                "prefecture": "沖縄県"
                            },
                            {
                                "id": 3,
                                "name": "頓宮裕馬",
                                "position": "捕手",
                                "uniformNumber": "44",
                                "prefecture": "岡山県"
                            },
                            {
                                "id": 4,
                                "name": "宗佑馬",
                                "position": "内野手",
                                "uniformNumber": "6",
                                "prefecture": "東京都"
                            },
                            {
                                "id": 5,
                                "name": "紅林弘太郎",
                                "position": "内野手",
                                "uniformNumber": "24",
                                "prefecture": "静岡県"
                            },
                            {
                                "id": 6,
                                "name": "T-岡田",
                                "position": "外野手",
                                "uniformNumber": "55",
                                "prefecture": "大阪府"
                            }
                        ]
                        """
                )).toString();
    }

    @Test
    @DataSet(value = "datasets/players.yml")
    @Transactional
    public void IDで指定した選手のデータが取得できること() throws Exception {
        mockMvc.perform(get("/players/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().json("{'id':1,'name':'山岡泰輔','position':'投手','uniformNumber':'19','prefecture':'広島県'}"));
    }

    @Test
    @DataSet(value = "datasets/players.yml")
    @Transactional
    public void IDで指定した選手のデータがないときにエラーが返ること() throws Exception {
        mockMvc.perform(get("/players/{id}", 10))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("player not found"));
    }

    @Test
    @DataSet(value = "datasets/players.yml")
    @Transactional
    @ExpectedDataSet(value = "datasets/expected-insertPlayer.yml", ignoreCols = "id")
    public void 選手の情報が追加できること() throws Exception {
        PlayerRequest playerRequest = new PlayerRequest("アンダーソン・エスピノーザ", "投手", "00", "ベネズエラ");
        
        ObjectMapper objectMapper = new ObjectMapper();
        String playerRequestJson = objectMapper.writeValueAsString(playerRequest);

        mockMvc.perform(post("/players")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(playerRequestJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("player created"));

        List<Player> players = playerMapper.findAll();
        assertThat(players).hasSize(7);
        Player insertedPlayer = players.get(players.size() - 1);

        mockMvc.perform(get("/players/{id}", insertedPlayer.getId()))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "name": "アンダーソン・エスピノーザ",
                            "position": "投手",
                            "uniformNumber": "00",
                            "prefecture": "ベネズエラ"
                        }
                        """));
    }

    @Test
    @DataSet(value = "datasets/players.yml")
    @Transactional
    @ExpectedDataSet("datasets/expected-updatePlayer.yml")
    void idで指定した選手の情報が新しい情報で更新できること() throws Exception {
        PlayerRequest updatedPlayerRequest = new PlayerRequest("田嶋大樹", "投手", "29", "栃木県");

        mockMvc.perform(patch("/players/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "name": "田嶋大樹",
                                    "position": "投手",
                                    "uniformNumber": "29",
                                    "prefecture": "栃木県"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("player updated"));

        mockMvc.perform(get("/players/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "id": 1,
                            "name": "田嶋大樹",
                            "position": "投手",
                            "uniformNumber": "29",
                            "prefecture": "栃木県"
                        }
                        """));
    }

    @Test
    @DataSet(value = "datasets/players.yml")
    @Transactional
    @ExpectedDataSet("datasets/expected-deletePlayer.yml")
    void idで指定した選手の情報が削除されること() throws Exception {
        mockMvc.perform(delete("/players/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("player deleted"));

        mockMvc.perform(get("/players/{id}", 1))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    @DataSet(value = "datasets/players.yml")
    void idで指定した選手が存在しない時にPlayerNotFoundが返されること() throws Exception {
        mockMvc.perform(delete("/players/{id}", 100))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Not Found"))
                .andExpect(jsonPath("$.message").value("Player not found"));
    }
}
