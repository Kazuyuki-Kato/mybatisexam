package integrationtest;

import com.eight.mybatistest.MybatisTestApplication;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest(classes = MybatisTestApplication.class)
@AutoConfigureMockMvc
@DBRider
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRestApiIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    @DataSet(value = "datasets/players.yml")
    @Transactional
    void 全てのプレイヤーの情報が取得できること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/players"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
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
}
