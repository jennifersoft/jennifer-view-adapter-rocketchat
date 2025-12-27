# 概要
このアダプタは、イベント通知を Rocket.Chatのチャンネルまたは Rocket.Chat ユーザーに送信します。

## はじめに

### 1- アダプタのインストール
最初のステップはアダプタを登録することです: 
1. JENNIFER ダッシュボードで、**設定** > **SMTP及びアダプタ(+DB Plan)** に移動します。
2. 画面右上の**[+追加]** ボタンをクリックします。
3. **アダプタ** をクリックして、登録に必要な情報を入力します。
    * 区分には **EVENT** を選択します。
    * IDには **rocketchat** を入力します。
    * [ファイルの選択]をクリックして、ダウンロードしたファイル(rocketchat-adapter-x.x.x.jar)を選択します。
    * クラスには **com.aries.rocketchat.RocketChatAdapter** を入力します。
4. **[追加]** ボタンをクリックしてアダプタを追加します。



### 2- アダプタオプション

次のステップはアダプタのオプション設定です。必須オプションは **rocketchat_webhook** の 1 つだけです。その他のオプションは任意です。このアダプタで利用可能なオプションの一覧は以下の表を参照してください。


このアダプタで利用可能なオプションは次のとおりです。

| Key                | Required |                      Description                       |  Default Value | 
| ------------------ |:--------:|:------------------------------------------------------:|:--------------:|
| rocketchat_webhook |   YES    |  Rocket.Chat の Incoming Webhook URL を設定します。[,]を区切りにして複数のWebhook URLを入力できます。                 | None           | 
| username           |    NO    |  Rocket.Chat メッセージ受信時の「From」ユーザー名を変更します。  | JENNIFER Notification | 
| share_url          |    NO    | X-View ポップアッププラグイン用の JENNIFER Share URL を設定します。URL が設定されている場合、このアダプタは X-View でトランザクションを表示するためのリンク生成を試み、そのリンクを Rocket.Chat メッセージ内に表示します。    | None                  | 
| button_text        |    NO    | share_url オプションが有効な場合にのみ使用されます。Rocket.Chat のインタラクティブボタンのテキストをカスタマイズします。 | View On X-View |

**重要: `share_url`機能を使用するには、[XView Pop-up Plugin](https://github.com/jennifersoft/jennifer-view-plugin-xviewpopup) を設定し、有効化しておく必要があります。**



