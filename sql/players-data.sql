DROP TABLE IF EXISTS players;
CREATE TABLE players
(
    id INTEGER unsigned AUTO_INCREMENT,
    last_name VARCHAR(20) NOT NULL,
    first_name VARCHAR(20) NOT NULL,
    position VARCHAR(15) NOT NULL,
    uniform_number VARCHAR(5) NOT NULL UNIQUE,
    prefecture VARCHAR(10) NOT NULL,
    PRIMARY KEY (id)
);
INSERT INTO players (last_name,first_name,position,uniform_number,prefecture)
VALUES ("山岡","泰輔","投手","19","広島県");
INSERT INTO players(last_name,first_name,position,uniform_number,prefecture)
VALUES ("宮城","大弥","投手","13","沖縄県");
INSERT INTO players(last_name,first_name,position,uniform_number,prefecture)
VALUES ("頓宮","裕馬","捕手","44","岡山県");
INSERT INTO players(last_name,first_name,position,uniform_number,prefecture)
VALUES("宗","佑馬","内野手","6","東京都");
INSERT INTO players(last_name,first_name,position,uniform_number,prefecture)
VALUES("紅林","弘太郎","内野手","24","静岡県");
INSERT INTO players(last_name,first_name,position,uniform_number,prefecture)
VALUES("岡田","貴弘","外野手","55","大阪府");
