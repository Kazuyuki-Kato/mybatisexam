# 内容
最終課題「CRUD処理を備えたREST APIの作成」

# API概要
オリックスバファローズの選手データを使用し、idをキーとして登録、読み取り、更新、削除を行います。
カラムは「name」「position」「uniform_number」「prefecture」で、idは自動製番にしております。

# 階層構造
``` sh
├─Dockerfile
  ├─HELP.md
  ├─README.md
  ├─build
  ├─build.gradle
  │  ├─classes
  │  │  ├─java
  │  │  │  ├─main
  │  │  │  │  ├─com
  │  │  │  │  │  ├─eight
  │  │  │  │  │  │  ├─mybatistest
  │  │  │  │  │  │  │  ├─ExceptionControllerAdvice$ErrorResponse.class
  │  │  │  │  │  │  │  ├─ExceptionControllerAdvice.class
  │  │  │  │  │  │  │  ├─MybatisTestApplication.class
  │  │  │  │  │  │  │  ├─Player.class
  │  │  │  │  │  │  │  ├─PlayerController.class
  │  │  │  │  │  │  │  ├─PlayerMapper.class
  │  │  │  │  │  │  │  ├─PlayerNotFoundException.class
  │  │  │  │  │  │  │  ├─PlayerRequest.class
  │  │  │  │  │  │  │  ├─PlayerResponse.class
  │  │  │  │  │  │  │  ├─PlayerService.class
  │  │  │  ├─test
  │  │  │  │  ├─com
  │  │  │  │  │  ├─eight
  │  │  │  │  │  │  ├─mybatistest
  │  │  │  │  │  │  │  ├─MybatisTestApplicationTests.class
  │  │  │  │  │  │  │  ├─PlayerMapperTest.class
  │  │  │  │  │  │  │  ├─PlayerServiceTest.class
  │  │  │  │  ├─integrationtest
  │  │  │  │  │  ├─UserRestApiIntegrationTest.class
  │  ├─generated
  │  │  ├─sources
  │  │  │  ├─annotationProcessor
  │  │  │  │  ├─java
  │  │  │  │  │  ├─main
  │  │  │  │  │  ├─test
  │  │  │  ├─headers
  │  │  │  │  ├─java
  │  │  │  │  │  ├─main
  │  │  │  │  │  ├─test
  │  ├─reports
  │  │  ├─tests
  │  │  │  ├─test
  │  │  │  │  ├─classes
  │  │  │  │  │  ├─integrationtest.UserRestApiIntegrationTest.html
  │  │  │  │  ├─css
  │  │  │  │  │  ├─base-style.css
  │  │  │  │  │  ├─style.css
  │  │  │  │  ├─index.html
  │  │  │  │  ├─js
  │  │  │  │  │  ├─report.js
  │  │  │  │  ├─packages
  │  │  │  │  │  ├─integrationtest.html
  │  ├─resources
  │  │  ├─main
  │  │  │  ├─application.properties
  │  │  │  ├─static
  │  │  │  ├─templates
  │  │  ├─test
  │  │  │  ├─datasets
  │  │  │  │  ├─expected-deletePlayer.yml
  │  │  │  │  ├─expected-insertPlayer.yml
  │  │  │  │  ├─expected-updatePlayer.yml
  │  │  │  │  ├─players.yml
  │  │  │  ├─dbunit.yml
  │  ├─test-results
  │  │  ├─test
  │  │  │  ├─TEST-integrationtest.UserRestApiIntegrationTest.xml
  │  │  │  ├─binary
  │  │  │  │  ├─output.bin
  │  │  │  │  ├─output.bin.idx
  │  │  │  │  ├─results.bin
  │  ├─tmp
  │  │  ├─compileJava
  │  │  │  ├─compileTransaction
  │  │  │  │  ├─backup-dir
  │  │  │  │  ├─stash-dir
  │  │  │  │  │  ├─ExceptionControllerAdvice$ErrorResponse.class.uniqueId1
  │  │  │  │  │  ├─ExceptionControllerAdvice.class.uniqueId0
  │  │  │  ├─previous-compilation-data.bin
  │  │  ├─compileTestJava
  │  │  │  ├─compileTransaction
  │  │  │  │  ├─backup-dir
  │  │  │  │  ├─stash-dir
  │  │  │  │  │  ├─UserRestApiIntegrationTest.class.uniqueId0
  │  │  │  ├─previous-compilation-data.bin
  │  │  ├─test
  ├─conf
  │  ├─mysql
  │  │  ├─my.cnf
  ├─docker-compose.yml
  ├─gradle
  │  ├─wrapper
  │  │  ├─gradle-wrapper.jar
  │  │  ├─gradle-wrapper.properties
  ├─gradlew
  ├─gradlew.bat
  ├─settings.gradle
  ├─sql
  │  ├─players-data.sql
  ├─src
  │  ├─main
  │  │  ├─java
  │  │  │  ├─com
  │  │  │  │  ├─eight
  │  │  │  │  │  ├─mybatistest
  │  │  │  │  │  │  ├─ExceptionControllerAdvice.java
  │  │  │  │  │  │  ├─MybatisTestApplication.java
  │  │  │  │  │  │  ├─Player.java
  │  │  │  │  │  │  ├─PlayerController.java
  │  │  │  │  │  │  ├─PlayerMapper.java
  │  │  │  │  │  │  ├─PlayerNotFoundException.java
  │  │  │  │  │  │  ├─PlayerRequest.java
  │  │  │  │  │  │  ├─PlayerResponse.java
  │  │  │  │  │  │  ├─PlayerService.java
  │  │  ├─resources
  │  │  │  ├─application.properties
  │  │  │  ├─static
  │  │  │  ├─templates
  │  ├─test
  │  │  ├─java
  │  │  │  ├─com
  │  │  │  │  ├─eight
  │  │  │  │  │  ├─mybatistest
  │  │  │  │  │  │  ├─MybatisTestApplicationTests.java
  │  │  │  │  │  │  ├─PlayerMapperTest.java
  │  │  │  │  │  │  ├─PlayerServiceTest.java
  │  │  │  ├─integrationtest
  │  │  │  │  ├─UserRestApiIntegrationTest.java
  │  │  ├─resources
  │  │  │  ├─datasets
  │  │  │  │  ├─expected-deletePlayer.yml
  │  │  │  │  ├─expected-insertPlayer.yml
  │  │  │  │  ├─expected-updatePlayer.yml
  │  │  │  │  ├─players.yml
  │  │  │  ├─dbunit.yml
```

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


### テストについて
- JUnit5、DBRiderを使用。
- 単体テスト、データベーステスト、結合テストを実施。
- GithubActionsを利用したCIの実装。
### CIの実装
- run-test.ymlに必要なコードを記載。
``` sh
name: Test with Gradle, Docker Compose, and MySQL

on:
  push:
    branches: [ "main" ]
  pull_request:
    branches: [ "main" ]

jobs:
  test:

    runs-on: ubuntu-latest
    permissions:
      contents: read

    steps:
      - uses: actions/checkout@v4

      - name: Set up JDK 17
        uses: actions/setup-java@v4
        with:
          java-version: '17'
          distribution: 'temurin'

      - name: Docker run
        run: docker compose up -d

      - name: Setup Gradle
        uses: gradle/actions/setup-gradle@417ae3ccd767c252f5661f1ace9f835f9654f2b5 # v3.1.0
        with:
          gradle-version: '8.5'

      - name: Build with Gradle 8.5
        run: gradle build

      - name: Publish Test Report
        uses: mikepenz/action-junit-report@v3
        if: always()
        with:
          report_paths: '**/build/test-results/test/TEST-*.xml'
          job_name: 'Test Report'
```

## 単体テスト
### Mapperテスト
- DBRiderを使用し、players.ymlにMySQLに登録した内容と同じもの記述。
- 結果として全件成功していることを確認。
![スクリーンショット (199)](https://github.com/user-attachments/assets/9cd3dccd-737e-4db8-acbe-923e3638dee6)

### 単体テスト
- Mockitoを使用してplayerMapperをモック化。
- 結果として全件成功していることを確認。
![スクリーンショット (200)](https://github.com/user-attachments/assets/dab805bc-3291-4dd5-bef8-3979f67dc48a)

### 結合テスト
- @SpringBootTestを使用。
- insert,update,deleteにはあらかじめそれぞれに対応するymlファイルを記述、datasets配下に設置。
- 全件成功していることを確認。
![スクリーンショット (201)](https://github.com/user-attachments/assets/01448ba3-8074-4895-992f-96c53834bf3a)










