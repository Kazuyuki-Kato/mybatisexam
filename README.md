# 内容
第8回の課題「MyBatisを使用してテーブルからレコードを取得するAPIの実装、並びにクエリ文字列を指定して検索するAPIの実装」を行います。

## 2024/02/08:テーブルからレコードを全件取得するAPIの実装（mainブランチ）
#### 橙:player_list_databaseでのURLの指定
#### 青:HTTPステータスコード:200（成功）であること、JSON形式でid.name.number.prefectureの要素を持ったレコードを2件取得できていることを確認。
![スクリーンショット (68)](https://github.com/Kazuyuki-Kato/mybatisexam/assets/154575590/771c97ee-3dbf-4c04-994f-45e78d6cf224)
INSERTしたコードは下記の通りです。
```sh
(
    id int unsigned AUTO_INCREMENT, name VARCHAR(20) NOT NULL, number int(5) NOT NULL, prefecture VARCHAR(10) NOT NULL,
    PRIMARY KEY (id)
);
INSERT INTO players (name,number,prefecture)
VALUES ('山岡泰輔',19,'広島県');
INSERT INTO players (name,number,prefecture)
VALUES ('宮城大弥',13,'沖縄県');
```


