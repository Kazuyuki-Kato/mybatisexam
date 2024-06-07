# 内容
最終課題「CRUD処理を備えたREST APIの作成」としてオリックスバファローズの選手名鑑をもとに、選手情報の登録・取得・更新・削除ができるアプリケーションを作成しております。

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
- cURLでの確認
  赤線部⇒HTTPステータスコード:200、Content-Type:Application/JSONを確認。
  ![スクリーンショット (69)](https://github.com/Kazuyuki-Kato/mybatisexam/assets/154575590/ad7cfb61-c90b-4857-9443-acf7fdded4b9)


