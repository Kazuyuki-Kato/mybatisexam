DROP TABLE IF EXISTS players;
CREATE TABLE players
(
    id int unsigned AUTO_INCREMENT, name VARCHAR(20) NOT NULL, number int(5) NOT NULL, prefecture VARCHAR(10) NOT NULL,
    PRIMARY KEY (id)
);
INSERT INTO players (name,number,prefecture)
VALUES ('山岡泰輔',19,'広島県');
INSERT INTO players (name,number,prefecture)
VALUES ('宮城大弥',13,'沖縄県');