# 内容
第8回の課題「MyBatisを使用してテーブルからレコードを取得するAPIの実装、並びにクエリ文字列を指定して検索するAPIの実装」を行います。

# 現状（2024/2/7にご質問の内容）
### HTTPステータス:500サーバーエラー（原因としてConnetionException）の解決
→下記は実施済み。
- MySQLサーバーが動作しているか
→SQLコンテナを開いた状態でsql>statusコマンドで実行ができていることも確認。
 また、dockerからMySQLに接続できてTableやカラムも作成できていることを確認できているため、SQLサーバーは動作していると思われます。
### ↓テーブルが変更され、内容も変更できているのを確認。
![スクリーンショット (57)](https://github.com/Kazuyuki-Kato/mybatisexam/assets/154575590/4b82e1bb-fef3-4838-a9c2-7cadd7151cb7)

- バックエンドサーバーにアクセスできない
  https://www.ibm.com/docs/ja/product-master/12.0.0?topic=issues-javanetconnectexception-connection-refused-error
→ulimit- uコマンドでサーバーで許可されるプロセスの上限の確認。256と小さいと思われる数値だったので131072とulimit -u unlimitedで変更しようとしたものの変更できませんとの表示がありました。
![スクリーンショット (56)](https://github.com/Kazuyuki-Kato/mybatisexam/assets/154575590/40b5f8fd-c321-43eb-a852-d5a05d790cf5)

- ファイアウォールによるブロック
→PCのファイアウォールを一度無効にしてPostmanをたたきましたが、動作に変わりはありませんでした。
