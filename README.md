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
![スクリーンショット (190)](https://github.com/user-attachments/assets/dc9ab453-8eca-482c-a0a6-76affa5a0217)

### カラムを空欄にして送信するとバリデーションエラーが返ることを確認。（今回はprefectureですが全て同様の処理を記述）
![スクリーンショット (195)](https://github.com/user-attachments/assets/78d27b05-1c8c-4e64-96d8-11c8e6a32c2e)

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
### idで指定した選手の情報が変更されていることを確認。
- ここでは「id:1 name:山岡泰輔 position:投手 uniform_number:13 prefecture:広島県」→「id:1 name:小木田敦也 position:投手 uniform_number:56 prefecture:秋田県」に変更。・
- 「200:OK player updated」が表示され、MySQLのデータも変更されている。
![スクリーンショット (191)](https://github.com/user-attachments/assets/ec5630e5-228c-479b-b5a2-10bd6ce71129)
![スクリーンショット (194)](https://github.com/user-attachments/assets/7f38b59e-097a-4c2e-a560-1635b6289ea9)

### 指定したidに情報がない時に「404:Not Found message:player not found」が返ることを確認。
![スクリーンショット (192)](https://github.com/user-attachments/assets/ac2a9948-f119-414f-a25a-4cced8a5e47b)

### すでに登録されているuniform_numberが指定されると「400:Bad Request message:その背番号はすでに使用されています」を返ることを確認。
![スクリーンショット (193)](https://github.com/user-attachments/assets/aac4f1e1-acdb-44da-8cd8-ee7f7e6a7e7f)

## 削除処理
### idで指定した選手の情報が削除されていることを確認。
- 事前チェック。「id:7 name:杉本裕太郎 position:外野手 uniform_number:99 prefecture:徳島県」が登録されている。
![スクリーンショット (196)](https://github.com/user-attachments/assets/24a77932-538e-4c76-a699-8df4cfc273e5)

- idで指定してリクエスト送信後、「player deleted」の表示が出ることを確認。
![スクリーンショット (197)](https://github.com/user-attachments/assets/dd5f5a21-66bf-45b7-a5c1-a761b2480015)

- MySQLのデータベースを確認、「id:7」に登録されていた情報が削除され、6件になっていることを確認。
![スクリーンショット (198)](https://github.com/user-attachments/assets/8dfcc26b-99bd-4851-afbf-3193725c3195)








