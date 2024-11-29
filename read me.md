# 範例: 表單資料檢查 收集錯誤訊息後的處理辦法

## Spring ResourceBundleMessageSource
優勢:
* 可以將錯誤訊息集中管理
* 自由組合回復訊息
* 配合i18n切換顯示語系

配置:
A: AppConfig.java(必要)

B: message_source.xml (無spring 框架 能直接使用)

C方法三: application.properties ..... 失敗，無法正確讀取

