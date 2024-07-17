# 内容
最終課題「CRUD処理を備えたREST APIの作成」

# API概要
オリックスバファローズの選手データを使用し、idをキーとして登録、読み取り、更新、削除を行います。
カラムは「name」「position」「uniform_number」「prefecture」で、idは自動製番にしております。

# 前提となるデータの登録
- MySQLを使用。
- テーブル名はplayers-data.sql
- 前述の通りidはauto inclementによる自動製番。Nullは許容せず、uniform_numberは重複を許さないためUNIQUE属性を付与。
![スクリーンショット (182)](https://github.com/user-attachments/assets/8ac695e4-5268-4ce2-9d21-2c18f948f341)

- データが登録されていることを確認。
![スクリーンショット (183)](https://github.com/user-attachments/assets/ff021f13-5cee-47d3-a83f-276d35750082)


# CRUD処理の動作確認
## 登録処理
- 動作確認にはPostmanを使用しています。
### playersにデータ入力した際にリクエストボディ通りにデータが登録されていることを確認。
![スクリーンショット (188)](https://github.com/user-attachments/assets/2c640a54-d40d-4043-9d26-725b141d618a)
![スクリーンショット (190)](https://github.com/user-attachments/assets/cc065182-6da2-4361-aae3-3a9195d3162b)

### idで指定をする（更新作業をする）とエラーが返ることを確認。
![スクリーンショット (189)](https://github.com/user-attachments/assets/014d0abf-e5bd-4f6c-bcf7-e5016f8d4d2f)


## 読み取り処理
### 全件取得（/players）で6件のデータがあることを確認。
![スクリーンショット (184)](https://github.com/user-attachments/assets/1190d862-8082-4c1c-992d-992bc767f499)

![スクリーンショット (185)](https://github.com/user-attachments/assets/845a7f8b-441a-4e12-8ce9-eec7acd702bc)

### idで指定したデータが取得できることを確認。（画像では1番の情報を取得）
![スクリーンショット (186)](https://github.com/user-attachments/assets/815c56ae-52f7-4045-9a18-46fb083d7d14)


### idで指定した番号にデータが存在しない時「player not found」が返ることを確認。
![スクリーンショット (187)](https://github.com/user-attachments/assets/113a746b-ea60-44e4-a82c-59eeff6e11a7)

## 更新処理
