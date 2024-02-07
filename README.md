# 内容
第8回の課題「MyBatisを使用してテーブルからレコードを取得するAPIの実装、並びにクエリ文字列を指定して検索するAPIの実装」を行います。

# 現状（2024/2/7にご質問の内容）
### HTTPステータス:500サーバーエラー（原因としてConnetionException）の解決
- <2024/2/7　13：06追記>
エラー箇所で下記のコンソールログが発生いたしました。
少し長いので、画像でも共有いたします。
# コードを転載している箇所
![スクリーンショット (61)](https://github.com/Kazuyuki-Kato/mybatisexam/assets/154575590/f56ff078-93e8-458c-ab57-8bd2d82ca5df)
# 上記のスクリーンショット上記に青文字で写っている「PlayerController.java:18」のINTERNAL LINEです。念のため共有いたします。
![スクリーンショット (62)](https://github.com/Kazuyuki-Kato/mybatisexam/assets/154575590/da938f7c-d587-4828-a5cf-c066b8759d29)

``` sh
Caused by: java.net.ConnectException: Connection refused: no further information
at java.base/sun.nio.ch.Net.pollConnect(Native Method) ~[na:na]
at java.base/sun.nio.ch.Net.pollConnectNow(Net.java:672) ~[na:na]
at java.base/sun.nio.ch.NioSocketImpl.timedFinishConnect(NioSocketImpl.java:547) ~[na:na]
at java.base/sun.nio.ch.NioSocketImpl.connect(NioSocketImpl.java:602) ~[na:na]
at java.base/java.net.SocksSocketImpl.connect(SocksSocketImpl.java:327) ~[na:na]
at java.base/java.net.Socket.connect(Socket.java:633) ~[na:na]
at com.mysql.cj.protocol.StandardSocketFactory.connect(StandardSocketFactory.java:153) ~[mysql-connector-j-8.3.0.jar:8.3.0]
at com.mysql.cj.protocol.a.NativeSocketConnection.connect(NativeSocketConnection.java:62) ~[mysql-connector-j-8.3.0.jar:8.3.0]
```

→下記は実施済み。
- MySQLサーバーが動作しているか<br>
→SQLコンテナを開いた状態でsql>statusコマンドで実行ができていることも確認。<br>
 また、dockerからMySQLに接続できてTableやカラムも作成できていることを確認できているため、SQLサーバーは動作していると思われます。<br>
### ↓テーブルが変更され、内容も変更できているのを確認。
![スクリーンショット (57)](https://github.com/Kazuyuki-Kato/mybatisexam/assets/154575590/4b82e1bb-fef3-4838-a9c2-7cadd7151cb7)

- バックエンドサーバーにアクセスできない<br>
  https://www.ibm.com/docs/ja/product-master/12.0.0?topic=issues-javanetconnectexception-connection-refused-error <br>
→ulimit- uコマンドでサーバーで許可されるプロセスの上限の確認。<br>
256と小さいと思われる数値だったので131072とulimit -u unlimitedで変更しようとしたものの変更できませんとの表示がありました。
![スクリーンショット (56)](https://github.com/Kazuyuki-Kato/mybatisexam/assets/154575590/40b5f8fd-c321-43eb-a852-d5a05d790cf5)

- ファイアウォールによるブロック<br>
→PCのファイアウォールを一度無効にしてPostmanをたたきましたが、動作に変わりはありませんでした。
