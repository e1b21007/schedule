<h1 id=i4>4.セットアップマニュアル</h1>

<h4 id=i4-1> インストール時 </h4>

サーバーで以下を実行する。<br>
```
$ git clone https://github.com/e1b21007/schedule.git
$ cd schedule/
$ bash ./gradlew
$ bash ./gradlew bootrun
```
<h4 id=i4-2> アンインストール時</h4>

サーバーで実行中の ```$ bash ./gradlew bootrun```を```Ctrl + c``` で中断する。<br>
**この時点で、あらゆるデータが削除されるので注意すること。**<br>
その後、scheduleファイルを通常の手段で削除する。
```rm -rf /path/to/schedule```

<a href=./../README.md>目次に戻る</a>
