# PizzaTimeBungee

LunaChatをBungeeCord側に入れた時にプレイヤーのいるサーバーによって自動的に
channelを割り振ることができるプラグイン

## Requirement

* LunaChat
* BungeeCord

## How to use

* pluginsフォルダにPizzaTimeBungee.jarを入れる
* 起動する
* PizzaTimeBungeeとその中にconfig.ymlが生成されているので任意で書き換える
```yaml
servers:
  lobby: #BungeeCordで設定しているサーバー名
    channel: ChatChannelName #LunaChatで使用するチャンネル名
  other:
    channel: OtherChatChannelName
```
* /pt reloadで設定を再ロードする
※初回起動時にデフォルト設定のチャンネルが作成されるので、それを防ぎたい場合は
　先にファイルを作成して書き換えてください。

## License

https://github.com/Be4rJP/PizzaTimeBungee/blob/master/LICENSE

## Download jar file

https://github.com/Be4rJP/PizzaTimeBungee/releases/tag/1.0.0
