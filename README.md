# PizzaTimeBungee

LunaChatをBungeeCord側に入れた時にプレイヤーのいるサーバーによって自動的にchannelを割り振ることができるBungeeCord / WaterFall専用プラグイン

## Requirement

* LunaChat
* BungeeCord

## How to use

* BungeeCordのpluginsフォルダにPizzaTimeBungee.jarを入れる
* BungeeCordを起動する
* PizzaTimeBungeeフォルダとその中にconfig.ymlが生成されているので任意で書き換える
```yaml
servers:
  lobby: #BungeeCordで設定しているサーバー名
    channel: 'ChatChannelName' #LunaChatで使用するチャンネル名
  other:
    channel: 'OtherChatChannelName'

#上記以外のサーバーであるときに参加させるチャンネル
#nullを指定するとLunaChatで指定されているグローバルチャンネルに参加させます
default-channel: null

#チャンネルを更新するときにメッセージフォーマットも更新するかどうかの設定
#true推奨
format-update: true
```
* /pt reloadで設定を再ロードする

※初回起動時にデフォルト設定のチャンネルが作成されるので、それを防ぎたい場合は先にファイルを作成して書き換えてください。

## Command
```
/pt reload
```
設定ファイルをリロードします

## Permission
```
pt.admin
```
/pt コマンドを使うために必要

## License

https://github.com/Be4rJP/PizzaTimeBungee/blob/master/LICENSE

## Download

https://github.com/Be4rJP/PizzaTimeBungee/releases
